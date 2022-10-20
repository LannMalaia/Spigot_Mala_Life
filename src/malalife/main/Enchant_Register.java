package malalife.main;

import laylia_enchant_data.Enchant_Manager;
import malalife.Enchant.Elytra_Boost_Enchant;
import malalife.Enchant.Elytra_Start_Jump_Enchant;
import malalife.Enchant.Elytra_Static_Fly_Enchant;
import malalife.Enchant.Firework_Arrow_Enchant;
import malalife.Enchant.Firework_Bow_Enchant;
import malalife.Enchant.Flare_Bow_Enchant;
import malalife.Enchant.Hook_Shot_Enchant;

public class Enchant_Register
{
  public static void Resister()
  {
    Enchant_Manager.GetInstance().Add_Enchant(new Hook_Shot_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Elytra_Boost_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Elytra_Static_Fly_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Elytra_Start_Jump_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Firework_Arrow_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Firework_Bow_Enchant(), Mala_Life.plugin);
    Enchant_Manager.GetInstance().Add_Enchant(new Flare_Bow_Enchant(), Mala_Life.plugin);
  }
}
