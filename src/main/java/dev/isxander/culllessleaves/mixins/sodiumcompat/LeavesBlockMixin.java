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