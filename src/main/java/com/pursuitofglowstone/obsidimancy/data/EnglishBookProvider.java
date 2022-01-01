package com.pursuitofglowstone.obsidimancy.data;

import com.pursuitofglowstone.obsidimancy.Obsidimancy;
import com.pursuitofglowstone.obsidimancy.items.ObsidimancyItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import xyz.brassgoggledcoders.patchouliprovider.BookBuilder;
import xyz.brassgoggledcoders.patchouliprovider.PatchouliBookProvider;

import java.util.function.Consumer;

public class EnglishBookProvider extends PatchouliBookProvider {
    public EnglishBookProvider(DataGenerator gen) {
        super(gen, Obsidimancy.MOD_ID, "en_us");
    }

    @Override
    protected void addBooks(Consumer<BookBuilder> consumer) {
        final BookBuilder bookBuilder = createBookBuilder("the_book", "The Book", "Dimensional Attunements and You");
        bookBuilder.setModel(new ResourceLocation(Obsidimancy.MOD_ID, "the_book"));
        bookBuilder.setSubtitle("Dimensional Attunements and You");
        bookBuilder.setCreativeTab(ObsidimancyItems.TAB_OBSIDIMANCY.getRecipeFolderName());
        consumer.accept(bookBuilder);
    }
}
