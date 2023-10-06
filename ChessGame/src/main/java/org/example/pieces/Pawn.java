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
        int colorIndex = isWhite ? 1 : -1;

        if(this.col == col && this.row - colorIndex == row  && board.getPiece(col, row) == null) {
            return true;
        }
        if(isFirstMove  && this.col == col && this.row - colorIndex * 2 == row && board.getPiece(col, row + colorIndex) == null) {
            return true;
        }
        if(col == this.col -1 && row == this.row  - colorIndex && board.getPiece(col, row) != null) {
            return true;
        }
        if(col == this.col +1 && row == this.row  - colorIndex && board.getPiece(col, row) != null) {
            return true;
        }
        if(board.getTitleNum(col,row) == board.enPassantTile && col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col,row + colorIndex) != null) {
            return true;
        }
        if(board.getTitleNum(col,row) == board.enPassantTile && col == this.col + 1 && row == this.row - colorIndex && board.getPiece(col,row + colorIndex) != null) {
            return true;
        }
        return false;
    }
}
