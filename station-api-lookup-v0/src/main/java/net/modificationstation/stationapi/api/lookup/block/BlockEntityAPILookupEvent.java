package net.modificationstation.stationapi.api.lookup.block;

import net.mine_diver.unsafeevents.event.Cancelable;
import net.minecraft.block.entity.BlockEntity;
import net.modificationstation.stationapi.api.lookup.ApiLookupEvent;
import net.modificationstation.stationapi.api.util.exception.LookupException;

import java.util.Optional;
import java.util.function.Function;

@Cancelable
public class BlockEntityAPILookupEvent extends ApiLookupEvent<Function<BlockEntity, Optional<?>>> {
    public final Class<?> blockEntityType;

    public BlockEntityAPILookupEvent(Class<?> apiClass, Class<?> blockEntityType) {
        super(apiClass, (x) -> Optional.empty());
        this.blockEntityType = blockEntityType;
    }

    public <T> void found(Class<T> clazz, BlockEntityAPIFactory<T> factory) {
        if (clazz == this.apiClass) {
            super.found(factory.andThen(Optional::ofNullable));
        }
        throw new LookupException(this.apiClass);
    }
}
