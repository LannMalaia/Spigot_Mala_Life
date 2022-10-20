package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import malalife.main.Mala_Life;

class Arrow_Trail
  implements Runnable
{
  Arrow arrow;
  int counter = 100;
  List<Player> players = new ArrayList<Player>();
  
  public Arrow_Trail(Arrow _arrow)
  {
    arrow = _arrow;
    for (Entity entity : arrow.getNearbyEntities(30.0D, 30.0D, 30.0D)) {
      if ((entity instanceof Player)) {
        players.add((Player)entity);
      }
    }
  }
  
  public void run()
  {
    if (arrow == null) {
      return;
    }
    if (arrow.isDead()) {
      return;
    }
    if (counter < 0) {
      return;
    }
    counter -= 1;
    arrow.getWorld().spawnParticle(Particle.SMOKE_LARGE, arrow.getLocation(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
    
    Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
  }
}
