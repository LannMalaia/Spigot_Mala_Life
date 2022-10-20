package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Elytra_Static_Fly_Enchant
  extends Enchant_Data
{
  public Elytra_Static_Fly_Enchant()
  {
    super("malalife_static_fly", "��f[��9��l�ѳ�����f] ", "��7���� ����", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("����� ���� �ӵ��� �����մϴ�.\n");
    desc.add("���� ��ũ���� ���� ������ ���� �� �ֽ��ϴ�.");
    return desc;
  }
  
  @EventHandler
  public void Player_Move(PlayerMoveEvent event)
  {
    Player player = event.getPlayer();
    if (!player.hasMetadata("static_flying"))
    {
      if (player.isGliding()) {
        if (Enchant_Check(player.getInventory().getChestplate()))
        {
          int power = Get_Enchant_Level(player.getInventory().getChestplate());
          player.setMetadata("static_flying", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
          Bukkit.getScheduler().runTask(Mala_Life.plugin, new Static_Flying(player, power));
        }
      }
    }
    else if (!player.isGliding()) {
      player.removeMetadata("static_flying", Mala_Life.plugin);
    }
  }
}
