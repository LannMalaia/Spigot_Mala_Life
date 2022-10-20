package malalife.Enchant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import malalife.main.Mala_Life;

class Static_Flying
  implements Runnable
{
  Player player;
  int power = 0;
  ItemStack chest;
  
  public Static_Flying(Player p, int _power)
  {
    player = p;
    power = _power;
    chest = p.getInventory().getChestplate();
  }
  
  public void run()
  {
    if ((player.isGliding()) && (chest.isSimilar(player.getInventory().getChestplate())))
    {
      if (power > 0)
      {
        if (!player.isSneaking()) {
          player.setVelocity(player.getEyeLocation().getDirection().clone().multiply(0.7D + 0.15D * power));
        }
        Spawn_Particle();
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
      }
      else
      {
        player.removeMetadata("static_flying", Mala_Life.plugin);
      }
    }
    else {
      player.removeMetadata("static_flying", Mala_Life.plugin);
    }
  }
  
void Spawn_Particle()
  {
      Location loc = player.getLocation();
	  player.getWorld().spawnParticle(Particle.END_ROD, loc, 1, 0.0D, 0.0D, 0.0D, 0.0D);
	  /*
      ItemStack i = player.getInventory().getItemInMainHand();
      if(i != null && i.getType() == Material.INK_SACK)
      {
          switch(i.getData().getData())
          {
          case 0:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0.0D, 0.0D, 0.0D, 1);
        	  break;
          case 1:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 1.0D, 0.0D, 0.0D, 1);
        	  break;
          case 2:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0.0D, 1.0D, 0.0D, 1);
        	  break;
          case 3:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0.6D, 0.45D, 0.12D, 1);
        	  break;
          case 4:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.0D, 0.0D, 1.0D, 0.0D);
        	  break;
          case 5:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 1.0D, 0.0D, 1.0D, 0.0D);
        	  break;
          case 6:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.0D, 1.0D, 1.0D, 0.0D);
        	  break;
          case 7:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.8D, 0.8D, 0.8D, 0.0D);
        	  break;
          case 8:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.4D, 0.4D, 0.4D, 0.0D);
        	  break;
          case 9:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.9D, 0.1D, 0.7D, 0.0D);
        	  break;
          case 10:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.3D, 1.0D, 0.3D, 0.0D);
        	  break;
          case 11:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 1.0D, 1.0D, 0.0D, 0.0D);
        	  break;
          case 12:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.3D, 0.3D, 1.0D, 0.0D);
        	  break;
          case 13:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.8D, 0.0D, 0.8D, 0.0D);
        	  break;
          case 14:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.8D, 0.6D, 0.0D, 0.0D);
        	  break;
          case 15:
        	  player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 1.0D, 1.0D, 1.0D, 0.0D);
        	  break;
          }
      }
      else
    	  player.getWorld().spawnParticle(Particle.END_ROD, loc, 1, 0.0D, 0.0D, 0.0D, 0.0D);
  */
  }
}
