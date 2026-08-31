package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Font;

public class FpsDisplay extends Module{
    public FpsDisplay() {
        super("FpsDisplay");
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTime) {
        if (!isEnabled()) return;


        Minecraft client = Minecraft.getInstance();
        int fps = client.getFps();
        Font font = client.font;

        int width = client.getWindow().getGuiScaledWidth();

        graphics.text(font, Integer.toString(fps), width / 2, 10, 0xFFFFFFFF);
    }


}
