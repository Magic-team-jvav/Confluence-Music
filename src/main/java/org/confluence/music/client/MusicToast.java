package org.confluence.music.client;

import com.mojang.math.Axis;
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
/// 绘制走 [net.neoforged.neoforge.client.event.RenderGuiEvent.Post]（主事件总线，见 [MusicHandler#renderGui]），
/// 因此它画在原版 HUD 之后、F1 隐藏 HUD 时也一起消失。
///
/// 滑入/停留/滑出按真实毫秒算，跟帧率无关；文字超过底图宽度时会整体等比缩小，
/// 免得像 `Awakening Moonlight (月照无眠) - SquidSine` 这种长曲名顶出去。
///
/// 曲名是**一个字一个字**画的，每个字都按底图缎带在它那一处的高低和斜率做平移、旋转，
/// 所以整行字顺着缎带起伏（波形参数见 [#WAVE_MIDDLE]，是从 `music_selection.png` 里量出来的）。
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
    private static final long SLIDE_IN_MS = 330L;
    private static final long HOLD_MS = 5000L;
    private static final long SLIDE_OUT_MS = 330L;

    /// 缎带中线（贴图里的 y）从 `music_selection.png` 里量出来的拟合：
    /// `11.65 - 1.8 · sin(2πx / 156.5 + 2.094)`，在 x = 30..176 上与原图差不到 0.25px。
    /// 文字按它上下起伏，看起来才是贴在缎带上而不是压在一条直线上。
    private static final float WAVE_MIDDLE = 11.65F;
    private static final float WAVE_AMPLITUDE = -1.8F;
    private static final float WAVE_PERIOD = 156.5F;
    private static final float WAVE_PHASE = 2.094F;
    /// 单个字跟着中线转的角度上限（弧度）；实际最大只有约 4°。
    private static final float MAX_TILT = 0.15F;

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
        // 一个字一个字地画：每个字按自己所在位置把中线的高低、斜率套上去，
        // 于是整行曲名是顺着缎带起伏、倾斜的（一次画整行做不到这个）。
        float left = x + BAND_LEFT + 4 + BAND_WIDTH / 2.0F - font.width(title) * scale / 2.0F;
        float advance = 0.0F;
        for (int i = 0; i < title.length(); ) {
            int codePoint = title.codePointAt(i);
            i += Character.charCount(codePoint);
            String glyph = new String(Character.toChars(codePoint));
            float glyphWidth = font.width(glyph);
            float centerX = left + (advance + glyphWidth / 2.0F) * scale;
            // 底图是 1:1 贴上去的，所以屏幕上的横向偏移就是贴图里的 x
            float textureX = centerX - x;
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(centerX, TOP + HEIGHT / 2.0F + waveOffset(textureX), 0.0F);
            guiGraphics.pose().mulPose(Axis.ZP.rotation(waveAngle(textureX)));
            guiGraphics.pose().scale(scale, scale, 1.0F);
            guiGraphics.drawString(font, glyph, -glyphWidth / 2.0F, -4, TEXT_COLOR, false);
            guiGraphics.pose().popPose();
            advance += glyphWidth;
        }
    }

    /// {@return 贴图 x 处的缎带中线高度（贴图坐标系，y 向下）}
    private static float waveMiddle(float textureX) {
        return WAVE_MIDDLE + WAVE_AMPLITUDE * Mth.sin(wavePhase(textureX));
    }

    /// {@return 贴图 x 处的文字该相对缎带正中（[#HEIGHT] / 2）上下挪多少}
    private static float waveOffset(float textureX) {
        return waveMiddle(textureX) - HEIGHT / 2.0F;
    }

    /// {@return 贴图 x 处中线切线的倾角（弧度，屏幕坐标 y 向下）}
    ///
    /// 中线是 `A · sin(2πx / P + φ)`，导数就是 `A · (2π / P) · cos(…)`；
    /// GUI 里 Z 轴正方向旋转在屏幕上看是顺时针，正好和“y 向下”的斜率同向。
    private static float waveAngle(float textureX) {
        float phase = wavePhase(textureX);
        float slope = WAVE_AMPLITUDE * (Mth.TWO_PI / WAVE_PERIOD) * Mth.cos(phase);
        return Mth.clamp((float) Math.atan(slope), -MAX_TILT, MAX_TILT);
    }

    private static float wavePhase(float textureX) {
        return Mth.TWO_PI * Mth.clamp(textureX, 0.0F, WIDTH) / WAVE_PERIOD + WAVE_PHASE;
    }

    private static float easeOut(float t) {
        float inverse = 1.0F - t;
        return 1.0F - inverse * inverse * inverse;
    }

    private static float easeIn(float t) {
        return t * t * t;
    }
}
