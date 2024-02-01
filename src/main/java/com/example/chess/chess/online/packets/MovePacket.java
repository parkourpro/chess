package com.example.chess.chess.online.packets;

import com.example.chess.chess.ChessboardPosition;

public class MovePacket implements Packet
{
    private ChessboardPosition chessPieceFromTile;
    private ChessboardPosition movedTo;
    private final PacketType packetType = PacketType.MOVE;

    public MovePacket()
    {

    }

    public MovePacket(ChessboardPosition chessPieceFromTile, ChessboardPosition movedTo)
    {
        this.chessPieceFromTile = chessPieceFromTile;
        this.movedTo = movedTo;
    }

    public ChessboardPosition getChessFromTile()
    {
        return chessPieceFromTile;
    }

    public ChessboardPosition getMovedTo()
    {
        return movedTo;
    }

    @Override
    public String toString()
    {
        return "MovePacket{" +
                "chessPieceFromTile=" + chessPieceFromTile +
                ", movedTo=" + movedTo +
                '}';
    }

    @Override
    public PacketType packetType()
    {
        return packetType;
    }
}
