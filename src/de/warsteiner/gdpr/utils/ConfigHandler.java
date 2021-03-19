package de.warsteiner.gdpr.utils;

import java.util.List;

import de.warsteiner.gdpr.UltimatedeGDPR;
 
public class ConfigHandler {

	public static String getPrefix() {
		return UltimatedeGDPR.getPlugin().getConfig().getString("Messages.Prefix").replaceAll("&", "§");
	}
	
	public static String get(String pt) {
		return UltimatedeGDPR.getPlugin().getConfig().getString(pt).replaceAll("&", "§");
	}
	
	public static List<String> getStringList(String pt) {
		return UltimatedeGDPR.getPlugin().getConfig().getStringList(pt);
	}

	public static int getInt(String pt) {
		return UltimatedeGDPR.getPlugin().getConfig().getInt(pt);
	}
	
	public static boolean getBoolean(String pt) {
		return UltimatedeGDPR.getPlugin().getConfig().getBoolean(pt);
	}
	
	
}
