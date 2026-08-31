package com.veritas.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
//import com.veritas.client.widgets.Button;
import com.veritas.client.widgets.* ;

public class TestScreen extends Screen {
    protected TestScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {



        // Center the button horizontally, place it slightly above center vertically
        int buttonX = this.width / 2 - 50; // Half of width (100) subtracted from center
        int buttonY = this.height / 2 - 10; // Half of height (20) subtracted from center

        int ScreenWidth = this.width;
        int ScreenHeight = this.height;

        // 1. Create your custom button
        Button myCustomButton = new Button(
                buttonX,
                buttonY,
                100, // width
                20,  // height
                Component.literal("Click Me!")

        );
        Background background = new Background(
                buttonX,
                buttonY,
                (ScreenWidth / 10) * 8,
                (ScreenHeight / 10) * 8
        );

        // 2. Register it so Minecraft updates and draws it automatically
        this.addRenderableWidget(background);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Set to true if you want the game to pause when this UI is open
    }
}
