package org.example.pieces;

import org.example.Board;

import java.awt.image.BufferedImage;

public class Rock extends Piece{
    public Rock(Board board,int col ,int row ,boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;

        this.isWhite = isWhite;
        this.name = "Rock";

        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);


    }

    public boolean isValidMovement(int col,int row){


        return this.col == col || this.row == row;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        if(this.col > col) {
            for(int c=this.col-1;c>col;c--) {
                if(board.getPiece(c,this.row) !=null) {
                    return true;
                }
            }
        }
        if(this.col < col) {
            for(int c=this.col+1;c<col;c++) {
                if(board.getPiece(c,this.row) !=null) {
                    return true;
                }
            }
        }
        if(this.row > row) {
            for(int r=this.row-1;r>row;r--) {
                if(board.getPiece(this.col,r) !=null) {
                    return true;
                }
            }
        }
        if(this.row < row) {
            for(int r=this.row+1;r<row;r++) {
                if(board.getPiece(this.col,r) !=null) {
                    return true;
                }
            }
        }
        return false;
    }
}
