package org.example.pieces;

import org.example.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece{
    public Pawn(Board board, int col , int row , boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row) {
        int colDiff = Math.abs(col - this.col);
        int rowDiff = Math.abs(row - this.row);

        int colorIndex = isWhite ? 1 : -1;

        // Pawn's standard move (one square forward)
        if (col == this.col && row == this.row - colorIndex && board.getPiece(col, row) == null) {
            return true;
        }

        // Pawn's initial double move
        if (isFirstMove && col == this.col && row == this.row - 2 * colorIndex && board.getPiece(col, row) == null && board.getPiece(col, row + colorIndex) == null) {
            return true;
        }

        // Pawn captures diagonally
        if (colDiff == 1 && rowDiff == 1 && row == this.row - colorIndex && board.getPiece(col, row) != null) {
            return true;
        }
        //enpassent left
        if (board.getTitleNum(col,row) == board.enPassantTile && col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col,row + colorIndex) != null){
            return true;
        }
        //enpassent right
        if (board.getTitleNum(col,row) == board.enPassantTile && col == this.col + 1 && row == this.row -colorIndex && board.getPiece(col,row + colorIndex) != null){
            return true;
        }

        return false;

    }
}
