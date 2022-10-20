package malalife.Enchant;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.metadata.MetadataValue;

import malalife.main.InstantFireworks;
import malalife.main.Mala_Life;

class Arrow_Random_Fire
  implements Runnable
{
  Arrow arrow;
  int counter = 40;
  int color_counter = 0;
  boolean color_pattern = false;
  
  public Arrow_Random_Fire(Arrow _arrow)
  {
    arrow = _arrow;
  }
  
  public void run()
  {
    if (arrow == null) {
      return;
    }
    if (arrow.isDead()) {
      return;
    }
    if (counter < 0) {
      return;
    }
    counter -= 1;
    if (arrow.hasMetadata("Firework_Arrow_Color")) {
      if (((MetadataValue)arrow.getMetadata("Firework_Arrow_Color").get(0)).asString() == "rainbow")
      {
        Location loc = arrow.getLocation().clone();
        FireworkEffect fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.PURPLE).build();
        new InstantFireworks(fe, loc);
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.NAVY).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.BLUE).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.GREEN).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.LIME).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.YELLOW).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.ORANGE).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.RED).build();
        new InstantFireworks(fe, loc.add(0.0D, 0.6D, 0.0D));
        Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
        return;
      }
    }
    FireworkEffect fe = FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(getColor()).build();
    new InstantFireworks(fe, arrow.getLocation());
    Bukkit.getScheduler().runTaskLater(Mala_Life.plugin, this, 2L);
  }
  
  Color getColor()
  {
    if (arrow.hasMetadata("Firework_Arrow_Color"))
    {
      if (((MetadataValue)arrow.getMetadata("Firework_Arrow_Color").get(0)).asString() == "sakura")
      {
        if (color_counter > 0)
        {
          color_counter -= 1;
          return Color.fromRGB(255, 40, 40);
        }
        color_counter += 1;
        return Color.fromRGB(255, 200, 200);
      }
      if (((MetadataValue)arrow.getMetadata("Firework_Arrow_Color").get(0)).asString() == "rainbow")
      {
        color_counter += (color_pattern ? -1 : 1);
        if (color_counter >= 7) {
          color_pattern = true;
        }
        if (color_counter <= 0) {
          color_pattern = false;
        }
        switch (color_counter)
        {
        case 0: 
          return Color.RED;
        case 1: 
          return Color.ORANGE;
        case 2: 
          return Color.YELLOW;
        case 3: 
          return Color.LIME;
        case 4: 
          return Color.GREEN;
        case 5: 
          return Color.BLUE;
        case 6: 
          return Color.NAVY;
        case 7: 
          return Color.PURPLE;
        }
      }
    }
    return Color.fromRGB((int)(Math.random() * 255.0D), (int)(Math.random() * 255.0D), (int)(Math.random() * 255.0D));
  }
}
