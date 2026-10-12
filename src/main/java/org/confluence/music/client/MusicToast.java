package org.confluence.music.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import org.confluence.music.ConfluenceMusic;
import org.jetbrains.annotations.Nullable;

/// “正在播放”提示条：用 `music_selection` 贴图当底，棕色字写当前曲名，从屏幕左侧外滑进来，
/// 停几秒再滑回去。
///
/// 什么时候弹：一首曲子**实实在在地响起来**的时候——也就是它从音量 0 渐入到原始音量的那一刻
/// （见 [MusicPlayer#fadeIn]），此时上一首正好淡完，于是“当前正在播放的音乐”就是它。
/// 自然放完、间隔之后重新开的那一首同样会走到这里，所以不会出现“有的歌没提示”。
///
/// 绘制走 [net.minecraftforge.client.event.RenderGuiEvent.Post]（主事件总线，见 [MusicHandler#renderGui]），
/// 因此它画在原版 HUD 之后、F1 隐藏 HUD 时也一起消失。
///
/// 滑入/停留/滑出按真实毫秒算，跟帧率无关；文字超过底图宽度时会整体等比缩小，
/// 免得像 `Awakening Moonlight (月照无眠) - SquidSine` 这种长曲名顶出去。
public final class MusicToast {
    private static final ResourceLocation TEXTURE = ConfluenceMusic.asResource("textures/gui/music_selection.png");
    /// 贴图尺寸。
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;
    /// 贴图左右各留了一圈透明边，真正有像素的是 x = 18..184，文字就居中在这一段里。
    private static final int BAND_LEFT = 18;
    private static final int BAND_WIDTH = 167;
    /// 文字与底图边缘之间再留一点空隙。
    private static final int TEXT_PADDING = 24;
    /// 曲名颜色（棕色）。
    private static final int TEXT_COLOR = DyeColor.BROWN.getFireworkColor();
    /// 停住时距离屏幕顶端的位置；原版进度提示在右上角，这里对应地贴在左上角。
    private static final int TOP = 8;
    private static final long SLIDE_IN_MS = 260L;
    private static final long HOLD_MS = 5000L;
    private static final long SLIDE_OUT_MS = 260L;

    /// 正在显示（或正在滑出）的曲名；null 表示什么都不画。
    private static @Nullable String title;
    /// [#title] 是什么时候开始显示的。
    private static long shownAt;
    /// 上一条提示的内容，用来压掉“同一首曲子被反复接回来”时的重复提示。
    private static @Nullable String lastTitle;
    private static long lastAt;

    private MusicToast() {}

    /// 显示一条曲名提示；重复调用会从滑入重新开始。
    ///
    /// 同一条曲名在 [#HOLD_MS] 之内再来一次（处境在边界上抖、同一首被接回来……）时忽略，
    /// 免得提示条一直重播。
    public static void show(@Nullable String title) {
        if (title == null) return;
        long now = Util.getMillis();
        if (title.equals(lastTitle) && now - lastAt < HOLD_MS) return;
        MusicToast.title = title;
        MusicToast.shownAt = now;
        MusicToast.lastTitle = title;
        MusicToast.lastAt = now;
    }

    /// 立刻收起（离开世界时用）。
    public static void clear() {
        title = null;
        lastTitle = null;
    }

    /// 在 HUD 上画提示条，由 [MusicHandler#renderGui] 每帧调一次。
    public static void render(GuiGraphics guiGraphics) {
        @Nullable String title = MusicToast.title;
        if (title == null) return;
        long elapsed = Util.getMillis() - shownAt;
        long holdEnd = SLIDE_IN_MS + HOLD_MS;
        float progress; // 0 = 完全在屏幕外，1 = 完全到位
        if (elapsed < SLIDE_IN_MS) {
            progress = easeOut((float) elapsed / SLIDE_IN_MS);
        } else if (elapsed < holdEnd) {
            progress = 1.0F;
        } else if (elapsed < holdEnd + SLIDE_OUT_MS) {
            progress = 1.0F - easeIn((float) (elapsed - holdEnd) / SLIDE_OUT_MS);
        } else {
            MusicToast.title = null; // 已经滑出屏幕，收工
            return;
        }
        int x = Math.round(Mth.lerp(progress, -WIDTH, 0.0F));
        guiGraphics.blit(TEXTURE, x, TOP, 0.0F, 0.0F, WIDTH, HEIGHT, WIDTH, HEIGHT);

        Font font = Minecraft.getInstance().font;
        float scale = Math.min(1.0F, (float) (BAND_WIDTH - TEXT_PADDING * 2) / Math.max(1, font.width(title)));
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(x + BAND_LEFT + 4 + BAND_WIDTH / 2.0F, TOP + HEIGHT / 2.0F, 0.0F);
        guiGraphics.pose().scale(scale, scale, 1.0F);
        guiGraphics.drawString(font, title, -font.width(title) / 2, -4, TEXT_COLOR, false);
        guiGraphics.pose().popPose();
    }

    private static float easeOut(float t) {
        float inverse = 1.0F - t;
        return 1.0F - inverse * inverse * inverse;
    }

    private static float easeIn(float t) {
        return t * t * t;
    }
}
