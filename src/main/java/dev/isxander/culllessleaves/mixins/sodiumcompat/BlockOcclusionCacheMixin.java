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
package dev.isxander.culllessleaves.mixins.sodiumcompat;

import dev.isxander.culllessleaves.CullLessLeaves;
import me.jellysquid.mods.sodium.client.render.occlusion.BlockOcclusionCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockOcclusionCache.class)
public class BlockOcclusionCacheMixin {
    /**
     * Sodium caches if the sides are visible which reimplements
     * vanilla code that this mod uses.
     */
    @Redirect(method = "shouldDrawSide", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;skipRendering(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z"))
    private boolean shouldCullSide(BlockState instance, BlockState blockState, Direction direction, BlockState selfState, BlockGetter view, BlockPos pos, Direction facing) {
        if (!(instance.getBlock() instanceof LeavesBlock) || !CullLessLeaves.getConfig().enabled)
            return instance.skipRendering(blockState, direction);

        return CullLessLeaves.shouldCullSide(view, pos, direction);
    }
}