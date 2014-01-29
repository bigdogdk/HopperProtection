package com.gmail.bigdogdak.deadboltHopperProtection;

import com.daemitus.deadbolt.Deadbolt;
import com.daemitus.deadbolt.Deadbolted;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeadboltHopperProtection
  extends JavaPlugin
  implements Listener
{
  public static final Logger log = Logger.getLogger("Minecraft");
  Block block;
  DoubleChest dc;
  
  public void load(Deadbolt plugin) {}
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onInvintoryChange(InventoryMoveItemEvent event)
  {
    if (event.getSource().getSize() == 27)
    {
      InventoryHolder holder = event.getSource().getHolder();
      if ((holder != null) && ((holder instanceof Chest)))
      {
        Chest chest = (Chest)holder;
        Deadbolted d = Deadbolt.get(chest.getBlock());
        if (d.isProtected()) {
          event.setCancelled(true);
        }
      }
    }
    else if (event.getSource().getSize() == 54)
    {
      InventoryHolder holder = event.getSource().getHolder();
      if ((holder != null) && ((holder instanceof DoubleChest)))
      {
        DoubleChest chest = (DoubleChest)holder;
        Deadbolted d = Deadbolt.get(chest.getWorld().getBlockAt(chest.getLocation()));
        if (d.isProtected()) {
          event.setCancelled(true);
        }
      }
    }
  }
  
  public void onEnable()
  {
    Bukkit.getPluginManager().registerEvents(this, this);
    log.info("DHP Enabled!");
  }
  
  public void onDisable()
  {
    log.info("DHP Disabled!");
  }
}
