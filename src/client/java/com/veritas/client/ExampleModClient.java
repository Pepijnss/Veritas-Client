package com.example.client;

import com.example.client.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
	private static final KeyMapping.Category CATEGORY =
			KeyMapping.Category.register(Identifier.fromNamespaceAndPath("veritas", "veritasclient"));

	private static KeyMapping openScreenKey;

	public static ModuleManager moduleManager;

	@Override
	public void onInitializeClient() {
		moduleManager = new ModuleManager();

		// Run the Keystrokes renderer before the chat loads.
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("example", "keystrokes_hud"),
				moduleManager.keystrokes::render
		);
		// Run the Coords renderer before the chat loads.
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("example", "coords_hud"),
				moduleManager.coords::render
		);
		HudElementRegistry.attachElementBefore(
				VanillaHudElements.CHAT,
				Identifier.fromNamespaceAndPath("example", "armour_durability"),
				moduleManager.armourDurability::render
		);

		// Key to open the mod menu.
		openScreenKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.veritas.modmenu",
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				CATEGORY));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openScreenKey.consumeClick()) {
				client.gui.setScreen(new ModMenu(Component.literal("My Screen")));
			}
		});
	}
}