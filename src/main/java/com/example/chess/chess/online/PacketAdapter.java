package com.example.chess.chess.online;

import com.google.gson.*;
import com.example.chess.chess.online.packets.MovePacket;
import com.example.chess.chess.online.packets.Packet;

import java.lang.reflect.Type;

public class PacketAdapter implements JsonDeserializer<Packet>
{
    @Override
    public Packet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("packetType");
        String type = jsonElement.getAsString();
        if (Packet.PacketType.MOVE.name().equalsIgnoreCase(type))
        {
            return context.deserialize(json, MovePacket.class);
        }
        return null;
    }
}
