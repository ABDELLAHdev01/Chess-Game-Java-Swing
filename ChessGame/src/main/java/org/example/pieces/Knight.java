package org.example.pieces;

import org.example.Board;

import java.awt.image.BufferedImage;

public class Knight extends Piece{
    public Knight(Board board, int col , int row , boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.sprite = sheet.getSubimage(3 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);

    }


    public boolean isValidMovement(int col, int row) {
        int dx = Math.abs(col - this.col);
        int dy = Math.abs(row - this.row);
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }
}