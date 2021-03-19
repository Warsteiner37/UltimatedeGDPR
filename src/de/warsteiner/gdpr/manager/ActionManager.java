package de.warsteiner.gdpr.manager;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.warsteiner.gdpr.UltimatedeGDPR;
import de.warsteiner.gdpr.command.CommandAction;
import de.warsteiner.gdpr.utils.ConfigHandler;
 
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ActionManager {

	public static void getMeth(Player p) {
 
		if(ConfigHandler.getBoolean("Plugin.Blind") == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1));
		}
		
		if(ConfigHandler.get("Plugin.Mode").equalsIgnoreCase("TEXT_MESSAGE")) {
			
		 
			
			if(ConfigHandler.getBoolean("Text_Message.ClearChat")) {
				 for (int i = 0; i < 100; ) {
					 /* 32 */         p.sendMessage("§7");
					 /* 33 */         i++;
					 /*    */       }  
				 
				 List<String> raw = ConfigHandler.getStringList("Text_Message.Text");
				 
				    TextComponent message = new TextComponent(ConfigHandler.get("Text_Message.Accept_Button.Display"));
			         message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, CommandAction.getMainCommand()+" accept"));
			         message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ConfigHandler.get("Text_Message.Accept_Button.Hover"))));
			         
					    TextComponent message3 = new TextComponent(ConfigHandler.get("Text_Message.Decline_Button.Display"));
				         message3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, CommandAction.getMainCommand() + " kick"));
				         message3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ConfigHandler.get("Text_Message.Decline_Button.Hover"))));
			     
				  
				 for (int i = 0; i < raw.size(); ) {
					 
					    TextComponent message2 = new TextComponent(raw.get(i) .replaceAll("<name>", p.getName()).replaceAll("&", "§"));
					 
					 p.spigot().sendMessage(message2);
					  i++;
				 }
				 if(ConfigHandler.getBoolean("Text_Message.Accept_Button.Enabled")) {
					 p.spigot().sendMessage(message);
				 }
				 if(ConfigHandler.getBoolean("Text_Message.Decline_Button.Enabled")) {
					 p.spigot().sendMessage(message3);
				 }
			}
			
		} else {
			if(ConfigHandler.get("Plugin.Mode").equalsIgnoreCase("GUI")) {
				p.openInventory(ActionGUI.load(p));
			}
		}
	}
	
}
