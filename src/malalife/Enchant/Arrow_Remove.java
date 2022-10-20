package malalife.Enchant;

import org.bukkit.entity.Arrow;

class Arrow_Remove
  implements Runnable
{
  Arrow arrow;
  
  public Arrow_Remove(Arrow _arrow)
  {
    arrow = _arrow;
  }
  
  public void run()
  {
    arrow.remove();
  }
}
