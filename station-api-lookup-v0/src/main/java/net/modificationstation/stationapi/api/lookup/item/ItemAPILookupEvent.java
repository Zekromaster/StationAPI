package net.modificationstation.stationapi.api.lookup.item;

import net.mine_diver.unsafeevents.event.Cancelable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.lookup.ApiLookupEvent;
import net.modificationstation.stationapi.api.util.exception.LookupException;

import java.util.Optional;
import java.util.function.Function;

@Cancelable
public class ItemAPILookupEvent extends ApiLookupEvent<Function<ItemStack, Optional<?>>> {

    public final Item item;
    public final int meta;

    public ItemAPILookupEvent(Class<?> apiClass, ItemStack itemStack) {
        super(apiClass, (i) -> Optional.empty());
        this.item = itemStack.getItem();
        this.meta = itemStack.getDamage();
    }

    public <T> void found(Class<T> clazz, ItemAPIFactory<T> factory) {
        if (clazz == this.apiClass) {
            super.found(factory.andThen(Optional::ofNullable));
        }
        throw new LookupException(this.apiClass);
    }
}
