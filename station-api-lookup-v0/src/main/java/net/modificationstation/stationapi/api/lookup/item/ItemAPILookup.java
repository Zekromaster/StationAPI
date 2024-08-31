package net.modificationstation.stationapi.api.lookup.item;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.util.API;

import java.util.Optional;
import java.util.function.Function;

public final class ItemAPILookup {

    @API
    public <T> Optional<T> find(Class<T> api, ItemStack stack) {
        return StationAPI.EVENT_BUS
            .post(new ItemAPILookupEvent(api, stack))
            .getFoundObject()
            .apply(stack)
            .filter(api::isInstance)
            .map(api::cast);
    }

    @API
    public <T> Function<ItemStack, Optional<T>> finder(Class<T> api) {
        return stack -> this.find(api, stack);
    }

}
