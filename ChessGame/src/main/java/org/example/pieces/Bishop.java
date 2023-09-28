package org.example.pieces;

import org.example.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece{
    public Bishop(Board board ,int col ,int row ,boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row - this.row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {

         //up left

        return false;
    }
}
