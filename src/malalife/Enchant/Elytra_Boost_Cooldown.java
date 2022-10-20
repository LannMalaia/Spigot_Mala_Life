package malalife.Enchant;

import org.bukkit.entity.Player;

import malalife.main.Mala_Life;

class Elytra_Boost_Cooldown
  implements Runnable
{
  Player player;
  
  public Elytra_Boost_Cooldown(Player _player)
  {
    player = _player;
  }
  
  public void run()
  {
    player.removeMetadata("Super_Filght_Cooldown", Mala_Life.plugin);
  }
}
