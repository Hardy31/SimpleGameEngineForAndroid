package com.example.simplegameengineforandroid;

public class Cell {
    public int positionX;
    public int positionY;
    public String picture;
    public int collor;
    public String text;
    private String  shrift;
    private int sizeText;

    public Cell( int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public Cell( int positionX, int positionY, int collor){
        this.positionX = positionX;
        this.positionY = positionY;
        this.collor = collor;
    }
}
