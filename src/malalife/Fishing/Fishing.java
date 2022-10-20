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
    temp = temp + ChatColor.GRAY + "����ġ�� ��� ��� : " + ChatColor.WHITE + "�����ϱ�" + "\n";
    temp = temp + ChatColor.GRAY + "���� : " + Get_Level(player) + " " + ChatColor.DARK_AQUA + "����ġ : " + ChatColor.YELLOW + "(" + ChatColor.GOLD + ExperienceAPI.getXP(player, "fishing") + "/" + ExperienceAPI.getXPToNextLevel(player, "fishing") + ChatColor.YELLOW + ")" + "\n";
    temp = temp + ChatColor.RED + "-----[]" + ChatColor.GREEN + "ȿ��" + ChatColor.RED + "[]-----" + "\n";
    temp = temp + ChatColor.DARK_AQUA + "���� �����͸� : " + ChatColor.GREEN + "���� ����, �̳� ��æƮ, ��� ������ ���� ��ų�� ������ ��" + "\n";
    
    temp = temp + ChatColor.DARK_AQUA + "��Ű ĳġ : " + ChatColor.GREEN + "���� Ȯ���� ���� ����⸦ �� ���� ����" + (player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(�̽���)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "Ʈ���� ĳġ : " + ChatColor.GREEN + "���� Ȯ���� ������ ����" + (player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(�̽���)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "���밨�� : " + ChatColor.GREEN + "ȿ���� ���� ����⸦ ���� ��" + (player.hasPermission("battleEnhancements.mcmmo.fish.absolute_sense") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(�̽���)").toString()) + "\n";
    temp = temp + ChatColor.DARK_AQUA + "�̽İ� : " + ChatColor.GREEN + "������ ���� �� ������ ���ʽ�" + (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus") ? "" : new StringBuilder().append(ChatColor.GRAY).append("(�̽���)").toString()) + "\n";
    temp = temp + ChatColor.RED + "-----[]" + ChatColor.GREEN + "����" + ChatColor.RED + "[]-----" + "\n";
    
    temp = temp + ChatColor.RED + "��Ű ĳġ : " + ChatColor.YELLOW + "����⸦ ������ �� " + Get_Lucky_Fish_Chance(player) + "% Ȯ���� " + Get_Lucky_Fishing_Desc(player) + "������ ����" + "\n";
    temp = temp + ChatColor.YELLOW + "  ���� ��Ű ĳġ - " + ChatColor.YELLOW + "��Ű ĳġ�� 10% Ȯ���� 5�� �� ���� ����⸦ ����" + "\n";
    temp = temp + ChatColor.RED + "Ʈ���� ĳġ : " + ChatColor.YELLOW + Get_Treasure_Chance(player) + "% Ȯ���� �������� ������, �� �� ������ ����� �������� ����" + "\n";
    temp = temp + "             ��7D��ũ ��f: " + Get_Treasure_Tier_Desc(player, 1) + " ��2C��ũ : ��f" + Get_Treasure_Tier_Desc(player, 2) + " ��bB��ũ : ��f" + Get_Treasure_Tier_Desc(player, 3) + "\n";
    temp = temp + "             ��aA��ũ : ��f" + Get_Treasure_Tier_Desc(player, 4) + " ��6S��ũ : ��f" + Get_Treasure_Tier_Desc(player, 5) + " ��9S+��ũ : ��f" + Get_Treasure_Tier_Desc(player, 6) + "\n";
    temp = temp + ChatColor.RED + "���밨�� : " + ChatColor.YELLOW + "����⸦ ������ �� " + Get_Absolute_Sense(player) + "%�� Ȯ���� ��� ����⸦ ����" + "\n";
    if ((Get_Level(player) >= 500) && (player.hasPermission("battleEnhancements.mcmmo.fish.absolute_sense"))) {
      temp = temp + ChatColor.YELLOW + "  �ʿ����� - " + ChatColor.YELLOW + "��� ����⸦ ������ �� 20%�� Ȯ���� �ְ�� ����⸦ ����" + "\n";
    } else {
      temp = temp + ChatColor.YELLOW + "  �ʿ����� - " + ChatColor.GRAY + "���밨���� ���� 500���� �̻��� �� �ڿ�����" + "\n";
    }
    temp = temp + ChatColor.RED + "�̽İ� : " + ChatColor.YELLOW + "������ �Ծ��� �� �������� " + Get_Food_Bonus_Percentage(player) + "% ��ŭ ����" + "\n";
    if ((Get_Level(player) >= 500) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
    	temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.YELLOW + "������ ���Ÿ� �Ծ��� �� ������� 1 ȸ��" + "\n";
    else
    	temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.GRAY + "�̽İ��� ���� 500���� �̻��� �� �ڿ�����" + "\n";

    if ((Get_Level(player) >= 1000) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.YELLOW + "�䳢 ��Ʃ�� �Ծ��� �� ���� ���� �� �� 1�а� ȹ��" + "\n";
    else
        temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.GRAY + "�̽İ��� ���� 1000���� �̻��� �� �ڿ�����" + "\n";
    
    if ((Get_Level(player) >= 1500) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.YELLOW + "���� �Ծ��� �� ������ �� �� 30�ʰ� ȹ��" + "\n";
      else
        temp = temp + ChatColor.YELLOW + "  ����� �� - " + ChatColor.GRAY + "�̽İ��� ���� 1500���� �̻��� �� �ڿ�����" + "\n";
    
    if ((Get_Level(player) >= 2000) && (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus")))
        temp = temp + ChatColor.YELLOW + "  ������ �� - " + ChatColor.YELLOW + "�ֻ�� ���� �Ծ��� �� �߰� ����, ���� ȣ��, ������ ������� 10�а� ȹ��" + "\n";
      else
        temp = temp + ChatColor.YELLOW + "  ������ �� - " + ChatColor.GRAY + "�̽İ��� ���� 2000���� �̻��� �� �ڿ�����" + "\n";

    player.sendMessage(temp);
  }
  
  // ���� ���� ���
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
   	* ��Ű ĳġ Ȯ��
   	* 0.2 + �̳� * 0.1 + ��� * 0.1 + �ֺ��� * 0.1(�ִ� 10��)
   	* �Ѱ�ġ�� 2%
   	*/
	public static double Get_Lucky_Fish_Chance(Player player)
	{
		double result = 0.2D;
		
		ItemStack fishing_rod = player.getInventory().getItemInMainHand(); // �տ� �� ������ ���
		if (player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch")) // ��Ű ĳġ ����°�?
		{
			// �̳� ��æƮ ��ġ�� ���, ����
			if (fishing_rod.containsEnchantment(Enchantment.LURE)) // �̳� ��æƮ ��ġ ���
				result += fishing_rod.getEnchantmentLevel(Enchantment.LURE) * 0.1;
		  
			// �÷��̾� ���� ��� ���� ���� ���, ����
			if (player.hasPotionEffect(PotionEffectType.LUCK))
				result += (player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() + 1) * 0.1;
		
			// ���� ���� ���� ���, ����
			FishFesta_Main ff = FishFesta_Main.Instance;
			if (ff.Is_Playing() && ff.m_Addon_LuckyCatch)
			{
				if(ff.m_Players.contains(player))
					result += 2.0;
				else
					result += 0.5;
			}
			
			// �ֺ� �÷��̾� �� ���ϱ�
			int player_count = 0;
		  
			for(Entity e : player.getNearbyEntities(50.0D, 50.0D, 50.0D))
			{
				if(e instanceof Player && e != player) // �ֺ� ���� �÷��̾��̵� �ڱ� �ڽ��� �ƴ� ���
					player_count = Math.min(player_count + 1, 10);
			}
			result += 0.1 * player_count;
		}
		return Math.min(2.5, result);
	}
  
	/*
	 * ��Ű ĳġ�� �ɷ��� �� ����� ������ ����
	 * ���� / 200 * 10~15 ���� (�ּ� 50, �̽���� 20)
	 */
	public static int Get_Lucky_Fishing_Count(Player player)
	{
		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
		int result = level / 200 * (10 + (int)(Math.random() * 5.0));

		// ��Ű ĳġ�� �ȹ�� ���
		if (!player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch"))
			return 20;
		
		return Math.max(50, result);
	}
  
  // �������� ������ ��Ű ĳġ ����� ����
  public static String Get_Lucky_Fishing_Desc(Player player)
  {
    int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
    // ��Ű ĳġ�� �ȹ�� ���
    if (!player.hasPermission("battleEnhancements.mcmmo.fish.lucky_catch"))
      return "20";
    
    int minimum = level / 200 * 10; // �ּ�
    int maximum = level / 200 * 15; // �ִ�
    return minimum + "~" + maximum;
  }
  
	/*
	* Ʈ���� ĳġ Ȯ��
	* 5 + �ٴ��� ��� * 3 + ��� ���� * 3
	*/
	public static int Get_Treasure_Chance(Player player)
	{
		int chance = 5;
		
		// Ʈ���� ĳġ�� �� ��� ���
		if (player.hasPermission("*"))
			return 50;
		
		if (!player.hasPermission("battleEnhancements.mcmmo.fish.treasure_catch"))
			return 5;
		
		if (player.getInventory().getItemInMainHand() != null)
		{
			// �տ� �� ��� �ٴ��� ��� ��æƮ�� �ִ� ��� Ȯ�� �߰�
			if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LUCK))
				chance += player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK) * 3;
			// ��� ������ �ɷ� �ִ� ��� Ȯ�� �߰�
			if (player.hasPotionEffect(PotionEffectType.LUCK))
				chance += player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() * 3;
		}
		return chance;
	}
  
  // Ʈ���� ĳġ Ƽ�� ���� ���
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
      return "��8X";
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
      percent = "��8X";
    } else {
      percent = percent + "%";
    }
    return percent;
  }
  
  // ������ �� Ʈ���� Ƽ� ���
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
	 * ���� ���� Ȯ��
	 * 5 + ���� / 200
	 * �ִ� 10%
	 */
  	public static int Get_Absolute_Sense(Player player)
  	{
  		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
  		return Math.min(10, 5 + level / 200);
	}
  
  	/*
  	 * ���� ���� ���ʽ�
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
  	 * ���� ���� ���ʽ� �ۼ�������
  	 * level / 10%
  	 * �ִ� 150%
  	 */
  	public static int Get_Food_Bonus_Percentage(Player player)
  	{
  		int level = ExperienceAPI.getLevel(player, PrimarySkillType.FISHING);
    
  		return Math.min(150, level / 10);
  	}
}
