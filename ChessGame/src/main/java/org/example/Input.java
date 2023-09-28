package org.example;

import org.example.pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

    Board board;
    public Input(Board board){
        this.board = board;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        Piece pieceXY = board.getPiece(col,row);
        if (pieceXY != null) {
            board.SelectedPiece = pieceXY;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
            if(board.SelectedPiece != null){
                board.SelectedPiece.xPos = e.getX() - board.titleSize / 2;
                board.SelectedPiece.yPos = e.getY() - board.titleSize / 2;

                board.repaint();
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        if (board.SelectedPiece != null){
            Move move = new Move(board,board.SelectedPiece,col,row);

            if (board.isValidMove(move)){
                board.makeMove(move);
            }
            if (!board.isValidMove(move)) {
                board.SelectedPiece.yPos = board.SelectedPiece.row * board.titleSize;
                board.SelectedPiece.xPos = board.SelectedPiece.col * board.titleSize;
            }
            board.SelectedPiece = null;
            board.repaint();


        }

    }








}
