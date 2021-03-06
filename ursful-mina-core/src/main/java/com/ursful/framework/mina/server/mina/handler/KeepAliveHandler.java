package com.ursful.framework.mina.server.mina.handler;

import com.ursful.framework.mina.common.Opcode;
import com.ursful.framework.mina.common.tools.ByteReader;
import com.ursful.framework.mina.server.client.Client;
import com.ursful.framework.mina.server.mina.packet.PacketHandler;


public class KeepAliveHandler implements PacketHandler {

    @Override
    public int opcode() {
        return Opcode.PING.ordinal();
    }

    @Override
    public void handlePacket(ByteReader reader, Client c) {
        long time = reader.readLong();
        c.pongReceived(time);
    }

    @Override
    public boolean validateState(Client c) {
        return true;
    }
}