package malalife.Adventure;

import malalife.main.Mala_Life;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.EulerAngle;

public class Throw_Weapon
{
  public static void throwing(Player player, ItemStack item, String type)
  {
    Location loc = player.getLocation().clone();
    if (type.contains("sword"))
    {
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw())) * -0.2D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw())) * -0.2D);
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
    }
    if (type.contains("axe"))
    {
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw())) * 0.365D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw())) * 0.365D);
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
    }
    if (type.contains("bow"))
    {
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw())) * 0.365D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw())) * 0.365D);
      loc.setX(loc.getX() + Math.cos(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
      loc.setZ(loc.getZ() + Math.sin(Math.toRadians(loc.getYaw() + 90.0F)) * 0.5D);
    }
    ArmorStand as = (ArmorStand)player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    as.setVisible(false);
    EulerAngle angle = new EulerAngle(Math.toRadians(-10.0D), Math.toRadians(loc.getPitch()), Math.toRadians(-90.0D));
    if (type.contains("axe")) {
      angle = new EulerAngle(Math.toRadians(-90.0D), 0.0D, 0.0D);
    }
    if (type.contains("bow")) {
      angle = new EulerAngle(Math.toRadians(-90.0D), Math.toRadians(loc.getPitch()), Math.toRadians(90.0D));
    }
    as.setArms(true);
    as.setRightArmPose(angle);
    as.setItemInHand(item);
    as.getLocation().setYaw(loc.getYaw());
    as.setGravity(false);
    as.setInvulnerable(true);
    as.setMetadata("Laylia_Throw_Weapon", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
    loc.getWorld().playSound(loc, Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
    Bukkit.getScheduler().runTask(Mala_Life.plugin, new Weapon_Throwing(player, as, loc, type.contains("axe"), type.contains("bow")));
  }
}
