package malalife.FishFesta.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import malalife.main.Mala_Life;

/**
 * @author jimja
 * @version 2020. 6. 7.
 * @apiNote 플레이어 임시 인벤토리
 * 수상했는데 아이템이 없거나 개최자가 아이템을 돌려받아야 하거나 할 때 쓰임
 */
public class Festa_Instant_Chest
{
	public static Festa_Instant_Chest Instance;
	
	public Festa_Instant_Chest()
	{
		Instance = this;
	}
	
	/**
	 * @author jimja
	 * @version 2020. 6. 7.
	 * @apiNote 해당 플레이어가 현재 임시 창고에 아이템을 얼마나 보유중인지를 반환
	 * @param _player
	 * @return
	 */
	public int Has_Instant_Chest(Player _player)
	{
		int size = 0;
		try
		{
			File original_dir = Mala_Life.plugin.getDataFolder();
			if (!original_dir.exists())
				original_dir.mkdir();
			File dir = new File(original_dir, "Fish_Festa_Instant_Chest");
			if (!dir.exists())
				dir.mkdir();
			File file = new File(dir, _player.getUniqueId().toString() + ".yml");
			if (!file.exists())
			{
				file.createNewFile();
				return 0; // 파일 없으면 false 반환
			}

			// 파일 읽기
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// 이것저것 읽고
			size = (int)c.get("size", 0);
			
			// 저장
			c.save(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return size;
	}
	
	/**
	 * @author jimja
	 * @version 2020. 6. 7.
	 * @apiNote 임시 창고 불러오기, 없으면 null 반환, 있으면 불러오고 초기화 되므로 주의!
	 * @param _player
	 * @return
	 */
	public Inventory Call_Instant_Chest(Player _player)
	{
		// 데이터 읽기
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		try
		{
			File original_dir = Mala_Life.plugin.getDataFolder();
			if (!original_dir.exists())
				original_dir.mkdir();
			File dir = new File(original_dir, "Fish_Festa_Instant_Chest");
			if (!dir.exists())
				dir.mkdir();
			File file = new File(dir, _player.getUniqueId().toString() + ".yml");
			if (!file.exists())
			{
				file.createNewFile();
				return Bukkit.createInventory(null, 9, _player.getName() + "님의 낚시 축제 임시 창고");
			}

			// 파일 읽기
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// 이것저것 읽고
			int size = (int)c.get("size", 0);
			for (int i = 0; i < size; i++)
			{
				ItemStack item = c.getItemStack("item." + i);
				list.add(item);
			}
			c.set("size", 0); // 크기 초기화
			
			// 저장
			c.save(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		Inventory inv = Bukkit.createInventory(null, (list.size() / 9 + 1) * 9, _player.getName() + "님의 낚시 축제 임시 창고");
		for(int i = 0; i < list.size(); i++)
			inv.addItem(list.get(i));
		
		return inv;
	}
	
	/**
	 * @author jimja
	 * @version 2020. 6. 7.
	 * @apiNote 임시 창고 저장
	 * @param _player
	 * @param _list
	 */
	public void Save_Instant_Chest(Player _player, List<ItemStack> _list)
	{
		try
		{
			File original_dir = Mala_Life.plugin.getDataFolder();
			if (!original_dir.exists())
				original_dir.mkdir();
			File dir = new File(original_dir, "Fish_Festa_Instant_Chest");
			if (!dir.exists())
				dir.mkdir();
			File file = new File(dir, _player.getUniqueId().toString() + ".yml");
			if (!file.exists())
				file.createNewFile();

			// 파일 읽기
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// 이것저것 쓰고
			c.set("size", _list.size());
			for (int i = 0; i < _list.size(); i++)
			{
				c.set("item." + i, _list.get(i));
			}
			
			// 저장
			c.save(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
