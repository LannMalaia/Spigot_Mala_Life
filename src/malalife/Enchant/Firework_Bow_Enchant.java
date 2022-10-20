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
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

import laylia_core.main.CooldownManager;

public class Firework_Bow_Enchant
  extends Enchant_Data
{
  public Firework_Bow_Enchant()
  {
    super("malalife_firework_bow", "", "¡×7ÆøÁ× È°", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("ÆøÁ×À» ÅÍÆ®¸®´Â È­»ìÀ» ¹ß»çÇÕ´Ï´Ù.");
    return desc;
  }
  
  @EventHandler
  public void Player_Shoot_Arrow(ProjectileLaunchEvent event)
  {
    if ((event.getEntity() instanceof Arrow))
    {
      Arrow arrow = (Arrow)event.getEntity();
      if ((arrow.getShooter() instanceof Player)) {
        Start_Firework_Arrow((Player)arrow.getShooter(), arrow);
      }
    }
  }
  
  public void Start_Firework(Player player)
  {
    ItemStack item = player.getInventory().getItemInMainHand();
    if ((item != null) && (!CooldownManager.Has_Cooldown(player, "Firework_Cooldown"))) {
      if (Enchant_Check(item))
      {
        new CooldownManager(player, "Firework_Cooldown", 1);
        if (item.getAmount() > 1) {
          item.setAmount(item.getAmount() - 1);
        } else {
          player.getInventory().remove(item);
        }
        Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
        arrow.setMetadata("Firework_Arrow_Bomb", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
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
        if (player.getInventory().getItemInOffHand() != null) {
          if (player.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET)
          {
            ItemStack sub = player.getInventory().getItemInOffHand();
            int duration = Get_Duration(sub) * 15;
            if (sub.getAmount() > 1) {
              sub.setAmount(sub.getAmount() - 1);
            } else {
              player.getInventory().remove(sub);
            }
            List<FireworkEffect> effects = Get_Effect(player.getInventory().getItemInOffHand());
            if (effects != null)
            {
              Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Bomb(effects, arrow), duration);
              return;
            }
          }
        }
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Remove(arrow), 50L);
        return;
      }
    }
  }
  
  public void Start_Firework_Arrow(Player player, Arrow arrow)
  {
    ItemStack item = player.getInventory().getItemInMainHand();
    if (Enchant_Check(item))
    {
      arrow.setMetadata("Firework_Arrow_Bomb", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
      arrow.setCritical(false);
      Bukkit.getScheduler().runTask(Mala_Life.plugin, new Arrow_Trail(arrow));
      if (player.getInventory().getItemInOffHand() != null) {
        if (player.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET)
        {
          List<FireworkEffect> effects = Get_Effect(player.getInventory().getItemInOffHand());
          if (effects != null)
          {
            Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Bomb(effects, arrow), 15L);
            return;
          }
        }
      }
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Arrow_Random_Fire(arrow), 2L);
      return;
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
