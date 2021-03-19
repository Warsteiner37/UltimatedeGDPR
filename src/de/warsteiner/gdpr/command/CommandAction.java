package de.warsteiner.gdpr.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import de.warsteiner.gdpr.UltimatedeGDPR;
import de.warsteiner.gdpr.events.PlayerFirstTimeJoinEvent;
import de.warsteiner.gdpr.utils.ConfigHandler;

public class CommandAction extends Command {
	
    public CommandAction(String cmd) { 
/*  23 */    super(cmd);
/*     */   }
    
    public static String getMainCommand() {
    	return "/"+ConfigHandler.getStringList("Plugin.Commands").get(0);
    }

	@Override
	public boolean execute(CommandSender s, String arg1, String[] args) {
		 
		Player p = (Player) s;
	 
			if(args.length == 0) {
			 
					if(p.hasPermission("Permissions.Command")) {
						p.sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.Usage")); 
					} else {
						p.sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.No_Perm"));
					}
				 
			} else 	if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase(ConfigHandler.get("Tab.SetSpawn"))) {
					if(p.hasPermission("Permissions.Command")) {
					UltimatedeGDPR.getLocationHandler().setLocation("HUB", p.getLocation());
					p.sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.Set"));
				 
				} else {
					p.sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.No_Perm"));
				}
			} else if(args[0].equalsIgnoreCase("accept")) {
				if(PlayerFirstTimeJoinEvent.list.contains(p.getName())) {
					
					 for (int i = 0; i < 100; ) {
						 /* 32 */         p.sendMessage("§7");
						 /* 33 */         i++;
						 /*    */       }  
					 
					 if(ConfigHandler.getBoolean("Plugin.Blind") == true) {
						 p.removePotionEffect(PotionEffectType.BLINDNESS);
					 }
					 
					 PlayerFirstTimeJoinEvent.list.remove(p.getName());
					
					p.sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.Accepted"));
					
					UltimatedeGDPR.getAPI().setAccepted(true, ""+p.getUniqueId());
					
				}
			} else if(args[0].equalsIgnoreCase("kick")) {
				if(PlayerFirstTimeJoinEvent.list.contains(p.getName())) {
					p.kickPlayer(ConfigHandler.get("Messages.Kick_Message"));
				}
			}
			}
			 
 
		return false;
	}
}