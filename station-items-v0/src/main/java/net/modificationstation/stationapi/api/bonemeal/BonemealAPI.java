package net.modificationstation.stationapi.api.bonemeal;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.class_239;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.collection.WeightedList;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.Map;
import java.util.Random;

public class BonemealAPI {
    private static final Map<TagKey<Block>, WeightedList<class_239>> PLACERS_TAG = new Reference2ObjectOpenHashMap<>();
    private static final Map<BlockState, WeightedList<class_239>> PLACERS_BLOCK = new Reference2ObjectOpenHashMap<>();
    private static final WeightedList<class_239> CACHE = new WeightedList<>();

    public static void addPlant(BlockState ground, BlockState plant, int weight) {
        addPlant(ground, new SimpleStateFeature(plant), weight);
    }

    public static void addPlant(BlockState ground, class_239 plant, int weight) {
        PLACERS_BLOCK.computeIfAbsent(ground, g -> new WeightedList<>()).add(plant, weight);
    }

    public static void addPlant(TagKey<Block> ground, BlockState plant, int weight) {
        addPlant(ground, new SimpleStateFeature(plant), weight);
    }

    public static void addPlant(TagKey<Block> ground, class_239 plant, int weight) {
        PLACERS_TAG.computeIfAbsent(ground, g -> new WeightedList<>()).add(plant, weight);
    }

    public static boolean generate(World world, int x, int y, int z, BlockState state, int side) {
        updateCache(state);
        if (CACHE.isEmpty()) return false;
        Random random = world.field_214;
        Direction offset = Direction.byId(side);
        CACHE.get(random).method_1142(
                world,
                random,
                x + offset.getOffsetX(),
                y + offset.getOffsetY(),
                z + offset.getOffsetZ()
        );
        for (byte i = 0; i < 127; i++) {
            int px = x + random.nextInt(7) - 3;
            int py = y + random.nextInt(5) - 2;
            int pz = z + random.nextInt(7) - 3;
            state = world.getBlockState(px, py, pz);
            updateCache(state);
            if (CACHE.isEmpty()) continue;
            CACHE.get(random).method_1142(world, random, px, py + 1, pz);
        }
        return true;
    }

    private static void updateCache(BlockState state) {
        CACHE.clear();
        WeightedList<class_239> structures = PLACERS_BLOCK.get(state);
        if (structures != null) CACHE.addAll(structures);
        state.streamTags().forEach(tag -> {
            WeightedList<class_239> tagStructures = PLACERS_TAG.get(tag);
            if (tagStructures != null) CACHE.addAll(tagStructures);
        });
    }

    private static class SimpleStateFeature extends class_239 {
        private final BlockState state;

        private SimpleStateFeature(BlockState state) {
            this.state = state;
        }

        @Override
        public boolean method_1142(World world, Random random, int x, int y, int z) {
            BlockState worldState = world.getBlockState(x, y, z);
            if (!worldState.isAir() && !worldState.getMaterial().method_893()) return false;
            if (state.getBlock().canPlaceAt(world, x, y, z)) {
                world.setBlockState(x, y, z, state);
                return true;
            }
            return false;
        }
    }

    private static class GrassFeature extends class_239 {
        private static final BlockState STATE = Block.GRASS.getDefaultState();

        @Override
        public boolean method_1142(World world, Random random, int x, int y, int z) {
            BlockState worldState = world.getBlockState(x, y, z);
            if (!worldState.isAir() && !worldState.getMaterial().method_893()) return false;
            if (STATE.getBlock().canPlaceAt(world, x, y, z)) {
                world.setBlockState(x, y, z, STATE);
                world.method_215(x, y, z, 1);
                return true;
            }
            return false;
        }
    }

    static {
        BlockState grass = Block.GRASS_BLOCK.getDefaultState();
        addPlant(grass, new GrassFeature(), 10);
        addPlant(grass, Block.DANDELION.getDefaultState(), 1);
        addPlant(grass, Block.ROSE.getDefaultState(), 1);
    }
}
