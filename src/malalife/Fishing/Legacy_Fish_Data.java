package malalife.Fishing;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Legacy_Fish_Data
{
  public static ItemStack Get_High_Fish()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COD);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l고급 물고기");
    lores.add("§h§i§g§h§f§i§s§h");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_High_Salmon()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.SALMON);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l고급 연어");
    lores.add("§h§i§g§h§s§a§l§m§o§n");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_High_CrownFish()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l고급 흰동가리");
    lores.add("§h§i§g§h§c§r§o§w§n§f§i§s§h");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_Highest_Fish()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COD);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l최고급 물고기");
    lores.add("§h§i§g§h§e§s§t§f§i§s§h");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_Highest_Salmon()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.SALMON);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l최고급 연어");
    lores.add("§h§i§g§h§e§s§t§s§a§l§m§o§n");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_Highest_CrownFish()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.TROPICAL_FISH);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l최고급 흰동가리");
    lores.add("§h§i§g§h§e§s§t§c§r§o§w§n§f§i§s§h");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_High_Fish_Cooked()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COOKED_COD);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l익힌 고급 물고기");
    lores.add("§h§i§g§h§f§i§s§h§c§o§o§k§e§d");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_High_Salmon_Cooked()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COOKED_SALMON);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l익힌 고급 연어");
    lores.add("§h§i§g§h§s§a§l§m§o§n§c§o§o§k§e§d");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_Highest_Fish_Cooked()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COOKED_COD);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l익힌 최고급 물고기");
    lores.add("§h§i§g§h§e§s§t§f§i§s§h§c§o§o§k§e§d");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
  
  public static ItemStack Get_Highest_Salmon_Cooked()
  {
    List<String> lores = new ArrayList<String>();
    ItemStack item = new ItemStack(Material.COOKED_SALMON);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName("§f§l익힌 최고급 연어");
    lores.add("§h§i§g§h§e§s§t§s§a§l§m§o§n§c§o§o§k§e§d");
    meta.setLore(lores);
    item.setItemMeta(meta);
    
    return item;
  }
}
