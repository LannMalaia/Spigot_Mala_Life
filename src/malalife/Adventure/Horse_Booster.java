package malalife.Adventure;

import malalife.main.Mala_Life;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class Horse_Booster
{
  public HashMap<String, Horse_Booster_Data> data = new HashMap<String, Horse_Booster_Data>();
  
  public static void Left_Click(Horse horse, Player player)
  {
    if (Mala_Life.horse_booster.data.get(player.getUniqueId().toString()) != null)
    {
      Horse_Booster_Data hbd = Mala_Life.horse_booster.data.get(player.getUniqueId().toString());
      hbd.left_clicked = true;
    }
    else
    {
    	Mala_Life.horse_booster.data.put(player.getUniqueId().toString(), new Horse_Booster_Data(player, horse));
      Horse_Booster_Data hbd2 = Mala_Life.horse_booster.data.get(player.getUniqueId().toString());
      
      Bukkit.getScheduler().runTask(Mala_Life.plugin, new Bossbar_Check(hbd2));
    }
  }
}
