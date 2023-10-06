package org.example;

import org.example.pieces.Piece;

public class CheckScanner {

    Board board;



    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        Piece King = board.findKing(move.piece.isWhite);
        assert King != null;
        int kingCol = King.col;
        int KingRow = King.row;
        if(board.SelectedPiece != null && board.SelectedPiece.name.equals("King")) {
            kingCol = move.newCol;
            KingRow = move.newRow;
        }

        return  hitByRook(move.newCol,move.newRow,King,kingCol,KingRow,0,1) ||
                hitByRook(move.newCol,move.newRow,King,kingCol,KingRow,1,0) ||
                hitByRook(move.newCol,move.newRow,King,kingCol,KingRow,0,-1) ||
                hitByRook(move.newCol,move.newRow,King,kingCol,KingRow,-1,0) ||

                hitByBishop(move.newCol,move.newRow,King,kingCol,KingRow,-1,-1) ||
                hitByBishop(move.newCol,move.newRow,King,kingCol,KingRow,1,-1) ||
                hitByBishop(move.newCol,move.newRow,King,kingCol,KingRow,1,1) ||
                hitByBishop(move.newCol,move.newRow,King,kingCol,KingRow,-1,1) ||
                hitByKnight(move.newCol,move.newRow,King,kingCol,KingRow) ||
                hitByPawn(move.newCol,move.newRow,King,kingCol,KingRow)  ||
                hitByKing(King , kingCol,KingRow);
    }


    public boolean hitByRook(int col,int row,Piece king,int KingCol,int KingRow,int colVal,int rowVal) {
        for(int i=1;i< 8;i++) {
            if( KingCol + (i*colVal) == col && KingRow + (i* rowVal) == row) {
                break;
            }
            Piece p = board.getPiece(KingCol + (i*colVal), KingRow + (i* rowVal));
            if(p != null && p != board.SelectedPiece) {
                if(!board.sameTeam(p, king) && ( p.name.equals("Rock") ||  p.name.equals("Queen") ) ) {
                    return true;
                }
                break;
            }
        }
        return false;

    }
    public boolean hitByBishop(int col,int row,Piece king,int KingCol,int KingRow,int colVal,int rowVal) {
        for(int i=1;i< 8;i++) {
            if( KingCol - (i*colVal) == col && KingRow - (i* rowVal) == row) {
                break;
            }
            Piece p = board.getPiece(KingCol - (i*colVal), KingRow - (i* rowVal));
            if(p != null && p != board.SelectedPiece) {
                if(!board.sameTeam(p, king) && ( p.name.equals("Bishop") ||  p.name.equals("Queen") ) ) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    public boolean hitByKnight(int col,int row,Piece king,int KingCol,int KingRow) {

        return  checkByKnight(board.getPiece(KingCol -1, KingRow -2),king,col,row) ||
                checkByKnight(board.getPiece(KingCol +1, KingRow -2),king,col,row) ||
                checkByKnight(board.getPiece(KingCol +2, KingRow -1),king,col,row) ||
                checkByKnight(board.getPiece(KingCol +2, KingRow +1),king,col,row) ||
                checkByKnight(board.getPiece(KingCol +1, KingRow +2),king,col,row) ||
                checkByKnight(board.getPiece(KingCol -1, KingRow +2),king,col,row) ||
                checkByKnight(board.getPiece(KingCol -2, KingRow +1),king,col,row) ||
                checkByKnight(board.getPiece(KingCol -2, KingRow -1),king,col,row);
    }
    public boolean checkByKnight(Piece p,Piece k,int col,int row) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Knight") && !(p.col == col && p.row  == row) ;
    }
    public boolean hitByKing(Piece King,int kingCol,int kingRow) {
        return  checkKing(board.getPiece(kingCol -1, kingRow -1),King) ||
                checkKing(board.getPiece(kingCol +1, kingRow -1),King) ||
                checkKing(board.getPiece(kingCol , kingRow -1),King) ||
                checkKing(board.getPiece(kingCol -1, kingRow ),King) ||
                checkKing(board.getPiece(kingCol +1, kingRow ),King) ||
                checkKing(board.getPiece(kingCol -1, kingRow +1),King) ||
                checkKing(board.getPiece(kingCol +1, kingRow +1),King) ||
                checkKing(board.getPiece(kingCol , kingRow +1),King) ;
    }
    public boolean checkKing(Piece p,Piece k) {
        return p !=null && !board.sameTeam(p, k) && p.name.equals("King");
    }
    public boolean hitByPawn(int col,int row,Piece King,int kingCol,int kingRow) {
        int colorVal = King.isWhite ? -1 : 1;
        return checkByPawn(board.getPiece(kingCol +1, kingRow + colorVal),King,col,row) ||
                checkByPawn(board.getPiece(kingCol -1, kingRow + colorVal),King,col,row) ;
    }
    public boolean checkByPawn(Piece p,Piece k,int col,int row) {
        System.out.println(p!= null && !board.sameTeam(p, k) && p.name.equals("Pawn"));
        return p!= null && !board.sameTeam(p, k) && p.name.equals("Pawn");
    }
}
