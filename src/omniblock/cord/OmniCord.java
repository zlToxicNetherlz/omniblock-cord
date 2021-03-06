/*
 *  Omniblock Developers Team - Copyright (C) 2016
 *
 *  This program is not a free software; you cannot redistribute it and/or modify it.
 *
 *  Only this enabled the editing and writing by the members of the team. 
 *  No third party is allowed to modification of the code.
 *
 */

package omniblock.cord;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.omniblock.packets.OmniPackets;
import net.omniblock.packets.network.socket.Sockets;
import net.omniblock.packets.network.socket.helper.SocketHelper;
import net.omniblock.packets.object.external.SystemType;
import omniblock.cord.addons.network.events.LoginListener;
import omniblock.cord.addons.resolver.ResolverListener;
import omniblock.cord.database.Database;
import omniblock.cord.network.packets.PacketsAdapter;

public class OmniCord extends Plugin {

    public static OmniCord instance;
    public static Configuration configuration;

	@Override
	public void onEnable() {

    	instance = this;

    	OmniPackets.setupSystem(SystemType.OMNICORD);
    	PacketsAdapter.registerReaders();

    	Sockets.SERVER.startServer(SocketHelper.OMNICORD_SOCKET_PORT);

    	loadConfig();
    	Implements();
    	Commands();

    }

	@Override
    public void onLoad() {

    	System.out.println();
		System.out.println();
		System.out.println("    ,----..    ");
		System.out.println("   /   /   \\  ");
		System.out.println("  /   .     : ");
		System.out.println(" .   /   ;.  \\ 		");
		System.out.println(".   ;   /  ` ;				OmniCord		");
		System.out.println(";   |  ; \\ ; | 		  OmniBlock Network");
		System.out.println("|   :  | ; | ' 			");
		System.out.println(".   |  ' ' ' : 			      MODO: BETA");
		System.out.println("'   ;  \\; /  | 		     VERSION: 4.2	");
		System.out.println(" \\   \\  ',  /  ");
		System.out.println("  ;   :    /   ");
		System.out.println("   \\   \\ .'    ");
		System.out.println("    `---`      ");
		System.out.println();
		System.out.println();

    }

    public void Implements() {

    	Database.makeConnection();

    	LoginListener.setup();
    	ResolverListener.setup();
    	
    }

    public void Commands() {
    	
    }

    @SuppressWarnings("deprecation")
	private void loadConfig() {
    	
        File file;
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        if (!(file = new File(this.getDataFolder(), "config.yml")).exists()) {
        	
            try {

				try (InputStream in = this.getResourceAsStream("config.yml")) {
					Files.copy(in, file.toPath());
				}
			    
			} catch (Exception e) {
			   e.printStackTrace();
			}
            
        }
        
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            instance.getProxy().getConsole().sendMessage("[OmniCord] [ERROR] Ha ocurrido un error al cargar la configuracion.");
            e.printStackTrace();
        }
        
    }

    public static OmniCord getInstance() {
    	return instance;
    }
    
    public static Plugin getPlugin() {
    	return instance;
    }
    
	public Configuration getConfig() {
		return configuration;
	}
    
}

