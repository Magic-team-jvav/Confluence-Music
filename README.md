# ConfluenceMusic (Forge 1.20.1)

汇流来世（Confluence: Otherworld）的音乐模组，由 `D:\Minecraft\1.21neoforge\ConfluenceMusic` 的
1.21.1 / NeoForge 版移植而来。

## 前置

| 前置 | 版本 | 说明 |
|---|---|---|
| ConfluenceOtherworld (forge) | 1.2.7+1.20.1 | `confluence`，本体 |
| Confluence-Magic-Lib | 1.3.0 | `confluence_magic_lib`，提供 `LibDateUtils`、`Boss`、datagen 基类等 |
| PortLib | 1.2.6 | `portlib`，把 NeoForge 风格 API 搬到 Forge 1.20.1（选曲事件、网络、自定义注册表、配置界面等） |
| TerraCurio (forge) | 1.3.1.5+1.20.1 | 本体运行期依赖 |

前四种以**已构建好的 jar** 放在 `libs/`（它们是生产用的 SRG 名）。

关键点：这类 jar 必须走 **mod 配置**（`modCompileOnly` / `modRuntimeOnly` / `modImplementation`），
MDG 才会按“模组依赖”做完整重映射 —— 包括把 mixin 类里被重混淆成 SRG 的 `@Shadow` 成员名换回命名映射；
如果只用 `files()` / `localRuntime`，成员名不会转换，开发环境启动时会崩在
`@Shadow method m_7804_ ... was not located in the target class`。

但 mod 配置只接受模块坐标、不认 `files(...)`，所以 `build.gradle` 里做了两步：

1. `prereqRepo` 任务把 `libs/` 里的 jar 连同自动生成的 POM 安装成本地 maven 仓库 `local-repo/`
   （`compileJava`、各 `run*` 任务、IDE 同步之前都会自动跑，改了 `libs/` 里的 jar 也会自动重装）；
2. 再按坐标依赖：
   ```groovy
   modCompileOnly 'org.confluence.mod:ConfluenceOtherworld-forge-1.20.1:1.2.7+1.20.1'
   modRuntimeOnly 'org.mesdag:PortLib:1.2.6'
   ```

其余运行期依赖（GeckoLib、Curios、ParticleStorm、OpalLight、ThreeDimensionParticle、TheTrackers）
只在开发环境需要，按坐标声明为 `modRuntimeOnly`。

> 换前置版本：把新 jar 放进 `libs/`，改 `build.gradle` 里 `prereqs` 的文件名与版本，重新构建即可
> （`local-repo/` 已加入 `.gitignore`；IntelliJ 里若报找不到坐标，先手动跑一次 `gradlew prereqRepo`）。

## 构建 / 运行

```bash
./gradlew build        # 产出 build/libs/confluence_music-<version>+1.20.1-<date>.jar
./gradlew runData      # 数据生成（已跑通，music_selection 产物与 1.21 版逐字节一致）
./gradlew runClient    # 开发客户端（已跑通：进世界 2.5 分钟，选曲每刻执行且无异常）
```

前置模组需要联网从 maven 解析（GeckoLib / Curios / ParticleStorm / OpalLight / ThreeDimensionParticle /
TheTrackers）；`libs/` 里的四个前置 jar 是本地文件，离线也能编译。

## 代码结构

选曲逻辑分四层，与 1.21 版一致：

| 类 | 职责 |
|---|---|
| `client.MusicEnvironment` | 看世界：维度、群系、高度、时间、天气、游戏事件、Boss、城镇 NPC（实体扫描按 20 刻节流） |
| `client.MusicResolver` | 选音乐种类：Boss → 地牢 → 创造 → 维度 →（主世界内）太空 → 群系 → 事件/天气 → 城镇 → 昼夜/地下 |
| `client.MusicRepository` | 种类换曲目：按配置的音乐类型从该种类的曲目里**随机抽一首**（抽中即记住），缺配置回退到 `confluence` |
| `client.MusicPlayer` | 换曲过程：种类一变就交叉淡化（旧的继续放并降音量、新的同刻开始从 0 起涨），间隔计时 |
| `client.MusicHandler` | 接线：接 PortLib 的 `PortSelectMusicEvent`，刷新环境并驱动状态机 |

### 换曲的时序（交叉淡化）

