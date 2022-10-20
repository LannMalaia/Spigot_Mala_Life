package malalife.FishFesta;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import laylia_core.main.Lang;

public class FF_Fish_Data
{
	public double m_Chance = 100.0;
	
	public Material m_Material = Material.COD;
	public String m_Name = "§e견본어";
	public ArrayList<String> m_Desc = new ArrayList<String>();

	public double m_Base_Price = 100.0;
	
	public boolean m_Is_Fish = true;
	public double m_Price_per_CM = 10.0;
	public double m_Size_Min = 7.0;
	public double m_Size_Max = 1000.0;
	
	public ItemStack m_Item_Data = null;
	
	public static FF_Fish_Data Get_Sample()
	{
		FF_Fish_Data sample = new FF_Fish_Data();
		
		sample.m_Chance = 100.0;
		sample.m_Material = Material.COD;
		sample.m_Name = "§e견본어";
		sample.m_Desc = new ArrayList<String>();
		sample.m_Desc.add("§7모든 물고기들의 본보기가 되어주는 물고기.");
		sample.m_Desc.add("§7이 세상 그 어떤 물고기들보다도 평범하다.");
		sample.m_Base_Price = 100.0;
		sample.m_Is_Fish = true;
		sample.m_Price_per_CM = 10.0;
		sample.m_Size_Min = 7.0;
		sample.m_Size_Max = 1000.0;
		
		return sample;
	}
	public static FF_Fish_Data Get_Bug()
	{
		FF_Fish_Data sample = new FF_Fish_Data();
		
		sample.m_Chance = 100.0;
		sample.m_Material = Material.COD;
		sample.m_Name = "§e버그어";
		sample.m_Desc = new ArrayList<String>();
		sample.m_Desc.add("§7버그 물고기.");
		sample.m_Desc.add("§7이 세상 그 어떤 물고기들보다 버그다.");
		sample.m_Desc.add("§7이걸 낚았다면 신고를 하자.");
		sample.m_Base_Price = 10.0;
		sample.m_Is_Fish = true;
		sample.m_Price_per_CM = 1.0;
		sample.m_Size_Min = 50.0;
		sample.m_Size_Max = 55.0;
		
		return sample;
	}

	public double Calculate_Size()
	{
		boolean pinch = false;
		double pinch_priority = 0.3;
		boolean need_big = false;

		FishFesta_Main ff = FishFesta_Main.Instance;
		if(ff.m_Addon_PinchChance)
		{
			int remained_minutes = (int) (ff.m_Remained_Ticks / 20 / 60);
			if(remained_minutes < ff.m_Setted_Minutes / 5) // 경기 시간이 정해진 시간의 5분의 1 남은 경우
				pinch = true;
			if(remained_minutes < ff.m_Setted_Minutes / 10) // 10분의 1 남은 경우
				pinch_priority = 0.4;
			if(remained_minutes < 1) // 1분 남은 경우
				pinch_priority = 0.5;
			need_big = !ff.m_Addon_SmallRank; // 작을수록 1등이 안 켜져 있다면 큰 거 요구
		}
		
		// 위기 속 기회가 켜져 있다면, 시간이 10분의 1 남았을 때 무조건 30, 40, 50% 이상 크기의 대어 혹은 30, 40, 50% 이하 크기의 소어가 낚인다
		double per = pinch ? (need_big ? Math.random() * (1.0 - pinch_priority) + pinch_priority : 1.0 - Math.random() * pinch_priority) : Math.random();
		double size = m_Size_Min + ((m_Size_Max - m_Size_Min) * per);
		size = (int)(size * 100) * 0.01d;
				
		return size;
	}
	
	public ItemStack Make_Item(double _size, boolean _silver, boolean _golden)
	{
		ItemStack item;
		if(m_Is_Fish)
		{
			item = new ItemStack(m_Material);

			String silv = FF_Msg_TBL.Get_Msg("Silver_Prefix", false);
			String gold = FF_Msg_TBL.Get_Msg("Golden_Prefix", false);
			String name = (_golden ? "§6§l" + gold + "§f " : _silver ? "§f§l" + silv + "§f " : "") + m_Name;
			
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(name);
			
			// 가격 책정
			double price = (m_Base_Price + m_Price_per_CM * _size);
			price *= _silver ? 2.0 : 1.0;
			price *= _golden ? 2.0 : 1.0;
			
			// String price_msg = String.format("%.2f", price);
			String size_msg = String.format("%.2f", _size);
			String final_msg = "§e가격: " + (int)price;
			// for(int i = 0; i < price_msg.length(); i++)
			//	final_msg += "§" + price_msg.charAt(i);
	
			ArrayList<String> desc = new ArrayList<String>(m_Desc);
			desc.add(0, "§e크기: §a§l" + size_msg + "§7cm");
			desc.add(1, final_msg);
			meta.setLore(desc);
			
			item.setItemMeta(meta);
		}
		else
		{
			item = new ItemStack(m_Item_Data);
		}
		return item;
	}
	
	public static double Read_Size(ItemStack _item)
	{
		ItemMeta meta = _item.getItemMeta();
		if(!meta.hasLore())
			return 0.0;
		
		String msg = meta.getLore().get(1);
		String final_msg = "";
		for(int i = 1; i < msg.length(); i += 2)
			final_msg += msg.charAt(i);
		
		return Double.valueOf(final_msg).doubleValue();
	}

	public void Write_Data(String data_road, FileConfiguration fc)
	{
		fc.set(data_road + ".chance", Double.valueOf(m_Chance));
		fc.set(data_road + ".is_fish", m_Is_Fish);
		if(m_Is_Fish) // 물고기로 분류되는 경우
		{
			fc.set(data_road + ".material", m_Material.toString());
			fc.set(data_road + ".name", m_Name);
			fc.set(data_road + ".desc", m_Desc);
			fc.set(data_road + ".base_price", m_Base_Price);
			fc.set(data_road + ".price_per_cm", m_Price_per_CM);
			fc.set(data_road + ".size_min", m_Size_Min);
			fc.set(data_road + ".size_max", m_Size_Max);
		}
		else
		{
			fc.set(data_road + ".item_data", m_Item_Data);
		}
	}
	  
	public void Read_Data(String data_road, FileConfiguration fc)
	{
		m_Chance = fc.getDouble(data_road + ".chance");
		m_Is_Fish = fc.getBoolean(data_road + ".is_fish");

		if(m_Is_Fish) // 물고기로 분류되는 경우
		{
			m_Material = Material.valueOf(fc.getString(data_road + ".material"));
			m_Name = fc.getString(data_road + ".name");
			m_Desc = new ArrayList<String>(fc.getStringList(data_road + ".desc"));
			m_Base_Price = fc.getDouble(data_road + ".base_price");
			m_Price_per_CM = fc.getDouble(data_road + ".price_per_cm");
			m_Size_Min = fc.getDouble(data_road + ".size_min");
			m_Size_Max = fc.getDouble(data_road + ".size_max");
		}
		else
		{
			m_Item_Data = fc.getItemStack(data_road + ".item_data");
			if(m_Item_Data.getItemMeta().hasDisplayName())
				m_Name = m_Item_Data.getItemMeta().getDisplayName();
			else
				m_Name = Lang.Localize(m_Item_Data.getType());
		}
	}
}
