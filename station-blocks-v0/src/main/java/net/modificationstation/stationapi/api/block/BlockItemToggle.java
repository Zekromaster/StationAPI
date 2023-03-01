package net.modificationstation.stationapi.api.block;

import net.minecraft.block.BlockBase;

public interface BlockItemToggle<T extends BlockBase> {

    T disableAutomaticBlockItemRegistration();

    boolean isAutomaticBlockItemRegistrationDisabled();
}
