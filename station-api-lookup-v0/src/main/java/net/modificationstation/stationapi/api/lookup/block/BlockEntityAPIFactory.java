package net.modificationstation.stationapi.api.lookup.block;

import net.minecraft.block.entity.BlockEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class BlockEntityAPIFactory<T> implements Function<BlockEntity, T> {

    public static final BlockEntityAPIFactory IDENTITY = new BlockEntityAPIFactory<>(Function.identity());

    private final Map<BlockEntity, T> cache = new ConcurrentHashMap<>();
    private final Function<BlockEntity, T> func;

    public BlockEntityAPIFactory(Function<BlockEntity, T> func) {
        this.func = func;
    }

    @Override
    public T apply(BlockEntity be) {
        return cache.computeIfAbsent(be, this.func);
    }

}
