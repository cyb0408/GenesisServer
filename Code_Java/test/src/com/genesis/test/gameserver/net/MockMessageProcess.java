package com.genesis.test.gameserver.net;

import com.google.protobuf.InvalidProtocolBufferException;

import com.genesis.core.net.msg.CSMessage;
import com.genesis.test.gameserver.net.MockCGMessage.CGTEST;
import com.genesis.network2client.handle.INettyMessageHandler;
import com.genesis.network2client.session.IClientSession;

import java.util.concurrent.CountDownLatch;

public class MockMessageProcess implements INettyMessageHandler {

    private final CountDownLatch latch;

    public int id = 0;
    public String name = "";
    public long other = 0L;

    public int count = 0;

    public MockMessageProcess(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void handle(IClientSession session, CSMessage gcMsg) {
        CGTEST message = null;
        try {
            message = MockCGMessage.CGTEST.PARSER.parseFrom(gcMsg.messageContent);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        id = message.getId();
        name = message.getName();
        other = message.getOther();
        count = count + 1;
        latch.countDown();
    }

}
