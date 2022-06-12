package com.pursuitofglowstone.obsidimancy.mixin;

import com.pursuitofglowstone.obsidimancy.items.IInherentlyEnchantedItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantmentHelper.class)
public abstract class InherentEnchantMixin {

    @Inject(method = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/nbt/CompoundTag;)I"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private static void obsidimancyBoostEnchantLevels(Enchantment enchantment,
                                                      ItemStack stack,
                                                      CallbackInfoReturnable<Integer> cir,
                                                      ResourceLocation resourcelocation,
                                                      ListTag listtag,
                                                      int i,
                                                      CompoundTag compoundtag,
                                                      ResourceLocation enchantmentID) {
        if (stack.getItem() instanceof IInherentlyEnchantedItem item && item.getEnchantments().containsKey(enchantment)) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + item.getEnchantments().get(enchantment));
        }
    }

    @Inject(method = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private static void obsidimancySetMinEnchantLevels(Enchantment enchantment,
                                                       ItemStack stack,
                                                       CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof IInherentlyEnchantedItem item && item.getEnchantments().containsKey(enchantment)) {
            cir.setReturnValue(item.getEnchantments().get(enchantment));
        }
    }
}
