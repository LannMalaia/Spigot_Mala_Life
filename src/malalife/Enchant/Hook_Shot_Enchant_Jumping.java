package malalife.Enchant;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.util.Vector;

import malalife.main.Mala_Life;

class Hook_Shot_Enchant_Jumping
  implements Runnable
{
  Player player;
  Location end_loc;
  Location start_loc;
  int length = 0;
  int started = 0;
  
  public Hook_Shot_Enchant_Jumping(Player _player, Location _end_loc, Location _start_loc)
  {
    player = _player;
    end_loc = _end_loc.clone();
    start_loc = _start_loc.clone();
    length = ((int)start_loc.distance(end_loc));
    end_loc.setDirection(end_loc.clone().subtract(start_loc).toVector().normalize());
  }
  
  public void run()
  {
    if (length > started)
    {
      started += 1;
      if (length > started)
      {
        player.setVelocity(end_loc.getDirection().clone().add(new Vector(0.0D, 0.05D, 0.0D)).multiply(1.2D));
      }
      else
      {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        Permission perm = (Permission)rsp.getProvider();
        perm.playerRemove(player, "nocheatplus.checks.moving");
        player.setVelocity(end_loc.getDirection().clone().multiply(0.5D).add(new Vector(0.0D, 0.4D, 0.0D)));
      }
      Draw_Particle();
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
    }
    else
    {
      player.removeMetadata("malalife.Hooking", Mala_Life.plugin);
    }
  }
  
  void Draw_Particle()
  {
    for (int i = started; i < length; i++) {
      for (int j = 0; j < 5; j++)
      {
        Location temp = start_loc.clone();
        temp.add(end_loc.getDirection().clone().multiply(i)).add(end_loc.getDirection().clone().multiply(j / 5.0D)).add(0.0D, 1.0D, 0.0D);
        
        start_loc.getWorld().spawnParticle(Particle.CRIT, temp, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      }
    }
  }
}
