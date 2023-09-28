package org.example.pieces;

import org.example.Board;

import java.awt.image.BufferedImage;

public class King extends Piece{

    public King(Board board,int col ,int row ,boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "King";

        this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);

    }
}
