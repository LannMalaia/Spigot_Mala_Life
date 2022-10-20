package malalife.FishFesta;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import malalife.FishFesta.Inventory.Festa_Instant_Chest;
import malalife.FishFesta.Inventory.Festa_Menu_Inv;
import malalife.FishFesta.Inventory.Festa_Shop_Inv;
import laylia_core.main.ItemParser;
import laylia_core.main.Lang;
import laylia_core.main.Laylia_API;
import laylia_enchant_data.Enchant_Manager;
import malalife.main.Mala_Life;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;

/**
 * @author jimja
 * @version 2020. 5. 9.
 * @apiNote 낚시 축제 관리자
 */
public class FishFesta_Main
{
	public static FishFesta_Main Instance;

	// 테이블
	public ArrayList<Player> m_Players = new ArrayList<Player>(); // 현재 축제에 참여중인 플레이어들
	ArrayList<Festa_Fishing_Data> m_Highest_Data = new ArrayList<Festa_Fishing_Data>(); // 최고 기록 순위
	ArrayList<Festa_Fishing_Data> m_Lowest_Data = new ArrayList<Festa_Fishing_Data>(); // 최저 기록 순위

	ArrayList<ItemStack> m_First_Prize = new ArrayList<ItemStack>();
	ArrayList<ItemStack> m_Second_Prize = new ArrayList<ItemStack>();
	ArrayList<ItemStack> m_Third_Prize = new ArrayList<ItemStack>();
	ArrayList<ItemStack> m_Part_Prize = new ArrayList<ItemStack>();
	
	// 정보
	Player m_Starter = null; // 게임을 시작한 플레이어
	boolean m_Now_Playing = false; // 축제가 현재 진행중인지

	// 시간
	public long m_Setted_Minutes = 0; // 축제 설정 시간 
	public long m_Max_Minutes = 360; // 축제 설정가능한 최대 시간 
	public long m_Remained_Ticks = 0; // 축제 중 잔여 시간(틱)

	// 예산
	public long m_Base_Money = 10000; // 축제 개최금 기본값
	public long m_Timer_Money = 100; // 축제 개최금 시간당 상승치

	// 모드 활성화 여부
	public boolean m_Addon_Effect = false;
	public boolean m_Addon_Chaos = false;
	public boolean m_Addon_LuckyCatch = false;
	public boolean m_Addon_FallbackTime = false;
	public boolean m_Addon_FallbackTimeHard = false;
	public boolean m_Addon_SilverFish = false;
	public boolean m_Addon_GoldenFish = false;
	public boolean m_Addon_SmallRank = false;
	public boolean m_Addon_PinchChance = false;

	// 모드 예산 배수
	public double m_Addon_Effect_mult = 1.0;
	public double m_Addon_Chaos_mult = 1.0;
	public double m_Addon_LuckyCatch_mult = 1.0;
	public double m_Addon_FallbackTime_mult = 1.0;
	public double m_Addon_FallbackTimeHard_mult = 1.0;
	public double m_Addon_SilverFish_mult = 1.0;
	public double m_Addon_GoldenFish_mult = 1.0;
	public double m_Addon_SmallRank_mult = 1.0;
	public double m_Addon_PinchChance_mult = 1.0;
	
	// 타이머 바
	public BossBar bar;
	
	HashMap<String, Long> m_Config;
	HashMap<String, Float> m_Mod_Money_Mult;
	
	NamespacedKey key;
	
	public FishFesta_Main()
	{
		Instance = this;
		
		new Festa_Menu_Inv();
		new Festa_Shop_Inv();
		new Festa_Instant_Chest();

		new FF_Fish_List();
		Load_Table();
		FF_Msg_TBL.Load_Table();
		
		Bukkit.getPluginManager().registerEvents(new FishFesta_Event(), Mala_Life.plugin);
		Bukkit.getScheduler().runTaskTimerAsynchronously(Mala_Life.plugin, new FishFesta_Timer(), 100, FishFesta_Timer.Running_Interval);
		
		key = new NamespacedKey(Mala_Life.plugin, "fishfes_timer");
		if(Bukkit.getBossBar(key) == null)
		{
			Bukkit.getConsoleSender().sendMessage("쓰려던 타이머 바가 없어서 다시 만들었어요.");
			Bukkit.createBossBar(key, FF_Msg_TBL.Get_Msg("TimerBar_Title", false), BarColor.BLUE, BarStyle.SOLID, BarFlag.CREATE_FOG);
		}
		bar = Bukkit.getBossBar(key);
		bar.removeFlag(BarFlag.CREATE_FOG);
		bar.setVisible(false);
		
		Initialize();
	}
	
	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote 축제 정보 초기화
	 */
	public void Initialize()
	{
		// 리스트
		m_Players.clear();
		m_Highest_Data.clear();
		m_Lowest_Data.clear();
		m_First_Prize.clear();
		m_Second_Prize.clear();
		m_Third_Prize.clear();
		m_Part_Prize.clear();
		
		// 정보
		m_Starter = null;
		m_Now_Playing = false;
		m_Setted_Minutes = 0;
		
		// 모드
		m_Addon_Effect = false;
		m_Addon_Chaos = false;
		m_Addon_LuckyCatch = false;
		m_Addon_FallbackTime = false;
		m_Addon_FallbackTimeHard = false;
		m_Addon_SilverFish = false;
		m_Addon_GoldenFish = false;
		m_Addon_SmallRank = false;
		m_Addon_PinchChance = false;

		bar.setVisible(false);
		for(Player player : bar.getPlayers())
			bar.removePlayer(player);
	}

