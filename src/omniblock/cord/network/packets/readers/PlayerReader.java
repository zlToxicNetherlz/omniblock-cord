package omniblock.cord.network.packets.readers;

import net.omniblock.packets.network.Packets;
import net.omniblock.packets.network.structure.data.PacketSocketData;
import net.omniblock.packets.network.structure.data.PacketStructure;
import net.omniblock.packets.network.structure.data.PacketStructure.DataType;
import net.omniblock.packets.network.structure.packet.BroadcastMessagePacket;
import net.omniblock.packets.network.structure.packet.BroadcastTitlePacket;
import net.omniblock.packets.network.structure.packet.PlayerLoginEvaluatePacket;
import net.omniblock.packets.network.structure.packet.PlayerLoginSucessPacket;
import net.omniblock.packets.network.structure.packet.PlayerSendBanPacket;
import net.omniblock.packets.network.structure.packet.PlayerSendKickPacket;
import net.omniblock.packets.network.structure.packet.PlayerSendMessagePacket;
import net.omniblock.packets.network.structure.packet.PlayerSendTexturepackPacket;
import net.omniblock.packets.network.structure.packet.PlayerSendTitlePacket;
import net.omniblock.packets.network.structure.packet.PlayerSendToNamedServerPacket;
import net.omniblock.packets.network.tool.object.PacketReader;
import omniblock.cord.network.packets.PacketsTools;

public class PlayerReader {

	public static void start() {
		
		Packets.READER.registerReader(new PacketReader<PlayerSendMessagePacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerSendMessagePacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				String message = structure.get(DataType.STRINGS, "message");
				
				PacketsTools.sendMessage2Player(playername, message);

			}

			@Override
			public Class<PlayerSendMessagePacket> getAttachedPacketClass() {
				return PlayerSendMessagePacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<PlayerSendTitlePacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerSendTitlePacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				
				String title = structure.get(DataType.STRINGS, "title");
				String subtitle = structure.get(DataType.STRINGS, "subtitle");
				
				PacketsTools.sendTitle2Player(playername, title, subtitle);

			}

			@Override
			public Class<PlayerSendTitlePacket> getAttachedPacketClass() {
				return PlayerSendTitlePacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<BroadcastTitlePacket>(){

			@Override
			public void readPacket(PacketSocketData<BroadcastTitlePacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String title = structure.get(DataType.STRINGS, "title");
				String subtitle = structure.get(DataType.STRINGS, "subtitle");
				
				PacketsTools.sendTitle2All(title, subtitle);

			}

			@Override
			public Class<BroadcastTitlePacket> getAttachedPacketClass() {
				return BroadcastTitlePacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<BroadcastMessagePacket>(){

			@Override
			public void readPacket(PacketSocketData<BroadcastMessagePacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String message = structure.get(DataType.STRINGS, "message");
				
				PacketsTools.sendMessage2All(message);

			}

			@Override
			public Class<BroadcastMessagePacket> getAttachedPacketClass() {
				return BroadcastMessagePacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<PlayerSendBanPacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerSendBanPacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				
				PacketsTools.sendBan2Player(playername);

			}

			@Override
			public Class<PlayerSendBanPacket> getAttachedPacketClass() {
				return PlayerSendBanPacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<PlayerSendKickPacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerSendKickPacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				String moderator = structure.get(DataType.STRINGS, "moderator");
				String reason = structure.get(DataType.STRINGS, "reason");
				
				PacketsTools.sendKick2Player(playername, moderator, reason);

			}

			@Override
			public Class<PlayerSendKickPacket> getAttachedPacketClass() {
				return PlayerSendKickPacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<PlayerLoginEvaluatePacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerLoginEvaluatePacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				Boolean iplogin = structure.get(DataType.BOOLEANS, "iplogin");
				
				PacketsTools.sendAuthEvaluate2Player(playername, iplogin);

			}

			@Override
			public Class<PlayerLoginEvaluatePacket> getAttachedPacketClass() {
				return PlayerLoginEvaluatePacket.class;
			}
			
		});
		
		Packets.READER.registerReader(new PacketReader<PlayerLoginSucessPacket>(){

			@Override
			public void readPacket(PacketSocketData<PlayerLoginSucessPacket> packetsocketdata) {
				
				PacketStructure structure = packetsocketdata.getStructure();
				
				String playername = structure.get(DataType.STRINGS, "playername");
				Boolean iplogin = structure.get(DataType.BOOLEANS, "iplogin");
				
				PacketsTools.registerAuthSucess2Player(playername, iplogin);

			}

			@Override
			public Class<PlayerLoginSucessPacket> getAttachedPacketClass() {
				return PlayerLoginSucessPacket.class;
			}
			
		});
		
	}
	
}
