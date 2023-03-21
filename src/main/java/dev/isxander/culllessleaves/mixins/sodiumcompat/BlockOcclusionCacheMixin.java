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