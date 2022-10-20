package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Elytra_Boost_Enchant
  extends Enchant_Data
{
  public Elytra_Boost_Enchant()
  {
    super("malalife_elytra_boost", "§f[§9§l겉날개§f] ", "§7활강", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("비행 중 달리기 키를 눌러 비행속도를 순간적으로 높힙니다.");
    return desc;
  }
  
  @EventHandler
  public void mala_Player_Sprint(PlayerToggleSprintEvent event)
  {
    if (!event.getPlayer().isSprinting()) {
      if (event.getPlayer().isGliding()) {
        Elytra_Boost_Judge(event.getPlayer());
      }
    }
  }
  
  public void Elytra_Boost_Judge(Player player)
  {
    ItemStack item = player.getInventory().getChestplate();
    if (Enchant_Check(item))
    {
      if (player.getMetadata("Super_Filght_Cooldown").size() != 0)
      {
        player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "재사용 대기시간입니다.");
        return;
      }
      Elytra_Boost_Action(player, Get_Enchant_Level(item));
    }
  }
  
  public void Elytra_Boost_Action(Player player, int power)
  {
    player.setMetadata("Super_Filght_Cooldown", new FixedMetadataValue(Mala_Life.plugin, Integer.valueOf(1)));
    for (int i = 0; i < 20; i++) {
      Mala_Life.plugin.getServer().getScheduler().runTaskLater(Mala_Life.plugin, new Elytra_Boost_Action(player, 0.5D + power * 2.0D), 3 * i);
    }
    Mala_Life.plugin.getServer().getScheduler().runTaskLater(Mala_Life.plugin, new Elytra_Boost_Cooldown(player), 900L);
  }
}