```
处境变化 → 种类（MusicSelection）连续保持 10 刻不变才确认换曲
        → 同一个刻：
             旧的声道移进“淡出列表”，继续出声，每刻 -1/lastSongFadeOutTime
             MusicManager.currentMusic 置空（只让原版松手，不叫它停声）
             event.setMusic(新曲目) → 原版同刻 startPlaying，新曲从音量 0 起每刻 +1/lastSongFadeOutTime
        → 同一个刻结束前（ClientTickEvent.End）：把新曲的声道补压到 0，
          抵掉原版建立声道时写下的那个“原始音量”初值
        → 旧的降到 0 的那一刻正是新的涨到 1（默认各 50 刻 = 2.5 秒），旧的那一首随即 stop、放掉声道
```

- 新种类要**连续保持 10 刻**才算数：群系边界、太空层高度线只抖一两个刻时不换曲。
- 同一时刻只允许有一轮淡化在走（`fadingOut` 非空就不再开新的），所以处境换成第三首
  （白天↔夜晚、进地牢……）也能开始新一轮，不会被上一轮挡住。
- **回来后怎么放，取决于旧的那一首有没有淡完**：
  - 还没淡完（音量 > 0、声道还活着）就被要回来：走 `resume`——把 `currentMusic` 与事件曲目都换回它，
    两处 location 相同，原版既不会 `stop` 它也不会重新 `startPlaying`，于是**从当前位置接着放**；
    刚换上的那一首则转成淡出。群系边界那种“抖一下就回来”属于这一类。
  - 已经淡完（音量到 0、声道已放掉）：`fadingOut` 里已经没有它，于是走 `crossfade` → 重新 `commit`
    → 原版重新 `startPlaying`，也就是**从头开始放**。稳定待了一阵再回来属于这一类。
- 新处境配的还是同一首（血月与陨石都用 `eerie` 这类）时只更新记录，不做无谓的淡化。
- 处境变了但没配曲目（或别的模组的音乐正在放而不该打断）时，就是单纯的淡出／不打断，等它结束再由“没有音乐在播放”分支接手。

### 曲目是怎么抽的

- **选好种类之后从该种类的曲目里随机抽一首**（`MusicRepository#pick(RandomSource, MusicSelection)`）。
- 抽的时机只有两处：**处境变化时抽一次**、**一首放完之后再开一首时再抽一次**。
  抽中的那一首由 `MusicPlayer#picked` 记着，不会每刻重抽——这样“想放的是不是正在放的这一首”这个判断
  在整首歌里都是稳定的（否则每刻重抽会让状态机反复横跳）。
- 从旧处境 `resume` 回来时，记着的就是那一首本身（不会另抽一首把它顶掉）。

### 为什么要在本刻末尾补一次静音

原版 `MusicManager.tick()` 是"先把事件抛给模组、本刻稍后才 `startPlaying`"，而它建立声道时直接按**原始音量**写初值。
如果等下一刻再开始渐入，新曲就会以满音量响一帧（交叉淡化时还会和还没降下来的旧曲叠成一下明显变响）。
所以 `MusicHandler` 订阅 `ClientTickEvent.End`（该事件在 `Minecraft.tick()` 里确实排在 `MusicManager.tick()` 之后），
在那里把声道压到 0。声道写入走 `SoundEngineExecutor` 这个单线程 FIFO 队列，
排得上原版那次初值写入之后，因此一定是最后生效的。

## 与 1.21 版的主要差异

* **选曲钩子**：Forge 1.20.1 没有 `SelectMusicEvent`，改用 PortLib 的 `PortSelectMusicEvent`
  （PortLib 用 Mixin 注入 `MusicManager.tick()`）。为了让 `setMusic(null)` 的语义与 NeoForge 一致
  （停止当前音乐并跳过本刻剩余逻辑，而不是空指针崩溃），**PortLib 的 `MusicManagerMixin` 已修改**，
  需要用修改后的 PortLib（`libs/` 里的 1.2.6 已包含该修复）。
* 额外接入了 1.20.1 才有的游戏事件：`ECLIPSE`（日食）、`PIRATE_INVASION`（海盗入侵），
  以及沙漠分支里的 `SANDSTORM`（沙尘暴覆盖沙漠音乐，与雪原的暴风雪同理）。
* **换曲改成真交叉淡化**（1.21 版是"先淡出到静音、再换曲"）：见上文时序；
  为此需要 `MusicManager.currentMusic` 的访问转换器，以及本刻末尾补一次静音。
* 访问转换器使用 SRG 名：`SoundManager.f_120349_`（`soundEngine`）、`SoundEngine.f_120226_`（`instanceToChannel`）、
  `MusicManager.f_120179_`（`currentMusic`，交叉淡化要在换曲时先把原版对旧声道的所有权拿掉，否则它会直接 `stop` 掉旧声道）。
