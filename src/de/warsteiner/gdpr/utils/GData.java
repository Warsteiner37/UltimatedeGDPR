/*     */ package de.warsteiner.gdpr.utils;
 
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;

import de.warsteiner.gdpr.UltimatedeGDPR;
/*     */ 
/*     */ 
/*     */ public class   GData
/*     */ {
/*     */   public File cfile;
/*     */   public FileConfiguration config;
/*     */   
/*     */   public void setAccepted(boolean yes, String uuid) {
/*  26 */     get().set("Player."+""+".Accepted."+uuid+".Type", yes);
/*     */     
/*  28 */     save();
/*     */   }
/*     */   
/*     */   public boolean getPage(String uuid) {
/*  32 */     return get().getBoolean("Player."+""+".Accepted."+uuid+".Type");
/*     */   }

			public boolean existPlayer(String uuid) {
				if(get().contains("Player."+""+".Accepted."+uuid+".Type")) {
					return true;
				}
				return false;
			}
/*     */   
/* 141 */   public String location = ConfigHandler.get("Plugin.Save_In");
/* 142 */   public UltimatedeGDPR plugin = UltimatedeGDPR.getPlugin();
/*     */   public void create() {
/* 144 */     if (getfile() != null && 
/* 145 */       !this.plugin.getDataFolder().exists())
/* 146 */       this.plugin.getDataFolder().mkdir(); 
/* 147 */     if (!getfile().exists()) {
/*     */       try {
/* 149 */         getfile().createNewFile();
/* 150 */       } catch (Exception e) {
/* 151 */         Bukkit.getConsoleSender().sendMessage(
/* 152 */             ChatColor.RED + "Error creating " + getfile().getName() + "!  - " + e.getMessage());
/*     */       } 
/*     */     }
/* 155 */     this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
/*     */   }
/*     */   
/*     */   public File getfile() {
/* 159 */     this.cfile = new File(this.location, "gdpr.yml");
/* 160 */     if (this.cfile != null) {
/* 161 */       return this.cfile;
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/* 169 */     if (getfile() != null) {
/* 170 */       this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
/*     */     } else {
/* 172 */       Bukkit.broadcastMessage(ChatColor.RED + "File does not exsist.  Cannot load file.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public FileConfiguration get() {
/* 177 */     return this.config;
/*     */   }
/*     */   
/*     */   public void save() {
/*     */     try {
/* 182 */       this.config.save(getfile());
/* 183 */     } catch (Exception e) {
/* 184 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + getfile().getName() + "!");
/*     */     } 
/*     */   }
/*     */ }

 