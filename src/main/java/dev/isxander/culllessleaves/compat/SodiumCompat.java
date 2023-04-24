/*
 * This file is part of CullLessLeavesReforged - https://github.com/CCr4ft3r/CullLessLeavesReforged
 * Copyright (C) isXanderIsDev and contributors: https://github.com/isXander/CullLessLeaves
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.isxander.culllessleaves.compat;

import dev.isxander.culllessleaves.CullLessLeaves;
import dev.isxander.culllessleaves.config.CullLessLeavesConfig;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
import net.minecraft.client.Minecraft;

public class SodiumCompat {
    private static final OptionStorage<CullLessLeavesConfig> OPTION_STORAGE = new OptionStorage<>() {
        @Override
        public CullLessLeavesConfig getData() {
            return CullLessLeaves.getConfig();
        }

        @Override
        public void save() {
            CullLessLeaves.getConfig().save();
        }
    };

    public static boolean isFancyLeaves() {
        return SodiumClientMod.options().quality.leavesQuality.isFancy(Minecraft.getInstance().options.graphicsMode);
    }

    public static OptionStorage<CullLessLeavesConfig> getOptionStorage() {
        return OPTION_STORAGE;
    }
}