package malalife.FishFesta;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import malalife.main.Mala_Life;

public class Festa_Logger
{
	public static void Add_Log(FishFesta_Main _data)
	{
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd_hh:mm:ss");
		String log_msg = dayTime.format(new Date(time));

		String first_prizes = "";
		for(ItemStack i : _data.m_First_Prize)
			first_prizes += i.toString() + ", ";
		if(first_prizes.length() > 0)
			first_prizes = first_prizes.substring(0, first_prizes.length() - 1);
		
		String second_prizes = "";
		for(ItemStack i : _data.m_Second_Prize)
			second_prizes += i.toString() + ", ";
		if(second_prizes.length() > 0)
			second_prizes = second_prizes.substring(0, second_prizes.length() - 1);
		
		String third_prizes = "";
		for(ItemStack i : _data.m_Third_Prize)
			third_prizes += i.toString() + ", ";
		if(third_prizes.length() > 0)
			third_prizes = third_prizes.substring(0, third_prizes.length() - 1);
		
		String part_prizes = "";
		for(ItemStack i : _data.m_Part_Prize)
			part_prizes += i.toString() + ", ";
		if(part_prizes.length() > 0)
			part_prizes = part_prizes.substring(0, part_prizes.length() - 1);
		
		
		String value_msg = "Starter - " + _data.m_Starter.getName() + "(" + _data.m_Starter.getUniqueId() + ")\n"
				+ "Time - " + _data.m_Setted_Minutes + " minutes\n"
				+ "Mods - " + (_data.m_Addon_Chaos ? "Chaos | " : "") + (_data.m_Addon_Effect ? "Random | " : "")
				+ (_data.m_Addon_FallbackTime ? "TimeAttack | " : "") + (_data.m_Addon_FallbackTimeHard ? "TimeAttackHard | " : "")
				+ (_data.m_Addon_GoldenFish ? "GoldScale | " : "") + (_data.m_Addon_SilverFish ? "SilverScale | " : "")
				+ (_data.m_Addon_LuckyCatch ? "LuckyCatch | " : "") + (_data.m_Addon_PinchChance ? "PinchChance | " : "")
				+ (_data.m_Addon_SmallRank ? "SmallRank | " : "") + "\n"
				+ "Prizes\n  First: " + first_prizes + "\n"
				+ "  Second: " + second_prizes + "\n"
				+ "  Third: " + third_prizes + "\n"
				+ "  Participate: " + part_prizes;
		
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "festa_start_log.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			c.set(log_msg, value_msg);
			
			c.save(f3);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
