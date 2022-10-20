package malalife.FishFesta;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class FF_Tab_Complete implements TabCompleter
{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args)
	{
		List<String> arr = new ArrayList<String>();
		if(cmd.getName().equalsIgnoreCase("fishfes") && args.length == 1)
		{
			if(!(sender instanceof Player))
				return arr;
			
			if("info".contains(args[0]))
				arr.add("info");
			if("join".contains(args[0]) && (sender.hasPermission("fes.fish.join") || sender.hasPermission("*")))
				arr.add("join");
			if("leave".contains(args[0]))
				arr.add("leave");
			if("top".contains(args[0]))
				arr.add("top");
			if("shop".contains(args[0]))
				arr.add("shop");
			if("chest".contains(args[0]))
				arr.add("chest");
			
			// 권한 필요
			if("start".contains(args[0]) && (sender.hasPermission("fes.fish.start") || sender.hasPermission("*")))
				arr.add("start");
			if("cancel".contains(args[0]) && (sender.hasPermission("fes.fish.cancel") || sender.hasPermission("*")))
				arr.add("cancel");
			if("end".contains(args[0]) && (sender.hasPermission("fes.fish.end") || sender.hasPermission("*")))
				arr.add("end");
			
			return arr;
		}
		return arr;
	}

}
