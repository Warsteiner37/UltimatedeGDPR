package de.warsteiner.gdpr.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.warsteiner.gdpr.UltimatedeGDPR;
 
public class LocationHandler {
 
	public boolean exist(String name) {
		if(get().getLocation("Server." + name + ".Loc") != null) {
			return true;
		}
		return false;
	}
 
	public Location getLocation(String name) {
		return get().getLocation("Server." + name + ".Loc");
	}
 
	
	public void setLocation(String name, Location loc) {
		 
		 get().set("Server." + name + ".Loc", loc);
			save();
	}
	
  
	 public File cfile;
	    public FileConfiguration config;
	   public String location = ConfigHandler.get("Plugin.Save_In");
	   public UltimatedeGDPR plugin = UltimatedeGDPR.getPlugin();
		   public void create() {
		        if (!(getfile() == null))
		            if (!plugin.getDataFolder().exists())
		                plugin.getDataFolder().mkdir();
		        if (!getfile().exists()) {
		            try {
		                getfile().createNewFile();
		            } catch (Exception e) {
		                Bukkit.getConsoleSender().sendMessage(
		                        ChatColor.RED + "Error creating " + getfile().getName() + "!  - " + e.getMessage());
		            }
		        }
		        config = YamlConfiguration.loadConfiguration(cfile);
		    }
		 
		    public File getfile() {
		        cfile = new File(location, "locations.yml");
		        if (!(cfile == null)) {
		            return cfile;
		        } else {
		            return null;
		        }

		    }

		    public void load() {
		        if (!(getfile() == null)) {
		            config = YamlConfiguration.loadConfiguration(cfile);
		        } else {
		            Bukkit.broadcastMessage(ChatColor.RED + "File does not exsist.  Cannot load file.");
		        }
		    }

		    public FileConfiguration get() {
		        return config;
		    }

		    public void save() {
		        try {
		            config.save(getfile());
		        } catch (Exception e) {
		            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + getfile().getName() + "!");
		        }
		    }
	
}
