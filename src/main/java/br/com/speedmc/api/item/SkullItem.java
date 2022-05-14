package br.com.speedmc.api.item;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class SkullItem extends Item{

    SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    private String owner;

    public SkullItem(int quantidade, String owner) {
        super(new ItemStack(Material.SKULL_ITEM, quantidade, (short) SkullType.PLAYER.ordinal()));
        this.owner = owner;
        skullMeta.setOwner(owner);
        itemStack.setItemMeta(skullMeta);
    }

    @Override
    public Item setName(String displayName) {
        skullMeta.setDisplayName(displayName);
        itemStack.setItemMeta(skullMeta);
        return this;
    }

    @Override
    public Item setLore(List<String> lore) {
        skullMeta.setLore(lore);
        itemStack.setItemMeta(skullMeta);
        return this;
    }

    @Override
    public Item addEnchant(Enchantment enchant, int nivel) {
        return null;
    }

}
