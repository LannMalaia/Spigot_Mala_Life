package malalife.Adventure;

import malalife.main.Mala_Life;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Horse;

class Bossbar_Check
  implements Runnable
{
  public Horse_Booster_Data hbd;
  
  public Bossbar_Check(Horse_Booster_Data _hbd)
  {
    hbd = _hbd;
  }
  
  public void run()
  {
    if ((hbd.player.getVehicle() != null) && ((hbd.player.getVehicle() instanceof Horse)))
    {
      if (hbd.be.bar.getProgress() > 0.0D)
      {
        if (hbd.left_clicked)
        {
          hbd.left_clicked = false;
          if (hbd.be.bar.getProgress() < 0.186D)
          {
            if (hbd.be.bar.getProgress() < 0.07D)
            {
              hbd.horse.getWorld().playSound(hbd.horse.getEyeLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1.0F, 1.0F);
              hbd.Boost(2);
            }
            else
            {
              hbd.horse.getWorld().playSound(hbd.horse.getEyeLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0F, 1.0F);
              hbd.Boost(1);
            }
          }
          else if (hbd.chain >= 3)
          {
            hbd.Boost(-2);
            Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
          }
          else
          {
            reset();
          }
        }
        else
        {
          hbd.be.bar.setProgress(Math.max(hbd.be.bar.getProgress() - 0.015D - hbd.chain / 300.0D, 0.0D));
          if (hbd.be.bar.getProgress() < 0.07D) {
            hbd.be.Change_Color(BarColor.YELLOW);
          } else if (hbd.be.bar.getProgress() < 0.186D) {
            hbd.be.Change_Color(BarColor.RED);
          } else {
            hbd.be.Change_Color(BarColor.WHITE);
          }
          hbd.be.Update();
        }
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
      }
      else if (hbd.chain >= 2)
      {
        hbd.Boost(-1);
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
      }
      else
      {
        reset();
      }
    }
    else
    {
      reset();
      return;
    }
  }
  
  void reset()
  {
    hbd.player.sendMessage("§c말 부스터가 끝났다요!");
    hbd.Reset();
    Mala_Life.horse_booster.data.remove(hbd.player.getUniqueId().toString());
  }
}
