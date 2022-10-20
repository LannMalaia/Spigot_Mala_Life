package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Elytra_Start_Jump_Enchant
  extends Enchant_Data
{
  public Elytra_Start_Jump_Enchant()
  {
    super("malalife_start_jump", "§f[§9§l겉날개§f] ", "§7점프", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("몸을 웅크려 점프 게이지를 모읍니다.\n");
    desc.add("웅크린 자세를 풀면 높게 뛰어오릅니다.");
    return desc;
  }
  
  @EventHandler
  public void Player_Sneak(PlayerToggleSneakEvent event)
  {
    if ((event.isSneaking()) && (event.getPlayer().isOnGround()))
    {
      Player player = event.getPlayer();
      if (!player.hasMetadata("static_charging"))
      {
        if (Enchant_Check(player.getInventory().getChestplate()))
        {
          player.setMetadata("static_charging", new FixedMetadataValue(Mala_Life.plugin, Double.valueOf(0.0D)));
          Bukkit.getScheduler().runTask(Mala_Life.plugin, new Flying_Charge(player, Get_Enchant_Level(player.getInventory().getChestplate())));
        }
      }
      else {
        player.removeMetadata("static_charging", Mala_Life.plugin);
      }
    }
  }
}
