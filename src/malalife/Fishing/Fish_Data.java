package malalife.Fishing;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;

public class Fish_Data
{
	public static ItemStack Get_Icicle_Fish()
	{
		ItemStack item = MMOItems.plugin.getItem(Type.CONSUMABLE, "ICICLE_FISH");
		if (item == null)
		{
			List<String> lores = new ArrayList<String>();
			item = new ItemStack(Material.COD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§b§l빙어");
			lores.add("§7갑자기 왠 빙어?");
			lores.add("§7가끔씩 발견된다.");
			meta.setLore(lores);
			item.setItemMeta(meta);
		}   
		return item;
	}
	public static ItemStack Get_Icicle_Fish_Big()
	{
		ItemStack item = MMOItems.plugin.getItem(Type.CONSUMABLE, "ICICLE_FISH_BIG");
		if (item == null)
		{
			List<String> lores = new ArrayList<String>();
			item = new ItemStack(Material.COD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§b§l빙어");
			lores.add("§7갑자기 왠 빙어?");
			lores.add("§7가끔씩 발견된다.");
			meta.setLore(lores);
			item.setItemMeta(meta);
		}   
		return item;
	}	
	public static ItemStack Get_Icicle_Fish_Huge()
	{
		ItemStack item = MMOItems.plugin.getItem(Type.CONSUMABLE, "ICICLE_FISH_HUGE");
		if (item == null)
		{
			List<String> lores = new ArrayList<String>();
			item = new ItemStack(Material.COD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§b§l빙어");
			lores.add("§7갑자기 왠 빙어?");
			lores.add("§7가끔씩 발견된다.");
			meta.setLore(lores);
			item.setItemMeta(meta);
		}   
		return item;
	}	
	public static ItemStack Get_Icicle_Fish_Tropical()
	{
		ItemStack item = MMOItems.plugin.getItem(Type.CONSUMABLE, "ICICLE_FISH_STRANGE");
		if (item == null)
		{
			List<String> lores = new ArrayList<String>();
			item = new ItemStack(Material.COD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§b§l빙어");
			lores.add("§7갑자기 왠 빙어?");
			lores.add("§7가끔씩 발견된다.");
			meta.setLore(lores);
			item.setItemMeta(meta);
		}   
		return item;
	}		
	
	  public static ItemStack Get_High_Fish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l상급 대구");
	    lores.add("§7기존 대구들보다 더 큰 대구.");
	    lores.add("§7가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Salmon()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l상급 연어");
	    lores.add("§7상당히 큰 연어. 맛있게 생겼다.");
	    lores.add("§7가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_CrownFish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l상급 열대어");
	    lores.add("§7제법 보기 어려운 열대어.");
	    lores.add("§7발색이 훨씬 진하다.");
	    lores.add("§7가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Fish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l최상급 대구");
	    lores.add("§7엄청나게 큰 대구.");
	    lores.add("§7사람도 잡아먹을 수 있을 것 같다.");
	    lores.add("§7아주 가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Salmon()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l최상급 연어");
	    lores.add("§7사람 키만한 연어. 힘이 장난 아니다.");
	    lores.add("§7아주 가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_CrownFish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l최상급 열대어");
	    lores.add("§7몸색이 상당히 화려한 열대어.");
	    lores.add("§7같은 개체들 중에서도 특히나 눈에 띈다.");
	    lores.add("§7아주 가끔씩 발견된다.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Fish_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l익힌 상급 대구");
	    lores.add("§7상급 대구를 익힌 것.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Salmon_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l익힌 상급 연어");
	    lores.add("§7상급 연어를 익힌 것.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Fish_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l익힌 최상급 대구");
	    lores.add("§7최상급 대구를 익힌 것.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Salmon_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("§f§l익힌 최상급 연어");
	    lores.add("§7최상급 연어를 익힌 것.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
}
