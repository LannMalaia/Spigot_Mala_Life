package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class Flare_Bow_Enchant
  extends Enchant_Data
{
  public Flare_Bow_Enchant()
  {
    super("malalife_flare_bow", "", "§7플레어 활", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("플레어를 터트리는 화살을 발사합니다.");
    return desc;
  }
  
  @EventHandler
  public void Player_Shoot_Arrow(ProjectileLaunchEvent event)
  {
    if ((event.getEntity() instanceof Arrow))
    {
      Arrow arrow = (Arrow)event.getEntity();
      if ((arrow.getShooter() instanceof Player)) {
        Start_Flare_Arrow((Player)arrow.getShooter(), arrow);
      }
    }
  }
  
  @EventHandler(ignoreCancelled=false)
  public void Entity_Hit_Flare(EntityDamageByEntityEvent event)
  {
    if (event.getEntity().hasMetadata("Firework_Arrow_Bomb")) {
      event.setCancelled(true);
    }
    if ((!event.isCancelled()) && (event.getDamager().hasMetadata("Shooted_Flare")))
    {
      Vector vec = event.getDamager().getLocation().clone().toVector();
      vec.subtract(event.getEntity().getLocation().toVector()).normalize();
      event.getEntity().setVelocity(vec.add(new Vector(0.0D, -0.1D, 0.0D)).multiply(-5.0D));
      if ((event.getEntity().getFireTicks() == 0) && (!event.getEntity().isDead())) {
        event.getEntity().setFireTicks(140);
      }
    }
  }
  
  public void Start_Flare_Arrow(Player player, Arrow arrow)
  {
    ItemStack item = player.getInventory().getItemInMainHand();
    if (Enchant_Check(item))
    {
      Vector dir = player.getEyeLocation().getDirection().clone();
      
      player.getEyeLocation().getWorld().playSound(player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2.0D)), Sound.ENTITY_GENERIC_EXPLODE, 2.0F, 1.0F);
      for (int i = 0; i < 5; i++)
      {
        double range = 0.8D;
        Vector temp = new Vector(dir.getX() - range / 2.0D + Math.random() * range, dir.getY() - range / 2.0D + Math.random() * range / 2.0D, dir.getZ() - range / 2.0D + Math.random() * range).normalize();
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Flare_Shot(player.getEyeLocation().clone(), temp), 0L);
      }
      int angle = 120;
      
      player.setMetadata("Shooted_Flare", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
      List<Entity> entities = player.getWorld().getEntities();
      for (int i = 0; i < entities.size(); i++)
      {
        Entity temp = (Entity)entities.get(i);
        Location loc = temp.getLocation().clone();
        loc.subtract(player.getLocation().clone());
        if ((Math.sqrt(loc.getX() * loc.getX() + loc.getZ() * loc.getZ()) < 15.0D) && (loc.getY() < 5.0D) && (loc.getY() > -4.0D)) {
          if (!player.isFlying()) {
            if ((temp instanceof LivingEntity))
            {
              double vec = player.getLocation().getDirection().normalize().dot(loc.toVector().normalize());
              if (vec > Math.cos(Math.toRadians(angle / 2)))
              {
                int level = 1;
                int damage = level / 250;
                LivingEntity temp2 = (LivingEntity)temp;
                if (!temp2.isDead()) {
                  temp2.damage(damage, player);
                }
              }
            }
          }
        }
      }
      player.removeMetadata("Shooted_Flare", Mala_Life.plugin);
      
      arrow.remove();
      return;
    }
  }
}
