package malalife.FishFesta;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import laylia_core.main.Lang;

public class FF_Effect_Data
{
	private int count = 0;
	public double sizePer = 1.0;
	public double pricePer = 1.0;
	public boolean nextSilver = false;
	public boolean nextGold = false;
	public boolean nextRainbow = false;
	public boolean doubleCatch = false;
	public boolean multiCatch = false;
	public boolean nextSmall = false;
	public boolean nextBig = false;
	
	public void When_Fishing(Player _player)
	{
		count++;
		if (count % 100 == 0)
			RollEffect_100(_player);
		if (count % 20 == 0)
			RollEffect_20(_player);
	}
	
	public void RollEffect_20(Player _player)
	{
		Random rand = new Random();
		switch(rand.nextInt(10))
		{
		case 0:
			sizePer = Math.max(0.5, sizePer - 0.01);
			break;
		case 1:
			sizePer = Math.min(0.5, sizePer + 0.02);
			break;
		case 2:
			pricePer = Math.max(0.5, pricePer - 0.01);
			break;
		case 3:
			pricePer = Math.min(0.5, pricePer + 0.02);
			break;
		case 4:
			_player.setExhaustion(20.0f);
			break;
		case 5:
			nextSilver = true;
			break;
		case 6:
			nextGold = true;
			break;
		case 7:
			doubleCatch = true;
			break;
		case 8:
			// ��� �ʱ�ȭ
			break;
		case 9:
			// ��� 0:30 ����
			break;
		case 10:
			// ��� 1:00 ����
			break;
		case 11:
			// ��� ����� �ޱ� 100~10000 (����)
			break;
		}
	}
	public void RollEffect_100(Player _player)
	{
		Random rand = new Random();
		switch(rand.nextInt(10))
		{
		case 0:
			nextGold = true;
			break;
		case 1:
			nextRainbow = true;
			break;
		case 2:
			multiCatch = true;
			break;
		case 3:
			nextBig = true;
			break;
		case 4:
			nextSmall = true; 
			break;
		case 5:
			sizePer = Math.max(0.5, sizePer - 0.05);
			break;
		case 6:
			sizePer = Math.min(0.5, sizePer + 0.1);
			break;
		case 7:
			pricePer = Math.max(0.5, pricePer - 0.05);
			break;
		case 8:
			pricePer = Math.min(0.5, pricePer + 0.1);
			break;
		case 9:
			// ��� ����� �ޱ� 1000~10000 (����)
			break;
		case 10:
			// ��� ����� �ޱ� 50000 (����)
			break;
		case 11:
			// ���� �ð� 1�� ����
			break;
		}
	}
	public void RollEffect_Chaos_50(Player _player)
	{
		Random rand = new Random();
		switch(rand.nextInt(10))
		{
		case 0:
			// ���� �ð� -4~4�� ����
			break;
		case 1:
			// ���� ��� 30��
			break;
		case 2:
			// ���� ������ ����Ʈ
			break;
		case 3:
			// 
			break;
		case 4:
			nextSmall = true; 
			break;
		case 5:
			sizePer = Math.max(0.5, sizePer - 0.05);
			break;
		case 6:
			sizePer = Math.min(0.5, sizePer + 0.1);
			break;
		case 7:
			pricePer = Math.max(0.5, pricePer - 0.05);
			break;
		case 8:
			pricePer = Math.min(0.5, pricePer + 0.1);
			break;
		case 9:
			// ��� ����� �ޱ� 1000~10000 (����)
			break;
		case 10:
			// ��� ����� �ޱ� 50000 (����)
			break;
		case 11:
			// ���� �ð� ����
			break;
		}
	}
}
