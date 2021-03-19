package de.warsteiner.gdpr.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import de.warsteiner.gdpr.UltimatedeGDPR;
import de.warsteiner.gdpr.command.CommandAction;
import de.warsteiner.gdpr.manager.ActionManager;
import de.warsteiner.gdpr.utils.ConfigHandler;

public class InventoryClickEventIfList  implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		 
		if (e.getClickedInventory() == null) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		
		if(e.getView().getTitle() == null) {
			return; 
		}
 
		Player p = (Player) e.getWhoClicked();
		
		if(PlayerFirstTimeJoinEvent.list.contains(p.getName())) {
			e.setCancelled(true);
			
			
			if(e.getView().getTitle().equalsIgnoreCase(ConfigHandler.get("GUI.GUI_Name"))) {
				String item = e.getCurrentItem().getItemMeta().getDisplayName();
				if(item != null) {
					if(item.equalsIgnoreCase(ConfigHandler.get("GUI.Items.Accept.Display"))) {
						p.performCommand(CommandAction.getMainCommand().replaceAll("/", "")+" accept");
						p.closeInventory();
					}
					if(item.equalsIgnoreCase(ConfigHandler.get("GUI.Items.Decline.Display"))) {
						p.performCommand(CommandAction.getMainCommand().replaceAll("/", "")+" kick");
						p.closeInventory();
					}
				}
			}
		}
		
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(PlayerFirstTimeJoinEvent.list.contains(e.getPlayer().getName())) {
			InventoryView inv = e.getView();
			if(inv.getTitle().equalsIgnoreCase(ConfigHandler.get("GUI.GUI_Name"))) {
	
				Bukkit.getScheduler().scheduleSyncDelayedTask(UltimatedeGDPR.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
					    e.getPlayer().openInventory(inv);
					}
				}, 1);
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String m = e.getMessage();
		if(PlayerFirstTimeJoinEvent.list.contains(e.getPlayer().getName())) {
			if(!m.equalsIgnoreCase(CommandAction.getMainCommand())) {
				e.getPlayer().sendMessage(ConfigHandler.getPrefix()+ConfigHandler.get("Messages.Chat"));
				e.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void OnItemInteract(PlayerInteractEvent e) {
		 
		if (e.getClickedBlock() == null) {
			return;
		}
		
		if (e.getItem() == null) {
			return;
		}
  
		if(PlayerFirstTimeJoinEvent.list.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			 
		}
		
	}
	
}
