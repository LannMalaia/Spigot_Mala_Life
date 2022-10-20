package malalife.Fishing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import malalife.main.Item_Table;
import malalife.main.Mala_Life;

public class Treasure_Datas
{
	public List<Item_Table> Trash = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_1 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_2 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_3 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_4 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_5 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_6 = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_1_sea = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_2_sea = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_3_sea = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_4_sea = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_5_sea = new ArrayList<Item_Table>();
	public List<Item_Table> Tier_6_sea = new ArrayList<Item_Table>();
	  
	public HashMap<String, List<Item_Table>> m_Dictionary = new HashMap<String, List<Item_Table>>();
	  
	public Treasure_Datas()
	{
		Read_Tier_Table(Trash, "trash");
		m_Dictionary.put("trash", Trash);
		Read_Tier_Table(Tier_1, "tier_1");
		m_Dictionary.put("tier_1", Tier_1);
		Read_Tier_Table(Tier_2, "tier_2");
		m_Dictionary.put("tier_2", Tier_2);
		Read_Tier_Table(Tier_3, "tier_3");
		m_Dictionary.put("tier_3", Tier_3);
		Read_Tier_Table(Tier_4, "tier_4");
		m_Dictionary.put("tier_4", Tier_4);
		Read_Tier_Table(Tier_5, "tier_5");
		m_Dictionary.put("tier_5", Tier_5);
		Read_Tier_Table(Tier_6, "tier_6");
		m_Dictionary.put("tier_6", Tier_6);
		
		Read_Tier_Table(Tier_1_sea, "tier_1_sea");
		m_Dictionary.put("tier_1_sea", Tier_1_sea);
		Read_Tier_Table(Tier_2_sea, "tier_2_sea");
		m_Dictionary.put("tier_2_sea", Tier_2_sea);
		Read_Tier_Table(Tier_3_sea, "tier_3_sea");
		m_Dictionary.put("tier_3_sea", Tier_3_sea);
		Read_Tier_Table(Tier_4_sea, "tier_4_sea");
		m_Dictionary.put("tier_4_sea", Tier_4_sea);
		Read_Tier_Table(Tier_5_sea, "tier_5_sea");
		m_Dictionary.put("tier_5_sea", Tier_5_sea);
		Read_Tier_Table(Tier_6_sea, "tier_6_sea");
		m_Dictionary.put("tier_6_sea", Tier_6_sea);
	}
  
	public void Read_Tier_Table(List<Item_Table> table, String Table_Name)
	{
		table.clear();
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
			{
				f.mkdir();
			}
			File f3 = new File(f, "Fish_Item_Tables");
			if (!f3.exists())
			{
				f3.mkdir();
			}
			File f2 = new File(f3, Table_Name + ".yml");
			if (!f2.exists())
			{
				f2.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f2);
				c.load(f2);
        
				table.add(new Item_Table());
				table.add(new Item_Table());
				table.add(new Item_Table());
				for (int j = 0; j < table.size(); j++)
				{
					((Item_Table)table.get(j)).Write_Sample_Data("item_table." + j, c);
				}
				c.save(f2);
				table.clear();
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f2);
			c.load(f2);
			for (int j = 0; j >= 0; j++)
			{
				Object temp = c.get("item_table." + j);
				if (temp == null)
				{
					break;
				}
				Item_Table it = new Item_Table();
				it.Read_Data("item_table." + j, c);
				table.add(it);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

  	public void Check_Table(Player _sender, String _table_Name)
  	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f3 = new File(f, "Fish_Item_Tables");
			if (!f3.exists())
				f3.mkdir();
			
			File f2 = new File(f3, _table_Name + ".yml");
			if (!f2.exists())
			{
				_sender.sendMessage("없는 테이블명이에요.");
				return;
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f2);
			c.load(f2);
			for (int j = 0; ; j++)
			{
				Object temp = c.get("item_table." + j);
				if (temp == null)
					break;
				Item_Table it_data = new Item_Table();
				it_data.Read_Data("item_table." + j, c);
				_sender.sendMessage(j + " 번째 아이템 체크됨!");
			}
			_sender.sendMessage("체크 완료!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
  
  	public ItemStack Get_Table_Item(int tier, boolean is_ocean)
  	{
  		List<Item_Table> table = new ArrayList<Item_Table>();
  		if (is_ocean)
  		{
  			switch (tier)
  			{
  			case 0: 
  				table = Trash;
  				break;
  			case 1: 
  				table = Tier_1_sea;
  				break;
  			case 2: 
  				table = Tier_2_sea;
  				break;
  			case 3: 
  				table = Tier_3_sea;
  				break;
  			case 4: 
  				table = Tier_4_sea;
  				break;
  			case 5: 
  				table = Tier_5_sea;
  				break;
  			case 6: 
  				table = Tier_6_sea;
  				break;
  			}
  		}
  		else
  		{
  			switch (tier)
  			{
  			case 0: 
  				table = Trash;
  				break;
  			case 1: 
  				table = Tier_1;
  				break;
  			case 2: 
  				table = Tier_2;
  				break;
  			case 3: 
  				table = Tier_3;
  				break;
  			case 4: 
  				table = Tier_4;
  				break;
  			case 5: 
  				table = Tier_5;
  				break;
  			case 6: 
  				table = Tier_6;
  				break;
  			}
  		}
  		int total_chance = 0;
  		for (int i = 0; i < table.size(); i++)
  		{
  			total_chance += table.get(i).chance;
  		}
  		int getted_chance = 0;
  		double random_value = Math.random() * total_chance;
  		for (int i = 0; i < table.size(); i++)
  		{
  			getted_chance += table.get(i).chance;
  			if (random_value <= getted_chance)
  			{
  				return table.get(i).Make_Item();
  			}
  		}
    
  		Bukkit.getConsoleSender().sendMessage("아이템 테이블이 뭔가 이상해요. - " + (is_ocean ? "바다, " : "강, ") + tier + "티어");
  		return null;
  	}
}
