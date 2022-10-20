package malalife.FishFesta;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import malalife.main.Mala_Life;

public class FF_Msg_TBL
{
	static HashMap<String, String> map = new HashMap<String, String>();
	static HashMap<String, String> loaded_map = new HashMap<String, String>();
	public static void Load_Table()
	{
		map = new HashMap<String, String>();
		map.put("Plugin_Title", "§e낚시 축제");
		map.put("Inv_Festamenu", "§5§l낚시 축제 개최 메뉴");
		map.put("Inv_SellShopMenu", "§5§l축제 매각 상점");

		map.put("Item_Name_First_Prize", "§6§l1등상");
		map.put("Item_Desc_First_Prize", "§71등에게 줄 상품을 오른쪽에 나열하세요.");
		map.put("Item_Name_Second_Prize", "§f§l2등상");
		map.put("Item_Desc_Second_Prize", "§72등에게 줄 상품을 오른쪽에 나열하세요.");
		map.put("Item_Name_Third_Prize", "§2§l3등상");
		map.put("Item_Desc_Third_Prize", "§73등에게 줄 상품을 오른쪽에 나열하세요.");
		map.put("Item_Name_Participation_Prize", "§2§l참가상");
		map.put("Item_Desc_Participation_Prize", "§7참가자들에게 줄 상품을 오른쪽에 나열하세요.(어드민 외 불가)");

		map.put("Item_Name_TimeUp", "§b§l{minute}분 증가");
		map.put("Item_Name_TimeDown", "§4§l{minute}분 감소");
		map.put("Item_Name_Time", "§6§l설정된 축제 진행 시간");
		map.put("Item_Desc_Time", "§e{hour}시간 {minute}분 동안 진행돼요.");
		
		map.put("Item_Addon_Prefix", "§b§l[모드]§f");

		map.put("Item_Addon_Name_Effect", "랜덤 이펙트");
		map.put("Item_Addon_ShortDesc_Effect", "일정 확률로 랜덤 효과");
		map.put("Item_Addon_Desc_Effect", "§7일정 횟수 낚을 때마다 랜덤한 효과를 얻어요.");
		map.put("Item_Addon_Desc_Effect_2", "§7좋은 효과라면, 축제가 끝나기 전까지 지속돼요.");

		map.put("Item_Addon_Name_Chaos", "카오스 이펙트");
		map.put("Item_Addon_ShortDesc_Chaos", "????????");
		map.put("Item_Addon_Desc_Chaos", "§7매 분 순위에 크게 영향을 주는 효과들이 펑펑 터져요.");
		map.put("Item_Addon_Desc_Chaos_2", "§7축제에 참여한 사람들의 멘탈도 펑펑 터질 수 있어요. 조심!");

		map.put("Item_Addon_Name_LuckyCatch", "어디서나 행운");
		map.put("Item_Addon_ShortDesc_LuckyCatch", "럭키 캐치 적용");
		map.put("Item_Addon_Desc_LuckyCatch", "§7축제로 얻는 물고기에 럭키 캐치 보너스를 적용해요.");
		map.put("Item_Addon_Desc_LuckyCatch_2", "§7축제에 참여하지 않으면 0.5%의 보너스 확률을 얻고,");
		map.put("Item_Addon_Desc_LuckyCatch_3", "§7축제에 참여중이라면 고정 2%의 확률을 가져요.");

		map.put("Item_Addon_Name_FallbackTime", "타임 어택");
		map.put("Item_Addon_ShortDesc_FallbackTime", "10분간 기록 유지시 대회 종료");
		map.put("Item_Addon_Desc_FallbackTime", "§71위의 기록이 10분간 갱신되지 않으면 대회를 종료해요.");

		map.put("Item_Addon_Name_FallbackTimeHard", "타임 어택 하드 모드");
		map.put("Item_Addon_ShortDesc_FallbackTimeHard", "5분간 기록 유지시 대회 종료");
		map.put("Item_Addon_Desc_FallbackTimeHard", "§71위의 기록이 5분간 갱신되지 않으면 대회를 종료해요.");

		map.put("Item_Addon_Name_SilverFish", "은 비늘!");
		map.put("Item_Addon_ShortDesc_SilverFish", "40% 확률로 매입가 * 2배");
		map.put("Item_Addon_Desc_SilverFish", "§7낚인 물고기의 매입가가 40% 확률로 2배가 돼요.");

		map.put("Item_Addon_Name_GoldenFish", "금 비늘!!!");
		map.put("Item_Addon_ShortDesc_GoldenFish", "20% 확률로 매입가 * 4배");
		map.put("Item_Addon_Desc_GoldenFish", "§7낚인 물고기의 매입가가 20% 확률로 4배!!!가 돼요.");

		map.put("Item_Addon_Name_SmallRank", "반대로 하는 날");
		map.put("Item_Addon_ShortDesc_SmallRank", "작을수록 1등");
		map.put("Item_Addon_Desc_SmallRank", "§7순위를 반대로 해서, 작을수록 1위가 되게 해요.");
		map.put("Item_Addon_Desc_SmallRank_2", "§7낚은 물고기의 가치까지 반대로 되지는 않아요.");

		map.put("Item_Addon_Name_PinchChance", "위기 속 기회");
		map.put("Item_Addon_ShortDesc_PinchChance", "시간이 없을수록 대어 확률 증가");
		map.put("Item_Addon_Desc_PinchChance", "§7시간이 얼마 없을 때 대어를 낚을 확률을 높여요.");
		map.put("Item_Addon_Desc_PinchChance_2", "§7[반대로 하는 날]이 켜져있다면 소어를 낚을 확률을 높여요.");
		map.put("Item_Addon_Desc_PinchChance_3", "§7크게 오르지는 않아요.");
		
		map.put("Item_Addon_Desc_Mult", "§7활성화시 예산이 {multiplier}배로 늘어나요.");
		map.put("Item_Addon_Desc_On", "§a현재 활성화되어 있어요.");
		map.put("Item_Addon_Desc_Off", "§4현재 활성화되지 않았어요.");
		
		map.put("Item_Name_Start", "§6§l축제 개최");
		map.put("Item_Desc_Money", "§7축제 개최에 {money} 만큼의 돈이 필요해요.");
		map.put("Item_Desc_Addons", "§7§l[ 현재 활성화 된 모드들 ]");

		map.put("Item_Name_Sell", "§6§l매각");
		map.put("Item_Desc_Sell", "§7올려놓은 물고기들을 전부 팔아버려요.");
		map.put("Item_Desc_Sell_2", "§e매입가 : {money}");
		
		map.put("Item_Name_Sell_Refresh", "§6§l매입가 새로고침");
		map.put("Item_Desc_Sell_Refresh", "§7매입가를 재계산해요.");
		
		// 설명
		map.put("Description",
				"§a------[ 낚시 축제 커맨드 리스트 ]------\n"
				+ "§b /fishfes : §e지금 이 설명을 볼 수 있어요.\n"
				+ "§b /fishfes info : §e축제에 대한 상세한 정보를 확인해요.\n"
				+ "§b /fishfes chest : §e상품 수령 창고를 열어요. 한 번 열면 꺼내지 않은 것들은 삭제돼요.\n"
				+ "§b /fishfes join : §e축제에 참여해요.\n"
				+ "§b /fishfes leave : §e참여중인 축제에서 이탈해요. 기록은 유지되지만, 순위에서 제외돼요.\n"
				+ "§b /fishfes top : §e순위를 확인해요.\n"
				+ "§b /fishfes shop : §e축제 중에 획득한 물고기들을 매각해요.\n"
				+ "§b /fishfes start : §e축제를 시작해요. 시작 전에 상품이나 시간등의 설정을 할 수 있어요.\n"
				+ "§b /fishfes cancel <사유> : §e대회를 취소시켜요. 상품은 개최자가 회수해요.\n"
				+ "§b /fishfes end <사유> : §e대회를 끝내요. 순위를 기준으로 상품을 줘요.\n");
		map.put("Admin_Description",
				"§a------[ 낚시 축제 어드민 커맨드 리스트 ]------\n"
				+ "§b /fishfes admin addtable : §e손에 든 아이템을 낚시 아이템 테이블에 추가해요.\n"
				+ "§b /fishfes admin addevent : §e손에 든 아이템을 이벤트 아이템 테이블에 추가해요.\n"
				+ "§b /fishfes admin reload : §e아이템과 메시지 테이블을 다시 불러와요. 축제는 취소되지 않아요.\n"
				+ "§d§l   테이블에 추가한 아이템들은 반드시 내부적인 수정을 거치세요.\n");
		map.put("Desc_Info",
				"§a------[ 낚시 축제 정보 ]------\n"
				+ "§b개최자: §a§l{name}\n"
				+ "§b잔여 시간: §e{hour}§b시간 §e{minute}§b분 §e{second}§b초\n"
				+ "§b참여자: §e{player_count}§b명");
		map.put("Desc_Mods", "§a[ 활성화된 모드들 ]");
		map.put("Desc_Prizes", "§a[ 상품 목록 ]");
		map.put("Desc_First", "1등");
		map.put("Desc_Second", "2등");
		map.put("Desc_Third", "3등");
		
		map.put("Desc_Top",
				"§a------[ 낚시 축제 현재 순위 ]------\n"
				+ "§b{first} : §e§l{rank_1_name}§f - {rank_1_fishname}§f - §a§l{rank_1_fishsize}§7cm\n"
				+ "§b{second} : §f§l{rank_2_name}§f - {rank_2_fishname}§f - §a§l{rank_2_fishsize}§7cm\n"
				+ "§b{third} : §2§l{rank_3_name}§f - {rank_3_fishname}§f - §a§l{rank_3_fishsize}§7cm");
		map.put("Desc_FinalTop",
				"§a[ 낚시 축제 최종 순위 ]\n"
				+ "§b{first} : §e§l{rank_1_name}§f - {rank_1_fishname}§f - §a§l{rank_1_fishsize}§7cm\n"
				+ "§b{second} : §f§l{rank_2_name}§f - {rank_2_fishname}§f - §a§l{rank_2_fishsize}§7cm\n"
				+ "§b{third} : §2§l{rank_3_name}§f - {rank_3_fishname}§f - §a§l{rank_3_fishsize}§7cm");
		map.put("No_Player", "(공석)");
		map.put("Notice_Festa_Is_Playing", "§4축제가 이미 진행중이에요.");
		map.put("Notice_Festa_Is_Not_Playing", "§4축제가 진행되고 있지 않아요.");
		map.put("Notice_Festa_Time_Too_Short", "§4축제 시간이 너무 짧아요. 못해도 5분은 넘어야 해요.");
		map.put("Notice_Festa_Not_Enough_Money", "§4개최를 하기에 필요한 돈이 부족해요.");
		map.put("Notice_Festa_EconomyProblem", "§4계좌 문제로 축제를 개최할 수 없었어요.");
		map.put("Notice_Festa_Inv_Using", "§4누군가가 인벤토리를 사용중이에요. : ");

		map.put("Notice_Festa_Join_OK", "§a축제에 참여했어요.");
		map.put("Notice_Festa_Already_Joined", "§4이미 참여중이에요!");
		
		map.put("Notice_Festa_Leave_OK", "§a축제에서 이탈했어요. 다시 참여하지 않으면 보상을 받을 수 없어요.");
		map.put("Notice_Festa_Already_Leaved", "§4축제에 참여중이지 않아요!");
		
		map.put("Notice_Festa_Started", "§b{name}님이 낚시 축제를 시작하셨습니다!\n참여를 원하시면 /fishfes join 을 입력해주세요!");
		map.put("Notice_Festa_Now_Playing", "§b현재 낚시 축제가 진행중입니다.\n참여를 원하시면 /fishfes join 을 입력해주세요!");
		map.put("Notice_Festa_Addons", "§b이번 축제에 적용되는 모드는...");
		map.put("Notice_Festa_No_Addon", "§b없네요!");
		
		map.put("Notice_Festa_Cancelled", "§4낚시 축제가 모종의 사유로 취소되었습니다...");
		map.put("Notice_Festa_Ended", "§4낚시 축제가 끝났습니다!");

		map.put("Notice_Festa_Time_Alarm", "§a{time} 남았습니다!");
		map.put("Hour", "시간");
		map.put("Minute", "분");
		map.put("Second", "초");
		
		map.put("Golden_Prefix", "금빛");
		map.put("Silver_Prefix", "은빛");
		
		map.put("Notice_No_Permission", "§c감히 그 커맨드를 실행할 수 없어요.");
		map.put("TimerBar_Title", "§b§l낚시 축제 남은 시간");
		
		map.put("Notice_Fish_Catch", "§a{fishname}을(를) 낚았습니다!");
		map.put("Notice_Instant_Chest", "§a{count}개의 수령하지 않은 아이템이 있어요. /fishfes chest 커맨드로 아이템을 수령하세요.\n(수령하지 않은 아이템은 사라지니 주의하세요!)");
		map.put("Notice_Selled", "§a{money} 골드를 벌었어요. 와!");
		map.put("Notice_1st_Winner", "§a이번 축제에서 1등을 했어요! 굉장해요!");
		map.put("Notice_2nd_Winner", "§a이번 축제에서 2등을 했어요! 1등보다 빛나는 2등!");
		map.put("Notice_3rd_Winner", "§a이번 축제에서 3등을 했어요! 이 기세로 다음에는 더 잘해보자고요!");
		map.put("Notice_Participate_Bonus", "§a이번 축제에서 입상하지는 못했지만, 참가상을 받았어요! 다음에는 꼭 입상을 노려봅시다!");
		map.put("Notice_Prize_in_the_Chest", "§a상품은 임시 창고에 있어요. /fishfes chest 명령어로 물품을 수령하세요.\n§c§l한 번 열면 창고는 비워지니 조심하세요!");
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "msg_table.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				c.set("Messages", map);
				
				c.save(f3);
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			Iterator<String> text = map.keySet().iterator();
			while(text.hasNext())
			{
				String key = text.next();
				String value = c.getString("Messages." + key, "");
				if(!value.equals(""))
				{
					loaded_map.put(key, value);
				}
				else
				{
					Bukkit.getConsoleSender().sendMessage("[FishFesta] 없는 문구를 추가했어요. (" + key + ")");
					c.set("Messages." + key, map.get(key));
					loaded_map.put(key, map.get(key));
				}
			}
			
			c.save(f3);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @author jimja
	 * @version 2020. 6. 14.
	 * @apiNote 메시지 취득
	 * @param _with_title 앞에 타이틀 붙음
	 * @return
	 */
	public static String Get_Msg(String _key, boolean _with_title)
	{
		if(!loaded_map.containsKey(_key))
		{
			try
			{
				File f = Mala_Life.plugin.getDataFolder();
				if (!f.exists())
					f.mkdir();
				File f2 = new File(f, "Fish_Festa_Data");
				if (!f2.exists())
					f2.mkdir();
				File f3 = new File(f2, "msg_table.yml");
				if (!f3.exists())
				{
					f3.createNewFile();
					FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
					c.load(f3);

					c.set("Messages", map);
					
					c.save(f3);
				}
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				String value = c.getString("Messages." + _key, "");
				if(!value.equals(""))
				{
					loaded_map.put(_key, value);
				}
				else
				{
					Bukkit.getConsoleSender().sendMessage("[FishFesta] 없는 문구를 추가했어요. (" + _key + ")");
					c.set("Messages." + _key, "");
					loaded_map.put(_key, "");
				}
				
				c.save(f3);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		String msg = loaded_map.get(_key);
		if(_with_title)
			msg = "§f[" + loaded_map.get("Plugin_Title") + "§f] " + msg;
		
		return msg;
	}
}