package net.modificationstation.stationapi.api.lookup.item;

import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ItemAPIFactory<T> implements Function<ItemStack, T> {

    public static final ItemAPIFactory IDENTITY = new ItemAPIFactory<>(Function.identity());


    private final Map<ItemStack, T> cache = new ConcurrentHashMap<>();
    private final Function<ItemStack, T> func;

    public ItemAPIFactory(Function<ItemStack, T> func) {
        this.func = func;
    }

    @Override
    public T apply(ItemStack be) {
        return cache.computeIfAbsent(be, this.func);
    }

}
