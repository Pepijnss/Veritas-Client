package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
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

        System.out.println(helmet.getItem());






        for (int i = 0; i < armorPieces.length; i++) {
            //System.out.println(armorPieces[i]);
            if (!armorPieces[i].isEmpty() || helmet.isDamageableItem()) {
                int currentDamage = helmet.getDamageValue();
                if (i == 0){
                    helmetDurability = currentDamage;
                } else if (i==1) {
                    chestplateDurability = currentDamage;
                } else if (i==2) {
                    leggingsDurability = currentDamage;
                } else {
                    bootsDurability = currentDamage;
                }
                graphics.item(armorPieces[i], 100, 100);
            }
        }
    }
}