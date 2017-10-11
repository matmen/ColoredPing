package listener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import main.ColoredPING;

public class PingListener
{
  private ColoredPING main = ColoredPING.INSTANCE;
  

  public void addPingResponsePacketListener()
  {
    try
    {
      this.main.protocolManager.addPacketListener(new PacketAdapter(
      
        PacketAdapter.params(this.main, 
        new com.comphenix.protocol.PacketType[] { PacketType.Status.Server.OUT_SERVER_INFO })
        .serverSide().gamePhase(com.comphenix.protocol.injector.GamePhase.BOTH)
        .listenerPriority(ListenerPriority.MONITOR)
        .optionAsync())
        {



          @SuppressWarnings("deprecation")
          public void onPacketSending(PacketEvent event)
          {



            try
            {


              WrappedServerPing ping = 
                (WrappedServerPing)event.getPacket().getServerPings().getValues().get(0);
              ping.setVersionProtocol(Integer.MAX_VALUE);
              ping.setVersionName("§c§l" + ping.getPlayersOnline() + "§7§l/§7§l" + ping.getPlayersMaximum());
              ping.setMotD("§0§lD§4§lB§6§lR §7| §c1.8 \n§7" + PingListener.this.main.eventMOTD);
              ping.setPlayersVisible(true);
              
              List<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();
              players.add(new WrappedGameProfile(UUID.randomUUID(), "        §0§lD§4§lB§6§lR §7| §c1.8        "));
              players.add(new WrappedGameProfile(UUID.randomUUID(), ""));
              players.add(new WrappedGameProfile(UUID.randomUUID(), "§7" + PingListener.this.main.eventMOTD));
              players.add(new WrappedGameProfile(UUID.randomUUID(), ""));
              players.add(new WrappedGameProfile(UUID.randomUUID(), "§cSpieler online:"));
              if (ping.getPlayersOnline() < 1) {
                players.add(new WrappedGameProfile(UUID.randomUUID(), "§7Keiner!"));
              } else {
                for (Player all : org.bukkit.Bukkit.getOnlinePlayers()) {
                  players.add(new WrappedGameProfile(all.getUniqueId().toString(), "§c" + all.getDisplayName()));
                }
              }
              
              ping.setPlayers(players);
              
              event.getPacket().getServerPings().getValues()
                .set(0, ping);

            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
            
          }
          

        });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}