package net.modificationstation.stationapi.template.common.block;

import net.minecraft.block.BlockSounds;
import net.modificationstation.stationapi.api.common.block.BlockRegistry;
import net.modificationstation.stationapi.api.common.registry.Identifier;

public class Furnace extends net.minecraft.block.Furnace {

    public Furnace(Identifier identifier, boolean flag) {
        this(BlockRegistry.INSTANCE.getNextSerializedID(), flag);
        BlockRegistry.INSTANCE.registerValue(identifier, this);
    }

    public Furnace(int i, boolean flag) {
        super(i, flag);
    }

    @Override
    public Furnace disableNotifyOnMetaDataChange() {
        return (Furnace) super.disableNotifyOnMetaDataChange();
    }

    @Override
    public Furnace setSounds(BlockSounds sounds) {
        return (Furnace) super.setSounds(sounds);
    }

    @Override
    public Furnace setLightOpacity(int i) {
        return (Furnace) super.setLightOpacity(i);
    }

    @Override
    public Furnace setLightEmittance(float f) {
        return (Furnace) super.setLightEmittance(f);
    }

    @Override
    public Furnace setBlastResistance(float resistance) {
        return (Furnace) super.setBlastResistance(resistance);
    }

    @Override
    public Furnace setHardness(float hardness) {
        return (Furnace) super.setHardness(hardness);
    }

    @Override
    public Furnace setUnbreakable() {
        return (Furnace) super.setUnbreakable();
    }

    @Override
    public Furnace setTicksRandomly(boolean ticksRandomly) {
        return (Furnace) super.setTicksRandomly(ticksRandomly);
    }

    @Override
    public Furnace setTranslationKey(String string) {
        return (Furnace) super.setTranslationKey(string);
    }

    @Override
    public Furnace disableStat() {
        return (Furnace) super.disableStat();
    }
}
