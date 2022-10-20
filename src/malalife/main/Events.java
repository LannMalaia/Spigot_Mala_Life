package malalife.main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import malalife.FishFesta.FishFesta_Main;
import malalife.Fishing.Fish_Data;
import malalife.Fishing.Fishing;
import malalife.Fishing.Fishing_Effect;
import malalife.Fishing.Legacy_Fish_Data;

public class Events implements Listener
{
	@EventHandler
	public void Player_Fishing(final PlayerFishEvent event)
	{
		if(FishFesta_Main.Instance.m_Players.contains(event.getPlayer())) // ���� �����ڴ� �� ���� ������ �� ��
			return;

		if(event.isCancelled())
			return;
		
		if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH)
		{
			Entity e = event.getCaught();
			if ((e instanceof Item))
			{
				final Item i = event.getPlayer().getWorld().dropItem(e.getLocation(), ((Item)e).getItemStack());
        
				int item_chance = Fishing.Get_Treasure_Chance(event.getPlayer());
				if (Math.random() * 100.0D > item_chance)
				{
					ItemStack i2 = Fishing_Effect.Get_Fish_Data(event.getPlayer());

					// ���, �ֻ�� ����
					i2 = Fishing_Effect.Check_Good_Fish(event.getPlayer(), i2, 0);
          
					// ��Ű ĳġ ����
					if (Math.random() * 100.0D < Fishing.Get_Lucky_Fish_Chance(event.getPlayer()))
					{
						if(Math.random() * 100.0D < 10.0D)
						{
							// ���� ��Ű ĳġ ����
							int amount = Fishing.Get_Lucky_Fishing_Count(event.getPlayer()) * 5;

							// ������ ����̳� �ֻ�� ����⸦ �ְ� �Ѵ�
							i2 = Fishing_Effect.Check_Good_Fish(event.getPlayer(), i2, 100);
							// new InstantFireworks(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.LIME).flicker(true).build(), event.getPlayer().getEyeLocation());
							event.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "[ ���� ��Ű ĳġ �ߵ� ]\n����⸦ " + amount + "���� ���Ҵ�!!!");
							while (amount > 64)
							{
								ItemStack i3 = i2.clone();
								i3.setAmount(64);
								event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
								amount -= 64;
							}
							ItemStack i3 = i2.clone();
							i3.setAmount(amount);
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
						}
						else
						{
							// ��Ű ĳġ ����
							int amount = Fishing.Get_Lucky_Fishing_Count(event.getPlayer());
							// new InstantFireworks(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.ORANGE).flicker(true).build(), event.getPlayer().getEyeLocation());
							event.getPlayer().sendMessage(ChatColor.AQUA + "[ ��Ű ĳġ �ߵ� ]\n����⸦ " + amount + "���� ���Ҵ�!");
							while (amount > 64)
							{
								ItemStack i3 = i2.clone();
								i3.setAmount(64);
								event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
								amount -= 64;
							}
							ItemStack i3 = i2.clone();
							i3.setAmount(amount);
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
						}
						return;
					}
					i.setItemStack(i2);
				}
				else
				{
					i.setItemStack(Fishing_Effect.Get_Treasure(event.getPlayer()));
				}
				Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Runnable()
				{
					public void run()
					{
						i.setVelocity(event.getCaught().getVelocity());
					}
				}, 1L);
        
				e.remove();
			}
		}
	}
  
  @EventHandler
  public void Fish_Cook(FurnaceSmeltEvent event)
  {
	  // �뱸�� ������
	  if (event.getSource().getType() == Material.COD)
	  {
		  if (event.getSource().isSimilar(Legacy_Fish_Data.Get_High_Fish()))
			  event.setResult(Legacy_Fish_Data.Get_High_Fish_Cooked());
		  if (event.getSource().isSimilar(Legacy_Fish_Data.Get_Highest_Fish()))
			  event.setResult(Legacy_Fish_Data.Get_Highest_Fish_Cooked());
	  }
	  if (event.getSource().getType() == Material.COD)
	  {
		  if (event.getSource().isSimilar(Fish_Data.Get_High_Fish()))
			  event.setResult(Fish_Data.Get_High_Fish_Cooked());
		  if (event.getSource().isSimilar(Fish_Data.Get_Highest_Fish()))
			  event.setResult(Fish_Data.Get_Highest_Fish_Cooked());
	  }
	
	  // ��� ������
	  if (event.getSource().getType() == Material.SALMON)
	  {
	      if (event.getSource().isSimilar(Legacy_Fish_Data.Get_High_Salmon()))
	          event.setResult(Legacy_Fish_Data.Get_High_Salmon_Cooked());
	      if (event.getSource().isSimilar(Legacy_Fish_Data.Get_Highest_Salmon()))
	    	  event.setResult(Legacy_Fish_Data.Get_Highest_Salmon_Cooked());
	  }
	  if (event.getSource().getType() == Material.SALMON)
	  {
	      if (event.getSource().isSimilar(Fish_Data.Get_High_Salmon()))
	          event.setResult(Fish_Data.Get_High_Salmon_Cooked());
	      if (event.getSource().isSimilar(Fish_Data.Get_Highest_Salmon()))
	    	  event.setResult(Fish_Data.Get_Highest_Salmon_Cooked());
	  }
  }
  
  @EventHandler
  public void Fish_Eat(PlayerItemConsumeEvent event)
  {
	  
	    switch(event.getItem().getType())
	    {
	    // ������ ���� ��� ���� ���� Ǫ�� ����Ʈ�� �� �ִ�
	    case COD:
	    case COOKED_COD:
	    case SALMON:
	    case COOKED_SALMON:
	    case PUFFERFISH:
	    case TROPICAL_FISH:
	    	Fishing_Effect.Get_Fish_Eat_Effect(event.getPlayer(), event.getItem());
	    	break;
    	default:
	    	break;
	    }
	    Fishing_Effect.Food_Bonus(event.getPlayer(), event.getItem());
	    Fishing_Effect.Effect_Bonus(event.getPlayer(), event.getItem());
	    
  }
}
