package yejunho10.magicplugin;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class Event implements Listener {
    private Logger log = Bukkit.getLogger();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0} broke {2} in {1}, {4} {5} {6} using {3}",
                new Object[]{p.getName(), e.getBlock().getLocation().getWorld().getName(), e.getBlock().getType().toString(), p.getInventory().getItemInMainHand().getType().toString(), (int) e.getBlock().getLocation().getX(), (int) e.getBlock().getLocation().getY(), (int) e.getBlock().getLocation().getZ()});
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0} place {2} in {1}, {3} {4} {5}",
                new Object[]{p.getName(), e.getBlock().getLocation().getWorld().getName(), e.getBlock().getType().toString(), (int) e.getBlock().getLocation().getX(), (int) e.getBlock().getLocation().getY(), (int) e.getBlock().getLocation().getZ()});
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0} pickuped {1} in {2}, {3} {4} {5}, amount: {6}",
                new Object[]{p.getName(), e.getItem().getItemStack().getType().toString(), e.getItem().getLocation().getWorld().getName(), (int) e.getItem().getLocation().getX(), (int) e.getItem().getLocation().getY(), (int) e.getItem().getLocation().getZ(), e.getItem().getItemStack().getAmount()});
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        log.log(Level.INFO, "{0} dropped {1} in {2}, {3} {4} {5}, amount: {6}",
                new Object[]{p.getName(), e.getItemDrop().getItemStack().getType().toString(), e.getItemDrop().getLocation().getWorld().getName(), (int) e.getItemDrop().getLocation().getX(), (int) e.getItemDrop().getLocation().getY(), (int) e.getItemDrop().getLocation().getZ(), e.getItemDrop().getItemStack().getAmount()});
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        log.log(Level.INFO, "{0} hit {1} in {2}, {3} {4} {5}, {6} Damage",
                new Object[]{e.getDamager().getName(), e.getEntity().getName(), e.getEntity().getLocation().getWorld().getName(), (int) e.getEntity().getLocation().getX(), (int) e.getEntity().getLocation().getY(), (int) e.getEntity().getLocation().getZ(), e.getDamage()});
    }

    @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent e) {
        log.log(Level.INFO, "{0} damagged by {1} in {2}, {3} {4} {5}, {6} Damage",
                new Object[]{e.getEntity().getName(), e.getDamager().getBlockData().getMaterial(), e.getEntity().getLocation().getWorld().getName(), (int) e.getEntity().getLocation().getX(), (int) e.getEntity().getLocation().getY(), (int) e.getEntity().getLocation().getZ(), e.getDamage()});
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        log.log(Level.INFO, "{0} joined in {1}, {2} {3} {4}",
                new Object[]{e.getPlayer().getName(), e.getPlayer().getLocation().getWorld().getName(), (int) e.getPlayer().getLocation().getX(), (int) e.getPlayer().getLocation().getY(), (int) e.getPlayer().getLocation().getZ()});
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        log.log(Level.INFO, "{0} quit in {1}, {2} {3} {4}",
                new Object[]{e.getPlayer().getName(), e.getPlayer().getLocation().getWorld().getName(), (int) e.getPlayer().getLocation().getX(), (int) e.getPlayer().getLocation().getY(), (int) e.getPlayer().getLocation().getZ()});
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        log.log(Level.INFO, "{0} died in {1}, {2} {3} {4}",
                new Object[]{e.getEntity().getName(), e.getEntity().getLocation().getWorld().getName(), (int) e.getEntity().getLocation().getX(), (int) e.getEntity().getLocation().getY(), (int) e.getEntity().getLocation().getZ()});
    }

    @EventHandler
    public void onBlockBurned(BlockBurnEvent e) {
        log.log(Level.INFO, "{0} block burned in {1}, {2} {3} {4}",
                new Object[]{e.getBlock().getType().toString(), e.getBlock().getLocation().getWorld().getName(), (int) e.getBlock().getLocation().getX(), (int) e.getBlock().getLocation().getY(), (int) e.getBlock().getLocation().getZ()});
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        ArrayList<String> Enchant = new ArrayList<String>();
        e.getEnchantsToAdd().forEach((enchantment, integer) -> {
            Enchant.add(EnchantmentWrapper.getByName(enchantment.getName()).getName());
        });

        log.log(Level.INFO, "{0} enchanted {1} in {2}, {3} {4} {5}, enchant: {6}",
                new Object[]{e.getEnchanter().getName(), e.getItem().getType().toString(), e.getEnchanter().getLocation().getWorld().getName(), (int) e.getEnchanter().getLocation().getX(), (int) e.getEnchanter().getLocation().getY(), (int) e.getEnchanter().getLocation().getZ(), Enchant});
    }
}