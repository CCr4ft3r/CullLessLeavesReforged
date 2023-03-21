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