package org.confluence.music.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import org.confluence.lib.network.IPacketS2C;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.client.MusicHandler;

/// 服务端告诉客户端“玩家目前在地牢的哪一层”，客户端据此切换地牢音乐。
public record StructureFoundPacketS2C(byte data) implements IPacketS2C {
    public static final byte NOT_FOUND = 0;
    public static final byte DUNGEON_FLOOR_1 = 1;
    public static final byte DUNGEON_FLOOR_2 = 2;
    public static final Type<StructureFoundPacketS2C> TYPE = new Type<>(ConfluenceMusic.asResource("structure_found"));
    public static final StreamCodec<ByteBuf, StructureFoundPacketS2C> STREAM_CODEC = ByteBufCodecs.BYTE.map(StructureFoundPacketS2C::new, StructureFoundPacketS2C::data);

    @Override
    public Type<StructureFoundPacketS2C> type() {
        return TYPE;
    }

    @Override
    public void work(Player player) {
        if (player.isLocalPlayer()) {
            MusicHandler.handleStructure(data);
        }
    }
}
