package malalife.Advancement;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.AdvancementVisibility;
import eu.endercentral.crazy_advancements.advancement.Advancement;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay.AdvancementFrame;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import eu.endercentral.crazy_advancements.packet.AdvancementsPacket;
import malalife.main.Mala_Life;
import eu.endercentral.crazy_advancements.advancement.AdvancementFlag;
import net.md_5.bungee.api.chat.TextComponent;

public class HiddenPaint_Advancement implements Listener
{
	public static AdvancementManager instance;

	Advancement root;
	
	public HiddenPaint_Advancement()
	{
		instance = new AdvancementManager(new NameKey("malalife", "hiddenpaint"));
		root = root();

		instance.addAdvancement(root,
				paint_1(root), paint_2(root), paint_3(root), paint_4(root),
				paint_5(root), paint_6(root), paint_7(root));
		instance.updateAdvancement(instance.getAdvancements().toArray(Advancement[]::new));
		instance.makeAccessible();

		for (Player player : Bukkit.getOnlinePlayers())
			loadPlayer(player);
		
		Bukkit.getPluginManager().registerEvents(this, Mala_Life.plugin);
	}
	
	public void disableServer()
	{
		for (Player player : instance.getPlayers())
			instance.saveProgress(player);
		AdvancementManager.getAccessibleManagers().remove(instance);
	}
	
	public void loadPlayer(Player player)
	{
		instance.addPlayer(player);
		instance.loadProgress(player);
		if (instance.getCriteriaProgress(player, root) == 0)
			instance.grantAdvancement(player, root);
		
		ArrayList<Advancement> advs = new ArrayList<>();
		for (Advancement adv : instance.getAdvancements())
		{
			// Bukkit.getConsoleSender().sendMessage(adv.getName() + " - " + adv.getVisibilityStatus(player));
			int criteria = instance.getCriteriaProgress(player, adv);
			if (criteria > 0)
				advs.add(adv);
		}
		AdvancementsPacket packet = new AdvancementsPacket(player, false, advs, null);
		packet.send();
	}
	
	@EventHandler
	public void whenJoin(PlayerJoinEvent event)
	{
		Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, () -> {
			Bukkit.getConsoleSender().sendMessage("�������� �ε� - " + event.getPlayer().getName());
			loadPlayer(event.getPlayer());
		}, 20);
	}
	
	@EventHandler
	public void whenLeave(PlayerQuitEvent event)
	{
		if (instance.getPlayers().contains(event.getPlayer()))
		{
			Bukkit.getConsoleSender().sendMessage("�������� ���� - " + event.getPlayer().getName());
			instance.saveProgress(event.getPlayer());
		}
	}
	
	Advancement root()
	{
		ItemStack icon = new ItemStack(Material.AMETHYST_SHARD);
		JSONMessage title = new JSONMessage(new TextComponent("��f���� ��� �׸� �÷���!"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a����߰� ���� ������ �׸��� ���ܳ����.\n��a���� ���� ã�� �� �������?"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(0f);
		display.setY(0f);
		return new Advancement(new NameKey("ml_paint", "root"), display, AdvancementFlag.SEND_WITH_HIDDEN_BOOLEAN);
	}
	Advancement paint_1(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.LIGHT_BLUE_DYE);
		JSONMessage title = new JSONMessage(new TextComponent("��f������ ������ ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a������ ���ܿ� ������ �׸��� ã�Ҿ��.\n��a����»��~\n��a���õ� �Ϳ���~!!\n��7��oȣ�ø�ġ ���̼��� in Hololive"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(1.5f);
		display.setY(-1f);
		return new Advancement(root, new NameKey("ml_paint", "1"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_2(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.SUNFLOWER);
		JSONMessage title = new JSONMessage(new TextComponent("��f������ ������ ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a������ ���忡 ������ �׸��� ã�Ҿ��.\n��a�ܴ���~!\n��7��o�ö��� �ķ��� in Hololive"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(1.5f);
		display.setY(0f);
		return new Advancement(root, new NameKey("ml_paint", "2"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_3(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.AXOLOTL_BUCKET);
		JSONMessage title = new JSONMessage(new TextComponent("��f������ ������ ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a������ ������ ������ �׸��� ã�Ҿ��.\n��aA!\n��7��o���츣 ���� in Hololive EN"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(1.5f);
		display.setY(1f);
		return new Advancement(root, new NameKey("ml_paint", "3"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_4(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.NETHER_STAR);
		JSONMessage title = new JSONMessage(new TextComponent("��f���� ����� ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a���� ��ҿ� ������ �׸��� ã�Ҿ��."));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(0f);
		display.setY(1.5f);
		return new Advancement(root, new NameKey("ml_paint", "4"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_5(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.PINK_DYE);
		JSONMessage title = new JSONMessage(new TextComponent("��f������ ���������� ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a������ ���������� ������ �׸��� ã�Ҿ��.\n��a���Ϸ�~\n��7��o����� ���� in Hololive"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(1.5f);
		display.setY(2f);
		return new Advancement(root, new NameKey("ml_paint", "5"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_6(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.ENDER_EYE);
		JSONMessage title = new JSONMessage(new TextComponent("��f������� ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a����ҿ� ������ �׸��� ã�Ҿ��.\n��a�����϶�!\n��7��o���ö� ��ũ�Ͻ� in Hololive"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(1.5f);
		display.setY(3f);
		return new Advancement(root, new NameKey("ml_paint", "6"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
	Advancement paint_7(Advancement root)
	{
		ItemStack icon = new ItemStack(Material.HONEY_BOTTLE);
		JSONMessage title = new JSONMessage(new TextComponent("��f�Ʒ罺 ������ ���"));
		JSONMessage desc = new JSONMessage(new TextComponent("��a�Ʒ罺 ���忡 ������ �׸��� ã�Ҿ��.\n��a(��Ī)Ȧ�ζ��̺� û�ʴ��\n��7��o�����̷� ������ in Hololive"));
		
		AdvancementFrame frame = AdvancementFrame.CHALLENGE;
		AdvancementVisibility visibility = AdvancementVisibility.HIDDEN;
		
		AdvancementDisplay display = new AdvancementDisplay(icon, title, desc, frame, visibility);
		display.setBackgroundTexture("textures/block/white_concrete.png");
		
		// display.setPositionOrigin();
		display.setX(2.5f);
		display.setY(2.5f);
		return new Advancement(root, new NameKey("ml_paint", "7"), display, AdvancementFlag.TOAST_AND_MESSAGE);
	}
}
