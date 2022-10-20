package malalife.Fishing;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;

import malalife.FishFesta.FishFesta_Main;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fishing
{
  public static void Fishing_Skill_View(Player player)
  {
    String temp = "";
    temp = temp + ChatColor.RED + "-----[]" + ChatColor.GREEN + "Fishing" + ChatColor.RED + "[]-----" + "\n";
    temp = temp + ChatColor.GRAY + "경험치를 얻는 방법 : " + ChatColor.WHITE + "낚시하기" + "\n";
    temp = temp + ChatColor.GRAY + "레벨 : " + Get_Level(player) + " " + ChatColor.DARK_AQUA + "경험치 : " + ChatColor.YELLOW + "(" + ChatColor.GOLD + ExperienceAPI.getXP(player, "fishing") + "/" + ExperienceAPI.getXPToNextLevel(player, "fishing") + ChatColor.YELLOW + ")" + "\n";
    temp = temp + ChatColor.RED + "-----[]" + ChatColor.GREEN + "효과" + ChatColor.RED + "[]-----" + "\n";
    temp = temp + ChatColor.DARK_AQUA + "낚시 마스터리 : " + ChatColor.GREEN + "낚시 레벨, 미끼 인챈트, 행운 버프가 낚시 스킬에 영향을 줌" + "\n";
    
    temp = temp + ChatColor.DARK_AQUA + "럭키 캐치 : " + ChatColor.GREEN + "낮은 확률로 많은 물고기를 한 번에 낚음" + (player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(미습득)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "트레져 캐치 : " + ChatColor.GREEN + "낮은 확률로 보물을 얻음" + (player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(미습득)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "절대감각 : " + ChatColor.GREEN + "효율이 좋은 물고기를 낚게 됨" + (player.hasPermission("battleEnhancements.mcmmo.fish.absolute_sense") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(미습득)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "미식가 : " + ChatColor.GREEN + "음식을 먹을 때 포만감 보너스" + (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(미습득)").toString()) + "\n";
    temp = temp + ChatColor.RED + "-----[]" + ChatColor.GREEN + "스탯" + ChatColor.RED + "[]-----" + "\n";
    
    temp = temp + ChatColor.RED + "럭키 캐치 : " + ChatColor.YELLOW + "물고기를 낚았을 때 " + Get_Lucky_Fish_Chance(player) + "% 확률로 " + Get_Lucky_Fishing_Desc(player) + "마리를 낚음" + "\n";
    temp = temp + ChatColor.YELLOW + "  휴즈 럭키 캐치 - " + ChatColor.YELLOW + "럭키 캐치시 10% 확률로 5배 더 많은 물고기를 낚음" + "\n";
    temp = temp + ChatColor.RED + "트레져 캐치 : " + ChatColor.YELLOW + Get_Treasure_Chance(player) + "% 확률로 아이템을 낚으며, 이 때 랜덤한 등급의 아이템을 낚음" + "\n";
    temp = temp + "             §7D랭크 §f: " + Get_Treasure_Tier_Desc(player, 1) + " §2C랭크 : §f" + Get_Treasure_Tier_Desc(player, 2) + " §bB랭크 : §f" + Get_Treasure_Tier_Desc(player, 3) + "\n";
    temp = temp + "             §aA랭크 : §f" + Get_Treasure_Tier_Desc(player, 4) + " §6S랭크 : §f" + Get_Treasure_Tier_Desc(player, 5) + " §9S+랭크 : §f" + Get_Treasure_Tier_Desc(player, 6) + "\n";
    temp = temp + ChatColor.RED + "절대감각 : " + ChatColor.YELLOW + "물고기를 낚았을 때 " + Get_Absolute_Sense(player) + "%의 확률로 고급 물고기를 낚음" + "\n";
    if ((Get_Level(player) >= 500) && (player.hasPermission("battleEnhancements.mcmmo.fish.absolute_sense"))) {
      temp = temp + ChatColor.YELLOW + "  초월감각 - " + ChatColor.YELLOW + "고급 물고기를 낚았을 때 20%의 확률로 최고급 물고기를 낚음" + "\n";
    } else {
      temp = temp + ChatColor.YELLOW + "  초월감각 - " + ChatColor.GRAY + "절대감각을 배우고 500레벨 이상일 때 자연습득" + "\n";
    }
    temp = temp + ChatColor.RED + "미식가 : " + ChatColor.YELLOW + "음식을 먹었을 때 포만감이 " + Get_Food_Bonus_Percentage(player) + "% 만큼 증가" + "\n";
    if ((Get_Level(player) >= 500) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
    	temp = temp + ChatColor.YELLOW + "  농업의 맛 - " + ChatColor.YELLOW + "달콤한 열매를 먹었을 때 생명력을 1 회복" + "\n";
    else
    	temp = temp + ChatColor.YELLOW + "  농업의 맛 - " + ChatColor.GRAY + "미식가를 배우고 500레벨 이상일 때 자연습득" + "\n";

    if ((Get_Level(player) >= 1000) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  사냥의 맛 - " + ChatColor.YELLOW + "토끼 스튜를 먹었을 때 힘과 저항 Ⅰ 를 1분간 획득" + "\n";
    else
        temp = temp + ChatColor.YELLOW + "  사냥의 맛 - " + ChatColor.GRAY + "미식가를 배우고 1000레벨 이상일 때 자연습득" + "\n";
    
    if ((Get_Level(player) >= 1500) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  양봉의 맛 - " + ChatColor.YELLOW + "꿀을 먹었을 때 성급함 Ⅱ 를 30초간 획득" + "\n";
      else
        temp = temp + ChatColor.YELLOW + "  양봉의 맛 - " + ChatColor.GRAY + "미식가를 배우고 1500레벨 이상일 때 자연습득" + "\n";
    
    if ((Get_Level(player) >= 2000) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  낚시의 맛 - " + ChatColor.YELLOW + "최상급 열대어를 먹었을 때 야간 투시, 수중 호흡, 돌고래의 우아함을 10분간 획득" + "\n";
      else
        temp = temp + ChatColor.YELLOW + "  낚시의 맛 - " + ChatColor.GRAY + "미식가를 배우고 2000레벨 이상일 때 자연습득" + "\n";

    player.sendMessage(temp);
  }
  
  // 낚시 레벨 취득
  public static int Get_Level(Player player)
  {
    return ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
  }
  
  // 
  public static String Get_Fishing_Mastery_Chance_Desc(Player player)
  {
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    int luck = 0;
    int lure = 0;
    if (player.hasPotionEffect(PotionEffectType.LUCK)) {
      for (PotionEffect pe : player.getActivePotionEffects()) {
        if (pe.getType().getName() == PotionEffectType.LUCK.getName()) {
          luck = Math.min(3, pe.getAmplifier());
        }
      }
    }
    if (player.getInventory().getItemInMainHand() != null)
    {
      ItemStack rod = player.getInventory().getItemInMainHand();
      if (player.hasPermission("battleEnhancements.mcmmo.fish.rod_mastery")) {
        if (rod.containsEnchantment(Enchantment.LURE)) {
          lure = Math.min(6, rod.getEnchantmentLevel(Enchantment.LURE));
        }
      }
    }
    int min = level / 250 + luck * 1 + lure * 0;
    int max = level / 250 + luck * 3 + lure * 4;
    
    return min + "~" + max;
  }
  
  public static int Get_Fishing_Mastery_Chance(Player player)
  {
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    int luck = 0;
    int lure = 0;
    if (player.hasPotionEffect(PotionEffectType.LUCK)) {
      for (PotionEffect pe : player.getActivePotionEffects()) {
        if (pe.getType().getName() == PotionEffectType.LUCK.getName()) {
          luck = Math.min(3, pe.getAmplifier());
        }
      }
    }
    if (player.getInventory().getItemInMainHand() != null)
    {
      ItemStack rod = player.getInventory().getItemInMainHand();
      if (player.hasPermission("battleEnhancements.mcmmo.fish.rod_mastery")) {
        if (rod.containsEnchantment(Enchantment.LURE)) {
          lure = Math.min(6, rod.getEnchantmentLevel(Enchantment.LURE));
        }
      }
    }
    int luck_chance = luck * (1 + (int)(Math.random() * 3.0D));
    int lure_chance = lure * (int)(Math.random() * 5.0D);
    
    int chance = level / 250 + luck_chance + lure_chance;
    
    return chance;
  }
  
  	/*
   	* 럭키 캐치 확률
   	* 0.2 + 미끼 * 0.1 + 행운 * 0.1 + 주변인 * 0.1(최대 10명)
   	* 한계치는 2%
   	*/
	public static double Get_Lucky_Fish_Chance(Player player)
	{
		double result = 0.2D;
		
		ItemStack fishing_rod = player.getInventory().getItemInMainHand(); // 손에 든 아이템 취득
		if (player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch")) // 럭키 캐치 배웠는가?
		{
			// 미끼 인챈트 수치를 취득, 적용
			if (fishing_rod.containsEnchantment(Enchantment.LURE)) // 미끼 인챈트 수치 취득
				result += fishing_rod.getEnchantmentLevel(Enchantment.LURE) * 0.1;
		  
			// 플레이어 현재 행운 버프 상태 취득, 적용
			if (player.hasPotionEffect(PotionEffectType.LUCK))
				result += (player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() + 1) * 0.1;
		
			// 낚시 축제 상태 취득, 적용
			FishFesta_Main ff = FishFesta_Main.Instance;
			if (ff.Is_Playing() && ff.m_Addon_LuckyCatch)
			{
				if(ff.m_Players.contains(player))
					result += 2.0;
				else
					result += 0.5;
			}
			
			// 주변 플레이어 수 구하기
			int player_count = 0;
		  
			for(Entity e : player.getNearbyEntities(50.0D, 50.0D, 50.0D))
			{
				if(e instanceof Player && e != player) // 주변 인이 플레이어이되 자기 자신은 아닌 경우
					player_count = Math.min(player_count + 1, 10);
			}
			result += 0.1 * player_count;
		}
		return Math.min(2.5, result);
	}
  
	/*
	 * 럭키 캐치가 걸렸을 때 물고기 갯수를 설정
	 * 레벨 / 200 * 10~15 마리 (최소 50, 미습득시 20)
	 */
	public static int Get_Lucky_Fishing_Count(Player player)
	{
		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
		int result = level / 200 * (10 + (int)(Math.random() * 5.0));

		// 럭키 캐치를 안배운 경우
		if (!player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch"))
			return 20;
		
		return Math.max(50, result);
	}
  
  // 설명으로 나오는 럭키 캐치 물고기 갯수
  public static String Get_Lucky_Fishing_Desc(Player player)
  {
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
    // 럭키 캐치를 안배운 경우
    if (!player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch"))
      return "20";
    
    int minimum = level / 200 * 10; // 최소
    int maximum = level / 200 * 15; // 최대
    return minimum + "~" + maximum;
  }
  
	/*
	* 트레져 캐치 확률
	* 5 + 바다의 행운 * 3 + 행운 버프 * 3
	*/
	public static int Get_Treasure_Chance(Player player)
	{
		int chance = 5;
		
		// 트레져 캐치를 안 배운 경우
		if (player.hasPermission("*"))
			return 50;
		
		if (!player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch"))
			return 5;
		
		if (player.getInventory().getItemInMainHand() != null)
		{
			// 손에 든 장비에 바다의 행운 인챈트가 있는 경우 확률 추가
			if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LUCK))
				chance += player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK) * 3;
			// 행운 버프에 걸려 있는 경우 확률 추가
			if (player.hasPotionEffect(PotionEffectType.LUCK))
				chance += player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() * 3;
		}
		return chance;
	}
  
  // 트레져 캐치 티어 설명 취득
  public static String Get_Treasure_Tier_Desc(Player player, int tier)
  {
    String percent = "";
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    if (player.getInventory().getItemInMainHand() != null) {
      if ((player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LURE)) && (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LUCK))) {
        level += Math.min(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LURE), player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK)) * 50;
      }
    }
    double ss = 0.0D;double s = 0.0D;double a = 0.0D;double b = 0.0D;double c = 0.0D;double d = 0.0D;
    d = Math.max(0.0D, Math.min(150.0D, level / 5.0D));
    c = Math.max(0.0D, Math.min(80.0D, (level - 250) / 15.0D));
    b = Math.max(0.0D, Math.min(40.0D, (level - 500) / 25.0D));
    a = Math.max(0.0D, Math.min(18.0D, (level - 750) / 50.0D));
    s = Math.max(0.0D, Math.min(7.0D, (level - 1400) / 100.0D));
    ss = Math.max(0.0D, Math.min(3.0D, (level - 1750) / 200.0D));
    double total = ss + s + a + b + c + d;
    if (!player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch")) {
      return "§8X";
    }
    switch (tier)
    {
    case 1: 
      percent = percent + Math.round(d / total * 1000.0D) / 10.0D;
      break;
    case 2: 
      percent = percent + Math.round(c / total * 1000.0D) / 10.0D;
      break;
    case 3: 
      percent = percent + Math.round(b / total * 1000.0D) / 10.0D;
      break;
    case 4: 
      percent = percent + Math.round(a / total * 1000.0D) / 10.0D;
      break;
    case 5: 
      percent = percent + Math.round(s / total * 1000.0D) / 10.0D;
      break;
    case 6: 
      percent = percent + Math.round(ss / total * 1000.0D) / 10.0D;
    }
    if (Double.parseDouble(percent) <= 0.0D) {
      percent = "§8X";
    } else {
      percent = percent + "%";
    }
    return percent;
  }
  
  // 낚았을 때 트레져 티어를 취득
  public static int Get_Treasure_Tier(Player player)
  {
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    if (player.getInventory().getItemInMainHand() != null) {
      if ((player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LURE)) && (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LUCK))) {
        level += Math.min(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LURE), player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK)) * 50;
      }
    }
    double ss = 0.0D;double s = 0.0D;double a = 0.0D;double b = 0.0D;double c = 0.0D;double d = 0.0D;
    d = Math.max(0.0D, Math.min(150.0D, level / 5.0D));
    c = Math.max(0.0D, Math.min(80.0D, (level - 250) / 15.0D));
    b = Math.max(0.0D, Math.min(40.0D, (level - 500) / 25.0D));
    a = Math.max(0.0D, Math.min(18.0D, (level - 750) / 50.0D));
    s = Math.max(0.0D, Math.min(7.0D, (level - 1400) / 100.0D));
    ss = Math.max(0.0D, Math.min(3.0D, (level - 1750) / 200.0D));
    double total = Math.max(100.0D, ss + s + a + b + c + d);
    double chance = Math.random() * total;
    double getted_total = ss;
    if (!player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch")) {
      return 0;
    }
    if (chance < getted_total) {
      return 6;
    }
    getted_total += s;
    if (chance < getted_total) {
      return 5;
    }
    getted_total += a;
    if (chance < getted_total) {
      return 4;
    }
    getted_total += b;
    if (chance < getted_total) {
      return 3;
    }
    getted_total += c;
    if (chance < getted_total) {
      return 2;
    }
    getted_total += d;
    if (chance < getted_total) {
      return 1;
    }
    return 0;
  }
  

	/*
	 * 절대 감각 확률
	 * 5 + 레벨 / 200
	 * 최대 10%
	 */
  	public static int Get_Absolute_Sense(Player player)
  	{
  		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
  		return Math.min(10, 5 + level / 200);
	}
  
  	/*
  	 * 음식 섭취 보너스
  	 * 
  	 */
  	@Deprecated
  	public static int Get_Food_Bonus(Player player, String Type)
  	{
	    if (Type.contains("fish")) {
	      return Math.min(5, Get_Level(player) / 350);
	    }
	    if (Type.contains("meat")) {
	      return Math.min(5, Get_Level(player) / 400);
	    }
	    if (Type.contains("else")) {
	      return Math.min(4, Get_Level(player) / 500);
	    }
	    return 0;
  	}
  	
  	/*
  	 * 음식 섭취 보너스 퍼센테이지
  	 * level / 10%
  	 * 최대 150%
  	 */
  	public static int Get_Food_Bonus_Percentage(Player player)
  	{
  		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
  		return Math.min(150, level / 10);
  	}
}
