package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import laylia_enchant_data.Enchant_Data;
import malalife.main.Mala_Life;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Hook_Shot_Enchant
  extends Enchant_Data
{
  public Hook_Shot_Enchant()
  {
    super("malalife_hookshot", "§f[§9§l기능§f] ", "§7훅샷", Get_Enchant_Description());
  }
  
  public static List<String> Get_Enchant_Description()
  {
    List<String> desc = new ArrayList<String>();
    desc.add("화살을 쏴, 그 자리로 이동합니다.\n");
    desc.add("너무 멀리 있으면 줄이 끊겨 갈 수 없습니다.\n");
    desc.add("몬스터에게 쏘면 끌어올 수 있지만, 무거운 경우 끌려가게 됩니다.");
    return desc;
  }
  
  @EventHandler
  public void Player_Interacting(PlayerInteractEvent event)
  {
    if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
      Shoot_Hook_Shot(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand());
    }
  }
  
  @EventHandler
  public void Arrow_Hit_entity(EntityDamageByEntityEvent event)
  {
    if (((event.getDamager() instanceof Arrow)) && (event.getEntity().hasMetadata("malalife.hookshot.arrow"))) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void Arrow_Hit(ProjectileHitEvent event)
  {
    if (!event.getEntity().hasMetadata("malalife.hookshot.arrow")) {
      return;
    }
    if ((((MetadataValue)event.getEntity().getMetadata("malalife.hookshot.arrow").get(0)).asBoolean()) && (event.getEntityType() == EntityType.ARROW) && ((event.getEntity().getShooter() instanceof Player)))
    {
      Player player = (Player)event.getEntity().getShooter();
      
      player.removeMetadata("malalife.Hooking", Mala_Life.plugin);
      Location p_loc = null;
      if ((event.getHitEntity() != null) && ((event.getHitEntity() instanceof LivingEntity)))
      {
	      p_loc = event.getEntity().getLocation();
	      switch (Get_Jump_Pull_to_Entity((LivingEntity)event.getHitEntity()))
	      {
	      case -1: 
	        player.sendMessage("§c끌어올 수 없는 대상이다.");
	        break;
	      case 0: 
	        p_loc.getWorld().playSound(p_loc, Sound.ENTITY_PLAYER_HURT, 2.0F, 1.0F);
	        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Hook_Shot_Enchant_Jumping(player, event.getHitEntity().getLocation(), player.getLocation()), 2L);
	        
	        break;
	      case 1: 
	        p_loc.getWorld().playSound(p_loc, Sound.ENTITY_PLAYER_HURT, 2.0F, 1.0F);
	        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Hook_Shot_Enchant_Pulling((LivingEntity)event.getHitEntity(), event.getHitEntity().getLocation(), player.getLocation()), 2L);
	      default: 
	        break;
	      }

      }
      else
      {
	  	p_loc = event.getEntity().getLocation();
		RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
		Permission perm = (Permission)rsp.getProvider();
		perm.playerAdd(player, "nocheatplus.checks.moving");
		p_loc.getWorld().playSound(p_loc, Sound.BLOCK_ANVIL_PLACE, 2.0F, 2.0F);
		Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Hook_Shot_Enchant_Jumping(player, event.getHitBlock().getLocation().clone().add(p_loc.getDirection()), player.getLocation()), 2L);

      }
      event.getEntity().remove();
    }
  }
  
  @EventHandler
  public void Player_Move(PlayerMoveEvent event)
  {
    if (event.getPlayer().hasMetadata("malalife.Hooking")) {
      event.setCancelled(false);
    }
  }
  
	@EventHandler
	public void Player_Join(PlayerJoinEvent event)
	{
		if (event.getPlayer().isInvulnerable())
			event.getPlayer().setInvulnerable(false);
	}
  
  int Get_Jump_Pull_to_Entity(LivingEntity entity)
  {
    if (entity.hasMetadata("NPC")) {
      return -1;
    }
    if ((entity instanceof EnderCrystal)) {
      return -1;
    }
    if (entity.isInvulnerable()) {
      return -1;
    }
    if ((entity instanceof Enderman)) {
      return 0;
    }
    if ((entity instanceof Wither)) {
      return 0;
    }
    if ((entity instanceof Giant)) {
      return 0;
    }
    if ((entity instanceof EnderDragon)) {
      return 0;
    }
    if ((entity instanceof Ghast)) {
      return 0;
    }
    if ((entity instanceof HumanEntity)) {
      return 1;
    }
    if ((entity instanceof IronGolem)) {
      return 0;
    }
    if ((entity instanceof PolarBear)) {
      return 0;
    }
    if ((entity instanceof Shulker)) {
      return 0;
    }
    if ((entity instanceof Cow)) {
      return 0;
    }
    if ((entity instanceof Horse)) {
      return 0;
    }
    if ((entity instanceof Sheep)) {
      return 0;
    }
    if ((entity instanceof Animals)) {
      return -1;
    }
    if ((entity instanceof Enderman)) {
      return -1;
    }
    return 1;
  }
  
  public void Shoot_Hook_Shot(Player player, ItemStack item)
  {
    int power = 0;
    if (Enchant_Check(item)) {
      power = Get_Enchant_Level(item);
    }
    if (power > 0)
    {
    	if (player.hasMetadata("malalife.HookShot_Cooldown"))
    	{
    		player.sendMessage("§c훅샷 쿨타임입니다.");
    	}
    	else
    	{
    		player.setMetadata("malalife.HookShot_Cooldown", new FixedMetadataValue(Mala_Life.plugin, true));
    		Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Runnable()
    				{
    				@Override
    				public void run()
    				{
    					player.removeMetadata("malalife.HookShot_Cooldown", Mala_Life.plugin);
    				}
    				}, 40);
    		Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
    		arrow.setMetadata("malalife.hookshot.arrow", new FixedMetadataValue(Mala_Life.plugin, Boolean.valueOf(true)));
    		arrow.setCritical(false);
    		arrow.setVelocity(arrow.getVelocity().clone().multiply(power * 0.1D));
    		Bukkit.getScheduler().runTask(Mala_Life.plugin, new Hook_Shot_Enchant_Arrow_Particle(arrow));
    	}
    }
  }
}
