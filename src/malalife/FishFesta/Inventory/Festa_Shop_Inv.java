package malalife.FishFesta.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import malalife.FishFesta.FF_Msg_TBL;
import malalife.FishFesta.FishFesta_Main;
import malalife.main.Mala_Life;

public class Festa_Shop_Inv
{
	public static Festa_Shop_Inv Instance;
	
	FishFesta_Main _ffm;
	public HashMap<Player, Inventory> player_shop_inv = new HashMap<Player, Inventory>();
	
	public Festa_Shop_Inv()
	{
		Instance = this;
	}
	
	public Inventory Get_Player_Shop(Player _player)
	{
		Inventory inv = player_shop_inv.get(_player);
		if(inv == null)
		{
			inv = Bukkit.createInventory(null, 54, FF_Msg_TBL.Get_Msg("Inv_SellShopMenu", false) + _player.getName());

			// 초기화
			for(int i = 45; i < 54; i++)
				inv.setItem(i, new ItemStack(Material.RED_STAINED_GLASS_PANE));
			inv.setItem(48, Button_Refresh()); // 새로고침 버튼
			inv.setItem(50, Button_Sell(inv)); // 팔기 버튼
			
			player_shop_inv.put(_player, inv);
		}
		return inv;
	}
	
	public void Refresh_Inventory(Player _player)
	{
		Inventory inv = Get_Player_Shop(_player);

		for(int i = 45; i < 54; i++)
			inv.setItem(i, new ItemStack(Material.RED_STAINED_GLASS_PANE)); // 그냥 유리
		inv.setItem(48, Button_Refresh()); // 새로고침 버튼
		inv.setItem(50, Button_Sell(inv)); // 팔기 버튼
	}

	public boolean Inventory_ItemGet_or_Drop(int _clicked_slot, ItemStack _item)
	{
		if(_item == null)
			return true;
		
		// 슬롯 체크
		if(_clicked_slot >= 0 && _clicked_slot < 45)
			return true;
		
		return false;
	}
	
	public boolean Inventory_Click(Player _player, int _clicked_slot)
	{
		Inventory inv = Get_Player_Shop(_player);
		ItemStack selected_item = inv.getItem(_clicked_slot);
		if(selected_item == null)
			return false;

		// 새로고침
		if(selected_item.isSimilar(Button_Refresh()))
		{
			Refresh_Inventory(_player);
			return true;
		}
		// 다 팔기
		if(selected_item.isSimilar(Button_Sell(inv)))
		{
			if(Get_Sell_Money(inv, false) == 0.0d)
				return false;
			
			Process_Sell(_player);
			Refresh_Inventory(_player);
			return true;
		}
		return false;
	}
	
	public void Inventory_Close(HumanEntity _player)
	{
		Inventory inv = Get_Player_Shop((Player)_player);
		for(int i = 0; i < 45; i++)
		{
			if(inv.getItem(i) != null)
			{
				_player.getInventory().addItem(inv.getItem(i));
				inv.setItem(i, new ItemStack(Material.AIR));
			}
		}
	}
	
	public void Process_Sell(Player _player)
	{
		Inventory inv = Get_Player_Shop(_player);
		
		double reward = Get_Sell_Money(inv, true); // 가격 얻으면서 밀어버리기 (해당되는 물건만)
		Mala_Life.econ.depositPlayer(_player, reward);
		
		String msg = FF_Msg_TBL.Get_Msg("Notice_Selled", false);
		_player.sendMessage(msg.replace("{money}", reward + ""));
	}

	public long Get_Sell_Money(Inventory _inv, boolean _cleaning)
	{
		double sum = 0;
		ItemStack[] items = _inv.getContents();
		for(int i = 0; i < items.length; i++)
		{
			ItemStack item = items[i];
			if(item == null)
				continue;
			if(!item.hasItemMeta())
				continue;
			ItemMeta meta = item.getItemMeta();
			if(!meta.hasLore())
				continue;
			if(meta.getLore().size() < 2)
				continue;
			String str = meta.getLore().get(1);
			if (!str.contains("§e가격: "))
				continue;
			str = str.substring("§e가격: ".length());
			try
			{
				sum += Double.parseDouble(str) * item.getAmount();
				if(_cleaning)
					_inv.setItem(i, null);
			}
			catch(NumberFormatException e)
			{
			}
		}
		return (long)sum;
	}
	public double Get_Sell_Money(Inventory _inv) { return Get_Sell_Money(_inv, false); }
	
	public ItemStack Button_Sell(Inventory _inv)
	{
		
		ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
		ItemMeta meta = item.getItemMeta();

		// 이름 설정
		String name = FF_Msg_TBL.Get_Msg("Item_Name_Sell", false);
		meta.setDisplayName(name);

		// 설명 설정
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Sell", false));
		String desc = FF_Msg_TBL.Get_Msg("Item_Desc_Sell_2", false);
		desc = desc.replace("{money}", "" + Get_Sell_Money(_inv));
		lore.add(desc);
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack Button_Refresh()
	{
		ItemStack item = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = item.getItemMeta();

		// 이름 설정
		String name = FF_Msg_TBL.Get_Msg("Item_Name_Sell_Refresh", false);
		meta.setDisplayName(name);

		// 설명 설정
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Sell_Refresh", false));
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	
}
