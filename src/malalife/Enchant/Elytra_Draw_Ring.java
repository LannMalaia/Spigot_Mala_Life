package malalife.Enchant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import malalife.main.Mala_Life;

class Elytra_Draw_Ring
  implements Runnable
{
  Player player;
  
  public Elytra_Draw_Ring(Player _player)
  {
    player = _player;
  }
  
  public void run()
  {
    Vector x1 = new Vector(-player.getLocation().getDirection().normalize().getZ(), 0.0D, player.getLocation().getDirection().normalize().getX()).normalize();
    Vector x2 = player.getLocation().getDirection().normalize().crossProduct(x1).normalize();
    for (int i = 0; i < 45; i++)
    {
      Location temp2 = player.getLocation().add(x1.clone().multiply(6.0D * Math.sin(i / 45.0D * 3.141592653589793D * 2.0D))).add(x2.clone().multiply(6.0D * Math.cos(i / 45.0D * 3.141592653589793D * 2.0D)));
      temp2.setDirection(new Vector(0, 0, 0));
      temp2.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, temp2, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    }
    if (player.getMetadata("High_Speed_Ring").size() != 0) {
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 10L);
    }
  }
}
