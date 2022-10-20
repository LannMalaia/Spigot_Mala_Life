package malalife.FishFesta;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import malalife.FishFesta.Inventory.Festa_Instant_Chest;
import malalife.FishFesta.Inventory.Festa_Menu_Inv;
import malalife.FishFesta.Inventory.Festa_Shop_Inv;
import malalife.Fishing.Fishing;
import malalife.main.InstantFireworks;
import malalife.main.Mala_Life;
import net.md_5.bungee.api.ChatColor;

public class FishFesta_Event implements Listener
{
	@EventHandler
	public void Player_Login(PlayerLoginEvent event)
	{
		if(event.getResult() != Result.ALLOWED)
			return;
		
		// 임시 창고 체크
		int size = Festa_Instant_Chest.Instance.Has_Instant_Chest(event.getPlayer());
		if(size > 0) // 임시 창고 내 아이템이 0개 이상?
		{
			Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, () -> {
				String str = new String(FF_Msg_TBL.Get_Msg("Notice_Instant_Chest", true));
				str = str.replace("{count}", size + "");
				event.getPlayer().sendMessage(str); // 알림 주기
			}, 40);
			
		}
	}

	@EventHandler
	public void Player_Login(PlayerQuitEvent event)
	{
		FishFesta_Main.Instance.Leave_Festa(event.getPlayer());
	}
	
	@EventHandler
	public void Player_Inv_Click(InventoryClickEvent event)
	{
		// 개최 인벤토리가 맞나 확인
		if(event.getClickedInventory() != Festa_Menu_Inv.Instance.Get_Inventory())
			return;
		// 보상 창에 아이템 놓는 경우
		if(Festa_Menu_Inv.Instance.Inventory_ItemGet_or_Drop((Player)event.getWhoClicked(), event.getSlot(), event.getCursor()))
		{
		
		}
		else // GUI 조작을 하는 경우
		{
			// 인벤토리 클릭했을 때 이벤트 발현
			switch(event.getAction())
			{
			case PICKUP_ALL:
			case PICKUP_HALF:
			case PICKUP_ONE:
			case PICKUP_SOME:
				Festa_Menu_Inv.Instance.Inventory_Click((Player)event.getWhoClicked(), event.getSlot());
				break;
			default:
				break;
			}
			event.setCancelled(true); // 인벤토리 이벤트는 취소됨
		}
	}

	@EventHandler
	public void Player_Inv_Click_2(InventoryClickEvent event)
	{
		// 상점 인벤토리가 맞나 확인
		Player player = (Player)event.getWhoClicked();
		
		if(event.getClickedInventory() != Festa_Shop_Inv.Instance.Get_Player_Shop(player))
			return;
		
		if(!Festa_Shop_Inv.Instance.Inventory_ItemGet_or_Drop(event.getSlot(), event.getCurrentItem()))
		{
			// 인벤토리 클릭했을 때 이벤트 발현
			switch(event.getAction())
			{
			case PICKUP_ALL:
			case PICKUP_HALF:
			case PICKUP_ONE:
			case PICKUP_SOME:
				Festa_Shop_Inv.Instance.Inventory_Click((Player)event.getWhoClicked(), event.getSlot());
				break;
			default:
				break;
			}
			event.setCancelled(true); // 인벤토리 이벤트는 취소됨
		}
		
	}
	
	@EventHandler
	public void Player_Inv_Close(InventoryCloseEvent event)
	{
		// 개최 인벤토리가 맞나 확인
		if(event.getInventory() != Festa_Menu_Inv.Instance.Get_Inventory())
			return;

		// 보상 창에 아이템 놓기
		Festa_Menu_Inv.Instance.Inventory_Close(event.getPlayer());
	}
	
	@EventHandler
	public void Player_Inv_Close_2(InventoryCloseEvent event)
	{
		// 상점 인벤토리가 맞나 확인
		if(event.getInventory() != Festa_Shop_Inv.Instance.Get_Player_Shop((Player)event.getPlayer()))
			return;

		// 안 판 아이템 돌려주기
		Festa_Shop_Inv.Instance.Inventory_Close(event.getPlayer());
	}
	  
	@EventHandler
	public void Player_Fishing(PlayerFishEvent event)
	{
		if(!FishFesta_Main.Instance.m_Players.contains(event.getPlayer()))
			return;

		if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
			return;

		if(event.isCancelled())
			return;
		
		Entity e = event.getCaught();
		if(!(e instanceof Item))
			return;
		
		// 아이템 설정
		FF_Fish_Data data = FF_Fish_List.Instance.Get_Random_Fish(FishFesta_Main.Instance.m_Addon_PinchChance);
		double size = data.Calculate_Size();

		boolean silver = FishFesta_Main.Instance.m_Addon_SilverFish ? Math.random() <= 0.4 : false;
		boolean golden = false;
		if(silver)
			golden = FishFesta_Main.Instance.m_Addon_GoldenFish ? Math.random() <= 0.5 : false;
		ItemStack item = data.Make_Item(size, silver, golden);

		// 아이템 엔티티 생성
		Item i = event.getPlayer().getWorld().dropItem(e.getLocation(), item);
		
		// 모드 - 운수 좋은 날
		if(data.m_Is_Fish && FishFesta_Main.Instance.m_Addon_LuckyCatch)
		{
			if (Math.random() * 100.0D < Fishing.Get_Lucky_Fish_Chance(event.getPlayer()) || (Math.random() < 0.5 && event.getPlayer().hasPermission("*")))
			{
				// 럭키 캐치 성공
				int amount = Fishing.Get_Lucky_Fishing_Count(event.getPlayer());
				new InstantFireworks(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.ORANGE).flicker(true).build(), event.getPlayer().getEyeLocation());
				event.getPlayer().sendMessage(ChatColor.AQUA + "[ 럭키 캐치 발동 ]\n물고기를 " + amount + "마리 낚았다!");
				while (amount > 64)
				{
					ItemStack i3 = item.clone();
					i3.setAmount(64);
					event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
					amount -= 64;
				}
				ItemStack i3 = item.clone();
				i3.setAmount(amount);
				event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().clone().add(0.0D, 1.0D, 0.0D), i3);
			}
		}
		Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, new Runnable()
		{
			public void run()
			{
				i.setVelocity(event.getCaught().getVelocity());
			}
		}, 1L);
		
		e.remove();
		FishFesta_Main.Instance.Send_Catch_Message(event.getPlayer(), item, data, size);
	}
}
