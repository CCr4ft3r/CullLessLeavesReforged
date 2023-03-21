package dev.isxander.culllessleaves.compat;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.ModList;

public class Compat {
    public static final boolean SODIUM = ModList.get().isLoaded("rubidium");

    public static boolean isFancyLeaves() {
        return SODIUM ? SodiumCompat.isFancyLeaves() : Minecraft.useFancyGraphics();
    }
}