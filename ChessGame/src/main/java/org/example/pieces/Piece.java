package org.example.pieces;

import org.example.Board;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
    public int col,row;
    public int xPos,yPos;

    public boolean isWhite;
    public String name;
    public int value;

    public boolean isFirstMove = true;

    BufferedImage sheet;
    {
        try{
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        }catch (IIOException e){
            e.printStackTrace();
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }


    }
    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;
    Board board;
    public Piece(Board board){
        this.board = board;
    }

    public boolean isValidMovement(int col,int row){
        return true;
    }
    public boolean moveCollidesWithPiece(int col,int row){
        return false;
    }


    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite,xPos,yPos,null);
    }
}
