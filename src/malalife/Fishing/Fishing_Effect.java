package malalife.Fishing;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gmail.nossr50.api.ExperienceAPI;

import malalife.main.Mala_Life;

public class Fishing_Effect
{
  public static boolean Check_Ocean(Player player)
  {
	  switch(player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()))
	  {
	  default:
		  break;
	  case BEACH:
	  case SNOWY_BEACH:
	  case DEEP_OCEAN:
	  case FROZEN_OCEAN:
	  case MUSHROOM_FIELDS:
	  case OCEAN:
	  case COLD_OCEAN:
	  case DEEP_COLD_OCEAN:
	  case DEEP_FROZEN_OCEAN:
	  case DEEP_LUKEWARM_OCEAN:
	  case LUKEWARM_OCEAN:
	  case WARM_OCEAN:
		  return true;
	  }
	  return false;
  }
  public static boolean Check_Cold(Player player)
  {
	  switch(player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()))
	  {
	  default:
		  break;
	  case COLD_OCEAN:
	  case DEEP_COLD_OCEAN:
	  case DEEP_FROZEN_OCEAN:
	  case FROZEN_OCEAN:
	  case FROZEN_PEAKS:
	  case FROZEN_RIVER:
	  case ICE_SPIKES:
	  case SNOWY_PLAINS:
	  case SNOWY_SLOPES:
	  case SNOWY_BEACH:
	  case SNOWY_TAIGA:
	  case TAIGA:
		  return true;
	  }
	  return false;
  }
  
  public static ItemStack Get_Fish_Data(Player player)
  {
    int fish = 60;
    int salmon = 25;
    int puffer = 13;
    int crown = 2;
    int icicle_fish = 0;
    int icicle_fish_tropical = 0;
    if (Check_Ocean(player))
    {
      fish = 35;
      salmon = 35;
      puffer = 26;
      crown = 4;
    }
    else
    {
      fish = 45;
      salmon = 45;
      puffer = 8;
      crown = 2;
    }
    if (Check_Cold(player))
    	icicle_fish = 4; // 원래 8임
    int total = fish + salmon + puffer + crown + icicle_fish;
    int getted = fish;
    int chance = (int)(Math.random() * total);
    if (chance <= icicle_fish_tropical) {
        return Fish_Data.Get_Icicle_Fish_Tropical();
      }
    if (chance <= icicle_fish) {
    	double per = Math.random() * 100.0d;
    	if (per < 0.2)
    		return Fish_Data.Get_Icicle_Fish_Huge();
    	if (per < 5.0)
    		return Fish_Data.Get_Icicle_Fish_Big();
        return Fish_Data.Get_Icicle_Fish();
      }
    if (chance <= fish) {
      return new ItemStack(Material.COD);
    }
    getted += salmon;
    if (chance <= getted) {
      return new ItemStack(Material.SALMON);
    }
    getted += puffer;
    if (chance <= getted) {
      return new ItemStack(Material.PUFFERFISH);
    }
    getted += crown;
    if (chance <= getted) {
      return new ItemStack(Material.TROPICAL_FISH);
    }
    return new ItemStack(Material.COD);
  }
  
  /*
   * 좋은 물고기를 얻었을 때의 아이템 변환
   */
  public static ItemStack Check_Good_Fish(Player player, ItemStack item, int percentage_additive)
  {
    ItemStack i = item;
    if (player.hasPermission("battleEnhancements.mcmmo.fish.absolute_sense"))
    {
      double limit = Fishing.Get_Absolute_Sense(player);
      if (Math.random() * 100.0D < limit + percentage_additive)
      {
    	  // 상급 물고기
        if (i.getType() == Material.COD) {
          i = Fish_Data.Get_High_Fish();
        }
        if (i.getType() == Material.SALMON) {
          i = Fish_Data.Get_High_Salmon();
        }
        if (i.getType() == Material.TROPICAL_FISH) {
          i = Fish_Data.Get_High_CrownFish();
        }
        // 최에상급 물고기
        if ((Fishing.Get_Level(player) >= 500) && (Math.random() * 100.0D <= 20.0D))
        {
          if (i.getType() == Material.COD) {
            i = Fish_Data.Get_Highest_Fish();
          }
          if (i.getType() == Material.SALMON) {
            i = Fish_Data.Get_Highest_Salmon();
          }
          if (i.getType() == Material.TROPICAL_FISH) {
            i = Fish_Data.Get_Highest_CrownFish();
          }
          player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 2.0F);
          player.sendMessage("§d§l이... 이 물고기는....!!?");
          return i;
        }
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 2.0F);
        player.sendMessage("§c아니, 이 물고기는...!?");
        return i;
      }
    }
    return i;
  }
  
  public static ItemStack Get_Treasure(Player player)
  {
    int level = Fishing.Get_Treasure_Tier(player);
    switch (level)
    {
    case 0: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§7으윽... 줘도 못쓸 것이 낚였다...");
        break;
      case 1: 
        player.sendMessage("§7별 볼 일 없는 것이 낚였다.");
        break;
      case 2: 
        player.sendMessage("§7기분 나쁜 것이 낚였다...");
        break;
      case 3: 
        player.sendMessage("§7이상한 냄새가 나는 것이 낚였다...");
      }
      break;
    case 1: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§f창고에 넣어두면 잊어버릴만한 것이 낚였다.");
        break;
      case 1: 
        player.sendMessage("§f별로 쓸 일 없어보이는 것이 낚였다.");
        break;
      case 2: 
        player.sendMessage("§f쓸 데 없이 부피만 차지하는 것이 낚였다.");
        break;
      case 3: 
        player.sendMessage("§f무념무상....");
      }
      break;
    case 2: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§2그냥 뭐... 그냥 그런 것이 낚였다.");
        break;
      case 1: 
        player.sendMessage("§2그나마 봐줄만한 것이 낚였다.");
        break;
      case 2: 
        player.sendMessage("§2가치가 있는 게 낚였다.");
        break;
      case 3: 
        player.sendMessage("§2꽤 괜찮은 게.... 아니라 그냥 그런 게 낚였다.");
      }
      break;
    case 3: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§b물고기보단 나은 것이 낚였다!");
        break;
      case 1: 
        player.sendMessage("§b자랑할만한 건 아니지만 꽤 좋은 것이 낚였다.");
        break;
      case 2: 
        player.sendMessage("§b월척은 아니어도 기분 좋은 것이 낚였다.");
      }
      break;
    case 4: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§a꽤 괜찮은 것이 낚였다...!");
        break;
      case 1: 
        player.sendMessage("§a뭔가 빛나는 것이 낚였다...!");
        break;
      case 2: 
        player.sendMessage("§a상당히 놀라운 것이 낚였다...!");
        break;
      case 3: 
        player.sendMessage("§b드디어 이 노동을 증명할만한게 낚인건가!");
      }
      break;
    case 5: 
      switch ((int)(Math.random() * 4.0D))
      {
      case 0: 
        player.sendMessage("§6범상찮은 것이 낚였다!");
        break;
      case 1: 
        player.sendMessage("§6절대로 흔하게 볼만한 것은 아닌 것이 낚였다!");
        break;
      case 2: 
        player.sendMessage("§6뭔가 엄청난 것이 낚였다!");
        break;
      case 3: 
        player.sendMessage("§6일생에 한 번 볼까말까...까진 아니고, 아무튼 굉장한 한 것이 낚였다!");
      }
      break;
    case 6: 
      player.sendMessage("§9§l이...... 이것은....?!");
    }
    return Mala_Life.treasures.Get_Table_Item(level, Check_Ocean(player));
  }
  
	/*
	 * 음식 섭취 시 포만감 보너스
	 * 
	 */
	public static void Food_Bonus(Player player, ItemStack item)
	{
		if (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus"))
		{
			if(FoodLevelData.Get_FoodLevel(item.getType()) > 0)
			{
				// 포만감 수치 적용
				int food_level = (int)(FoodLevelData.Get_FoodLevel(item.getType()) * Fishing.Get_Food_Bonus_Percentage(player) * 0.01D);
				// player.sendMessage("추가로 회복되는 수치 - " + food_level);
				player.setFoodLevel(player.getFoodLevel() + food_level);
			}
		}
	}

	/*
	 * 음식 섭취 시 버프 보너스
	 * 
	 */
	public static void Effect_Bonus(Player player, ItemStack item)
	{
		if (player.hasPermission("battleEnhancements.mcmmo.fish.food_bonus"))
		{
	  		int level = ExperienceAPI.getLevel(player, "fishing");
	  		
			switch(item.getType())
			{
			case SWEET_BERRIES:
				if(level >= 500)
				{
					double max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					player.setHealth(Math.min(max, player.getHealth() + 1));
				}
				break;
			case RABBIT_STEW:
				if(level >= 1000)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0));
				}
				break;
			case HONEY_BOTTLE:
				if(level >= 1500)
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 1));
				break;
			case TROPICAL_FISH:
				if(level >= 2000 && item.isSimilar(Fish_Data.Get_Highest_CrownFish()))
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60 * 10, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20 * 60 * 10, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 60 * 10, 1));
				}
				break;
			default:
				break;
			}
		}
	}
  
	/*
	 * 물고기를 먹었을 때의 효과
	 */
	public static void Get_Fish_Eat_Effect(Player player, ItemStack item)
	{
		// 이전 물고기들의 효과들
		if (item.isSimilar(Legacy_Fish_Data.Get_High_Fish()))
			player.setFoodLevel(player.getFoodLevel() + 2);
		if (item.isSimilar(Legacy_Fish_Data.Get_High_Salmon()))
			player.setFoodLevel(player.getFoodLevel() + 3);
		if (item.isSimilar(Legacy_Fish_Data.Get_High_Fish_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 4);
		if (item.isSimilar(Legacy_Fish_Data.Get_High_Salmon_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 4);
		if (item.isSimilar(Legacy_Fish_Data.Get_Highest_Fish()))
			player.setFoodLevel(player.getFoodLevel() + 5);
		if (item.isSimilar(Legacy_Fish_Data.Get_Highest_Salmon()))
			player.setFoodLevel(player.getFoodLevel() + 6);
		if (item.isSimilar(Legacy_Fish_Data.Get_Highest_Fish_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 8);
		if (item.isSimilar(Legacy_Fish_Data.Get_Highest_Salmon_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 9);
		if (item.isSimilar(Legacy_Fish_Data.Get_High_CrownFish()))
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
		}
		if (item.isSimilar(Legacy_Fish_Data.Get_Highest_CrownFish()))
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 900, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 3));
		}
		
		// 현재 물고기의 효과들
		if (item.isSimilar(Fish_Data.Get_High_Fish()))
			player.setFoodLevel(player.getFoodLevel() + 2);
		if (item.isSimilar(Fish_Data.Get_High_Salmon()))
			player.setFoodLevel(player.getFoodLevel() + 3);
		if (item.isSimilar(Fish_Data.Get_High_Fish_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 4);
		if (item.isSimilar(Fish_Data.Get_High_Salmon_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 4);
		if (item.isSimilar(Fish_Data.Get_Highest_Fish()))
			player.setFoodLevel(player.getFoodLevel() + 5);
		if (item.isSimilar(Fish_Data.Get_Highest_Salmon()))
			player.setFoodLevel(player.getFoodLevel() + 6);
		if (item.isSimilar(Fish_Data.Get_Highest_Fish_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 8);
		if (item.isSimilar(Fish_Data.Get_Highest_Salmon_Cooked()))
			player.setFoodLevel(player.getFoodLevel() + 9);
		if (item.isSimilar(Fish_Data.Get_High_CrownFish()))
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
		}
		if (item.isSimilar(Fish_Data.Get_Highest_CrownFish()))
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 900, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 3));
		}
  	}
}
