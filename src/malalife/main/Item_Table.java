package malalife.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import net.Indyuce.mmoitems.MMOItems;

public class Item_Table
{
	// 공통값
	public int chance = 10;
	public int amount = 1;
	public List<String> random_enchant = new ArrayList<String>();
	
	// mmoitem
	public boolean is_mmoitem = false;
	public String mmoitem_ID = "CATEGORY/ID";
	
	// 일반적인 아이템
	public String name = "§f~~Sample Treasure~~";
	public Material material = Material.STONE;
	public boolean is_unbreakable = false;
	public String color = Color.AQUA.toString();
	public List<String> lores = new ArrayList<String>();
	public List<String> potion_effect = new ArrayList<String>();
	
	public void Write_Sample_Data(String data_road, FileConfiguration fc)
	{
		for (PotionEffectType pet : PotionEffectType.values())
		{
			potion_effect.add(pet.getName() + "/0/4/20/60");
		}
		for (Enchantment enchant : Enchantment.values())
		{
			random_enchant.add(/*enchant.getKey().getNamespace() + "." + */enchant.getKey().getKey()
								+ "/0/" + (enchant.getMaxLevel() - 1));
		}
		lores.add("§7§lThis is Sample Treasure.");
		lores.add("§7§lAdd your own Items!");

		fc.set(data_road + ".common.chance", chance);
		fc.set(data_road + ".common.amount", amount);
		fc.set(data_road + ".common.random_enchant_type", random_enchant);
		
		fc.set(data_road + ".mmoitem.is_mmoitem", is_mmoitem);
		fc.set(data_road + ".mmoitem.ID", mmoitem_ID);

		fc.set(data_road + ".vanilla.name", name);
		fc.set(data_road + ".vanilla.material", material.toString());
		fc.set(data_road + ".vanilla.unbreak", is_unbreakable);
		fc.set(data_road + ".vanilla.lores", lores);
		fc.set(data_road + ".vanilla.color", "255/255/255");
		fc.set(data_road + ".vanilla.potion.effects", potion_effect);
	}
  
	public void Read_Data(String data_road, FileConfiguration fc)
	{
		chance = fc.getInt(data_road + ".common.chance", 0);
		amount = fc.getInt(data_road + ".common.amount", 1);
		random_enchant = fc.getStringList(data_road + ".common.random_enchant_type");
		
		is_mmoitem = fc.getBoolean(data_road + ".mmoitem.is_mmoitem", false);
		mmoitem_ID = fc.getString(data_road + ".mmoitem.ID", "NONE:NONE");

		name = fc.getString(data_road + ".vanilla.name", "NONE");
		material = Material.valueOf(fc.getString(data_road + ".vanilla.material", "STONE"));
		is_unbreakable = fc.getBoolean(data_road + ".vanilla.unbreak", false);
		lores = fc.getStringList(data_road + ".vanilla.lores");
		color = fc.getString(data_road + ".vanilla.color");
		potion_effect = fc.getStringList(data_road + ".vanilla.potion.effects");
		
//		Bukkit.getConsoleSender().sendMessage(amount + " - " + chance);
//		Bukkit.getConsoleSender().sendMessage(is_mmoitem ? "mmoitem" : "vanilla");
//		Bukkit.getConsoleSender().sendMessage(name + " - " + material + " - " + is_unbreakable);
//		Bukkit.getConsoleSender().sendMessage("lore");
//		for (String str : lores)
//			Bukkit.getConsoleSender().sendMessage(str);
//		Bukkit.getConsoleSender().sendMessage("enchant");
//		for (String str : random_enchant)
//			Bukkit.getConsoleSender().sendMessage(str);
//		Bukkit.getConsoleSender().sendMessage("potion");
//		for (String str : potion_effect)
//			Bukkit.getConsoleSender().sendMessage(str);
	}
	
