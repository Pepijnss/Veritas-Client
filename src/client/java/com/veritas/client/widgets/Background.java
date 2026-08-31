package com.veritas.client.widgets;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class Background extends AbstractWidget {

    // Corrected format for direct pipeline resource extraction
    private static final Identifier RAW_TEXTURE = Identifier.fromNamespaceAndPath(
            "veritas",
            "gui/rounded_bg"
    );


    public Background(int x, int y, int width, int height) {
        super(x, y, width, height, Component.empty());
    }

    @Override
    protected void extractWidgetRenderState(@NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        int w = this.getWidth();
        int h = this.getHeight();

        /*
         * ADJUSTED FOR 24x24 ASSETS WITH TIGHT CORNERS:
         * If your image has small rounded edges, we allocate 4 pixels to the corners.
         * The remaining middle area handles all stretching dynamically.
         * Math: Left Corner (4px) + Middle Stretch (16px) + Right Corner (4px) = 24px total file width.
         */
        float c = 4.0F;         // Reduced corner width mapping from 8 to 4
        float s = 16.0F;        // Increased center stretch zone width from 8 to 16
        float texSize = 24.0F;  // Keeping your exact 24x24 asset scale factor

        // =====================================================================
        // ROW 1: TOP BORDERS
        // =====================================================================
        // Top Left Corner
        drawSlice(graphics, 0, 0, (int) c, (int) c, 0.0F, 0.0F, texSize);
        // Top Middle Section
        drawSlice(graphics, (int) c, 0, w - ((int) c * 2), (int) c, c, 0.0F, texSize);
        // Top Right Corner
        drawSlice(graphics, w - (int) c, 0, (int) c, (int) c, c + s, 0.0F, texSize);

        // =====================================================================
        // ROW 2: MIDDLE BODY
        // =====================================================================
        // Center Left Column
        drawSlice(graphics, 0, (int) c, (int) c, h - ((int) c * 2), 0.0F, c, texSize);
        // Absolute Core Center Fill Box
        drawSlice(graphics, (int) c, (int) c, w - ((int) c * 2), h - ((int) c * 2), c, c, texSize);
        // Center Right Column
        drawSlice(graphics, w - (int) c, (int) c, (int) c, h - ((int) c * 2), c + s, c, texSize);

        // =====================================================================
        // ROW 3: BOTTOM BORDERS
        // =====================================================================
        // Bottom Left Corner
        drawSlice(graphics, 0, h - (int) c, (int) c, (int) c, 0.0F, c + s, texSize);
        // Bottom Middle Section
        drawSlice(graphics, (int) c, h - (int) c, w - ((int) c * 2), (int) c, c, c + s, texSize);
        // Bottom Right Corner
        drawSlice(graphics, w - (int) c, h - (int) c, (int) c, (int) c, c + s, c + s, texSize);
    }

    private void drawSlice(GuiGraphicsExtractor graphics, int x, int y, int width, int height, float u, float v, float texSize) {
        if (width <= 0 || height <= 0) return;

        float uPercentage = u / texSize;
        float vPercentage = v / texSize;

        graphics.blit(
                RenderPipelines.VIGNETTE,
                RAW_TEXTURE,
                x,
                y,
                uPercentage,
                vPercentage,
                width,
                height,
                (int) 1.0F,
                (int) 1.0F
        );
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput output) {
        // Intentionally left blank
    }
}
