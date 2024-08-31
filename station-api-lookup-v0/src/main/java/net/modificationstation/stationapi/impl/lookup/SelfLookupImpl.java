package net.modificationstation.stationapi.impl.lookup;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.lookup.block.BlockAPIFactory;
import net.modificationstation.stationapi.api.lookup.block.BlockAPILookupEvent;
import net.modificationstation.stationapi.api.lookup.block.BlockEntityAPIFactory;
import net.modificationstation.stationapi.api.lookup.block.BlockEntityAPILookupEvent;
import net.modificationstation.stationapi.api.lookup.item.ItemAPIFactory;
import net.modificationstation.stationapi.api.lookup.item.ItemAPILookupEvent;

public class SelfLookupImpl {

    @EventListener(priority = ListenerPriority.HIGH)
    private static void blockEntitySelfRegister(BlockEntityAPILookupEvent event) {
        if (event.apiClass.isAssignableFrom(event.blockEntityType)) {
            event.found(event.apiClass, BlockEntityAPIFactory.IDENTITY);
        }
    }

    @EventListener(priority = ListenerPriority.HIGH)
    private static void blockSelfRegister(BlockAPILookupEvent event) {
        if (event.apiClass.isInstance(event.block)) {
            event.found(event.apiClass, BlockAPIFactory.IDENTITY);
        }
    }

    @EventListener(priority = ListenerPriority.HIGH)
    private static void itemSelfRegister(ItemAPILookupEvent event) {
        if (event.apiClass.isInstance(event.item)) {
            event.found(event.apiClass, ItemAPIFactory.IDENTITY);
        }
    }

}
