package com.example.chess.chess.piece;

import com.example.chess.chess.ChessBoard;
import com.example.chess.chess.ChessGame;
import com.example.chess.chess.ChessboardPosition;

public class Queen extends ChessPiece
{
    public Queen(Side side, ChessboardPosition position)
    {
        super(side, position, side == Side.BLACK ? "D:/CommonWorkSpace/intelij workspace/chess/src/main/java/com/example/chess/icons/icons8-queen-50.png" : "D:/CommonWorkSpace/intelij workspace/chess/src/main/java/com/example/chess/icons/icons8-queen-50-white.png");
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
        if (!(((absDistanceX == 0 && absDistanceY > 0) || absDistanceY == 0 && absDistanceX > 0) || absDistanceX == absDistanceY))
            return false;

        if (isChessPieceInWay(tile))
            return false;

        return chessPieceAtNewPosition == null || !chessPieceAtNewPosition.getSide().equals(this.getSide());
    }

    private boolean isChessPieceInWay(final ChessBoard.Tile tile)
    {
        if (tile == null)
            return false;

        final ChessBoard.Tile ourTile = super.getTile();

//        int testRow = ourTile.getRow();
//        int testColumn = ourTile.getColumn();

        int newColumn = tile.getColumn();
        int newRow = tile.getRow();
//
//        final ChessBoard.Tile startTile = tile.getRow() >
//        int maxRow = Math.max(testRow, tile.getRow());
//        int maxColumn = Math.max(testColumn, tile.getColumn());
//        int minRow = Math.min(testRow, tile.getRow());
//        int minColumn = Math.min(testColumn, tile.getColumn());

//        while (true)
//        {
//            final ChessBoard.Tile newTile = ChessGame.getGame().getChessBoard().getTileAt(minRow, minColumn);
//            if (newTile != this.getTile() && newTile.getChessPiece() != null)
//                return true;
//
//            if (minColumn == maxColumn && minRow == maxRow)
//                break;
//
//            if (minRow < maxRow)
//                minRow++;
//            if (minColumn < maxColumn)
//                minColumn++;
//        }

//        for (int row = minRow; row < maxRow; row++)
//        {
//            for (int column = minColumn; column < maxColumn; column++)
//            {
//
//            }
//        }

//        return false;

        // Left-down
        if (ourTile.getColumn() < tile.getColumn() && ourTile.getRow() < tile.getRow())
        {
            newColumn = tile.getColumn() - 1;
            newRow = tile.getRow() - 1;
        }
        // Left-up
        else if (ourTile.getColumn() < tile.getColumn() && ourTile.getRow() > tile.getRow())
        {
            newColumn = tile.getColumn() - 1;
            newRow = tile.getRow() + 1;
        }
        // Right-down
        else if (ourTile.getColumn() > tile.getColumn() && ourTile.getRow() < tile.getRow())
        {
            newColumn = tile.getColumn() + 1;
            newRow = tile.getRow() - 1;
        }
        // Right-up
        else if (ourTile.getColumn() > tile.getColumn() && ourTile.getRow() > tile.getRow())
        {
            newColumn = tile.getColumn() + 1;
            newRow = tile.getRow() + 1;
        }
        // Left and Right
        else if (ourTile.getRow() == tile.getRow())
        {
            if (ourTile.getColumn() < tile.getColumn())
            {
                newColumn = tile.getColumn() - 1;
            }
            else
            {
                newColumn = tile.getColumn() + 1;
            }
        }
        else
        {
            if (ourTile.getRow() < tile.getRow())
            {
                newRow = tile.getRow() - 1;
            }
            else
            {
                newRow = tile.getRow() + 1;
            }
        }

        final ChessPiece chessPieceAtNewTile = ChessGame.getGame().getChessBoard().getFigureAt(newRow, newColumn);
        final ChessBoard.Tile newTile = ChessGame.getGame().getChessBoard().getTileAt(newRow, newColumn);
        if (chessPieceAtNewTile == null)
        {
            return isChessPieceInWay(newTile);
        }
        else
        {
            return !chessPieceAtNewTile.equals(this);
        }
    }
}
