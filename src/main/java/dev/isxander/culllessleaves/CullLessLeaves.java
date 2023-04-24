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
package dev.isxander.culllessleaves;

import dev.isxander.culllessleaves.compat.Compat;
import dev.isxander.culllessleaves.config.CullLessLeavesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.fml.common.Mod;

@Mod(CullLessLeaves.MOD_ID)
public class CullLessLeaves {
    public static final String MOD_ID = "culllessleaves";

    public CullLessLeaves() {
        /* There is currently no forge mod
        ModList.get().getModContainerById("moreculling").ifPresent((mod) -> {
            if (mod.getModInfo().getVersion().compareTo(new DefaultArtifactVersion("0.5.0")) < 0)
                throw new RuntimeException("MoreCulling compatibility requires version >=0.5.0");
        });*/
        getConfig().load();
    }

    public static CullLessLeavesConfig getConfig() {
        return CullLessLeavesConfig.INSTANCE;
    }

    @SuppressWarnings("ConstantValue")
    public static boolean shouldCullSide(BlockGetter view, BlockPos pos, Direction facing) {
        var depth = getConfig().depth;

        // if not fancy leaves, cull everything apart from outside
        if (!Compat.isFancyLeaves())
            depth = 1;

        var vec = facing.getNormal();
        var cull = true;
        for (int i = 1; i <= depth; i++) {
            var state = view.getBlockState(pos.offset(vec.multiply(i)));
            cull &= state != null && state.getBlock() instanceof LeavesBlock;
        }
        return cull;
    }
}