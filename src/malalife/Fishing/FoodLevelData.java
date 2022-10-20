package malalife.Fishing;

import org.bukkit.Material;

public class FoodLevelData
{
	public static int Get_FoodLevel(Material _material)
	{
		switch(_material)
		{
		case COD:
			return 2;
		case COOKED_COD:
			return 5;
		case SALMON:
			return 2;
		case COOKED_SALMON:
			return 6;
		case TROPICAL_FISH:
			return 1;
		case PUFFERFISH:
			return 1;
		case DRIED_KELP:
			return 1;
		case BEEF:
			return 3;
		case PORKCHOP:
			return 3;
		case COOKED_BEEF:
			return 8;
		case COOKED_PORKCHOP:
			return 8;
		case CHICKEN:
			return 2;
		case COOKED_CHICKEN:
			return 6;
		case ROTTEN_FLESH:
			return 4;
		case SPIDER_EYE:
			return 2;
		case RABBIT:
			return 3;
		case COOKED_RABBIT:
			return 5;
		case RABBIT_STEW:
			return 10;
		case MUTTON:
			return 2;
		case COOKED_MUTTON:
			return 6;
		case APPLE:
			return 4;
		case GOLDEN_APPLE:
			return 4;
		case ENCHANTED_GOLDEN_APPLE:
			return 4;
		case MUSHROOM_STEW:
			return 6;
		case SUSPICIOUS_STEW:
			return 6;
		case BREAD:
			return 5;
		case CAKE:
			return 2;
		case COOKIE:
			return 2;
		case MELON_SLICE:
			return 2;
		case CARROT:
			return 3;
		case GOLDEN_CARROT:
			return 6;
		case POISONOUS_POTATO:
			return 2;
		case POTATO:
			return 1;
		case BAKED_POTATO:
			return 5;
		case PUMPKIN_PIE:
			return 8;
		case BEETROOT:
			return 1;
		case BEETROOT_SOUP:
			return 6;
		case CHORUS_FRUIT:
			return 4;
		case SWEET_BERRIES:
			return 2;
		case HONEY_BOTTLE:
			return 6;
		default:
			break;
		}
		return 0;
	}
}
