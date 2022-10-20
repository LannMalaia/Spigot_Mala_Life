package malalife.Adventure;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Adventure_Event implements Listener
{
  @EventHandler
  public void Player_Dropping(PlayerDropItemEvent event)
  {
    if ((event.getPlayer().isSneaking()) && (event.getPlayer().hasPermission("*")))
    {
      if (event.getItemDrop().getItemStack().getType().toString().contains("SWORD"))
      {
        Throw_Weapon.throwing(event.getPlayer(), event.getItemDrop().getItemStack(), "sword");
        event.setCancelled(true);
      }
      if (event.getItemDrop().getItemStack().getType().toString().contains("AXE"))
      {
        Throw_Weapon.throwing(event.getPlayer(), event.getItemDrop().getItemStack(), "axe");
        event.setCancelled(true);
      }
      if (event.getItemDrop().getItemStack().getType().toString().contains("BOW"))
      {
        Throw_Weapon.throwing(event.getPlayer(), event.getItemDrop().getItemStack(), "bow");
        event.setCancelled(true);
      }
      if (event.getItemDrop().getItemStack().getType().toString().contains("SHIELD"))
      {
        Throw_Weapon.throwing(event.getPlayer(), event.getItemDrop().getItemStack(), "bow");
        event.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void Player_Interacting(PlayerInteractEvent event)
  {
    if (event.getAction() == Action.LEFT_CLICK_AIR)
    {
      Entity vehicle = event.getPlayer().getVehicle();
      if ((vehicle != null) && ((vehicle instanceof Horse))) {
        Horse_Booster.Left_Click((Horse)vehicle, event.getPlayer());
      }
    }
  }
}
