package com.practice.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.practice.Game;
import com.practice.objects.Tile;
import com.practice.utilz.ImageLibrary;

public class TileButton {
    private int xPos, yPos, tileIndex;
    private int xOffsetCenter = TILE_BUTTON_SIZE /2;
    private ImageLibrary imageLibrary;
    private boolean mouseOver, mousePressed;
    private Rectangle buttonBounds;
    private Tile tile;

    public static final int TILE_BUTTON_SIZE = Game.TILES_SIZE;

    
    public TileButton(int xPos, int yPos, int tileIndex, ImageLibrary imageLibrary) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tileIndex = tileIndex;
        this.imageLibrary = imageLibrary;
        loadImgs();
        initBounds();
    }
    
    public void update() {
    }

    protected void initBounds() {
        buttonBounds = new Rectangle(xPos - xOffsetCenter, yPos, TILE_BUTTON_SIZE, TILE_BUTTON_SIZE);
    }
    
    protected void loadImgs() {
        tile = new Tile(imageLibrary.getTileLibrary().get("Ground Tiles").get(tileIndex), tileIndex);

    }
    
    public void draw(Graphics g) {
        g.drawImage(tile.getSprite(), xPos - xOffsetCenter, yPos, TILE_BUTTON_SIZE, TILE_BUTTON_SIZE, null);

        if (mouseOver == true) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }

        if (mousePressed == true) {
            g.drawRect( xPos - xOffsetCenter+1, yPos+1, TILE_BUTTON_SIZE-2, TILE_BUTTON_SIZE-2);
        }
        g.drawRect( xPos - xOffsetCenter, yPos, TILE_BUTTON_SIZE, TILE_BUTTON_SIZE);


    }
    
    public Tile getTile() {
        return tile;
    }
    
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
    
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    public boolean isMouseOver() {
        return mouseOver;
    }
    
    public boolean isMousePressed() {
        return mousePressed;
    }
    
    public Rectangle getButtonBounds() {
        return buttonBounds;
    }
}
