package malalife.Breaking;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.experience.PlayerProfessions;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;

public class MiningEvent implements Listener
{
	@EventHandler
	public void When_Mining(BlockBreakEvent event)
	{
		PlayerData data = MMOCore.plugin.dataProvider.getDataManager().get(event.getPlayer());
		PlayerProfessions skills = data.getCollectionSkills();
		
		// 실크 터치 무시
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		if (item != null)
		{
			if (item.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0)
				return;
		}
		
		World world = event.getBlock().getWorld();
		Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
		switch(event.getBlock().getType())
		{
		case COPPER_ORE:
		case GOLD_ORE:
		case IRON_ORE:
			if (skills.getLevel("mining") >= 20 && Math.random() < 0.0005)
			{
				ItemStack mana_steel = MMOItems.plugin.getItem(Type.get("Material"), "FINE_IRON");
				world.dropItem(loc, mana_steel);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6이 광물에서는 신기한 힘이 느껴진다.");
			}
			break;
		case DEEPSLATE_COPPER_ORE:
		case DEEPSLATE_GOLD_ORE:
		case DEEPSLATE_IRON_ORE:
			if (skills.getLevel("mining") >= 20 && Math.random() < 0.001)
			{
				ItemStack mana_steel = MMOItems.plugin.getItem(Type.get("Material"), "FINE_IRON");
				world.dropItem(loc, mana_steel);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6이 광물에서는 신기한 힘이 느껴진다.");
			}
			break;
		case LARGE_AMETHYST_BUD:
		case AMETHYST_CLUSTER:
		case DIAMOND_ORE:
		case EMERALD_ORE:
		case LAPIS_ORE:
			if (skills.getLevel("mining") >= 40 && Math.random() < 0.003)
			{
				ItemStack mana_diamond = MMOItems.plugin.getItem(Type.get("Material"), "MANA_DIAMOND");
				world.dropItem(loc, mana_diamond);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6이 보석에서는 신기한 힘이 느껴진다.");
			}
			break;
		case DEEPSLATE_DIAMOND_ORE:
		case DEEPSLATE_EMERALD_ORE:
		case DEEPSLATE_LAPIS_ORE:
			if (skills.getLevel("mining") >= 40 && Math.random() < 0.005)
			{
				ItemStack mana_diamond = MMOItems.plugin.getItem(Type.get("Material"), "MANA_DIAMOND");
				world.dropItem(loc, mana_diamond);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6이 보석에서는 신기한 힘이 느껴진다.");
			}
			break;
		case WHEAT:
		case POTATOES:
		case CARROTS:
			Ageable age = (Ageable) event.getBlock().getBlockData();
			if (age.getAge() == 7 && skills.getLevel("farming") >= 40 && Math.random() < 0.0001)
			{
				ItemStack fine_honey = MMOItems.plugin.getItem(Type.get("Material"), "FINE_HONEY");
				world.dropItem(loc, fine_honey);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6작물에서 보기 드문 것이 나타났다!");
			}
			break;
		case OAK_LOG:
		case SPRUCE_LOG:
		case BIRCH_LOG:
		case JUNGLE_LOG:
		case ACACIA_LOG:
		case DARK_OAK_LOG:
			if (skills.getLevel("woodcutting") >= 40 && Math.random() < 0.00005)
			{
				ItemStack fine_wood = MMOItems.plugin.getItem(Type.get("Material"), "FINE_WOOD");
				world.dropItem(loc, fine_wood);
				data.getPlayer().sendMessage("§f§l[§c§l!§f§l] §6이번에 캔 나무는 상당히 단단한 것 같다...");
			}
			break;
		default:
			break;
		}
	}
}
