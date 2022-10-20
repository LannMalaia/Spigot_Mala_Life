package malalife.FishFesta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import malalife.main.Mala_Life;

public class FF_Fish_List
{
	public static FF_Fish_List Instance;

	public double m_Event_Percent = 0.0; // 이벤트템이 걸릴 확률
	
	public ArrayList<FF_Fish_Data> datas;
	public ArrayList<FF_Fish_Data> event_datas;
	
	public FF_Fish_List()
	{
		Instance = this;
		datas = new ArrayList<FF_Fish_Data>();
		event_datas = new ArrayList<FF_Fish_Data>();
		Read_Data();
		Read_Data_Event();
	}

	public void Read_Data()
	{
		datas.clear();
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "fish_table.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				datas.add(FF_Fish_Data.Get_Sample());
				datas.add(FF_Fish_Data.Get_Sample());
				datas.add(FF_Fish_Data.Get_Sample());
				for (int j = 0; j < datas.size(); j++)
					datas.get(j).Write_Data("fish_table." + j, c);
				c.save(f3);
				datas.clear();
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);
			for (int j = 0; j >= 0; j++)
			{
				Object temp = c.get("fish_table." + j);
				if (temp == null)
					break;
				datas.add(new FF_Fish_Data());
				datas.get(j).Read_Data("fish_table." + j, c);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Save_Data()
	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "fish_table.yml");
			if (!f3.exists())
				f3.createNewFile();
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			for (int j = 0; j < datas.size(); j++)
				datas.get(j).Write_Data("fish_table." + j, c);
							
			c.save(f3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Add_Data(FF_Fish_Data _new_data)
	{
		datas.add(_new_data);
		Save_Data();
	}

	public void Read_Data_Event()
	{
		event_datas.clear();
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "event_table.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				event_datas.add(FF_Fish_Data.Get_Sample());
				event_datas.add(FF_Fish_Data.Get_Sample());
				event_datas.add(FF_Fish_Data.Get_Sample());
				for (int j = 0; j < event_datas.size(); j++)
					event_datas.get(j).Write_Data("event_table." + j, c);
				c.set("table_chance", Double.valueOf(0.0));
				c.save(f3);
				event_datas.clear();
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);
			m_Event_Percent = c.getDouble("table_chance");
			for (int j = 0; j >= 0; j++)
			{
				Object temp = c.get("event_table." + j);
				if (temp == null)
					break;
				event_datas.add(new FF_Fish_Data());
				event_datas.get(j).Read_Data("event_table." + j, c);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Save_Data_Event()
	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "event_table.yml");
			if (!f3.exists())
				f3.createNewFile();
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			for (int j = 0; j < event_datas.size(); j++)
				event_datas.get(j).Write_Data("event_table." + j, c);
							
			c.save(f3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Add_Data_Event(FF_Fish_Data _new_data)
	{
		event_datas.add(_new_data);
		Save_Data_Event();
	}
	

	public static List<ItemStack> Read_Data_Backup()
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "crash_backup.yml");			
			if (!f3.exists())
				f3.createNewFile();
			
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);
			
			ItemStack item = new ItemStack(Material.PAPER);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("First =>");
			item.setItemMeta(meta);
			items.add(item);
			for (int j = 0; j >= 0; j++)
			{
				ItemStack temp = c.getItemStack("first." + j);
				if (temp == null)
					break;
				items.add(temp);
			}
			item = new ItemStack(Material.PAPER);
			meta = item.getItemMeta();
			meta.setDisplayName("Second =>");
			item.setItemMeta(meta);
			items.add(item);
			for (int j = 0; j >= 0; j++)
			{
				ItemStack temp = c.getItemStack("second." + j);
				if (temp == null)
					break;
				items.add(temp);
			}
			item = new ItemStack(Material.PAPER);
			meta = item.getItemMeta();
			meta.setDisplayName("Third =>");
			item.setItemMeta(meta);
			items.add(item);
			for (int j = 0; j >= 0; j++)
			{
				ItemStack temp = c.getItemStack("third." + j);
				if (temp == null)
					break;
				items.add(temp);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}
	public static void Save_Data_Backup(List<ItemStack> _first, List<ItemStack> _second, List<ItemStack> _third)
	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "crash_backup.yml");
			if (!f3.exists())
				f3.createNewFile();
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			for (int j = 0; j < _first.size(); j++)
				c.set("first." + j, _first.get(j));
			for (int j = 0; j < _second.size(); j++)
				c.set("second." + j, _second.get(j));
			for (int j = 0; j < _third.size(); j++)
				c.set("third." + j, _third.get(j));
							
			c.save(f3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void Remove_Backup()
	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "crash_backup.yml");			
			if (f3.exists())
				f3.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static boolean Has_Crash_Backup()
	{
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "crash_backup.yml");			
			if (f3.exists())
				return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public FF_Fish_Data Get_Random_Fish(boolean _pinch_chance)
	{
		double chances = 0.0;
		
		if(Math.random() * 100.0 < m_Event_Percent)
		{
			// 이벤트 테이블
			for(int i = 0; i < event_datas.size(); i++)
				chances += event_datas.get(i).m_Chance;
			
			double current_chance = Math.random() * chances;
			for(int i = 0; i < event_datas.size(); i++)
			{
				current_chance -= event_datas.get(i).m_Chance;
				if(current_chance <= 0.0)
					return event_datas.get(i);
			}
		}
		else
		{
			// 일반 테이블
			for(int i = 0; i < datas.size(); i++)
				chances += datas.get(i).m_Chance;
			
			double current_chance = Math.random() * chances;
			for(int i = 0; i < datas.size(); i++)
			{
				current_chance -= datas.get(i).m_Chance;
				if(current_chance <= 0.0)
					return datas.get(i);
			}
		}
		return FF_Fish_Data.Get_Bug();
	}
}
