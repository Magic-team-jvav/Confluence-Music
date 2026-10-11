package org.confluence.music.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.client.MusicHandler;
import org.mesdag.portlib.network.IPortPacket;
import org.mesdag.portlib.network.codec.PortByteBufCodecs;
import org.mesdag.portlib.network.codec.PortStreamCodec;

/// 服务端告诉客户端“玩家目前在地牢的哪一层”，客户端据此切换地牢音乐。
public record StructureFoundPacketS2C(byte data) implements IPortPacket.S2C {
    public static final byte NOT_FOUND = 0;
    public static final byte DUNGEON_FLOOR_1 = 1;
    public static final byte DUNGEON_FLOOR_2 = 2;
    public static final ResourceLocation ID = ConfluenceMusic.asResource("structure_found");
    public static final PortStreamCodec<ByteBuf, StructureFoundPacketS2C> STREAM_CODEC =
            PortByteBufCodecs.BYTE.map(StructureFoundPacketS2C::new, StructureFoundPacketS2C::data);

    @Override
    public ResourceLocation identifier() {
        return ID;
    }

    @Override
    public void work(Player player) {
        MusicHandler.handleStructure(data);
    }
}
