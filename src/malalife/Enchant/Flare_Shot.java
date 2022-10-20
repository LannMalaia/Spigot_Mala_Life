package malalife.Enchant;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import malalife.main.InstantFireworks;
import malalife.main.Mala_Life;

class Flare_Shot
  implements Runnable
{
  Location loc;
  Vector vec;
  int count = 0;
  List<Player> players = new ArrayList<Player>();
  
  public Flare_Shot(Location _loc, Vector _vec)
  {
    loc = _loc;
    vec = _vec.multiply(2.0D);
    loc.add(vec);
    for (Entity entity : loc.getWorld().getNearbyEntities(loc, 30.0D, 30.0D, 30.0D)) {
      if ((entity instanceof Player)) {
        players.add((Player)entity);
      }
    }
  }
  
  public void run()
  {
    for (int i = 0; i < 1; i++)
    {
      count += 1;
      loc.add(vec);
      
      new InstantFireworks(FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.fromRGB(150 + (int)(Math.random() * 105.0D), 70, 30)).withFade(Color.BLACK).build(), loc);
    }
    if (count < 4) {
      Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 1L);
    }
  }
}
