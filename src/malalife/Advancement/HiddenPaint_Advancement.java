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
			Bukkit.getConsoleSender().sendMessage("발전과제 로드 - " + event.getPlayer().getName());
			loadPlayer(event.getPlayer());
		}, 20);
	}
	
	@EventHandler
	public void whenLeave(PlayerQuitEvent event)
	{
		if (instance.getPlayers().contains(event.getPlayer()))
		{
			Bukkit.getConsoleSender().sendMessage("발전과제 저장 - " + event.getPlayer().getName());
			instance.saveProgress(event.getPlayer());
		}
	}
	
	Advancement root()
	{
		ItemStack icon = new ItemStack(Material.AMETHYST_SHARD);
		JSONMessage title = new JSONMessage(new TextComponent("§f말라리 비밀 그림 컬렉션!"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a말라야가 서버 곳곳에 그림을 숨겨놨어요.\n§a과연 전부 찾을 수 있을까요?"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f빙한의 제단의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a빙한의 제단에 숨겨진 그림을 찾았어요.\n§a스이쨩은~\n§a오늘도 귀여워~!!\n§7§o호시마치 스이세이 in Hololive"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f잊혀진 전장의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a잊혀진 전장에 숨겨진 그림을 찾았어요.\n§a콘누이~!\n§7§o시라누이 후레아 in Hololive"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f대해의 흔적의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a대해의 흔적에 숨겨진 그림을 찾았어요.\n§aA!\n§7§o가우르 구라 in Hololive EN"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f스폰 장소의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a스폰 장소에 숨겨진 그림을 찾았어요."));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f비투스 엔더기지의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a비투스 엔더기지에 숨겨진 그림을 찾았어요.\n§a냐하로~\n§7§o사쿠라 미코 in Hololive"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f벌목소의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a벌목소에 숨겨진 그림을 찾았어요.\n§a괄목하라!\n§7§o라플라스 다크니스 in Hololive"));
		
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
		JSONMessage title = new JSONMessage(new TextComponent("§f아루스 월드의 비밀"));
		JSONMessage desc = new JSONMessage(new TextComponent("§a아루스 월드에 숨겨진 그림을 찾았어요.\n§a(자칭)홀로라이브 청초담당\n§7§o나츠이로 마츠리 in Hololive"));
		
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
