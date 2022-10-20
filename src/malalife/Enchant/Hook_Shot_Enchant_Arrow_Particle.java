package malalife.Enchant;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;

import malalife.main.Mala_Life;

class Hook_Shot_Enchant_Arrow_Particle
  implements Runnable
{
  Arrow arrow;
  
  public Hook_Shot_Enchant_Arrow_Particle(Arrow _arrow)
  {
    arrow = _arrow;
  }
  
  public void run()
  {
    if ((arrow == null) || (arrow.isDead())) {
      return;
    }
    arrow.getWorld().spawnParticle(Particle.END_ROD, arrow.getLocation(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
    if (arrow.getLocation().getY() < -1.0D) {
      arrow.remove();
    }
    Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
  }
}
