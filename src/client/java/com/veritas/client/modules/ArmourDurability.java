package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArmourDurability extends Module {


    public ArmourDurability() {
        super("ArmourDurability");
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTime) {
        if (!isEnabled()) return;

        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int hotbarEdge = (screenWidth / 2) + 91;

        //System.out.println(screenWidth);
        //System.out.println(screenHeight);

        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        if (player == null) return; // No player loaded yet

        int helmetDurability;
        int chestplateDurability;
        int leggingsDurability;
        int bootsDurability;

        ItemStack helmet = player.getSlot(103).get();
        ItemStack chestplate = player.getSlot(102).get();
        ItemStack leggings = player.getSlot(101).get();
        ItemStack boots = player.getSlot(100).get();

        ItemStack[] armorPieces = {helmet, chestplate, leggings, boots};

        //System.out.println(helmet.getItem());


        Font font = client.font;


        for (int i = 0; i < armorPieces.length; i++) {
            //System.out.println(armorPieces[i]);
            if (!armorPieces[i].isEmpty() || armorPieces[i].isDamageableItem()) {
                int damageValue = armorPieces[i].getDamageValue();
                int maxDamage = armorPieces[i].getMaxDamage();
                int currentDamage = maxDamage - damageValue;

                int x;
                int y;
                if (i == 0){
                    helmetDurability = currentDamage;
                    x = hotbarEdge + 5;
                    y = screenHeight - 65;
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else if (i==1) {
                    chestplateDurability = currentDamage;
                    x = hotbarEdge + 5;
                    y = screenHeight - 50;
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else if (i==2) {
                    leggingsDurability = currentDamage;
                    x = hotbarEdge + 5;
                    y = screenHeight - 35;
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else {
                    bootsDurability = currentDamage;
                    x = hotbarEdge + 5;
                    y = screenHeight - 20;
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                }
            }
        }
    }
}