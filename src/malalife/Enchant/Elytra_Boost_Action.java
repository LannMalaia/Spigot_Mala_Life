package malalife.Enchant;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

class Elytra_Boost_Action
  implements Runnable
{
  private static Player player_data;
  double power;
  
  public Elytra_Boost_Action(Player player, double _power)
  {
    player_data = player;
    power = _power;
  }
  
  public void run()
  {
    double dir_x = player_data.getLocation().getDirection().getX();
    double dir_y = player_data.getLocation().getDirection().getY();
    double dir_z = player_data.getLocation().getDirection().getZ();
    
    Vector x1 = new Vector(-player_data.getLocation().getDirection().normalize().getZ(), 0.0D, player_data.getLocation().getDirection().normalize().getX()).normalize();
    Vector x2 = player_data.getLocation().getDirection().normalize().crossProduct(x1).normalize();
    for (int i = 0; i < 90; i++)
    {
      Location temp2 = player_data.getLocation().add(x1.clone().multiply(6.0D * Math.sin(i / 90.0D * 3.141592653589793D * 2.0D))).add(x2.clone().multiply(6.0D * Math.cos(i / 90.0D * 3.141592653589793D * 2.0D)));
      temp2.setDirection(new Vector(0, 0, 0));
      temp2.getWorld().spawnParticle(Particle.END_ROD, temp2, 1, 1.0D, 1.0D, 0.0D, 0.0D);
    }
    player_data.setVelocity(new Vector(dir_x * power, dir_y * power, dir_z * power));
  }
}
