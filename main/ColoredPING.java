/*    */ package main;
/*    */ 
/*    */ import com.comphenix.protocol.ProtocolLibrary;
/*    */ import com.comphenix.protocol.ProtocolManager;
/*    */ import com.comphenix.protocol.metrics.Metrics;
/*    */ import java.io.IOException;
/*    */ import listener.PingListener;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColoredPING
/*    */   extends JavaPlugin
/*    */ {
/*    */   public static ColoredPING INSTANCE;
/*    */   public ProtocolManager protocolManager;
/*    */   private PingListener listener;
/* 21 */   public String eventMOTD = "";
/* 22 */   public String prefix = "§7[§cColored§lPING§7] ";
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)
/*    */   {
/* 27 */     if (cmd.getName().equalsIgnoreCase("event")) {
/* 28 */       if (sender.hasPermission("coloredping.event")) {
/* 29 */         if (args.length == 0) {
/* 30 */           sender.sendMessage(this.prefix + "§cUsage: /event <MOTD / clear>");
/*    */         }
/* 32 */         else if (args[0].equalsIgnoreCase("clear")) {
/* 33 */           sender.sendMessage(this.prefix + "§cMOTD gelöscht!");
/* 34 */           this.eventMOTD = "";
/*    */         } else {
/* 36 */           int a = args.length;
/* 37 */           int i = 0;
/* 38 */           StringBuilder emotd = new StringBuilder();
/* 39 */           this.eventMOTD = "";
/* 40 */           while (i < a)
/*    */           {
/* 42 */             emotd.append(args[i].replace("&", "§") + " ");
/* 43 */             i++;
/*    */           }
/*    */           
/*    */ 
/* 47 */           this.eventMOTD = emotd.toString();
/* 48 */           sender.sendMessage(this.prefix + "§cEvent- MOTD gesetzt: §f'§r§7" + 
/* 49 */             this.eventMOTD + "§r§f'");
/*    */         }
/*    */       }
/*    */       else {
/* 53 */         sender.sendMessage(this.prefix + "§cKeine Rechte!");
/*    */       }
/*    */       
/* 56 */       return true;
/*    */     }
/* 58 */     return false;
/*    */   }
/*    */   
/*    */   public void onEnable()
/*    */   {
/* 63 */     INSTANCE = this;
/* 64 */     this.protocolManager = ProtocolLibrary.getProtocolManager();
/* 65 */     this.listener = new PingListener();
/* 66 */     this.listener.addPingResponsePacketListener();
/*    */     try
/*    */     {
/* 69 */       Metrics metrics = new Metrics(this);
/* 70 */       metrics.start();
/*    */     }
/*    */     catch (IOException localIOException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\matmen\Desktop\ColoredPING.jar!\main\ColoredPING.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */