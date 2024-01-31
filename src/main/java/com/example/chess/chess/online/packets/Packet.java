package com.example.chess.chess.online.packets;

public interface Packet
{
    PacketType packetType();

    enum PacketType
    {
        MOVE
    }
}
