package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_core.main.BossbarElement;
import malalife.main.Mala_Life;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

class Flying_Charge
  implements Runnable
{
  Player player;
  BossbarElement be;
  List<Player> near_players;
  int power = 0;
  
  public Flying_Charge(Player p, int _power)
  {
    near_players = new ArrayList<Player>();
    player = p;
    power = _power;
    be = Mala_Life.core_api.bossbar_manager.createBossbar(p.getName() + "fly_charge", "---", BarColor.GREEN, BarStyle.SOLID, null, 0.0D, 1.0D);
    be.Change_Color(BarColor.WHITE);
    be.Change_Style(BarStyle.SOLID);
    be.Change_Title("§l점프 게이지");
    be.bar.setProgress(0.0D);
    be.bar.removeFlag(BarFlag.CREATE_FOG);
    be.bar.removeFlag(BarFlag.DARKEN_SKY);
    be.bar.removeFlag(BarFlag.PLAY_BOSS_MUSIC);
    be.Add_Player(p);
    for (Entity e : player.getNearbyEntities(20.0D, 20.0D, 20.0D)) {
      if ((e instanceof Player)) {
        near_players.add((Player)e);
      }
    }
  }
  
  public void run()
  {
    double charged_power = 0.0D;
    if (player.hasMetadata("static_charging")) {
      charged_power = ((MetadataValue)player.getMetadata("static_charging").get(0)).asDouble();
    }
    if (power > 0)
    {
      if (player.isSneaking())
      {
        if (charged_power < 1.0D)
        {
          charged_power += 0.06D;
          if (charged_power >= 1.0D) {
            charged_power = 0.99D;
          }
          player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, (float)(charged_power * 2.0D));
          player.setMetadata("static_charging", new FixedMetadataValue(Mala_Life.plugin, Double.valueOf(charged_power)));
        }
        be.bar.setProgress(charged_power);
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
      }
      else
      {
        Vector vec = new Vector(0.0D, 1.0D, 0.0D);
        Location loc = player.getLocation();
        for (double theta = 0.0D; theta < 6.283185307179586D; theta += 0.19634954084936207D)
        {
          Location new_loc = loc.clone().add(Math.cos(theta) * 2.0D, 0.0D, Math.sin(theta) * 2.0D);
          player.getWorld().spawnParticle(Particle.SMOKE_LARGE, new_loc, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
        player.setVelocity(vec.multiply(0.7D + power * 0.3D + charged_power * 2.0D));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1.0F, 1.2F);
        be.Remove_Bar();
        player.removeMetadata("static_charging", Mala_Life.plugin);
      }
    }
    else
    {
      be.Remove_Bar();
      player.removeMetadata("static_charging", Mala_Life.plugin);
    }
  }
}
