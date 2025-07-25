package org.confluence.music.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.confluence.music.ConfluenceMusic;
import org.confluence.music.client.MusicHandler;

public record StructureFoundPacketS2C(byte data) implements CustomPacketPayload {
    public static final byte NOT_FOUND = 0;
    public static final byte DUNGEON_FLOOR_1 = 1;
    public static final byte DUNGEON_FLOOR_2 = 2;
    public static final Type<StructureFoundPacketS2C> TYPE = new Type<>(ConfluenceMusic.asResource("structure_found"));
    public static final StreamCodec<ByteBuf, StructureFoundPacketS2C> STREAM_CODEC = ByteBufCodecs.BYTE.map(StructureFoundPacketS2C::new, StructureFoundPacketS2C::data);

    @Override
    public Type<StructureFoundPacketS2C> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player().isLocalPlayer()) {
                MusicHandler.handleStructure(data);
            }
        }).exceptionally(e -> {
            context.disconnect(Component.translatable("neoforge.network.invalid_flow", e.getMessage()));
            return null;
        });
    }
}
