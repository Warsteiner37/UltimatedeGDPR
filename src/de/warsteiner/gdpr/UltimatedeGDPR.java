package de.warsteiner.gdpr;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.YamlConfiguration;

import de.warsteiner.gdpr.command.CommandAction;
import de.warsteiner.gdpr.events.InventoryClickEventIfList;
import de.warsteiner.gdpr.events.PlayerFirstTimeJoinEvent;
import de.warsteiner.gdpr.utils.ConfigHandler;
import de.warsteiner.gdpr.utils.GData;
import de.warsteiner.gdpr.utils.LocationHandler;
 
public class UltimatedeGDPR extends org.bukkit.plugin.java.JavaPlugin  {
	 
		private static UltimatedeGDPR plugin;
		
		public static YamlConfiguration file;

		
		private static GData api;
		private static LocationHandler loc;

		@Override
		public void onEnable() {
	 
			plugin = this;
			
			loadConfig();
			
			api = new GData();
		     
			if(api.getfile() == null) {
				api.create();
			} else {
				api.load();
			}
			
			loc = new LocationHandler();
		     
			if(loc.getfile() == null) {
				loc.create(); asd
			} else {
				loc.load();
			}
 
		 	Bukkit.getPluginManager().registerEvents(new PlayerFirstTimeJoinEvent(), this);
		 	Bukkit.getPluginManager().registerEvents(new InventoryClickEventIfList(), this);

	    
		 	/*     */                         Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
		 	/*     */                         Bukkit.getConsoleSender().sendMessage("            §3UltimateGDPR");
		 	/*     */                         Bukkit.getConsoleSender().sendMessage("       §8- §7Easy Rules & GDPR §8-");
		 	/*     */                         Bukkit.getConsoleSender().sendMessage("§8");
		 	/*     */                              Bukkit.getConsoleSender().sendMessage("§f-> Loaded Version §a" + getPlugin().getDescription().getVersion());
		 	/*     */                              Bukkit.getConsoleSender().sendMessage("§f-> API Version §c" + getPlugin().getDescription().getAPIVersion());  
 
		 	/*     */                         Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
		 	
	        List<String> commands = ConfigHandler.getStringList("Plugin.Commands");

	        for(String b : commands) {
	        	registerCommand(new CommandAction(b));
	         
	        }

	        
		}

		private void registerCommand(Command command) {
			/*  99 */     CommandMap cmap = null;
			/*     */     try {
			/* 101 */       Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			/* 102 */       field.setAccessible(true);
			/* 103 */       cmap = (CommandMap)field.get(Bukkit.getServer());
			/* 104 */       cmap.register("", command);
			/* 105 */     } catch (Exception e) {
			/* 106 */       e.printStackTrace();
			/*     */     } 
			/*     */   }
		
		@Override
		public void onDisable() {
			 
		}
		 

		public static UltimatedeGDPR getPlugin() {
			return plugin;
		}
		
		public static GData getAPI() {
			return api;
		}
		
		public static LocationHandler getLocationHandler() {
			return loc;
		}
	 
		public    void reloadValues() {
			  
		     reloadConfig();
		     
		}
		/*     */   
		/*     */   private void loadConfig() {
		/* 214 */     File d = new File("plugins//" + getDescription().getName() + "//config.yml");
					 
		/* 215 */     if (!d.exists()) {
		/* 216 */  	     saveDefaultConfig();
		file = YamlConfiguration.loadConfiguration(d);
		/*     */     } else {
			 
			 
		     file = YamlConfiguration.loadConfiguration(d);
					}
		/*     */   }

}