package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

import laylia_core.main.CooldownManager;

public class Firework_Arrow_Enchant
  extends Enchant_Data
{
  public Firework_Arrow_Enchant()
  {
    super("malalife_firework_arrow", "", "¡×7ÆøÁ× È­»ì", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("ÆøÁ×À» ÅÍÆ®¸®´Â È­»ìÀ» ¹ß»çÇÕ´Ï´Ù.");
    return desc;
  }
  
  @EventHandler
  public void Player_Interacting(PlayerInteractEvent event)
  {
    if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      Start_Firework(event.getPlayer());
    }
  }
  
  @EventHandler
  public void Arrow_Hit(ProjectileHitEvent event)
  {
    if ((event.getEntity() instanceof Arrow)) {
      if (event.getEntity().hasMetadata("Firework_Arrow_Bomb")) {
        event.getEntity().remove();
      }
    }
  }
  
  public void Start_Firework(Player player)
  {
    ItemStack item = player.getInventory().getItemInMainHand().clone();
    if ((item != null) && (!CooldownManager.Has_Cooldown(player, "Firework_Cooldown")))
    {
      if (Enchant_Check(item))
      {
        new CooldownManager(player, "Firework_Cooldown", 1);
        
        /*if (item.getAmount() > 1)*/ player.getInventory().getItemInMainHand().setAmount(item.getAmount() - 1);
        //else player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        
        Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
        arrow.setMetadata("Firework_Arrow_Bomb", new FixedMetadataValue(Mala_Life.plugin, true));
        for (String lore2 : item.getItemMeta().getLore())
        {
          if (lore2.contains("º¢²É ¹öÀü")) {
            arrow.setMetadata("Firework_Arrow_Color", new FixedMetadataValue(Mala_Life.plugin, "sakura"));
          }
          if (lore2.contains("¹«Áö°³ °°Àº ¹öÀü")) {
            arrow.setMetadata("Firework_Arrow_Color", new FixedMetadataValue(Mala_Life.plugin, "rainbow"));
          }
        }
        arrow.setCritical(false);
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Random_Fire(arrow), 2L);
        if (player.getInventory().getItemInOffHand() != null)
        {
          if (player.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET)
          {
            ItemStack sub = player.getInventory().getItemInOffHand().clone();
            int duration = Get_Duration(sub) * 15;
            List<FireworkEffect> effects = Get_Effect(sub);
            if (effects != null)
            {
              Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Bomb(effects, arrow), duration);
              
              player.getInventory().getItemInOffHand().setAmount(sub.getAmount() - 1);
              
              return;
            }
          }
        }
        
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Remove(arrow), 70L);
        return;
      }
    }
  }
  
  public static List<FireworkEffect> Get_Effect(ItemStack item)
  {
    if (item != null) {
      if (item.hasItemMeta()) {
        if ((item.getItemMeta() instanceof FireworkMeta))
        {
          FireworkMeta meta = (FireworkMeta)item.getItemMeta();
          if (meta.hasEffects()) {
            return meta.getEffects();
          }
        }
      }
    }
    return null;
  }
  
  public static int Get_Duration(ItemStack item)
  {
    if (item != null) {
      if (item.hasItemMeta()) {
        if ((item.getItemMeta() instanceof FireworkMeta))
        {
          FireworkMeta meta = (FireworkMeta)item.getItemMeta();
          return meta.getPower();
        }
      }
    }
    return 1;
  }
}
