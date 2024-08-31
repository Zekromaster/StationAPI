package net.modificationstation.stationapi.api.lookup.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.util.API;

import java.util.Optional;
import java.util.function.BiFunction;

public final class BlockAPILookup {

    @API
    public <T> Optional<T> find(Class<T> api, World world, BlockPos pos) {
        var be = world.method_1777(pos.x, pos.y, pos.z);
        if (be != null) {
            var apiInstance = StationAPI.EVENT_BUS
                .post(new BlockEntityAPILookupEvent(api, be.getClass()))
                .getFoundObject()
                .apply(be);
            if (apiInstance.isPresent()) {
                return apiInstance.filter(api::isInstance).map(api::cast);
            }
        }
        var block = world.getBlockState(pos).getBlock();
        var apiInstance = StationAPI.EVENT_BUS
            .post(new BlockAPILookupEvent(api, block))
            .getFoundObject()
            .apply(block, world, pos);
        return apiInstance.filter(api::isInstance).map(api::cast);
    }

    @API
    public <T> BiFunction<World, BlockPos, Optional<T>> finder(Class<T> api) {
        return (world, blockPos) -> this.find(api, world, blockPos);
    }
}
