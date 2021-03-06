package com.genesis.test.core.ByteBuf;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ByteBufTest_short {

    @Test
    public void test_add_and_pop() {

        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.heapBuffer();
        for (int i = 0; i <= 65535; i++) {
            buffer.writeShort(i);
            int type = buffer.readUnsignedShort();
            assertThat(type, is(i));
        }
        assertThat(buffer.readableBytes(), is(0));

        for (int i = 0; i <= 65535; i++) {
            buffer.writeChar(i);
            int type = buffer.readUnsignedShort();
            assertThat(type, is(i));
        }
        assertThat(buffer.readableBytes(), is(0));
    }
}
