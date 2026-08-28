package com.veritas.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class ModMenu extends Screen { // Extend Screen class for making a Screen like documented in the fabric docs.

    public ModMenu(Component title) {
        super(title);
    }

    private void ToggleModule(String Module){
        ExampleModClient.moduleManager.toggleModule(Module); // Toggle the module using ExampleModClient.
    }

    // Create toggle for module.
    protected void CreateModuleToggle(String Text, int x, int y, Consumer<String> func) {
        Button toggleButton = Button.builder( // Build the button widget.
                getToggleLabel(Text, ExampleModClient.moduleManager.getModule(Text).isEnabled()), // Set the text of the button.
                (btn) -> {
                    func.accept(Text);
                    boolean nowEnabled = ExampleModClient.moduleManager.getModule(Text).isEnabled(); // Set nowEnabled to the value isEnabled returns in Module.java.
                    btn.setMessage(getToggleLabel(Text, nowEnabled)); // Gets the label from getToggleLabel.
                }
        ).bounds(x, y, 120, 20).build();

        this.addRenderableWidget(toggleButton); // Render the actual button.
    }

    @Override
    protected void init() { // Pass required args to CreateModuleToggle for buttons.
        CreateModuleToggle("Keystrokes", 40, 40, (func) -> { ToggleModule("Keystrokes"); });
        CreateModuleToggle("Coords", 170, 40, (func) -> { ToggleModule("Coords"); });
        System.out.println("Making armor widget");
        CreateModuleToggle("ArmourDurability", 300, 40, (func) -> { ToggleModule("ArmourDurability"); });
    }
    private Component getToggleLabel(String Text, boolean Toggled) { // Generate the text for the buttons.
        String stateText = Toggled ? "On" : "Off"; // If toggled true set stateText to On else set it to Off.
        return Component.literal(Text+ ": " + stateText); // Return the text.
    }
}
