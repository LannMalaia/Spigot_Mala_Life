package malalife.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import malalife.Adventure.Adventure_Event;
import malalife.Adventure.Horse_Booster;
import malalife.Breaking.MiningEvent;
import malalife.FishFesta.FF_Tab_Complete;
import malalife.FishFesta.FishFesta_Main;
import malalife.Fishing.Fish_Data;
import malalife.Fishing.Fishing;
import malalife.Fishing.Treasure_Datas;
import laylia_core.main.Laylia_API;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.plugins.Economy_Essentials;

public class Mala_Life extends JavaPlugin
{
	public static JavaPlugin plugin;
	public static Economy econ = null;
	public static String started_time = "";
	public static List<String> log_line = new ArrayList<String>();
	public static Treasure_Datas treasures;
	public static Horse_Booster horse_booster;
	public static Laylia_API core_api;
	public static JavaPlugin enchant_api;
	public static FishFesta_Main fish_festa;
	//HiddenPaint_Advancement hiddenpaint_advancement;
	
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "말라 라이프 활성화!");
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		Bukkit.getPluginManager().registerEvents(new Adventure_Event(), this);
		Bukkit.getPluginManager().registerEvents(new MiningEvent(), this);
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		started_time = dayTime.format(new Date(time));
		plugin = this;
		if (!Check_Laylia_API())
		{
			getServer().getConsoleSender().sendMessage("라일리아 코어 API가 없어요!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		if (!Check_Laylia_Enchant())
		{
			getServer().getConsoleSender().sendMessage("라일리아 인챈트가 없어요!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		if (!setupEconomy())
		{
			getServer().getConsoleSender().sendMessage("이코노미가 없어요!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		treasures = new Treasure_Datas();
		horse_booster = new Horse_Booster();
		fish_festa = new FishFesta_Main();
		
		Enchant_Register.Resister();
		for (World world : Bukkit.getWorlds())
		{
			for(Entity e : world.getEntities())
			{
				if (e.hasMetadata("Laylia_Throw_Weapon"))
				{
					e.remove();
				}
			}
		}		
		getCommand("fishfes").setTabCompleter(new FF_Tab_Complete());
		
		
		//hiddenpaint_advancement = new HiddenPaint_Advancement();
	}
  
	public void onDisable()
	{
		fish_festa.End_Festa(null, false, new String[1]);
		//hiddenpaint_advancement.disableServer();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "말라 라이프 b활성화..");
		
		
	}
  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("mala-money-drop"))
		{
			if (args.length == 2 && sender.hasPermission("*"))
			{
				Player player = Bukkit.getPlayer(args[0]);
				if (player == null)
				{
					sender.sendMessage("§c그런 이름을 가진 플레이어가 없어요.");
					return true;
				}
				double take = Double.parseDouble(args[1]);
				if (take <= 0)
				{
					sender.sendMessage("§c0 이하로는 버릴 수 없어요.");
					return true;
				}
				
				econ.withdrawPlayer(player, take);
				player.sendMessage("§c주머니에 있던 돈에서 §f§l" + take + "§c원이 공중분해됐습니다.");
				
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("be-fish"))
		{
			if (sender instanceof Player)
			{
				Fishing.Fishing_Skill_View((Player)sender);
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("fishfes"))
		{
			if (sender instanceof Player)
			{
				fish_festa.Command((Player)sender, args);
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("be-fish-checktable"))
		{
			if (sender instanceof Player && sender.hasPermission("*.*"))
			{
				treasures.Check_Table((Player)sender, args[0]);
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("be-fish-get-all-fishes"))
		{
			if (sender instanceof Player && sender.hasPermission("*.*"))
			{
				Player player = (Player)sender;
				player.getInventory().addItem(Fish_Data.Get_High_Fish());
				player.getInventory().addItem(Fish_Data.Get_High_Salmon());
				player.getInventory().addItem(Fish_Data.Get_High_Fish_Cooked());
				player.getInventory().addItem(Fish_Data.Get_High_Salmon_Cooked());
				player.getInventory().addItem(Fish_Data.Get_High_CrownFish());
				player.getInventory().addItem(Fish_Data.Get_Highest_Fish());
				player.getInventory().addItem(Fish_Data.Get_Highest_Salmon());
				player.getInventory().addItem(Fish_Data.Get_Highest_Fish_Cooked());
				player.getInventory().addItem(Fish_Data.Get_Highest_Salmon_Cooked());
				player.getInventory().addItem(Fish_Data.Get_Highest_CrownFish());
				return true;
			}
		}
		return false;
	}
  
	private boolean setupEconomy()
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null)
		{
			return false;
		}
//		Bukkit.getConsoleSender().sendMessage("[로드된 플긴]");
//		for (RegisteredServiceProvider<?> p : getServer().getServicesManager().getRegistrations(
//				getServer().getPluginManager().getPlugin("Vault")))
//		{
//			Bukkit.getConsoleSender().sendMessage(p.getProvider().getClass().getName());
//			Bukkit.getConsoleSender().sendMessage(p.getService().getGenericSuperclass().getTypeName());
//		}
//		Bukkit.getConsoleSender().sendMessage("-----------");
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
			return false;
		//Bukkit.getConsoleSender().sendMessage("-----2-----");
		econ = rsp.getProvider();
		return rsp.getProvider() != null;
	}
  
	private boolean Check_Laylia_API()
	{
		if (getServer().getPluginManager().getPlugin("Laylia_Core_API") == null)
		{
			return false;
		}
		RegisteredServiceProvider<Laylia_API> rsp = getServer().getServicesManager().getRegistration(Laylia_API.class);
		if (rsp == null)
		{
			return false;
		}
		core_api = (Laylia_API)rsp.getProvider();
		return core_api != null;
	}
  
	private boolean Check_Laylia_Enchant()
	{
		if (getServer().getPluginManager().getPlugin("Laylia_Enchant") == null)
		{
			return false;
		}
		RegisteredServiceProvider<laylia_enchant_main.Main> rsp = getServer().getServicesManager().getRegistration(laylia_enchant_main.Main.class);
		if (rsp == null)
		{
			return false;
		}
		enchant_api = (laylia_enchant_main.Main)rsp.getProvider();
		return enchant_api != null;
	}
  
	public static boolean isStringInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException e) {}
		return false;
	}
  
	public static String get_Time()
	{
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		return dayTime.format(new Date(time));
	}
}
