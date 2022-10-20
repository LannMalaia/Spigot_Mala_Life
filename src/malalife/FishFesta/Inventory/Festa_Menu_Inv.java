package malalife.FishFesta.Inventory;

import malalife.FishFesta.FF_Msg_TBL;
import malalife.FishFesta.FishFesta_Main;
import malalife.main.Mala_Life;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Festa_Menu_Inv
{
	public static Festa_Menu_Inv Instance;
	
	Inventory inv;
	FishFesta_Main _ffm;
	
	public Festa_Menu_Inv()
	{
		Instance = this;
		
	}
	
	public boolean Can_I_See_Inventory()
	{
		return inv.getViewers().size() == 0;
	}
	public Inventory Get_Inventory()
	{
		if(inv != null)
			return inv;
		
		inv = Bukkit.createInventory(null, 54, FF_Msg_TBL.Get_Msg("Inv_Festamenu", false));
		_ffm = FishFesta_Main.Instance;
		Refresh_Inventory();
		return inv;
	}
	public void Refresh_Inventory()
	{
		// 1~4���� ��,��,��, ������ ���
		inv.setItem(0, Desc_First_Prize());
		inv.setItem(9, Desc_Second_Prize());
		inv.setItem(18, Desc_Third_Prize());
		
		boolean is_admin = false;
		for(HumanEntity e : inv.getViewers())
		{
			if(e.hasPermission("*"))
			{
				is_admin = true;
				break;
			}
		}
		if(is_admin) // ���� �ƴϸ� �������� ����
			inv.setItem(27, Desc_Participation_Prize());
		
		// 5~6���� ���� ���
		for(int i = 36; i < 54; i++) // ���������� ���� ���Ÿ� ���ϰ� �ϱ�
			inv.setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE)); 
		
		inv.setItem(36, Instance.Button_Time_Up(15));
		inv.setItem(37, Instance.Button_Time_Up(1));
		// inv.setItem(38, Instance.Button_Time_Up(1));
		
		// inv.setItem(37, Instance.Desc_Time());

		inv.setItem(45, Instance.Button_Time_Down(15));
		inv.setItem(46, Instance.Button_Time_Down(1));
		// inv.setItem(47, Instance.Button_Time_Down(1));
		
		// ��� ��ư �߰�
		//inv.setItem(39, Addon_Effect(_ffm.m_Addon_Effect, _ffm.m_Addon_Effect_mult));
		//inv.setItem(40, Addon_Chaos(_ffm.m_Addon_Chaos, _ffm.m_Addon_Chaos_mult));
		inv.setItem(39, Addon_FallbackTime(_ffm.m_Addon_FallbackTime, _ffm.m_Addon_FallbackTime_mult));
		inv.setItem(40, Addon_FallbackTimeHard(_ffm.m_Addon_FallbackTimeHard, _ffm.m_Addon_FallbackTimeHard_mult));
		//inv.setItem(41, Addon_LuckyCatch(_ffm.m_Addon_LuckyCatch, _ffm.m_Addon_LuckyCatch_mult));
		inv.setItem(42, Addon_PinchChance(_ffm.m_Addon_PinchChance, _ffm.m_Addon_PinchChance_mult));
		inv.setItem(48, Addon_SmallRank(_ffm.m_Addon_SmallRank, _ffm.m_Addon_SmallRank_mult));
		inv.setItem(49, Addon_SilverFish(_ffm.m_Addon_SilverFish, _ffm.m_Addon_SilverFish_mult));
		inv.setItem(50, Addon_GoldenFish(_ffm.m_Addon_GoldenFish, _ffm.m_Addon_GoldenFish_mult));
		
		inv.setItem(53, Button_Start());
	}

	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote �κ��丮�� �������� ������ ��
	 * Ư�� ������ ���� �� ������ true, �ƴϸ� false
	 * @return
	 */
	public boolean Inventory_ItemGet_or_Drop(Player _player, int _clicked_slot, ItemStack _item)
	{
		if(_item == null)
			return true;
		
		/*
		// ���� ������ �� ����
		switch(_item.getType())
		{
		case WRITABLE_BOOK:
		case WRITTEN_BOOK:
			return false;
		default:
			break;
		}
		*/
		
		// ���� üũ
		if((_clicked_slot >= 1 && _clicked_slot <= 8) ||
			(_clicked_slot >= 10 && _clicked_slot <= 17) ||
			(_clicked_slot >= 19 && _clicked_slot <= 26))
			return true;
		if((_clicked_slot >= 28 && _clicked_slot <= 35) && _player.hasPermission("*"))
			return true;
		
		return false;
	}
	public void Inventory_Click(Player _player, int _clicked_slot)
	{
		ItemStack selected_item = inv.getItem(_clicked_slot);
		if(selected_item == null)
			return;
		
		// �ð� �ø���
		if(selected_item.isSimilar(Button_Time_Up(1)))
			_ffm.m_Setted_Minutes += 1;
		if(selected_item.isSimilar(Button_Time_Up(15)))
			_ffm.m_Setted_Minutes += 15;
		if(selected_item.isSimilar(Button_Time_Up(100)))
			_ffm.m_Setted_Minutes += 100;

		if(_ffm.m_Addon_FallbackTime)
			_ffm.m_Setted_Minutes = Math.min(10, _ffm.m_Setted_Minutes);
		if(_ffm.m_Addon_FallbackTimeHard)
			_ffm.m_Setted_Minutes = Math.min(5, _ffm.m_Setted_Minutes);
		
		_ffm.m_Setted_Minutes = Math.min(_ffm.m_Max_Minutes, _ffm.m_Setted_Minutes);

		// �ð� ������
		if(selected_item.isSimilar(Button_Time_Down(1)))
			_ffm.m_Setted_Minutes -= 1;
		if(selected_item.isSimilar(Button_Time_Down(15)))
			_ffm.m_Setted_Minutes -= 15;
		if(selected_item.isSimilar(Button_Time_Down(100)))
			_ffm.m_Setted_Minutes -= 100;
		_ffm.m_Setted_Minutes = Math.max(0, _ffm.m_Setted_Minutes);

		// ��� ����
		if(selected_item.isSimilar(Addon_Effect(_ffm.m_Addon_Effect, _ffm.m_Addon_Effect_mult)))
			_ffm.m_Addon_Effect = !_ffm.m_Addon_Effect;
		if(selected_item.isSimilar(Addon_Chaos(_ffm.m_Addon_Chaos, _ffm.m_Addon_Chaos_mult)))
			_ffm.m_Addon_Chaos = !_ffm.m_Addon_Chaos;
		if(selected_item.isSimilar(Addon_LuckyCatch(_ffm.m_Addon_LuckyCatch, _ffm.m_Addon_LuckyCatch_mult)))
			_ffm.m_Addon_LuckyCatch = !_ffm.m_Addon_LuckyCatch;
		if(selected_item.isSimilar(Addon_FallbackTime(_ffm.m_Addon_FallbackTime, _ffm.m_Addon_FallbackTime_mult)))
		{
			if(!_ffm.m_Addon_FallbackTimeHard)
			{
				_ffm.m_Addon_FallbackTime = !_ffm.m_Addon_FallbackTime;
				_ffm.m_Setted_Minutes = Math.min(10, _ffm.m_Setted_Minutes);
			}
		}
		if(selected_item.isSimilar(Addon_FallbackTimeHard(_ffm.m_Addon_FallbackTimeHard, _ffm.m_Addon_FallbackTimeHard_mult)))
		{
			_ffm.m_Addon_FallbackTime = true;
			_ffm.m_Addon_FallbackTimeHard = !_ffm.m_Addon_FallbackTimeHard;
			_ffm.m_Setted_Minutes = Math.min(5, _ffm.m_Setted_Minutes);
		}
		if(selected_item.isSimilar(Addon_SilverFish(_ffm.m_Addon_SilverFish, _ffm.m_Addon_SilverFish_mult)))
		{
			if(!_ffm.m_Addon_GoldenFish)
				_ffm.m_Addon_SilverFish = !_ffm.m_Addon_SilverFish;
		}
		if(selected_item.isSimilar(Addon_GoldenFish(_ffm.m_Addon_GoldenFish, _ffm.m_Addon_GoldenFish_mult)))
		{
			_ffm.m_Addon_SilverFish = true;
			_ffm.m_Addon_GoldenFish = !_ffm.m_Addon_GoldenFish;
		}
		if(selected_item.isSimilar(Addon_SmallRank(_ffm.m_Addon_SmallRank, _ffm.m_Addon_SmallRank_mult)))
			_ffm.m_Addon_SmallRank = !_ffm.m_Addon_SmallRank;
		if(selected_item.isSimilar(Addon_PinchChance(_ffm.m_Addon_PinchChance, _ffm.m_Addon_PinchChance_mult)))
			_ffm.m_Addon_PinchChance = !_ffm.m_Addon_PinchChance;
		
		// ���� ����
		if(selected_item.isSimilar(Button_Start()))
		{
			// �ð� üũ
			if (_ffm.m_Setted_Minutes < 5)
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Time_Too_Short", false));
				inv.getViewers().get(0).closeInventory();
				return;
			}
			
			// �÷��̾� �� üũ
			long money = Get_Budget_Money();
			if (!Mala_Life.econ.has(_player, money))
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Not_Enough_Money", false));
				inv.getViewers().get(0).closeInventory();
				return;
			}
			
			if (Mala_Life.econ.withdrawPlayer(_player, money).type != ResponseType.SUCCESS)
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_EconomyProblem", false));
				inv.getViewers().get(0).closeInventory();
				return;
			}
			
			FishFesta_Main.Instance.Start_Festa(_player);
			inv.getViewers().get(0).closeInventory();
		}
		Refresh_Inventory();
	}
	
	public void Inventory_Close(HumanEntity _player)
	{
		for(int i = 1; i <= 8; i++)
		{
			if(inv.getItem(i) != null)
			{
				_player.getInventory().addItem(inv.getItem(i));
				inv.setItem(i, new ItemStack(Material.AIR));
			}
			if(inv.getItem(i + 9) != null)
			{
				_player.getInventory().addItem(inv.getItem(i + 9));
				inv.setItem(i + 9, new ItemStack(Material.AIR));
			}
			if(inv.getItem(i + 18) != null)
			{
				_player.getInventory().addItem(inv.getItem(i + 18));
				inv.setItem(i + 18, new ItemStack(Material.AIR));
			}
			if(inv.getItem(i + 27) != null)
			{
				_player.getInventory().addItem(inv.getItem(i + 18));
				inv.setItem(i + 27, new ItemStack(Material.AIR));
			}
		}
	}
	
	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote ���� ���
	 * @return
	 */
	public long Get_Budget_Money()
	{ 
		long money = _ffm.m_Base_Money;
		money += _ffm.m_Timer_Money * _ffm.m_Setted_Minutes;

		if(_ffm.m_Addon_Effect)
			money *= _ffm.m_Addon_Effect_mult;
		if(_ffm.m_Addon_Chaos)
			money *= _ffm.m_Addon_Chaos_mult;
		if(_ffm.m_Addon_LuckyCatch)
			money *= _ffm.m_Addon_LuckyCatch_mult;
		if(_ffm.m_Addon_FallbackTime)
			money *= _ffm.m_Addon_FallbackTime_mult;
		if(_ffm.m_Addon_FallbackTimeHard)
			money *= _ffm.m_Addon_FallbackTimeHard_mult;
		if(_ffm.m_Addon_SilverFish)
			money *= _ffm.m_Addon_SilverFish_mult;
		if(_ffm.m_Addon_GoldenFish)
			money *= _ffm.m_Addon_GoldenFish_mult;
		if(_ffm.m_Addon_SmallRank)
			money *= _ffm.m_Addon_SmallRank_mult;
		if(_ffm.m_Addon_PinchChance)
			money *= _ffm.m_Addon_PinchChance_mult;
		
		return money;
	}
		
	// ���� ��ǰ ���
	public static ItemStack Desc_First_Prize()
	{
		ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Name_First_Prize", false));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_First_Prize", false));
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack Desc_Second_Prize()
	{
		ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Name_Second_Prize", false));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Second_Prize", false));
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack Desc_Third_Prize()
	{
		ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Name_Third_Prize", false));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Third_Prize", false));
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack Desc_Participation_Prize()
	{
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Name_Participation_Prize", false));
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Participation_Prize", false));
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}

	// �ð� ����
	public ItemStack Button_Time_Up(int _minute)
	{
		ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		String name = FF_Msg_TBL.Get_Msg("Item_Name_TimeUp", false);
		name = name.replace("{minute}", _minute + "");
		
		meta.setDisplayName(name);

		// ���� ����
		String desc = FF_Msg_TBL.Get_Msg("Item_Desc_Time", false);
		desc = desc.replace("{hour}", (_ffm.m_Setted_Minutes / 60) + "");
		desc = desc.replace("{minute}", (_ffm.m_Setted_Minutes - _ffm.m_Setted_Minutes / 60 * 60) + "");

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack Desc_Time()
	{
		ItemStack item = new ItemStack(Material.CLOCK);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		String name = FF_Msg_TBL.Get_Msg("Item_Name_Time", false);
		meta.setDisplayName(name);

		// ���� ����
		String desc = FF_Msg_TBL.Get_Msg("Item_Desc_Time", false);
		desc = desc.replace("{hour}", (_ffm.m_Setted_Minutes / 60) + "");
		desc = desc.replace("{minute}", (_ffm.m_Setted_Minutes - _ffm.m_Setted_Minutes / 60 * 60) + "");

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack Button_Time_Down(int _minute)
	{
		ItemStack item = new ItemStack(Material.REDSTONE);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		String name = FF_Msg_TBL.Get_Msg("Item_Name_TimeDown", false);
		name = name.replace("{minute}", _minute + "");
		
		meta.setDisplayName(name);

		// ���� ����
		String desc = FF_Msg_TBL.Get_Msg("Item_Desc_Time", false);
		desc = desc.replace("{hour}", (_ffm.m_Setted_Minutes / 60) + "");
		desc = desc.replace("{minute}", (_ffm.m_Setted_Minutes - _ffm.m_Setted_Minutes / 60 * 60) + "");

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}

	// ����
	public ItemStack Button_Start()
	{
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		String name = FF_Msg_TBL.Get_Msg("Item_Name_Start", false);
		meta.setDisplayName(name);

		// ���� ����
		String desc = FF_Msg_TBL.Get_Msg("Item_Desc_Time", false);
		desc = desc.replace("{hour}", (_ffm.m_Setted_Minutes / 60) + "");
		desc = desc.replace("{minute}", (_ffm.m_Setted_Minutes - _ffm.m_Setted_Minutes / 60 * 60) + "");
		String desc2 = FF_Msg_TBL.Get_Msg("Item_Desc_Money", false);
		desc2 = desc2.replace("{money}", Get_Budget_Money() + "");

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		lore.add(desc2);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Desc_Addons", false));

		if(_ffm.m_Addon_Effect)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Effect", false));
		if(_ffm.m_Addon_Chaos)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Chaos", false));
		if(_ffm.m_Addon_LuckyCatch)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_LuckyCatch", false));
		if(_ffm.m_Addon_FallbackTime)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTime", false));
		if(_ffm.m_Addon_FallbackTimeHard)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTimeHard", false));
		if(_ffm.m_Addon_SilverFish)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SilverFish", false));
		if(_ffm.m_Addon_GoldenFish)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_GoldenFish", false));
		if(_ffm.m_Addon_SmallRank)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SmallRank", false));
		if(_ffm.m_Addon_PinchChance)
			lore.add(ChatColor.GRAY + FF_Msg_TBL.Get_Msg("Item_Addon_Name_PinchChance", false));
			
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		return item;
	}
	
	// ���
	public ItemStack Addon_Effect(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Effect", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Effect", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Effect_2", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_Chaos(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.ENDER_EYE);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Chaos", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Chaos", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Chaos_2", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_LuckyCatch(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.FEATHER);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_LuckyCatch", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_LuckyCatch", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_LuckyCatch_2", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_LuckyCatch_3", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_FallbackTime(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.ARROW);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTime", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_FallbackTime", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_FallbackTimeHard(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.SPECTRAL_ARROW);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTimeHard", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_FallbackTimeHard", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_SilverFish(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.IRON_NUGGET);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SilverFish", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_SilverFish", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_GoldenFish(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_GoldenFish", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_GoldenFish", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_SmallRank(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.TROPICAL_FISH);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SmallRank", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_SmallRank", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_SmallRank_2", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}
	public ItemStack Addon_PinchChance(boolean _activated, double _multiplier)
	{
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta meta = item.getItemMeta();

		// �̸� ����
		meta.setDisplayName(FF_Msg_TBL.Get_Msg("Item_Addon_Prefix", false) + " " + FF_Msg_TBL.Get_Msg("Item_Addon_Name_PinchChance", false));

		// ���� ����
		ArrayList<String> lore = new ArrayList<String>();
		String mult = FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Mult", false);
		mult = mult.replace("{multiplier}", "" + _multiplier);
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_PinchChance", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_PinchChance_2", false));
		lore.add(FF_Msg_TBL.Get_Msg("Item_Addon_Desc_PinchChance_3", false));
		// ������ �¿��� ����
		lore.add(mult);
		lore.add(_activated ? FF_Msg_TBL.Get_Msg("Item_Addon_Desc_On", false) : FF_Msg_TBL.Get_Msg("Item_Addon_Desc_Off", false));
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		if(_activated)
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return item;
	}

}
