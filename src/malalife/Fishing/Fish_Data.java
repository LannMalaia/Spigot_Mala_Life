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
			meta.setDisplayName("��b��l����");
			lores.add("��7���ڱ� �� ����?");
			lores.add("��7������ �߰ߵȴ�.");
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
			meta.setDisplayName("��b��l����");
			lores.add("��7���ڱ� �� ����?");
			lores.add("��7������ �߰ߵȴ�.");
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
			meta.setDisplayName("��b��l����");
			lores.add("��7���ڱ� �� ����?");
			lores.add("��7������ �߰ߵȴ�.");
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
			meta.setDisplayName("��b��l����");
			lores.add("��7���ڱ� �� ����?");
			lores.add("��7������ �߰ߵȴ�.");
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
	    meta.setDisplayName("��f��l��� �뱸");
	    lores.add("��7���� �뱸�麸�� �� ū �뱸.");
	    lores.add("��7������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Salmon()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l��� ����");
	    lores.add("��7����� ū ����. ���ְ� �����.");
	    lores.add("��7������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_CrownFish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l��� �����");
	    lores.add("��7���� ���� ����� �����.");
	    lores.add("��7�߻��� �ξ� ���ϴ�.");
	    lores.add("��7������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Fish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l�ֻ�� �뱸");
	    lores.add("��7��û���� ū �뱸.");
	    lores.add("��7����� ��Ƹ��� �� ���� �� ����.");
	    lores.add("��7���� ������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Salmon()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l�ֻ�� ����");
	    lores.add("��7��� Ű���� ����. ���� �峭 �ƴϴ�.");
	    lores.add("��7���� ������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_CrownFish()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l�ֻ�� �����");
	    lores.add("��7������ ����� ȭ���� �����.");
	    lores.add("��7���� ��ü�� �߿����� Ư���� ���� ���.");
	    lores.add("��7���� ������ �߰ߵȴ�.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Fish_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l���� ��� �뱸");
	    lores.add("��7��� �뱸�� ���� ��.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_High_Salmon_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l���� ��� ����");
	    lores.add("��7��� ��� ���� ��.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Fish_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_COD);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l���� �ֻ�� �뱸");
	    lores.add("��7�ֻ�� �뱸�� ���� ��.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
	  
	  public static ItemStack Get_Highest_Salmon_Cooked()
	  {
	    List<String> lores = new ArrayList<String>();
	    ItemStack item = new ItemStack(Material.COOKED_SALMON);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("��f��l���� �ֻ�� ����");
	    lores.add("��7�ֻ�� ��� ���� ��.");
	    meta.setLore(lores);
	    item.setItemMeta(meta);
	    
	    return item;
	  }
}
