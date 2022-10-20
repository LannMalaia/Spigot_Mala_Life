package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Arrow;

import malalife.main.InstantFireworks;

class Arrow_Bomb implements Runnable
{
  Arrow arrow;
  List<FireworkEffect> fe = new ArrayList<FireworkEffect>();
  
  public Arrow_Bomb(List<FireworkEffect> _fe, Arrow _arrow)
  {
    arrow = _arrow;
    fe = _fe;
  }
  
  public void run()
  {
    for (FireworkEffect e : fe) {
      new InstantFireworks(e, arrow.getLocation());
    }
    arrow.remove();
  }
}
