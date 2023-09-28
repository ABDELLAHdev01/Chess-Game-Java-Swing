package org.example;

import org.example.pieces.Piece;

public class Move {
    int oldCol;
    int oldRow;
    int newCol;
    int newRow;

    Piece piece;
    Piece captured;

    public Move(Board board,Piece piece, int newCol, int newRow) {
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.captured = board.getPiece(newCol,newRow);

    }
}
