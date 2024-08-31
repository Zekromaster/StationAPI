package net.modificationstation.stationapi.api.lookup.block;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.benjiweber.expressions.function.TriFunction;

public final class BlockAPIFactory<T> implements TriFunction<Block, World, BlockPos, T> {

    public static final BlockAPIFactory IDENTITY = new BlockAPIFactory<>((b, w, p) -> b);

    private final Table<World, BlockPos, T> cache = HashBasedTable.create();
    private final TriFunction<Block, World, BlockPos, T> func;

    public BlockAPIFactory(TriFunction<Block, World, BlockPos, T> func) {
        this.func = func;
    }

    @Override
    public T apply(Block block, World world, BlockPos blockPos) {
        if (!cache.contains(world, blockPos)) {
            cache.put(world, blockPos, this.func.apply(block, world, blockPos));
        }
        return cache.get(world, blockPos);
    }

}
