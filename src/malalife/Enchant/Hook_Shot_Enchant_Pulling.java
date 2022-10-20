package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import malalife.main.Mala_Life;

class Hook_Shot_Enchant_Pulling
  implements Runnable
{
  LivingEntity entity;
  List<Player> particle_players = new ArrayList<Player>();
  Location end_loc;
  Location start_loc;
  int started = 0;
  
  public Hook_Shot_Enchant_Pulling(LivingEntity _entity, Location _end_loc, Location _start_loc)
  {
    entity = _entity;
    end_loc = _end_loc.clone();
    start_loc = _start_loc.clone();
    started = ((int)start_loc.distance(end_loc));
    end_loc.setDirection(end_loc.clone().subtract(start_loc).toVector().normalize());
  }
  
  public void run()
  {
    if (started >= 0)
    {
      start_loc.getWorld().playSound(start_loc, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1.0F, 2.0F);
      if (started > 0)
      {
    	  //entity.setInvulnerable(true);
    	  entity.setVelocity(start_loc.getDirection().clone().multiply(-1.2D));
      }
      else
      {
    	  entity.setInvulnerable(false);
    	  entity.setVelocity(start_loc.getDirection().clone().multiply(0));
      }
      Draw_Particle();
      started -= 1;
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
    }
  }
  
  void Draw_Particle()
  {
    for (int i = 0; i < started; i++) {
      for (int j = 0; j < 5; j++)
      {
        Location temp = start_loc.clone();
        temp.add(end_loc.getDirection().clone().multiply(i)).add(end_loc.getDirection().clone().multiply(j / 5.0D)).add(0.0D, 1.0D, 0.0D);
        
        start_loc.getWorld().spawnParticle(Particle.CRIT, temp, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      }
    }
  }
}
