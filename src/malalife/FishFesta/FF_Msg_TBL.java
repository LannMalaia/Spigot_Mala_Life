package malalife.FishFesta;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import malalife.main.Mala_Life;

public class FF_Msg_TBL
{
	static HashMap<String, String> map = new HashMap<String, String>();
	static HashMap<String, String> loaded_map = new HashMap<String, String>();
	public static void Load_Table()
	{
		map = new HashMap<String, String>();
		map.put("Plugin_Title", "��e���� ����");
		map.put("Inv_Festamenu", "��5��l���� ���� ���� �޴�");
		map.put("Inv_SellShopMenu", "��5��l���� �Ű� ����");

		map.put("Item_Name_First_Prize", "��6��l1���");
		map.put("Item_Desc_First_Prize", "��71��� �� ��ǰ�� �����ʿ� �����ϼ���.");
		map.put("Item_Name_Second_Prize", "��f��l2���");
		map.put("Item_Desc_Second_Prize", "��72��� �� ��ǰ�� �����ʿ� �����ϼ���.");
		map.put("Item_Name_Third_Prize", "��2��l3���");
		map.put("Item_Desc_Third_Prize", "��73��� �� ��ǰ�� �����ʿ� �����ϼ���.");
		map.put("Item_Name_Participation_Prize", "��2��l������");
		map.put("Item_Desc_Participation_Prize", "��7�����ڵ鿡�� �� ��ǰ�� �����ʿ� �����ϼ���.(���� �� �Ұ�)");

		map.put("Item_Name_TimeUp", "��b��l{minute}�� ����");
		map.put("Item_Name_TimeDown", "��4��l{minute}�� ����");
		map.put("Item_Name_Time", "��6��l������ ���� ���� �ð�");
		map.put("Item_Desc_Time", "��e{hour}�ð� {minute}�� ���� ����ſ�.");
		
		map.put("Item_Addon_Prefix", "��b��l[���]��f");

		map.put("Item_Addon_Name_Effect", "���� ����Ʈ");
		map.put("Item_Addon_ShortDesc_Effect", "���� Ȯ���� ���� ȿ��");
		map.put("Item_Addon_Desc_Effect", "��7���� Ƚ�� ���� ������ ������ ȿ���� ����.");
		map.put("Item_Addon_Desc_Effect_2", "��7���� ȿ�����, ������ ������ ������ ���ӵſ�.");

		map.put("Item_Addon_Name_Chaos", "ī���� ����Ʈ");
		map.put("Item_Addon_ShortDesc_Chaos", "????????");
		map.put("Item_Addon_Desc_Chaos", "��7�� �� ������ ũ�� ������ �ִ� ȿ������ ���� ������.");
		map.put("Item_Addon_Desc_Chaos_2", "��7������ ������ ������� ��Ż�� ���� ���� �� �־��. ����!");

		map.put("Item_Addon_Name_LuckyCatch", "��𼭳� ���");
		map.put("Item_Addon_ShortDesc_LuckyCatch", "��Ű ĳġ ����");
		map.put("Item_Addon_Desc_LuckyCatch", "��7������ ��� ����⿡ ��Ű ĳġ ���ʽ��� �����ؿ�.");
		map.put("Item_Addon_Desc_LuckyCatch_2", "��7������ �������� ������ 0.5%�� ���ʽ� Ȯ���� ���,");
		map.put("Item_Addon_Desc_LuckyCatch_3", "��7������ �������̶�� ���� 2%�� Ȯ���� ������.");

		map.put("Item_Addon_Name_FallbackTime", "Ÿ�� ����");
		map.put("Item_Addon_ShortDesc_FallbackTime", "10�а� ��� ������ ��ȸ ����");
		map.put("Item_Addon_Desc_FallbackTime", "��71���� ����� 10�а� ���ŵ��� ������ ��ȸ�� �����ؿ�.");

		map.put("Item_Addon_Name_FallbackTimeHard", "Ÿ�� ���� �ϵ� ���");
		map.put("Item_Addon_ShortDesc_FallbackTimeHard", "5�а� ��� ������ ��ȸ ����");
		map.put("Item_Addon_Desc_FallbackTimeHard", "��71���� ����� 5�а� ���ŵ��� ������ ��ȸ�� �����ؿ�.");

		map.put("Item_Addon_Name_SilverFish", "�� ���!");
		map.put("Item_Addon_ShortDesc_SilverFish", "40% Ȯ���� ���԰� * 2��");
		map.put("Item_Addon_Desc_SilverFish", "��7���� ������� ���԰��� 40% Ȯ���� 2�谡 �ſ�.");

		map.put("Item_Addon_Name_GoldenFish", "�� ���!!!");
		map.put("Item_Addon_ShortDesc_GoldenFish", "20% Ȯ���� ���԰� * 4��");
		map.put("Item_Addon_Desc_GoldenFish", "��7���� ������� ���԰��� 20% Ȯ���� 4��!!!�� �ſ�.");

		map.put("Item_Addon_Name_SmallRank", "�ݴ�� �ϴ� ��");
		map.put("Item_Addon_ShortDesc_SmallRank", "�������� 1��");
		map.put("Item_Addon_Desc_SmallRank", "��7������ �ݴ�� �ؼ�, �������� 1���� �ǰ� �ؿ�.");
		map.put("Item_Addon_Desc_SmallRank_2", "��7���� ������� ��ġ���� �ݴ�� ������ �ʾƿ�.");

		map.put("Item_Addon_Name_PinchChance", "���� �� ��ȸ");
		map.put("Item_Addon_ShortDesc_PinchChance", "�ð��� �������� ��� Ȯ�� ����");
		map.put("Item_Addon_Desc_PinchChance", "��7�ð��� �� ���� �� �� ���� Ȯ���� ������.");
		map.put("Item_Addon_Desc_PinchChance_2", "��7[�ݴ�� �ϴ� ��]�� �����ִٸ� �Ҿ ���� Ȯ���� ������.");
		map.put("Item_Addon_Desc_PinchChance_3", "��7ũ�� �������� �ʾƿ�.");
		
		map.put("Item_Addon_Desc_Mult", "��7Ȱ��ȭ�� ������ {multiplier}��� �þ��.");
		map.put("Item_Addon_Desc_On", "��a���� Ȱ��ȭ�Ǿ� �־��.");
		map.put("Item_Addon_Desc_Off", "��4���� Ȱ��ȭ���� �ʾҾ��.");
		
		map.put("Item_Name_Start", "��6��l���� ����");
		map.put("Item_Desc_Money", "��7���� ���ֿ� {money} ��ŭ�� ���� �ʿ��ؿ�.");
		map.put("Item_Desc_Addons", "��7��l[ ���� Ȱ��ȭ �� ���� ]");

		map.put("Item_Name_Sell", "��6��l�Ű�");
		map.put("Item_Desc_Sell", "��7�÷����� �������� ���� �Ⱦƹ�����.");
		map.put("Item_Desc_Sell_2", "��e���԰� : {money}");
		
		map.put("Item_Name_Sell_Refresh", "��6��l���԰� ���ΰ�ħ");
		map.put("Item_Desc_Sell_Refresh", "��7���԰��� �����ؿ�.");
		
		// ����
		map.put("Description",
				"��a------[ ���� ���� Ŀ�ǵ� ����Ʈ ]------\n"
				+ "��b /fishfes : ��e���� �� ������ �� �� �־��.\n"
				+ "��b /fishfes info : ��e������ ���� ���� ������ Ȯ���ؿ�.\n"
				+ "��b /fishfes chest : ��e��ǰ ���� â�� �����. �� �� ���� ������ ���� �͵��� �����ſ�.\n"
				+ "��b /fishfes join : ��e������ �����ؿ�.\n"
				+ "��b /fishfes leave : ��e�������� �������� ��Ż�ؿ�. ����� ����������, �������� ���ܵſ�.\n"
				+ "��b /fishfes top : ��e������ Ȯ���ؿ�.\n"
				+ "��b /fishfes shop : ��e���� �߿� ȹ���� �������� �Ű��ؿ�.\n"
				+ "��b /fishfes start : ��e������ �����ؿ�. ���� ���� ��ǰ�̳� �ð����� ������ �� �� �־��.\n"
				+ "��b /fishfes cancel <����> : ��e��ȸ�� ��ҽ��ѿ�. ��ǰ�� �����ڰ� ȸ���ؿ�.\n"
				+ "��b /fishfes end <����> : ��e��ȸ�� ������. ������ �������� ��ǰ�� ���.\n");
		map.put("Admin_Description",
				"��a------[ ���� ���� ���� Ŀ�ǵ� ����Ʈ ]------\n"
				+ "��b /fishfes admin addtable : ��e�տ� �� �������� ���� ������ ���̺� �߰��ؿ�.\n"
				+ "��b /fishfes admin addevent : ��e�տ� �� �������� �̺�Ʈ ������ ���̺� �߰��ؿ�.\n"
				+ "��b /fishfes admin reload : ��e�����۰� �޽��� ���̺��� �ٽ� �ҷ��Ϳ�. ������ ��ҵ��� �ʾƿ�.\n"
				+ "��d��l   ���̺� �߰��� �����۵��� �ݵ�� �������� ������ ��ġ����.\n");
		map.put("Desc_Info",
				"��a------[ ���� ���� ���� ]------\n"
				+ "��b������: ��a��l{name}\n"
				+ "��b�ܿ� �ð�: ��e{hour}��b�ð� ��e{minute}��b�� ��e{second}��b��\n"
				+ "��b������: ��e{player_count}��b��");
		map.put("Desc_Mods", "��a[ Ȱ��ȭ�� ���� ]");
		map.put("Desc_Prizes", "��a[ ��ǰ ��� ]");
		map.put("Desc_First", "1��");
		map.put("Desc_Second", "2��");
		map.put("Desc_Third", "3��");
		
		map.put("Desc_Top",
				"��a------[ ���� ���� ���� ���� ]------\n"
				+ "��b{first} : ��e��l{rank_1_name}��f - {rank_1_fishname}��f - ��a��l{rank_1_fishsize}��7cm\n"
				+ "��b{second} : ��f��l{rank_2_name}��f - {rank_2_fishname}��f - ��a��l{rank_2_fishsize}��7cm\n"
				+ "��b{third} : ��2��l{rank_3_name}��f - {rank_3_fishname}��f - ��a��l{rank_3_fishsize}��7cm");
		map.put("Desc_FinalTop",
				"��a[ ���� ���� ���� ���� ]\n"
				+ "��b{first} : ��e��l{rank_1_name}��f - {rank_1_fishname}��f - ��a��l{rank_1_fishsize}��7cm\n"
				+ "��b{second} : ��f��l{rank_2_name}��f - {rank_2_fishname}��f - ��a��l{rank_2_fishsize}��7cm\n"
				+ "��b{third} : ��2��l{rank_3_name}��f - {rank_3_fishname}��f - ��a��l{rank_3_fishsize}��7cm");
		map.put("No_Player", "(����)");
		map.put("Notice_Festa_Is_Playing", "��4������ �̹� �������̿���.");
		map.put("Notice_Festa_Is_Not_Playing", "��4������ ����ǰ� ���� �ʾƿ�.");
		map.put("Notice_Festa_Time_Too_Short", "��4���� �ð��� �ʹ� ª�ƿ�. ���ص� 5���� �Ѿ�� �ؿ�.");
		map.put("Notice_Festa_Not_Enough_Money", "��4���ָ� �ϱ⿡ �ʿ��� ���� �����ؿ�.");
		map.put("Notice_Festa_EconomyProblem", "��4���� ������ ������ ������ �� �������.");
		map.put("Notice_Festa_Inv_Using", "��4�������� �κ��丮�� ������̿���. : ");

		map.put("Notice_Festa_Join_OK", "��a������ �����߾��.");
		map.put("Notice_Festa_Already_Joined", "��4�̹� �������̿���!");
		
		map.put("Notice_Festa_Leave_OK", "��a�������� ��Ż�߾��. �ٽ� �������� ������ ������ ���� �� �����.");
		map.put("Notice_Festa_Already_Leaved", "��4������ ���������� �ʾƿ�!");
		
		map.put("Notice_Festa_Started", "��b{name}���� ���� ������ �����ϼ̽��ϴ�!\n������ ���Ͻø� /fishfes join �� �Է����ּ���!");
		map.put("Notice_Festa_Now_Playing", "��b���� ���� ������ �������Դϴ�.\n������ ���Ͻø� /fishfes join �� �Է����ּ���!");
		map.put("Notice_Festa_Addons", "��b�̹� ������ ����Ǵ� ����...");
		map.put("Notice_Festa_No_Addon", "��b���׿�!");
		
		map.put("Notice_Festa_Cancelled", "��4���� ������ ������ ������ ��ҵǾ����ϴ�...");
		map.put("Notice_Festa_Ended", "��4���� ������ �������ϴ�!");

		map.put("Notice_Festa_Time_Alarm", "��a{time} ���ҽ��ϴ�!");
		map.put("Hour", "�ð�");
		map.put("Minute", "��");
		map.put("Second", "��");
		
		map.put("Golden_Prefix", "�ݺ�");
		map.put("Silver_Prefix", "����");
		
		map.put("Notice_No_Permission", "��c���� �� Ŀ�ǵ带 ������ �� �����.");
		map.put("TimerBar_Title", "��b��l���� ���� ���� �ð�");
		
		map.put("Notice_Fish_Catch", "��a{fishname}��(��) ���ҽ��ϴ�!");
		map.put("Notice_Instant_Chest", "��a{count}���� �������� ���� �������� �־��. /fishfes chest Ŀ�ǵ�� �������� �����ϼ���.\n(�������� ���� �������� ������� �����ϼ���!)");
		map.put("Notice_Selled", "��a{money} ��带 �������. ��!");
		map.put("Notice_1st_Winner", "��a�̹� �������� 1���� �߾��! �����ؿ�!");
		map.put("Notice_2nd_Winner", "��a�̹� �������� 2���� �߾��! 1��� ������ 2��!");
		map.put("Notice_3rd_Winner", "��a�̹� �������� 3���� �߾��! �� �⼼�� �������� �� ���غ��ڰ��!");
		map.put("Notice_Participate_Bonus", "��a�̹� �������� �Ի������� ��������, �������� �޾Ҿ��! �������� �� �Ի��� ������ô�!");
		map.put("Notice_Prize_in_the_Chest", "��a��ǰ�� �ӽ� â�� �־��. /fishfes chest ��ɾ�� ��ǰ�� �����ϼ���.\n��c��l�� �� ���� â��� ������� �����ϼ���!");
		try
		{
			File f = Mala_Life.plugin.getDataFolder();
			if (!f.exists())
				f.mkdir();
			File f2 = new File(f, "Fish_Festa_Data");
			if (!f2.exists())
				f2.mkdir();
			File f3 = new File(f2, "msg_table.yml");
			if (!f3.exists())
			{
				f3.createNewFile();
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				c.set("Messages", map);
				
				c.save(f3);
			}
			FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
			c.load(f3);

			Iterator<String> text = map.keySet().iterator();
			while(text.hasNext())
			{
				String key = text.next();
				String value = c.getString("Messages." + key, "");
				if(!value.equals(""))
				{
					loaded_map.put(key, value);
				}
				else
				{
					Bukkit.getConsoleSender().sendMessage("[FishFesta] ���� ������ �߰��߾��. (" + key + ")");
					c.set("Messages." + key, map.get(key));
					loaded_map.put(key, map.get(key));
				}
			}
			
			c.save(f3);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @author jimja
	 * @version 2020. 6. 14.
	 * @apiNote �޽��� ���
	 * @param _with_title �տ� Ÿ��Ʋ ����
	 * @return
	 */
	public static String Get_Msg(String _key, boolean _with_title)
	{
		if(!loaded_map.containsKey(_key))
		{
			try
			{
				File f = Mala_Life.plugin.getDataFolder();
				if (!f.exists())
					f.mkdir();
				File f2 = new File(f, "Fish_Festa_Data");
				if (!f2.exists())
					f2.mkdir();
				File f3 = new File(f2, "msg_table.yml");
				if (!f3.exists())
				{
					f3.createNewFile();
					FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
					c.load(f3);

					c.set("Messages", map);
					
					c.save(f3);
				}
				FileConfiguration c = YamlConfiguration.loadConfiguration(f3);
				c.load(f3);

				String value = c.getString("Messages." + _key, "");
				if(!value.equals(""))
				{
					loaded_map.put(_key, value);
				}
				else
				{
					Bukkit.getConsoleSender().sendMessage("[FishFesta] ���� ������ �߰��߾��. (" + _key + ")");
					c.set("Messages." + _key, "");
					loaded_map.put(_key, "");
				}
				
				c.save(f3);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		String msg = loaded_map.get(_key);
		if(_with_title)
			msg = "��f[" + loaded_map.get("Plugin_Title") + "��f] " + msg;
		
		return msg;
	}
}