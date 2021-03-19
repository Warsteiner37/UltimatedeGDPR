package de.warsteiner.gdpr.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.warsteiner.gdpr.UltimatedeGDPR;
import de.warsteiner.gdpr.manager.ActionManager;
 
public class PlayerFirstTimeJoinEvent implements Listener {
	
	public static ArrayList<String> list = new ArrayList<String>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		String uuid = ""+e.getPlayer().getUniqueId();
		
		if(UltimatedeGDPR.getLocationHandler().getLocation("HUB") == null) {
			return;
		}
		
		if(!UltimatedeGDPR.getAPI().existPlayer(uuid)) {
		 e.getPlayer().teleport(UltimatedeGDPR.getLocationHandler().getLocation("HUB"));
			
		
		Bukkit.getScheduler().runTaskAsynchronously(UltimatedeGDPR.getPlugin(), new Runnable() {

			@Override
			public void run() {
	 
				
				if(!UltimatedeGDPR.getAPI().existPlayer(uuid)) {
					
				 
					list.add(e.getPlayer().getName());
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(UltimatedeGDPR.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							ActionManager.getMeth(e.getPlayer());
							
						}
					}, 20);
					
					 
				}
			}
			
		});
		
		}
	}
	
	 @EventHandler
	 public void OnMove(PlayerMoveEvent e) {
		 if(list.contains(e.getPlayer().getName())) {
	         e.getPlayer().teleport(UltimatedeGDPR.getLocationHandler().getLocation("HUB"));
		 }
	 }
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		
		
		Bukkit.getScheduler().runTaskAsynchronously(UltimatedeGDPR.getPlugin(), new Runnable() {

			@Override
			public void run() {
				 
				if(list.contains(e.getPlayer().getName())) {
					list.remove(e.getPlayer().getName());
				}
			}
			
		});
		
	 
	}

}
