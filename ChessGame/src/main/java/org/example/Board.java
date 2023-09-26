package org.example;

import org.example.pieces.Knight;
import org.example.pieces.Pawn;
import org.example.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int titleSize = 85;
    int cols=8;
    int rows=8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Board(){
        this.setPreferredSize(new Dimension(cols*titleSize,rows*titleSize));
        addPieces();
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


// fari9 bayna abyad wa asswad

        //nzido ga3 pawns baydin
        pieceList.add(new Pawn(this,0,6,true));
        pieceList.add(new Pawn(this,1,6,true));
        pieceList.add(new Pawn(this,2,6,true));
        pieceList.add(new Pawn(this,3,6,true));
        pieceList.add(new Pawn(this,4,6,true));
        pieceList.add(new Pawn(this,5,6,true));
        pieceList.add(new Pawn(this,6,6,true));
        pieceList.add(new Pawn(this,7,6,true));
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int r = 0;r<rows;r++)
            for (int c = 0;c<cols;c++){
                g2d.setColor((c+r) % 2==0? new Color(194, 217, 236): new Color(33, 33, 194));
                g2d.fillRect(c*titleSize,r*titleSize,titleSize,titleSize);
            }
        for (Piece piece : pieceList){
            piece.paint(g2d);
        }
    }
}
