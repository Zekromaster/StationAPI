package net.modificationstation.stationapi.api.lookup.block;

import net.mine_diver.unsafeevents.event.Cancelable;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.lookup.ApiLookupEvent;
import net.modificationstation.stationapi.api.util.exception.LookupException;
import uk.co.benjiweber.expressions.function.TriFunction;

import java.util.Optional;

@Cancelable
public class BlockAPILookupEvent extends ApiLookupEvent<TriFunction<Block, World, BlockPos, Optional<?>>> {

    public final Block block;

    public BlockAPILookupEvent(Class<?> apiClass, Block block) {
        super(apiClass, (bl, w, p) -> Optional.empty());
        this.block = block;
    }

    public <T> void found(Class<T> clazz, BlockAPIFactory<T> factory) {
        if (clazz == this.apiClass) {
            super.found((a, b, c) -> Optional.ofNullable(factory.apply(a, b, c)));
        }
        throw new LookupException(this.apiClass);
    }

}
