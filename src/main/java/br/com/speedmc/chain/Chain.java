package br.com.speedmc.chain;

import br.com.speedmc.api.item.CommonItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static br.com.speedmc.SpeedPvP.getInstance;

public class Chain {

    private final List<ItemStack> itens = new ArrayList<>();
    private final List<Player> onChain = new ArrayList<>();
    private final HashMap<String, Location> locs;

    public Chain() {
        locs = getInstance().getLocs();

        itens.add(new CommonItem(new ItemStack(Material.CHAINMAIL_HELMET, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3).getItem());

        itens.add(new CommonItem(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3).getItem());

        itens.add(new CommonItem(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3).getItem());

        itens.add(new CommonItem(new ItemStack(Material.CHAINMAIL_BOOTS, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addEnchant(Enchantment.DURABILITY, 3).getItem());

        itens.add(new CommonItem(new ItemStack(Material.DIAMOND_SWORD, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.DAMAGE_ALL, 2)
                .addEnchant(Enchantment.DURABILITY, 1).getItem());

        itens.add(new CommonItem(new ItemStack(Material.BOW, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain"))
                .addEnchant(Enchantment.ARROW_DAMAGE, 2)
                .addEnchant(Enchantment.DURABILITY, 1).getItem());

        itens.add(new CommonItem(new ItemStack(Material.GOLDEN_APPLE, 2))
                .setLore(Arrays.asList("", "§eItem da arena chain")).getItem());
        itens.add(new CommonItem(new ItemStack(Material.FISHING_ROD, 1))
                .setLore(Arrays.asList("", "§eItem da arena chain")).getItem());
        itens.add(new CommonItem(new ItemStack(Material.ARROW, 3))
                .setLore(Arrays.asList("", "§eItem da arena chain")).getItem());

    }

    public void joinChain(Player p) {
        if (onChain.contains(p)) {
            exitChain(p);
            return;
        }

        if (!isEmpty(p)) {
            p.sendMessage("");
            p.sendMessage(" §e§lCHAIN");
            p.sendMessage(" §cVocê precisa esvaziar seu inventário para entrar.");
            p.sendMessage("");
            return;
        }
        if (locs.get("chain") == null) {
            p.sendMessage("§cA arena chain está indisponível no momento!");
            return;
        }
        p.teleport(locs.get("chain"));
        p.setAllowFlight(false);
    }

    public boolean containsP(Player p) {
        if (onChain.contains(p)) return true;
        return false;
    }

    public void enteredChain(Player p) {
        if (onChain.contains(p)) return;
        p.getActivePotionEffects().clear();
        p.setGameMode(GameMode.SURVIVAL);
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());
        p.setHealth(20);
        giveKit(p);
        for (Player player : onChain) {
            player.sendMessage("§e§lCHAIN ➜ §e" + p.getName() + " §fentrou na chain.");
        }
        onChain.add(p);
        p.sendMessage("");
        p.sendMessage(" §e§lCHAIN");
        p.sendMessage(" §fVocê entrou na arena chain.");
        p.sendMessage(" §fPara sair, use §n/chain§f.");
        p.sendMessage("");
    }

    public void removeAllChain() {
        for (Player p : onChain) {
            exitChain(p);
            p.sendMessage("§cA arena chain foi reinicializada, todos os jogadores foram removidos.");
        }
    }

    public void exitChain(Player p) {
        onChain.remove(p);
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.AIR));
        p.getInventory().setChestplate(new ItemStack(Material.AIR));
        p.getInventory().setLeggings(new ItemStack(Material.AIR));
        p.getInventory().setBoots(new ItemStack(Material.AIR));
        p.setHealth(20);
        for (PotionEffect pf : p.getActivePotionEffects()) {
            if (pf != null) p.removePotionEffect(pf.getType());
        }
        p.teleport(locs.get("chainsaida"));
        p.sendMessage("");
        p.sendMessage(" §e§lCHAIN");
        p.sendMessage(" §cVocê saiu da chain.");
        p.sendMessage("");
    }

    public void giveKit(Player p) {
        for (ItemStack item : itens) {
            if (item.getType() == Material.CHAINMAIL_HELMET) {
                p.getInventory().setHelmet(item);
            } else if (item.getType() == Material.CHAINMAIL_CHESTPLATE) {
                p.getInventory().setChestplate(item);
            } else if (item.getType() == Material.CHAINMAIL_LEGGINGS) {
                p.getInventory().setLeggings(item);
            } else if (item.getType() == Material.CHAINMAIL_BOOTS) {
                p.getInventory().setBoots(item);
            } else {
                p.getInventory().addItem(item);
            }
        }
    }

    public void killEvent(Player p) {
        p.setHealth(20);
        p.setFoodLevel(20);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
        p.getInventory().addItem(new CommonItem(new ItemStack(Material.GOLDEN_APPLE, 2))
                .setLore(Arrays.asList("", "§eItem da arena chain")).getItem());
        p.getInventory().addItem(new CommonItem(new ItemStack(Material.ARROW, 3))
                .setLore(Arrays.asList("", "§eItem da arena chain")).getItem());
    }

    public void removeChain(Player p) {
        onChain.remove(p);
    }

    public List<Player> getOnChain() {
        return onChain;
    }

    public boolean isEmpty(Player p) {
        Inventory inv = p.getInventory();
        for(ItemStack it : inv.getContents())
        {
            if(it != null) return false;
        }
        // VERIFICAR ARMADURA
        if (p.getInventory().getHelmet() != null || p.getInventory().getChestplate() != null || p.getInventory().getLeggings() != null || p.getInventory().getBoots() != null) {
            return false;
        }
        return true;
    }

}
