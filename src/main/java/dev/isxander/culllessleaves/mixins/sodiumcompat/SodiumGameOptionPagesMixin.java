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

import dev.isxander.culllessleaves.compat.SodiumCompat;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class SodiumGameOptionPagesMixin {
    @ModifyVariable(method = "performance", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;copyOf(Ljava/util/Collection;)Lcom/google/common/collect/ImmutableList;"))
    private static List<OptionGroup> addLeavesCulling(List<OptionGroup> groups) {
        groups.add(OptionGroup.createBuilder()
            .add(OptionImpl.createBuilder(boolean.class, SodiumCompat.getOptionStorage())
                .setName(new TranslatableComponent("cull-less-leaves.sodium.option.enabled"))
                .setTooltip(new TranslatableComponent("cull-less-leaves.sodium.option.enabled.description"))
                .setControl(TickBoxControl::new)
                .setBinding((opts, value) -> opts.enabled = value, opts -> opts.enabled)
                .setImpact(OptionImpact.MEDIUM)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build()
            )
            .add(OptionImpl.createBuilder(int.class, SodiumCompat.getOptionStorage())
                .setName(new TranslatableComponent("cull-less-leaves.sodium.option.depth"))
                .setTooltip(new TranslatableComponent("cull-less-leaves.option.depth.tooltip"))
                .setControl(o -> new SliderControl(o, 1, 4, 1, ControlValueFormatter.number()))
                .setBinding((opts, value) -> opts.depth = value, opts -> opts.depth)
                .setImpact(OptionImpact.MEDIUM)
                .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                .build()
            )
            .build()
        );

        return groups;
    }
}