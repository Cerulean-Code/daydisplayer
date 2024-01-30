package com.minecrafteur.daydisplayermod;


import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class DayDisplayerDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AdvancementsProvider::new);
    }

    static class AdvancementsProvider extends FabricAdvancementProvider {
        protected AdvancementsProvider(FabricDataOutput dataGenerator) {
            super(dataGenerator);
        }

        @Override
        public void generateAdvancement(Consumer<Advancement> consumer) {
            Advancement.Builder root = Advancement.Builder
                    .create()
                    .display(Items.CLOCK,
                            Text.literal("Day Displayer"),
                            Text.literal("Thanks for downloading my mod :)"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.CHALLENGE,
                            false,
                            false,
                            false

                    ).criterion("installed_daydisplayer", new ImpossibleCriterion.Conditions());
            Advancement rootAdvancement = root.build(new Identifier("daydisplayermod:installed"));
            consumer.accept(rootAdvancement);

            // Playing in hardcore
            Advancement.Builder playingHardcore = Advancement.Builder
                    .create().parent(rootAdvancement)
                    .display(Items.RED_WOOL,
                            Text.literal("Survival is Elementary!"),
                            Text.literal("In Hardcore mode, every clue leads to death."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.GOAL,
                            true,
                            true,
                            true).criterion("playing_hardcore", new ImpossibleCriterion.Conditions());
            Advancement playingHardcoreAdvancement = playingHardcore.build(new Identifier("daydisplayermod:playing_hardcore"));
            consumer.accept(playingHardcoreAdvancement);

            // time based
            Advancement.Builder survive1 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.RED_BED,
                            Text.literal("Survived the night!"),
                            Text.literal("You survived your first night!"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_1", new ImpossibleCriterion.Conditions());
            Advancement survive1Advancement = survive1.build(new Identifier("daydisplayermod:survive_1"));
            consumer.accept(survive1Advancement);


            Advancement.Builder survive5 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.ORANGE_BED,
                            Text.literal("Five nights..."),
                            Text.literal("at Freddy's"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_5", new ImpossibleCriterion.Conditions());
            Advancement survive5Advancement = survive5.build(new Identifier("daydisplayermod:survive_5"));
            consumer.accept(survive5Advancement);

            Advancement.Builder survive10 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.GREEN_BED,
                            Text.literal("Ben 10"),
                            Text.literal("Talk later, survive now."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_10", new ImpossibleCriterion.Conditions());
            Advancement survive10Advancement = survive10.build(new Identifier("daydisplayermod:survive_10"));
            consumer.accept(survive10Advancement);

            Advancement.Builder survive15 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.BLUE_BED,
                            Text.literal("FORTNITE"),
                            Text.literal("plus one"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_15", new ImpossibleCriterion.Conditions());
            Advancement survive15Advancement = survive15.build(new Identifier("daydisplayermod:survive_15"));
            consumer.accept(survive15Advancement);


            Advancement.Builder survive30 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.BLUE_BED,
                            Text.literal("Groundhog month"),
                            Text.literal("again... and again."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_30", new ImpossibleCriterion.Conditions());
            Advancement survive30Advancement = survive30.build(new Identifier("daydisplayermod:survive_30"));
            consumer.accept(survive30Advancement);

            Advancement.Builder survive50 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.MAGENTA_BED,
                            Text.literal("Golden Jubilee"),
                            Text.literal("The queen would be proud"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_50", new ImpossibleCriterion.Conditions());
            Advancement survive50Advancement = survive50.build(new Identifier("daydisplayermod:survive_50"));
            consumer.accept(survive50Advancement);

            Advancement.Builder survive100 = Advancement.Builder
                    .create().parent(playingHardcoreAdvancement)
                    .display(Items.PINK_BED,
                            Text.literal("The Hundred Acre Wood"),
                            Text.literal("100 days of adventures greater than Pooh could have ever imagined."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            true).criterion("survive_100", new ImpossibleCriterion.Conditions());
            Advancement survive100Advancement = survive100.build(new Identifier("daydisplayermod:survive_100"));
            consumer.accept(survive100Advancement);
        }
    }
}


// Have the mod installed: Clockwork orange: Thanks for downloading my mod :)

// Be in hardcore mode: Survival is Elementary! : In Hardcore mode, every clue leads to death.

// Day one: Survived the night! : You survived your first night!

// Day Five: High Five! : High five, you are still alive!

// Day Ten: Double Digit Dan : You've left the tutorial island. Time for the real adventure.

// Day Fifteen: FORTNITE: plus one

// Day 30 : Groundhog month: again... and again.

// Day 50: Golden Jubilee: The queen would be proud

// Day 100: The Hundred Acre Wood: 100 days of adventures greater than Pooh could have ever imagined.