* `LibUtils` 在 1.20.1 没有 `getOrCreatePersistedData`，改用 `player.getPersistentData()`。
* `LevelRenderer.getLastViewDistance()` 在 1.20.1 返回区块数，Boss 扫描范围乘 16。
* 城镇判定用 `BaseNPC`（1.20.1 的汇流 NPC 基类）。

## 验证情况

* `./gradlew build`：通过，产出可用的模组 jar。
* `./gradlew runData`：通过；`music_selection/confluence.json` 与 `otherworldly.json` 与 1.21 版**逐字节一致**，
  语言文件因注释文案调整而不同。
* `./gradlew runClient`：通过。日志可见 `Mixing MusicManagerMixin ... into net.minecraft.client.sounds.MusicManager`
  与 `Dev joined the game`，随后在游戏中运行约 2.5 分钟，`MusicHandler.selectMusic` 每刻执行且无任何异常，
  OpenAL 正常初始化；除 `MUSIC_LIST.md` 记录的缺音频文件告警外没有其他报错。
* 换曲交叉淡化（`runClient` + `-PquickPlay="New World"`，日志见 `run/logs/debug.log`）：
  ```
  [MusicPlayer] fading out confluence_music:overworld_day (OVERWORLD_DAY -> WINDY_DAY)  ┐ 同一毫秒：旧的开始降
  [MusicPlayer] commit confluence_music:windy_day for WINDY_DAY                        ┘ 新的开始播
  [MusicPlayer] start at volume 0: confluence_music:windy_day   ← 1 毫秒后（本刻末尾）：新声道被压到 0
  [MusicPlayer] faded out confluence_music:overworld_day                              ┐ 同起同落：旧的归零
  [MusicPlayer] fade in finished: confluence_music:windy_day                          ┘ 新的涨满
  ```
  即两侧各走 `lastSongFadeOutTime`（默认 50 刻）且同起同落，全程两首同时出声；
  `start at volume 0` 落在 `commit` 之后 1~2 毫秒（同一客户端刻），满音量那一帧被抵掉。
  另外验证过：新种类连续保持不满 10 刻时不换曲、一轮淡化结束后才允许下一轮。
* **群系边缘震荡不重启曲目**（同一次 `runClient`，玩家正好站在丛林/平原边界，75 秒内处境来回变了 11 次）：
  统计 `commit` 2 次、`resume` 11 次、`faded out` 10 次、本模组异常帧 0 ——
  第一次换曲之后，两首曲子只是轮流淡入/淡出并互相交换所有权，**没有任何一次 `startPlaying`，播放位置不会重置**。
* **回来后"接着放 / 从头放"两种语义**（临时用"3.5 秒真实处境 + 1.2 秒 SNOW"的不对称相位触发，8 秒预热让当前曲目先淡满）：
  ```
  crossfade alt_overworld_night -> snow      ← 换曲，旧的开始淡出
  resume    alt_overworld_night vol=0.52     ← 1.2 秒后回来：音量还有 0.52（没淡完）→ 接着放
  faded out snow                             ← 另一首已淡完 → 放掉
  crossfade alt_overworld_night -> snow      ← 再回到 snow：它已淡完 → 重新 startPlaying，从头放
  resume    alt_overworld_night vol=0.52
  ```
  20 轮 `resume` 全部带 `vol≈0.52`（都是"没淡完"），与"已淡完就重新 `commit`"交替出现，符合预期。
  注：周期性相位下"回来"必然晚于淡化结束，所以这种"没淡完就回来"只会在处境变化不规则时出现。
* **第三首还能不能开始（回归测试）**：临时让处境在“真实处境 / SNOW / JUNGLE_DAY”之间每 6 秒轮换，
  统计 `commit` 8 次、`resume` 18 次：既有新曲目不断开始，也有反复交接，
  证明上一轮的旧声道不会把后续的新曲目挡住（白天↔夜晚、进地牢都走这条路径）。
* **地牢链路**：临时让服务端每 60 秒交替推送 `DUNGEON_FLOOR_1` / `NOT_FOUND`，实测客户端会立刻切到
  `confluence_music:dungeon_floor_1`，并在 `NOT_FOUND` 时接回原来的曲目、再次进入时由 `resume` 接着放。
  即 `StructureFoundPacketS2C` 传输、客户端 `dungeonMusic`、解析优先级都正常；
  服务端“是否判定在地牢里”用的是汇流自己的 `DungeonStructure.iterateDungeon` +
  `confluence$cachedBoundingBox`（与汇流的 `checkSkeletronDefeated`/`skipSpawn` 用法逐行一致，已反编译核对）。
  注意：这个开发客户端**失焦时会整体暂停**（世界与音乐状态机都不跑刻），自动化冒烟测试需要让窗口保持焦点。

