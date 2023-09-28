package org.example;

import org.example.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int titleSize = 85;
    int cols=8;
    int rows=8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece SelectedPiece;

    Input input = new Input(this);


    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.captured)) {
            return false;
        }

        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false;
        }

        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false;
        }

        return true;
    }

    public void makeMove(Move move){
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * titleSize;
        move.piece.yPos = move.newRow * titleSize;

        captured(move);
    }

    public void captured(Move move){
        pieceList.remove(move.captured);
    }

    public Board(){
        this.setPreferredSize(new Dimension(cols*titleSize,rows*titleSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }
    public Piece getPiece(int col,int row){
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row){
                return piece;

            }
        }
        return null;
    }

    public boolean sameTeam(Piece p1,Piece p2){
        if (p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    public void addPieces(){
        // nzido ga3 3wadn
        pieceList.add(new Knight(this,1,0,false));
        pieceList.add(new Knight(this,6,0,false));
        // nzido ga3 pawns k7lin
        pieceList.add(new Pawn(this,0,1,false));
        pieceList.add(new Pawn(this,1,1,false));
        pieceList.add(new Pawn(this,2,1,false));
        pieceList.add(new Pawn(this,3,1,false));
        pieceList.add(new Pawn(this,4,1,false));
        pieceList.add(new Pawn(this,5,1,false));
        pieceList.add(new Pawn(this,6,1,false));
        pieceList.add(new Pawn(this,7,1,false));
        // adding the Rocks
        pieceList.add(new Rock(this,0,0,false));
        pieceList.add(new Rock(this,7,0,false));
        //nzido king
        pieceList.add(new King(this,3,0,false));
        //nzido queen
        pieceList.add(new Queen(this,4,0,false));
        // nzido bishop
        pieceList.add(new Bishop(this,2,0,false));
        pieceList.add(new Bishop(this,5,0,false));




// fari9 bayna abyad wa asswad



        // nzido ga3 3wadn
        pieceList.add(new Knight(this,1,7,true));
        pieceList.add(new Knight(this,6,7,true));
        //nzido ga3 pawns baydin
        pieceList.add(new Pawn(this,0,6,true));
        pieceList.add(new Pawn(this,1,6,true));
        pieceList.add(new Pawn(this,2,6,true));
        pieceList.add(new Pawn(this,3,6,true));
        pieceList.add(new Pawn(this,4,6,true));
        pieceList.add(new Pawn(this,5,6,true));
        pieceList.add(new Pawn(this,6,6,true));
        pieceList.add(new Pawn(this,7,6,true));
        // nzido rocks lka7lin
        // adding the Rocks
        pieceList.add(new Rock(this,0,7,true));
        pieceList.add(new Rock(this,7,7,true));
        //nzido king lbyd
        pieceList.add(new King(this,3,7,true));
        //nzido queen
        pieceList.add(new Queen(this,4,7,true));
        // nzido bishop
        pieceList.add(new Bishop(this,2,7,true));
        pieceList.add(new Bishop(this,5,7,true));
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
    //nsbgho board
        for(int r = 0;r<rows;r++)
            for (int c = 0;c<cols;c++){
                g2d.setColor((c+r) % 2==0? new Color(194, 217, 236): new Color(33, 33, 194));
                g2d.fillRect(c*titleSize,r*titleSize,titleSize,titleSize);
            }
        // nsbgho blayss li valid nmvhi lihom
        if (SelectedPiece != null)
        for(int r = 0;r<rows;r++)
            for (int c = 0;c<cols;c++){
                if(isValidMove(new Move(this,SelectedPiece,c,r))){
                    g2d.setColor(new Color(101, 242, 248, 211));
                    g2d.fillRect(c*titleSize,r *titleSize,titleSize,titleSize);

                }
            }
        for (Piece piece : pieceList){
            piece.paint(g2d);
        }
    }
}
