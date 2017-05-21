package omniblock.cord.addons.motd;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import omniblock.cord.OmniCord;
import omniblock.cord.addons.motd.type.MOTDType;
import omniblock.cord.util.TextUtil;

public class MOTDManager implements Listener {

	public static MOTDType motd = MOTDType.COMMON_MOTD;
	
	public static void start() {
		
		OmniCord.getPlugin().getProxy().getPluginManager().registerListener(OmniCord.getPlugin(), new MOTDManager());
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onPing(ProxyPingEvent e){
		
        ServerPing serverPing = e.getResponse();
        serverPing.setDescription(TextUtil.format(motd.getPreset().getLine(1) + "" + motd.getPreset().getLine(2)));
        e.setResponse(serverPing);
        
    }
	
}
