package net.modificationstation.stationapi.template.common.block;

import net.minecraft.block.BlockSounds;
import net.modificationstation.stationapi.api.common.block.BlockRegistry;
import net.modificationstation.stationapi.api.common.registry.Identifier;

public class RedstoneDust extends net.minecraft.block.RedstoneDust {

    public RedstoneDust(Identifier identifier, int j) {
        this(BlockRegistry.INSTANCE.getNextSerializedID(), j);
        BlockRegistry.INSTANCE.registerValue(identifier, this);
    }

    public RedstoneDust(int i, int j) {
        super(i, j);
    }

    @Override
    public RedstoneDust disableNotifyOnMetaDataChange() {
        return (RedstoneDust) super.disableNotifyOnMetaDataChange();
    }

    @Override
    public RedstoneDust setSounds(BlockSounds sounds) {
        return (RedstoneDust) super.setSounds(sounds);
    }

    @Override
    public RedstoneDust setLightOpacity(int i) {
        return (RedstoneDust) super.setLightOpacity(i);
    }

    @Override
    public RedstoneDust setLightEmittance(float f) {
        return (RedstoneDust) super.setLightEmittance(f);
    }

    @Override
    public RedstoneDust setBlastResistance(float resistance) {
        return (RedstoneDust) super.setBlastResistance(resistance);
    }

    @Override
    public RedstoneDust setHardness(float hardness) {
        return (RedstoneDust) super.setHardness(hardness);
    }

    @Override
    public RedstoneDust setUnbreakable() {
        return (RedstoneDust) super.setUnbreakable();
    }

    @Override
    public RedstoneDust setTicksRandomly(boolean ticksRandomly) {
        return (RedstoneDust) super.setTicksRandomly(ticksRandomly);
    }

    @Override
    public RedstoneDust setTranslationKey(String string) {
        return (RedstoneDust) super.setTranslationKey(string);
    }

    @Override
    public RedstoneDust disableStat() {
        return (RedstoneDust) super.disableStat();
    }
}