	public ItemStack Make_Item()
	{
//		Bukkit.getConsoleSender().sendMessage("make start");
		if (is_mmoitem)
		{
//			Bukkit.getConsoleSender().sendMessage("mmo");
			StringTokenizer stk = new StringTokenizer(mmoitem_ID, "/");
			ItemStack item = MMOItems.plugin.getItem(stk.nextToken(), stk.nextToken());
			Enchant_Item(item);
			
			return item;
		}

//		Bukkit.getConsoleSender().sendMessage("vanilla");
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
//		Bukkit.getConsoleSender().sendMessage("name and lore");
		
		if (!name.equals("NONE"))
			meta.setDisplayName(name);
		
//		Bukkit.getConsoleSender().sendMessage("lore");
//		for (String str : lores)
//			Bukkit.getConsoleSender().sendMessage(str);
		
		meta.setLore(lores);
		item.setItemMeta(meta);

//		Bukkit.getConsoleSender().sendMessage("color");
		Color_Item(item);
//		Bukkit.getConsoleSender().sendMessage("enchant");
		Enchant_Item(item);
//		Bukkit.getConsoleSender().sendMessage("potion");
		Potion_Item(item);
		return item;
	}
	
	public void Enchant_Item(ItemStack item)
	{
		ItemMeta meta = item.getItemMeta();
		
		for (String str : random_enchant)
			Bukkit.getConsoleSender().sendMessage(str);
		
		for (Enchantment enchant : Enchantment.values())
		{
			for (String temp : random_enchant)
			{
				StringTokenizer stk = new StringTokenizer(temp, "/");
				String ench = stk.nextToken();
//				Bukkit.getConsoleSender().sendMessage(enchant.getKey().getKey() + " == " + ench);
				if (enchant.getKey().getKey().equals(ench))
				{
//					Bukkit.getConsoleSender().sendMessage("OK");
					int min_level = Integer.parseInt(stk.nextToken());
					int max_level = Integer.parseInt(stk.nextToken());
					int level = min_level + (int)Math.round((max_level - min_level) * Math.random());
					if (level > 0)
						meta.addEnchant(enchant, level, true);
					break;
				}
			}
		}
		item.setItemMeta(meta);
	}
	public void Potion_Item(ItemStack item)
	{
		PotionMeta pm;
		if (item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta)
			pm = (PotionMeta)item.getItemMeta();
		else
		{
			switch(item.getType())
			{
			case POTION:
			case LINGERING_POTION:
			case SPLASH_POTION:
				pm = (PotionMeta)item.getItemMeta();
				break;
			default:
				return;
			}
		}
		
		// 포션 기본 값 설정
		// pm.setBasePotionData(new PotionData(PotionType.REGEN, false, true));

		// 색 설정
		StringTokenizer ctk = new StringTokenizer(color, "/");
		pm.setColor(Color.fromRGB(Integer.parseInt(ctk.nextToken()),
				Integer.parseInt(ctk.nextToken()), Integer.parseInt(ctk.nextToken())));
		
		// 포션 효과 설정
		for (String temp : potion_effect)
		{
			StringTokenizer stk = new StringTokenizer(temp, "/");
			PotionEffectType pet = PotionEffectType.getByName(stk.nextToken());
			if (pet == null)
				continue;
			int min_level = Integer.parseInt(stk.nextToken());
			int max_level = Integer.parseInt(stk.nextToken());
			int level = min_level + (int)Math.round((max_level - min_level) * Math.random());
			int min_sec = Integer.parseInt(stk.nextToken());
			int max_sec = Integer.parseInt(stk.nextToken());
			int sec = min_sec + (int)Math.round((max_sec - min_sec) * Math.random());
			if (pet == PotionEffectType.HEAL || pet == PotionEffectType.HARM)
				pm.addCustomEffect(new PotionEffect(pet, 0, level), true);
			pm.addCustomEffect(new PotionEffect(pet, sec * 20, level), true);
		}
		
		item.setItemMeta(pm);
	}
	public void Color_Item(ItemStack item)
	{
		switch(item.getType())
		{
		case LEATHER_BOOTS:
		case LEATHER_CHESTPLATE:
		case LEATHER_HELMET:
		case LEATHER_LEGGINGS:
			break;
		default:
			return;
		}
		
		LeatherArmorMeta lam = (LeatherArmorMeta)item.getItemMeta();
		// 색 설정
		StringTokenizer ctk = new StringTokenizer(color, "/");
		lam.setColor(Color.fromRGB(Integer.parseInt(ctk.nextToken()),
				Integer.parseInt(ctk.nextToken()), Integer.parseInt(ctk.nextToken())));
		
		item.setItemMeta(lam);
	}
}
