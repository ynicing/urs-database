package com.ursful.framework.mina.client.mina;

import com.ursful.framework.mina.client.UrsClient;
import com.ursful.framework.mina.client.mina.packet.ClientPacketHandler;
import com.ursful.framework.mina.client.mina.packet.PacketWriter;
import com.ursful.framework.mina.client.presence.IPresence;
import com.ursful.framework.mina.client.tools.ClientPacketCreator;
import com.ursful.framework.mina.client.tools.Cryptor;
import com.ursful.framework.mina.common.UrsManager;
import com.ursful.framework.mina.common.Opcode;
import com.ursful.framework.mina.common.packet.Packet;
import com.ursful.framework.mina.common.support.ClientInfo;
import com.ursful.framework.mina.common.tools.ByteReader;
import com.ursful.framework.mina.server.client.Client;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHandler extends IoHandlerAdapter{

	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	private Map<Integer, ClientPacketHandler> processorHandlers = new HashMap<Integer, ClientPacketHandler>();

	public ClientPacketHandler getHandler(int packetId) {
		return processorHandlers.get(packetId);
	}

	public  void register(ClientPacketHandler handler) {
		processorHandlers.put(handler.opcode(), handler);
	}

	private UrsClient client;

	private String serverId;
	private String cid;
	private String resource;

	private PacketWriter writer;

	public PacketWriter getWriter() {
		return writer;
	}

	public void setWriter(PacketWriter writer) {
		this.writer = writer;
	}

	public ClientHandler(){}

	public ClientHandler(UrsClient client){
		this.client = client;
		this.cid = client.getClientId();
		this.resource = client.getResource();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error(client.isCluster() ? "cluster client:" : "client:" + cause.getMessage(), cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		Cryptor cryptor = (Cryptor)session.getAttribute(Cryptor.CRYPTOR);
		ByteReader reader = new ByteReader((byte[])message);
		if(cryptor == null){
			if(255 == reader.readByte()){
				if(Opcode.HELLO.ordinal() == reader.readShort()){
					int version = reader.readShort();
					this.serverId = reader.readString();
					this.client.setServerId(this.serverId);
					if(reader.available() > 0) {
						byte[] sendIv = reader.readBytes();
						byte[] recvIv = reader.readBytes();
						session.setAttribute(Cryptor.CRYPTOR, new Cryptor(recvIv, sendIv));
					}else{
						session.setAttribute(Cryptor.CRYPTOR, new Cryptor());
					}
					Opcode code = Opcode.INFO;
					Packet packet = ClientPacketCreator.getClientInfo(code, this.serverId, this.cid, this.resource, client.isCluster(), client.getMetaData());
					session.write(packet);
					logger.info((client.isCluster()?"server-client":"client") + " " + this.cid + "@" + this.serverId + " meta-data:" + client.getMetaData());
					String csid = cid + "@" + serverId;
					if(resource != null){
						csid += "/" + resource;
					}
					String clientServerId = csid;
					session.setAttribute(Client.CLIENT_ID_KEY, clientServerId);
					writer = new PacketWriter(session);
					writer.startup();
					UrsClient thisClient = this.client;
					thisClient.clientReady();
//					for(IClientStatus status : statuses){
//						ThreadUtils.start(new Runnable() {
//							@Override
//							public void run() {
//								status.clientReady(thisClient, clientServerId);
//								status.clientReady(clientServerId);
//							}
//						});
//					}
				}
			}
		}else{
			int packetId = reader.readShort();
			ClientPacketHandler packetHandler = getHandler(packetId);
			if (packetHandler != null) {
				try {
					packetHandler.handlePacket(reader, writer);
				}catch (Exception e){
					e.printStackTrace();
				}
			}else{
				logger.warn("unhanlder message : " + packetId + ">" + reader);
			}
		}
		
		//
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		String clientServerId = (String)session.getAttribute(Client.CLIENT_ID_KEY);
		logger.warn("Server close:" + clientServerId);
		if(clientServerId == null){
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<IPresence> presenceInfos = UrsManager.getObjects(IPresence.class);
				for(IPresence presence : presenceInfos){
					presence.presence(new ClientInfo(clientServerId, false, client.getMetaData()));
				}
			}
		}).start();

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
	}
	
}