	public void Load_Table()
	{
		m_Config = new HashMap<String, Long>();
		m_Mod_Money_Mult = new HashMap<String, Float>();
		
		m_Config.put("Max_Minutes", Long.valueOf(360));
		m_Config.put("Base_Money", Long.valueOf(10000));
		m_Config.put("Money_per_Minute", Long.valueOf(100));

		m_Mod_Money_Mult.put("Random", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("Chaos", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("LuckyDay", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("TimeAttack", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("SilverScale", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("GoldenScale", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("SmallIsGood", Float.valueOf(1.0f));
		m_Mod_Money_Mult.put("PinchChance", Float.valueOf(1.0f));
		
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "config.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				c.set("Config", m_Config);
				c.set("Mod", m_Mod_Money_Mult);
				
				c.save(f3);
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			Iterator<String> text = c.getConfigurationSection("Config").getKeys(false).iterator();
			while(text.hasNext())
			{
				String key = text.next();
				m_Config.put(key, c.getLong("Config." + key, 0));
			}
			text = c.getConfigurationSection("Mod").getKeys(false).iterator();
			while(text.hasNext())
			{
				String key = text.next();
				m_Mod_Money_Mult.put(key, (float)c.getDouble("Mod." + key, 0));
			}
			
			// m_Config = (HashMap<String, Long>) getConfigurationSection("Config").getmap  .getMapList("Config");
			// m_Mod_Money_Mult = (HashMap<String, Float>)c.getMapList("Mod");

			m_Max_Minutes = m_Config.get("Max_Minutes");
			m_Base_Money = m_Config.get("Base_Money");
			m_Timer_Money = m_Config.get("Money_per_Minute");

			m_Addon_Effect_mult = m_Mod_Money_Mult.get("Random");
			m_Addon_Chaos_mult = m_Mod_Money_Mult.get("Chaos");
			m_Addon_LuckyCatch_mult = m_Mod_Money_Mult.get("LuckyDay");
			m_Addon_FallbackTime_mult = m_Mod_Money_Mult.get("TimeAttack");
			m_Addon_SilverFish_mult = m_Mod_Money_Mult.get("SilverScale");
			m_Addon_GoldenFish_mult = m_Mod_Money_Mult.get("GoldenScale");
			m_Addon_SmallRank_mult = m_Mod_Money_Mult.get("SmallIsGood");
			m_Addon_PinchChance_mult = m_Mod_Money_Mult.get("PinchChance");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @apiNote 축제 진행중임?
	 */
	public boolean Is_Playing()
	{
		return m_Now_Playing;
	}
	
	/**
	 * @apiNote 커맨드를 입력했을 때
	 * @param player 커맨드 친 놈
	 * @param _args 파라미터
	 */
	public void Command(Player _player, String[] _args)
	{
		if(_args.length == 0)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Description", false));
			if(_player.hasPermission("*"))
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Admin_Description", false));
			return;
		}
		switch(_args[0])
		{
		case "info": // 축제 정보 확인
			Send_Info(_player);
			break;
		case "join": // 축제 들가기
			if(_player.hasPermission("*") || _player.hasPermission("fes.fish.join"))
				Join_Festa(_player);
			else
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
			break;
		case "leave": // 축제 나가기
			Leave_Festa(_player);
			break;
		case "top": // 순위 확인
			Send_Top(_player);
			break;
		case "shop": // 매각 상점 열기
			Open_SellShop_Menu(_player);
			break;
		case "start": // 축제 시작 메뉴 (퍼미션 필요)
			if(_player.hasPermission("*") || _player.hasPermission("fes.fish.start"))
				Open_Festa_Menu(_player);
			else
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
			break;
		case "cancel": // 축제 취소시키기 (어드민이랑 시작한 사람만 가능)
			if(_player.hasPermission("*") || _player.hasPermission("fes.fish.cancel"))
				End_Festa(_player, true, _args);
			else
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
			break;
		case "end": // 축제 끝내기 (어드민이랑 시작한 사람만 가능)
			if(_player.hasPermission("*") || _player.hasPermission("fes.fish.end"))
				End_Festa(_player, false, _args);
			else
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
			break;
		case "chest":
			Open_Instant_Chest(_player);
			break;			
		case "admin":
			if(!_player.hasPermission("*")) // 권한 없는 경우
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
				break;
			}
			if(_args.length < 2) // 너무 적게 입력한 경우
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Description", false));
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Admin_Description", false));
				break;
			}
			FF_Fish_Data data = new FF_Fish_Data();
			switch(_args[1])
			{
			case "addtable":
				data.m_Is_Fish = false;
				data.m_Item_Data = _player.getInventory().getItemInMainHand();
				FF_Fish_List.Instance.Add_Data(data);
				_player.sendMessage("table add OK!");
				break;
			case "addevent":
				data.m_Is_Fish = false;
				data.m_Item_Data = _player.getInventory().getItemInMainHand();
				FF_Fish_List.Instance.Add_Data_Event(data);
				_player.sendMessage("event add OK!");
				break;
			case "reload":
				new FF_Fish_List();
				Load_Table();
				FF_Msg_TBL.Load_Table();
				_player.sendMessage("Table Load OK!");
				break;
			case "solve":
				List<ItemStack> items = FF_Fish_List.Read_Data_Backup();
				for(ItemStack item : items)
					_player.getInventory().addItem(item);
					// _player.getInventory().setItem(_player.getInventory().firstEmpty(), item);
				_player.sendMessage("solve OK!");
				break;
			case "remove_crash":
				FF_Fish_List.Remove_Backup();
				_player.sendMessage("crash backup remove OK!");
				break;
			}
			break;			
		default:
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Description", false));
			if(_player.hasPermission("*"))
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Admin_Description", false));
			break;
		}
	}

	// 정보 확인
	public void Send_Info(Player _player)
	{
		// 축제를 시작하지 않은 경우
		if (!m_Now_Playing)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Not_Playing", true));
			return;
		}
		ArrayList<TextComponent> msg_list = new ArrayList<TextComponent>();
		
		String msg = FF_Msg_TBL.Get_Msg("Desc_Info", false);
		msg = msg.replace("{name}", m_Starter.getName());
		long remained_hour = m_Remained_Ticks / 72000;
		msg = msg.replace("{hour}", "" + remained_hour);
		long remained_minute = (m_Remained_Ticks - (remained_hour * 72000)) / 1200;
		msg = msg.replace("{minute}", "" + (remained_minute));
		long remained_second = (m_Remained_Ticks - (remained_hour * 72000) - (remained_minute * 1200)) / 20;
		msg = msg.replace("{second}", "" + (remained_second));
		msg = msg.replace("{player_count}", "" + m_Players.size());

		// 모드 설명
		msg += "\n" + FF_Msg_TBL.Get_Msg("Desc_Mods", false) + "\n";
		
		if(!(m_Addon_Effect || m_Addon_Chaos || m_Addon_LuckyCatch || m_Addon_FallbackTime || m_Addon_FallbackTimeHard
				|| m_Addon_SilverFish || m_Addon_GoldenFish || m_Addon_SmallRank || m_Addon_PinchChance))
			msg += "  §7" + FF_Msg_TBL.Get_Msg("Notice_Festa_No_Addon", false) + "\n";
		
		if(m_Addon_Effect)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Effect", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_Effect", false) + "\n";
		if(m_Addon_Chaos)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_Chaos", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_Chaos", false) + "\n";
		if(m_Addon_LuckyCatch)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_LuckyCatch", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_LuckyCatch", false) + "\n";
		if(m_Addon_FallbackTime)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTime", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_FallbackTime", false) + "\n";
		if(m_Addon_FallbackTimeHard)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_FallbackTimeHard", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_FallbackTimeHard", false) + "\n";
		if(m_Addon_SilverFish)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SilverFish", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_SilverFish", false) + "\n";
		if(m_Addon_GoldenFish)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_GoldenFish", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_GoldenFish", false) + "\n";
		if(m_Addon_SmallRank)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_SmallRank", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_SmallRank", false) + "\n";
		if(m_Addon_PinchChance)
			msg += "  §e" + FF_Msg_TBL.Get_Msg("Item_Addon_Name_PinchChance", false) + "§f : " + FF_Msg_TBL.Get_Msg("Item_Addon_ShortDesc_PinchChance", false) + "\n";
		
		// 순위 상품 설명
		msg_list.add(new TextComponent(msg));
		msg_list.add(new TextComponent(FF_Msg_TBL.Get_Msg("Desc_Prizes", false) + "\n  §b" + FF_Msg_TBL.Get_Msg("Desc_First", false) + " : "));
		msg_list.addAll(Get_Prize_List(1));
		msg_list.add(new TextComponent("\n  §b" + FF_Msg_TBL.Get_Msg("Desc_Second", false) + " : "));
		msg_list.addAll(Get_Prize_List(2));
		msg_list.add(new TextComponent("\n  §b" + FF_Msg_TBL.Get_Msg("Desc_Third", false) + " : "));
		msg_list.addAll(Get_Prize_List(3));
		
		TextComponent[] texts = new TextComponent[msg_list.size()];
		for(int i = 0; i < msg_list.size(); i++)
			texts[i] = msg_list.get(i);
		
		_player.spigot().sendMessage(texts);
	}

	// 들오고 나가고
	public void Join_Festa(Player _player)
	{
		// 축제를 시작하지 않은 경우
		if (!m_Now_Playing)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Not_Playing", true));
			return;
		}
		
		if(m_Players.contains(_player))
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Already_Joined", true));
			return;
		}
		_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Join_OK", true));
		m_Players.add(_player);
		bar.addPlayer(_player);
	}
	public void Leave_Festa(Player _player)
	{
		// 축제를 시작하지 않은 경우
		if (!m_Now_Playing)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Not_Playing", true));
			return;
		}
		
		if(!m_Players.contains(_player))
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Already_Leaved", true));
			return;
		}
		_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Leave_OK", true));
		m_Players.remove(_player);
		bar.removePlayer(_player);
	}

	// 순위 확인
	public void Send_Top(Player _player)
	{
		// 축제를 시작하지 않은 경우
		if (!m_Now_Playing)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Not_Playing", true));
			return;
		}
		String msg = Get_TopList_MSG(FF_Msg_TBL.Get_Msg("Desc_Top", false), _player);
		
		_player.sendMessage(msg);
	}
	public String Get_TopList_MSG(String _msg, Player _player)
	{
		Festa_Fishing_Data[] top_list = Get_Top_List();
		String msg = _msg;
		msg = msg.replace("{first}", FF_Msg_TBL.Get_Msg("Desc_First", false));
		msg = msg.replace("{second}", FF_Msg_TBL.Get_Msg("Desc_Second", false));
		msg = msg.replace("{third}", FF_Msg_TBL.Get_Msg("Desc_Third", false));
		
		msg = msg.replace("{rank_1_name}", top_list[0] != null ? top_list[0].m_Player.getName() : FF_Msg_TBL.Get_Msg("No_Player", false));
		msg = msg.replace("{rank_2_name}", top_list[1] != null ? top_list[1].m_Player.getName() : FF_Msg_TBL.Get_Msg("No_Player", false));
		msg = msg.replace("{rank_3_name}", top_list[2] != null ? top_list[2].m_Player.getName() : FF_Msg_TBL.Get_Msg("No_Player", false));
		
		msg = msg.replace("{rank_1_fishname}", top_list[0] != null ? top_list[0].m_Name : "[ ]");
		msg = msg.replace("{rank_2_fishname}", top_list[1] != null ? top_list[1].m_Name : "[ ]");
		msg = msg.replace("{rank_3_fishname}", top_list[2] != null ? top_list[2].m_Name : "[ ]");
		
		msg = msg.replace("{rank_1_fishsize}", top_list[0] != null ? String.format("%.3f", top_list[0].m_Size) + "" : "[ ]");
		msg = msg.replace("{rank_2_fishsize}", top_list[1] != null ? String.format("%.3f", top_list[1].m_Size) + "" : "[ ]");
		msg = msg.replace("{rank_3_fishsize}", top_list[2] != null ? String.format("%.3f", top_list[2].m_Size) + "" : "[ ]");
		
		// 권외인 플레이어 등수 체크
		if (_player != null)
		{
			boolean check_player = false;
			for (Festa_Fishing_Data data : top_list)
			{
				if (data == null)
					break;
				if (data.m_Player == _player)
					check_player = true;
			}
			if (!check_player)
			{
				// 이 부분 되나 몰게슴
				Festa_Fishing_Data[] ffd = new Festa_Fishing_Data[1];
				int rank = Get_Player_Rank(ffd, _player);
				if (ffd[0] != null)
				{
					msg = msg + "\n" + FF_Msg_TBL.Get_Msg("Desc_CurRank", false);
					msg = msg.replace("{num}", rank + "");
					msg = msg.replace("{grade}", FF_Msg_TBL.Get_Msg("Desc_OtherGrade", false));
					msg = msg.replace("{playername}", ffd[0].m_Player.getName());
					msg = msg.replace("{fishname}", ffd[0].m_Name);
					msg = msg.replace("{fishsize}", ffd[0].m_Size + "");
				}
				
			}
		}
		
		return msg;
	}
	
	public ArrayList<TextComponent> Get_Prize_List(int _rank)
	{
		ArrayList<ItemStack> prizes = new ArrayList<ItemStack>();
		switch(_rank)
		{
		case 0:
			prizes = m_Part_Prize;
			break;
		case 1:
			prizes = m_First_Prize;
			break;
		case 2:
			prizes = m_Second_Prize;
			break;
		case 3:
			prizes = m_Third_Prize;
			break;
		}
		
		ArrayList<TextComponent> prize_list = new ArrayList<TextComponent>();
		prize_list.add(new TextComponent("§f"));
		for(int i = 0; i < Math.min(3, prizes.size()); i++)
		{
			// BaseComponent[] tc = { new TextComponent(Enchanting_Temp.Parse_Item(prizes.get(i))) };
			String name = Lang.Localize(prizes.get(i).getType());
			if(prizes.get(i).getItemMeta().hasDisplayName())
				name = prizes.get(i).getItemMeta().getDisplayName();
			
			TextComponent item_name = new TextComponent(name);
			Content cont = ItemParser.getItemContent(prizes.get(i));
			item_name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, cont));
			prize_list.add(item_name);
			if(i < Math.min(3, prizes.size()) - 1 && i != 2)
				prize_list.add(new TextComponent("§f, "));
		}
		if(prizes.size() > 3)
			prize_list.add(new TextComponent("§f..."));
		
		return prize_list;
	}
	
	// 매각 상점
	public void Open_SellShop_Menu(Player _player)
	{
		_player.openInventory(Festa_Shop_Inv.Instance.Get_Player_Shop(_player));
	}

	// 임시 창고
	public void Open_Instant_Chest(Player _player)
	{
		_player.openInventory(Festa_Instant_Chest.Instance.Call_Instant_Chest(_player));
	}
	
	// 시작하기
	/**
	 * @author jimja
	 * @version 2020. 5. 9.
	 * @apiNote 낚시 축제 개최 메뉴를 연다
	 * @param _player
	 */
	public void Open_Festa_Menu(Player _player)
	{
		// 축제가 이미 시작한 경우
		if (m_Now_Playing)
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Playing", true));
			return;
		}
		// 이전에 크래시가 난 경우
		if (FF_Fish_List.Has_Crash_Backup())
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("", true) + "§c이전에 크래시가 났어요. 개최를 하기 전에 크래시 백업을 지우세요.");
			_player.sendMessage(FF_Msg_TBL.Get_Msg("", true) + "§c이게 무슨 말인지 모르겠다면 어드민에게 요청하세요.");
			return;
		}
		
		Inventory festa_inv = Festa_Menu_Inv.Instance.Get_Inventory();
		
		// 누군가가 이용중
		if (!Festa_Menu_Inv.Instance.Can_I_See_Inventory())
		{
			_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Inv_Using", true)
					+ Festa_Menu_Inv.Instance.Get_Inventory().getViewers().get(0).getName());
			return;
		}
		_player.openInventory(festa_inv);
		Festa_Menu_Inv.Instance.Refresh_Inventory();
	}
	public void Start_Festa(Player _player)
	{
		// 잔여 시간 설정
		m_Remained_Ticks = m_Setted_Minutes * 20 * 60;
		FishFesta_Timer.Instance.Initialize(m_Remained_Ticks);
		
		// 상품 아이템 설정
		Inventory inv = Festa_Menu_Inv.Instance.Get_Inventory();
		for(int i = 1; i < 9; i++)
		{
			if(inv.getItem(i) != null)
				m_First_Prize.add(inv.getItem(i).clone());
			inv.setItem(i, new ItemStack(Material.AIR));
			
			if(inv.getItem(i + 9) != null)
				m_Second_Prize.add(inv.getItem(i + 9).clone());
			inv.setItem(i + 9, new ItemStack(Material.AIR));
			
			if(inv.getItem(i + 18) != null)
				m_Third_Prize.add(inv.getItem(i + 18).clone());
			inv.setItem(i + 18, new ItemStack(Material.AIR));
			
			if(inv.getItem(i + 27) != null)
				m_Part_Prize.add(inv.getItem(i + 27).clone());
			inv.setItem(i + 27, new ItemStack(Material.AIR));
		}
		m_Starter = _player;
		m_Now_Playing = true;

		Festa_Logger.Add_Log(this);
		FF_Fish_List.Save_Data_Backup(m_First_Prize, m_Second_Prize, m_Third_Prize);
		
		String msg = FF_Msg_TBL.Get_Msg("Notice_Festa_Started", true);
		msg = msg.replace("{name}", _player.getName());
		Bukkit.broadcastMessage(msg);

		bar.setVisible(true);
		bar.setProgress(1.0);
	}
	
	// 끝내기
	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote 축제를 종료시킨다
	 * @param _player 중지시킨 플레이어명
	 * @param _cancel 이거 취소임?
	 * @param _args 사유
	 */
	public void End_Festa(Player _player, boolean _cancel, String[] _args)
	{
		// 축제를 시작하지 않은 경우
		if (!m_Now_Playing)
		{
			if(_player != null)
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Festa_Is_Not_Playing", true));
			return;
		}
		
		// 권한이 있지만 어드민이 아닌 경우에는 개최자 본인인지를 체크
		if(_player != null)
			if(!_player.hasPermission("*") && m_Starter != _player)
			{
				_player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_No_Permission", true));
				return;
			}
		
		String reason = "";
		for(int i = 1; i < _args.length; i++)
			reason += _args[i] + " ";
		
		String msg = _cancel ? FF_Msg_TBL.Get_Msg("Notice_Festa_Cancelled", true) : FF_Msg_TBL.Get_Msg("Notice_Festa_Ended", true);
		if(reason.length() > 0)
			msg += " (" + reason + ")";
		Bukkit.broadcastMessage(msg);

		msg = Get_TopList_MSG(FF_Msg_TBL.Get_Msg("Desc_FinalTop", false), null);
		Bukkit.broadcastMessage(msg);
		
		// 상주기
		if(!_cancel)
		{
			Festa_Fishing_Data[] tops = Get_Top_List();
			List<ItemStack> starter_chest = new ArrayList<ItemStack>();
			for(int i = 0; i < tops.length; i++)
			{
				List<ItemStack> prizes = new ArrayList<ItemStack>(m_Part_Prize);
				String temp_msg = "";
				switch(i)
				{
				case 0:
					prizes.addAll(m_First_Prize);
					temp_msg = FF_Msg_TBL.Get_Msg("Notice_1st_Winner", true);
					break;
				case 1:
					prizes.addAll(m_Second_Prize);
					temp_msg = FF_Msg_TBL.Get_Msg("Notice_2nd_Winner", true);
					break;
				case 2:
					prizes.addAll(m_Third_Prize);
					temp_msg = FF_Msg_TBL.Get_Msg("Notice_3rd_Winner", true);
					break;
				default:
					temp_msg = FF_Msg_TBL.Get_Msg("Notice_Participate_Bonus", true);
					break;
				}
				if(prizes.size() == 0)
					continue;
				
				if(tops[i] == null) // 순위 리스트에 1~3등 데이터가 없는 경우
				{
					starter_chest.addAll(prizes); // 주최자 임시 인벤에 넣어두기
					continue;
				}
				
				// 있긴 있는데 수상자가 주최자라면
				if(tops[i].m_Player == m_Starter)
					starter_chest.addAll(prizes); // 주최자 임시 인벤에 넣어두기
				else
					Festa_Instant_Chest.Instance.Save_Instant_Chest(tops[i].m_Player, prizes);
				tops[i].m_Player.sendMessage(temp_msg);
				tops[i].m_Player.sendMessage(FF_Msg_TBL.Get_Msg("Notice_Prize_in_the_Chest", true));
			}
			
			if(starter_chest.size() > 0) // 주최자도 임시 인벤에 뭐 넣어야하면 넣어줄 것
				Festa_Instant_Chest.Instance.Save_Instant_Chest(m_Starter, starter_chest);
		}
		else // 취소시에는 걍 회수
		{
			List<ItemStack> prizes = new ArrayList<ItemStack>(m_Part_Prize);
			prizes.addAll(m_First_Prize);
			prizes.addAll(m_Second_Prize);
			prizes.addAll(m_Third_Prize);
			Festa_Instant_Chest.Instance.Save_Instant_Chest(m_Starter, prizes);
		}
		
		FF_Fish_List.Remove_Backup(); // 크래시 백업 지우기
		Initialize();		
	}

	// 목록
	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote 낚시를 했을 때 그에 대한 정보를 넣는다
	 * @param _player
	 * @param _item
	 * @param _name
	 * @param _size
	 */
	public void Add_Fishing_Data(Player _player, ItemStack _item, String _name, double _size)
	{
		// 최고 순위 갱신
		boolean listed = false;
		Festa_Fishing_Data first = m_Highest_Data.size() == 0 ? null : m_Highest_Data.get(0);
		boolean rank_changed = false; // 플레이어 등수 변경
		int before_player_rank = 9999; // 이전 등수
		int current_player_rank = 0; // 현재 등수
		
		for(int i = 0; i < m_Highest_Data.size(); i++)
		{
			Festa_Fishing_Data data = m_Highest_Data.get(i);
			if (data.m_Player == _player)
			{
				before_player_rank = i + 1;
				current_player_rank = before_player_rank;
				if (data.m_Size < _size)
				{
					m_Highest_Data.set(i, new Festa_Fishing_Data(_player, _item, _name, _size));
					rank_changed = true;
				}
				listed = true;
			}
		}
		if(!listed)
			m_Highest_Data.add(new Festa_Fishing_Data(_player, _item, _name, _size));
		
		// 정렬
		Collections.sort(m_Highest_Data); // 오름차순 정렬
		Collections.reverse(m_Highest_Data); // ...후 거꾸로
		
		// 1~3위 가리기
		if(rank_changed && !m_Addon_SmallRank) // 플레이어 랭크가 갱신된 경우
		{
			for(int i = 0; i < m_Highest_Data.size(); i++)
			{
				Festa_Fishing_Data data = m_Highest_Data.get(i);
				if (data.m_Player == _player)
				{
					current_player_rank = i + 1;
					if(current_player_rank == 1 && before_player_rank != 1) // 1등이 되었고 이전에 1등이 아닌 경우에만
					{
						if(m_Addon_FallbackTime || m_Addon_FallbackTimeHard) // 타임 어택 켜져 있는 경우
						{
							m_Remained_Ticks = 20 * 60 * (m_Addon_FallbackTimeHard ? 5 : 10); // 5-10분으로 초기화

							for(Player p : m_Players)
								p.sendMessage(FF_Msg_TBL.Get_Msg("", true) + " 1위가 바뀌어, 시간이 갱신되었습니다!");
						}
					}
				}
			}

			_player.sendMessage("랭크 갱신! 현재 " + current_player_rank + "위입니다!");
			if(current_player_rank < 4)
			{
				for(int i = 0; i < Math.min(3, m_Highest_Data.size()); i++)
				{
					Festa_Fishing_Data data = m_Highest_Data.get(i);
					if(current_player_rank - 1 < i && data.m_Player != _player)
						data.m_Player.sendMessage("랭크 갱신! 현재 " + (i + 1) + "위입니다!");
				}
			}
		}
		
		// 최저 순위 갱신 
		rank_changed = false;
		first = m_Lowest_Data.size() == 0 ? null : m_Lowest_Data.get(0);
		listed = false;
		before_player_rank = 9999; // 이전 등수
		current_player_rank = 0; // 현재 등수
		
		for(int i = 0; i < m_Lowest_Data.size(); i++)
		{
			Festa_Fishing_Data data = m_Lowest_Data.get(i);
			if (data.m_Player == _player)
			{
				if (data.m_Size > _size)
				{
					before_player_rank = i + 1;
					current_player_rank = before_player_rank;
					m_Lowest_Data.set(i, new Festa_Fishing_Data(_player, _item, _name, _size));
					rank_changed = true;
				}
				listed = true;
			}
		}
		if(!listed)
			m_Lowest_Data.add(new Festa_Fishing_Data(_player, _item, _name, _size));
		Collections.sort(m_Lowest_Data); // 오름차순 정렬
		
		// 1~3위 가리기
		if(rank_changed && m_Addon_SmallRank) // 플레이어 랭크가 갱신된 경우
		{
			for(int i = 0; i < m_Lowest_Data.size(); i++)
			{
				Festa_Fishing_Data data = m_Lowest_Data.get(i);
				if (data.m_Player == _player)
				{
					current_player_rank = i + 1;
					if(current_player_rank == 1 && before_player_rank != 1) // 1등이 되었고 이전에 1등이 아닌 경우에만
					{
						if(m_Addon_FallbackTime || m_Addon_FallbackTimeHard) // 타임 어택 켜져 있는 경우
						{
							m_Remained_Ticks = 20 * 60 * (m_Addon_FallbackTimeHard ? 5 : 10); // 5-10분으로 초기화
							for(Player p : m_Players)
								p.sendMessage(FF_Msg_TBL.Get_Msg("", true) + "1위가 바뀌어, 시간이 갱신되었습니다!");
						}
					}
				}
			}

			_player.sendMessage("랭크 갱신! 현재 " + current_player_rank + "위입니다!");
			if(current_player_rank < 4)
			{
				for(int i = 0; i < Math.min(3, m_Lowest_Data.size()); i++)
				{
					Festa_Fishing_Data data = m_Lowest_Data.get(i);
					if(current_player_rank - 1 < i && data.m_Player != _player)
						data.m_Player.sendMessage("랭크 갱신! 현재 " + (i + 1) + "위입니다!");
				}
			}
		}
	}

	public Festa_Fishing_Data Get_Fishing_Data(Player _player)
	{
		for(Festa_Fishing_Data data : m_Highest_Data)
		{
			if(data.m_Player == _player)
				return data;
		}
		return null;
	}
	
	public void Send_Catch_Message(Player _player, ItemStack _item, FF_Fish_Data _data, double _size)
	{
		String msg = FF_Msg_TBL.Get_Msg("Notice_Fish_Catch", true);
		msg = msg.replace("{fishname}", _data.m_Name);
		
		_player.sendMessage(msg);
		
		if(_data.m_Is_Fish)
			Add_Fishing_Data(_player, _item, _data.m_Name, _size);
	}
		
	/**
	 * @author jimja
	 * @version 2020. 5. 10.
	 * @apiNote 현재 가장 순위가 높은 사람들을 추려낸다
	 * 참여자가 아닌 데이터는 전부 제외됨
	 * @return
	 */
	public Festa_Fishing_Data[] Get_Top_List()
	{
		Festa_Fishing_Data[] datas = new Festa_Fishing_Data[4];
		int count = 0;
		if (m_Addon_SmallRank) // 작을수록 1순위인 경우
		{
			for (int i = 0; i < m_Lowest_Data.size() && count < 3; i++)
			{
				if (m_Players.contains(m_Lowest_Data.get(i).m_Player))
					datas[count++] = m_Lowest_Data.get(i);
			}
		}
		else // 높을수록 1순위인 경우
		{
			for (int i = 0; i < m_Highest_Data.size() && count < 3; i++)
			{
				if (m_Players.contains(m_Highest_Data.get(i).m_Player))
					datas[count++] = m_Highest_Data.get(i);
			}
		}

		return datas;
	}
	/**
	 * @author jimja
	 * @version 2022. 3. 24.
	 * @apiNote 자신의 순위를 확인한다
	 * @param player
	 * @return
	 */
	public int Get_Player_Rank(Festa_Fishing_Data[] _out_data, Player player)
	{
		int count = 0;
		if (m_Addon_SmallRank) // 작을수록 1순위인 경우
		{
			for (int i = 0; i < m_Lowest_Data.size(); i++)
			{
				count++;
				if (m_Lowest_Data.get(i).m_Player == player)
				{
					_out_data[0] = m_Lowest_Data.get(i);
					return count;
				}
			}
		}
		else // 높을수록 1순위인 경우
		{
			for (int i = 0; i < m_Highest_Data.size(); i++)
			{
				count++;
				if (m_Highest_Data.get(i).m_Player == player)
				{
					_out_data[0] = m_Highest_Data.get(i);
					return count;
				}
			}
		}
		return 0;
	}
}

class Festa_Fishing_Data implements Comparable<Festa_Fishing_Data>
{
	Player m_Player;
	ItemStack m_Fish_item;
	String m_Name;
	double m_Size;
	
	public Festa_Fishing_Data(Player _player, ItemStack _item, String _name, double _size)
	{
		m_Player = _player;
		m_Fish_item = _item.clone();
		m_Name = _name;
		m_Size = _size;
	}

	
	@Override
	public int compareTo(Festa_Fishing_Data o)
	{
		return m_Size > o.m_Size ? 1 : m_Size < o.m_Size ? -1 : 0;
	}
}
