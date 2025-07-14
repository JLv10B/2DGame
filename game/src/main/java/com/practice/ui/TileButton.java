package com.practice.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.practice.Game;
import com.practice.gamestates.Gamestate;
import com.practice.utilz.ImageLibrary;

import static com.practice.utilz.Constants.UI.Buttons.*;

public class TileButton {
    protected int xPos, yPos, tileIndex, index;
    protected int xOffsetCenter = TILE_BUTTON_SIZE /2;
    protected BufferedImage tileImg;
    protected ImageLibrary imageLibrary;
    protected boolean mouseOver, mousePressed;
    protected Rectangle buttonBounds;
    protected int UI_SPRITE;

    public static final int TILE_BUTTON_SIZE = 50;

    
    public TileButton(int xPos, int yPos, int tileIndex, ImageLibrary imageLibrary) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tileIndex = tileIndex;
        this.imageLibrary = imageLibrary;
        loadImgs();
        initBounds();
    }
    
    protected void initBounds() {
        buttonBounds = new Rectangle(xPos - xOffsetCenter, yPos, TILE_BUTTON_SIZE, TILE_BUTTON_SIZE);
    }
    
    protected void loadImgs() {
        tileImg = imageLibrary.getTileLibrary().get("Ground Tiles").get(tileIndex);
    }
    
    public void draw(Graphics g) {
        g.drawImage(tileImg, xPos - xOffsetCenter, yPos, TILE_BUTTON_SIZE, TILE_BUTTON_SIZE, null);
    }
    
    public void update() {
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
