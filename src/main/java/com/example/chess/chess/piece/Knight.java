package com.example.chess.chess.piece;

import com.example.chess.chess.ChessBoard;
import com.example.chess.chess.ChessGame;
import com.example.chess.chess.ChessboardPosition;

public class Knight extends ChessPiece
{
    public Knight(Side side, ChessboardPosition tilePosition)
    {
        super(side, tilePosition, side == Side.BLACK ? "D:/20231/network_programming/project/Github/chess/src/main/java/com/example/chess/icons/icons8-knight-50.png" : "D:/20231/network_programming/project/Github/chess/src/main/java/com/example/chess/icons/icons8-knight-50-white.png");
    }

    @Override
    public boolean canMoveTo(ChessBoard.Tile tile)
    {
        if (ChessGame.getGame().getCurrentMoveSide().equals(super.getSide()) && willUncoverKing(tile))
            return false;

        final ChessBoard.Tile currentTile = super.getTile();

        if (currentTile.getRow() == tile.getRow() && currentTile.getColumn() == tile.getColumn())
            return false;

        int absDistanceY = Math.abs(tile.getRow() - currentTile.getRow());
        int absDistanceX = Math.abs(tile.getColumn() - currentTile.getColumn());

        // Validate movement
        final ChessPiece chessPieceAtNewPosition = tile.getChessPiece();

        if (absDistanceY == 2 && absDistanceX == 1)
            return chessPieceAtNewPosition == null || !chessPieceAtNewPosition.getSide().equals(super.getSide());
        else if (absDistanceX == 2 && absDistanceY == 1)
            return chessPieceAtNewPosition == null || !chessPieceAtNewPosition.getSide().equals(super.getSide());
        return false;
    }
}
