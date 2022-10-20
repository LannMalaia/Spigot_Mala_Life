package malalife.Adventure;

import malalife.main.Mala_Life;
import laylia_core.main.BossbarElement;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class Horse_Booster_Data
{
  public Player player;
  public Horse horse;
  public boolean left_clicked = false;
  public int carrot_left = 0;
  public int chain = 1;
  public BossbarElement be;
  
  public Horse_Booster_Data(Player p, Horse h)
  {
    player = p;
    horse = h;
    be = Mala_Life.core_api.bossbar_manager.createBossbar(player.getName() + "_horse_booster", "富 何胶磐", BarColor.BLUE, BarStyle.SOLID, null, 0.0D, 1.0D);
    be.Change_Title("富 何胶磐");
    be.Change_Color(BarColor.WHITE);
    be.bar.setProgress(1.0D);
    be.Change_Style(BarStyle.SEGMENTED_6);
    be.bar.addPlayer(p);
    Boost(0);
  }
  
  public void Boost(int up)
  {
    chain = Math.min(chain + up, 10);
    for (AttributeModifier am : horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers())
    {
      am.getName().contains("laylia_horse_booster");
      horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(am);
    }
    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier("laylia_horse_booster", chain / 20.0D, AttributeModifier.Operation.ADD_NUMBER));
    be.bar.setProgress(1.0D);
    be.Change_Title("∽3∽l富 何胶磐 ∽6∽l" + chain + "∽3∽lx");
    horse.getWorld().playSound(horse.getEyeLocation(), Sound.ENTITY_HORSE_AMBIENT, 1.0F, 1.0F);
  }
  
  public void Reset()
  {
    for (AttributeModifier am : horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers())
    {
      am.getName().contains("laylia_horse_booster");
      horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(am);
    }
    be.Remove_Bar();
  }
}
