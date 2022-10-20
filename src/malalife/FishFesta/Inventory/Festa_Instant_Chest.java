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
 * @apiNote �÷��̾� �ӽ� �κ��丮
 * �����ߴµ� �������� ���ų� �����ڰ� �������� �����޾ƾ� �ϰų� �� �� ����
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
	 * @apiNote �ش� �÷��̾ ���� �ӽ� â�� �������� �󸶳� ������������ ��ȯ
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
				return 0; // ���� ������ false ��ȯ
			}

			// ���� �б�
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// �̰����� �а�
			size = (int)c.get("size", 0);
			
			// ����
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
	 * @apiNote �ӽ� â�� �ҷ�����, ������ null ��ȯ, ������ �ҷ����� �ʱ�ȭ �ǹǷ� ����!
	 * @param _player
	 * @return
	 */
	public Inventory Call_Instant_Chest(Player _player)
	{
		// ������ �б�
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
				return Bukkit.createInventory(null, 9, _player.getName() + "���� ���� ���� �ӽ� â��");
			}

			// ���� �б�
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// �̰����� �а�
			int size = (int)c.get("size", 0);
			for (int i = 0; i < size; i++)
			{
				ItemStack item = c.getItemStack("item." + i);
				list.add(item);
			}
			c.set("size", 0); // ũ�� �ʱ�ȭ
			
			// ����
			c.save(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		Inventory inv = Bukkit.createInventory(null, (list.size() / 9 + 1) * 9, _player.getName() + "���� ���� ���� �ӽ� â��");
		for(int i = 0; i < list.size(); i++)
			inv.addItem(list.get(i));
		
		return inv;
	}
	
	/**
	 * @author jimja
	 * @version 2020. 6. 7.
	 * @apiNote �ӽ� â�� ����
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

			// ���� �б�
			FileConfiguration c = YamlConfiguration.loadConfiguration(file);
			c.load(file);

			// �̰����� ����
			c.set("size", _list.size());
			for (int i = 0; i < _list.size(); i++)
			{
				c.set("item." + i, _list.get(i));
			}
			
			// ����
			c.save(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
