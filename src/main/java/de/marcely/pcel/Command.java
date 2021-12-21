package de.marcely.pcel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
		
		if(args.length >= 3 && args[0].equalsIgnoreCase("playeffect")){
			CameraEffect effect = CameraEffect.getCameraEffect(args[1]);
			Player player = Bukkit.getPlayer(args[2]);
			
			if(player != null){
				if(effect != null){
					PlayerCameraEffectPlugin.playEffect(player, effect);
					sender.sendMessage(ChatColor.GREEN + "Playing effect " + ChatColor.DARK_GREEN + effect.name() + ChatColor.GREEN + " for " + ChatColor.DARK_GREEN + player.getName());
				}else
					sender.sendMessage(ChatColor.RED + "Unkown effect " + ChatColor.DARK_RED + args[1]);
			}else
				sender.sendMessage(ChatColor.RED + "Unkown player " + ChatColor.DARK_RED + args[0]);
			
		}else if(args.length == 2 && args[0].equalsIgnoreCase("playeffect") && sender instanceof Player){
			CameraEffect effect = CameraEffect.getCameraEffect(args[1]);
			Player player = (Player) sender;
			
			if(effect != null){
				PlayerCameraEffectPlugin.playEffect(player, effect);
				sender.sendMessage(ChatColor.GREEN + "Playing effect " + ChatColor.DARK_GREEN + effect.name() + ChatColor.GREEN + " for " + ChatColor.DARK_GREEN + player.getName());
			}else
				sender.sendMessage(ChatColor.RED + "Unkown effect " + ChatColor.DARK_RED + args[1]);
			
		}else if(args.length >= 1 && args[0].equalsIgnoreCase("effects")){
			
			sendEffects(sender);
			
		}else
			sendCmds(sender, label);
		
		return true;
	}
	
	private void sendCmds(CommandSender sender, String label){
		sender.sendMessage("" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + "======== " + ChatColor.AQUA + "Commands" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " ========");
		sender.sendMessage(ChatColor.GREEN + " /" + label + " effects");
		if(sender instanceof Player) sender.sendMessage(ChatColor.GREEN + " /" + label + " playeffect <effect>");
		sender.sendMessage(ChatColor.GREEN + " /" + label + " playeffect <effect> <player name>");
	}
	
	private void sendEffects(CommandSender sender){
		sender.sendMessage("" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + "======== " + ChatColor.GOLD + "Effects" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " ========");
		for(CameraEffect effect:CameraEffect.values())
			sender.sendMessage(ChatColor.YELLOW + " " + effect.name());
	}
}
