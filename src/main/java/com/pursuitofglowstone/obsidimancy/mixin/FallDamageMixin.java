package com.pursuitofglowstone.obsidimancy.mixin;

import com.pursuitofglowstone.obsidimancy.items.precursorpickaxe.AbstractPrecursorPickaxe;
import com.pursuitofglowstone.obsidimancy.items.skydivershood.AbstractSkydiversHood;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantmentHelper.class)
public abstract class FallDamageMixin {

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
        if (enchantment == Enchantments.FALL_PROTECTION && stack.getItem() instanceof AbstractSkydiversHood hood) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + hood.getFeatherFallingLevel());
        }
        if (enchantment == Enchantments.UNBREAKING && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + pickaxe.getUnbreaking());
        }
        if (enchantment == Enchantments.BLOCK_EFFICIENCY && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + pickaxe.getEfficiency());
        }
        if (enchantment == Enchantments.SILK_TOUCH && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + pickaxe.getSilkTouch());
        }
        if (enchantment == Enchantments.BLOCK_FORTUNE && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(EnchantmentHelper.getEnchantmentLevel(compoundtag) + pickaxe.getFortune());
        }
    }

    @Inject(method = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private static void obsidimancySetMinEnchantLevels(Enchantment enchantment,
                                                       ItemStack stack,
                                                       CallbackInfoReturnable<Integer> cir) {
        if (enchantment == Enchantments.FALL_PROTECTION && stack.getItem() instanceof AbstractSkydiversHood hood) {
            cir.setReturnValue(hood.getFeatherFallingLevel());
        }
        if (enchantment == Enchantments.UNBREAKING && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(pickaxe.getUnbreaking());
        }
        if (enchantment == Enchantments.BLOCK_EFFICIENCY && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(pickaxe.getEfficiency());
        }
        if (enchantment == Enchantments.SILK_TOUCH && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(pickaxe.getSilkTouch());
        }
        if (enchantment == Enchantments.BLOCK_FORTUNE && stack.getItem() instanceof AbstractPrecursorPickaxe pickaxe) {
            cir.setReturnValue(pickaxe.getFortune());
        }
    }
}
