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

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LeavesBlock.class, priority = 1100)
public class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(BlockBehaviour.Properties settings) {
        super(settings);
    }

    /**
     * sodium implements custom logic for
     * culling leaves when set to fast
     * <p>
     * this mixin simply reverts to vanilla behaviour
     *
     * @see me.jellysquid.mods.sodium.mixin.features.render_layer.leaves.MixinLeavesBlock
     */
    @Override
    @SuppressWarnings("deprecation")
    public boolean skipRendering(@NotNull BlockState state, @NotNull BlockState stateFrom, @NotNull Direction direction) {
        return super.skipRendering(state, stateFrom, direction);
    }
}