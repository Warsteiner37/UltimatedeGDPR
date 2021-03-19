package de.warsteiner.gdpr.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.warsteiner.gdpr.UltimatedeGDPR;
import de.warsteiner.gdpr.utils.ConfigHandler;
 
public class ActionGUI {
 
	public static Inventory load(Player p) {
 
		Inventory inv = Bukkit.createInventory(null, 9*ConfigHandler.getInt("GUI.GUI_Size"), ConfigHandler.get("GUI.GUI_Name").replaceAll("&", "§"));
		
	 	Bukkit.getScheduler().runTaskAsynchronously(UltimatedeGDPR.getPlugin(), new Runnable() {
 
	  
			@Override
			public void run() {

				List<String> list_of_placeholders = ConfigHandler.getStringList("GUI.GUI_PlaceHolders");
 
				for(String pl : list_of_placeholders) {
					String[] t = pl.split(":");
		 
					Material material = Material.valueOf(t[0]);
					int slot = Integer.valueOf(t[1]);
					String display = t[2];
					
					ItemStack item = new ItemStack(material, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(display.replaceAll("&", "§"));
					item.setItemMeta(meta);
					
					inv.setItem(slot, item);
					 
				 
				}
				
				if(ConfigHandler.getBoolean("GUI.Items.Decline.Enabled")) {
					
					
					Material mat = Material.valueOf(ConfigHandler.get("GUI.Items.Decline.Material"));
					
					String display = ConfigHandler.get("GUI.Items.Decline.Display");
					
					int slot = ConfigHandler.getInt("GUI.Items.Decline.Slot");
			 
					ItemStack item = new ItemStack(mat, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(display.replaceAll("&", "§"));
 
					item.setItemMeta(meta);
					
					inv.setItem(slot, item);
				
				}
				
				if(ConfigHandler.getBoolean("GUI.Items.Accept.Enabled")) {
				 
					Material mat = Material.valueOf(ConfigHandler.get("GUI.Items.Accept.Material"));
					
					String display = ConfigHandler.get("GUI.Items.Accept.Display");
					
					int slot = ConfigHandler.getInt("GUI.Items.Accept.Slot");
			 
					ItemStack item = new ItemStack(mat, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(display.replaceAll("&", "§"));
 
					item.setItemMeta(meta);
					
					inv.setItem(slot, item);
				
				}
				
				if(ConfigHandler.getBoolean("GUI.Items.Info_Item.Enabled")) {
					
					
					Material mat = Material.valueOf(ConfigHandler.get("GUI.Items.Info_Item.Material"));
					
					String display = ConfigHandler.get("GUI.Items.Info_Item.Display");
					
					int slot = ConfigHandler.getInt("GUI.Items.Info_Item.Slot");
					
					List<String> lore = ConfigHandler.getStringList("GUI.Items.Info_Item.Lore");
					
					ItemStack item = new ItemStack(mat, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(display.replaceAll("&", "§"));
					
					ArrayList<String> aa = new ArrayList<String>();
					
					for(String b : lore) {
						aa.add(b.replaceAll("&", "§"));
					}
					
					meta.setLore(aa);
					
					item.setItemMeta(meta);
					
					inv.setItem(slot, item);
				
				}
				
			}
			
	 	});
		return inv;
	}
}
				
 



















