package com.example.chess.chess.online;

import com.example.chess.chess.online.packets.Packet;
import com.example.chess.chess.piece.Side;

public interface ChessOnlineConnection
{
    void sendMessage(Packet packet);

    Side getChessSide();
}
