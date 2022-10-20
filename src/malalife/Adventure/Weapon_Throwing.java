package malalife.Adventure;

import malalife.main.Mala_Life;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

class Weapon_Throwing
  implements Runnable
{
  Player player;
  ArmorStand as;
  Location loc;
  Vector dir;
  boolean is_axe = false;
  boolean is_bow = false;
  int time = 0;
  int correct_angle = -90;
  double speed = 0.9D;
  boolean turn_to_player = false;
  
  public Weapon_Throwing(Player _p, ArmorStand _a, Location _d, boolean axe, boolean bow)
  {
    player = _p;
    as = _a;
    loc = _d;
    dir = loc.getDirection();
    is_axe = axe;
    is_bow = bow;
    if (!is_bow) {
      speed = 0.4D;
    }
  }
  
  public void run()
  {
    boolean collided = false;
    if (is_axe)
    {
      EulerAngle angle = new EulerAngle(Math.toRadians(correct_angle), 0.0D, 0.0D);
      as.setRightArmPose(angle);
    }
    else if (is_bow)
    {
      EulerAngle angle = new EulerAngle(Math.toRadians(correct_angle), Math.toRadians(-loc.getPitch()), Math.toRadians(90.0D));
      as.setRightArmPose(angle);
      if (turn_to_player)
      {
        dir = new Vector(player.getLocation().getX() - loc.getX(), player.getLocation().getY() - loc.getY(), player.getLocation().getZ() - loc.getZ()).normalize();
        speed += 0.035D;
      }
      else
      {
        speed -= 0.023D;
      }
    }
    else
    {
      loc.getWorld().spawnParticle(Particle.END_ROD, loc.getX() + Math.cos(Math.toRadians(loc.getYaw())) * 0.2D, loc.getY() + 1.4D, loc.getZ() + Math.sin(Math.toRadians(loc.getYaw())) * 0.2D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    }
    loc.add(dir.getX() * speed, dir.getY() * speed, dir.getZ() * speed);
    as.teleport(loc, TeleportCause.PLUGIN);
    for (Entity e : loc.getWorld().getNearbyEntities(loc, 0.5D, 1.0D, 0.5D))
    {
      if ((turn_to_player) && (e == player))
      {
        collided = true;
        break;
      }
      if ((e != player) && (e != as) && ((e instanceof LivingEntity)))
      {
        LivingEntity le = (LivingEntity)e;
        le.damage(4.0D * (is_axe ? 3 : 0), as);
        if (is_bow) {
          break;
        }
        collided = true;
        break;
      }
    }
    time += 1;
    if (time > 40) {
      turn_to_player = true;
    }
    if ((time < 100) && (!collided))
    {
      correct_angle += 40;
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
    }
    else
    {
      as.remove();
    }
  }
}
