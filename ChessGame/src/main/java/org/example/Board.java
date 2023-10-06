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

    public int enPassantTile = -1;

    CheckScanner checkScanner = new CheckScanner(this);

    String last_move= "";


    Piece findKing(boolean isWhite){
        for (Piece piece: pieceList){
            if (isWhite == piece.isWhite && piece.name == "King"){
                return piece;
            }
        }
        return null;
    }



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

        if (last_move == "white") {
            if(move.piece.isWhite) {
                return false;
            }
            if(move.piece.moveCollidesWithPiece(move.newCol,move.newRow)) {
                return false;
            }
            if(checkScanner.isKingChecked(move)) {
                return false;
            }
            return true;
        }
        if (last_move == "black") {
            if(!move.piece.isWhite) {
                return false;
            }
            if(move.piece.moveCollidesWithPiece(move.newCol,move.newRow)) {
                return false;
            }
            if(checkScanner.isKingChecked(move)) {
                return false;
            }
            return true;
        }
//




        return true;
    }

    public void makeMove(Move move){
        if(last_move == "white") {
            last_move = "black";
        }else
        if(last_move == "black") {
            last_move = "white";
        }else {
            last_move = "white";
        }
        if (move.piece.name.equals("Pawn")){
            movePawn(move);
        }else {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * titleSize;
        move.piece.yPos = move.newRow * titleSize;

        move.piece.isFirstMove = false;
        captured(move.captured);}
    }

    private void movePawn(Move move) {
        int colorIndex = move.piece.isWhite ? 1 : -1;

//        if (getTitleNum(move.newCol,move.newRow) == enPassantTile){
//            move.captured = getPiece(move.newCol,move.newRow + colorIndex);
//        }
        if (getTitleNum(move.newCol, move.newRow) == enPassantTile) {
            move.captured = getPiece(move.newCol,move.newRow + colorIndex);

        }

        if (Math.abs(move.piece.row - move.newRow) == 2) {
            enPassantTile = getTitleNum(move.newCol, move.newRow + colorIndex);
        } else {
            enPassantTile = -1;
        }

        //promotion
         colorIndex = move.piece.isWhite ? 0 : 7;
        if (move.newRow==colorIndex){
            promotePawn(move);
        }



        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * titleSize;
        move.piece.yPos = move.newRow * titleSize;

        move.piece.isFirstMove = false;
        captured(move.captured);
    }

    private void promotePawn(Move move) {
        pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
        captured(move.piece);
    }

    public void captured(Piece piece){
        pieceList.remove(piece);
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

    public int getTitleNum(int col,int row){
        return row* rows + col;
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
        pieceList.add(new King(this,4,0,false));
        //nzido queen
        pieceList.add(new Queen(this,3,0,false));
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
        pieceList.add(new King(this,4,7,true));
        //nzido queen
        pieceList.add(new Queen(this,3,7,true));
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
