package com.practice.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.practice.Game;
import com.practice.gamestates.Gamestate;
import com.practice.utilz.ImageLibrary;

import static com.practice.utilz.Constants.UI.Buttons.*;

public class OptionButton {
    protected int xPos, yPos, spirteIndex, index;
    protected int xOffsetCenter = B_WIDTH /2;
    protected Gamestate state;
    protected BufferedImage[] imgs;
    protected ImageLibrary imageLibrary;
    protected boolean mouseOver, mousePressed;
    protected Rectangle buttonBounds;
    protected int UI_SPRITE = OPTION_BUTTONS;

    
    public OptionButton(int xPos, int yPos, int spirteIndex, Gamestate state, ImageLibrary imageLibrary) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.spirteIndex = spirteIndex;
        this.state = state;
        this.imageLibrary = imageLibrary;
        this.UI_SPRITE = OPTION_BUTTONS;
        loadImgs();
        initBounds();
    }
    
    protected void initBounds() {
        buttonBounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }
    
    protected void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = imageLibrary.getUILibrary().get("Menu").get(UI_SPRITE);
        for (int i=0; i<imgs.length; i++) {
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT, spirteIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT , B_HEIGHT_DEFAULT);
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }
    
    public void update() {
        index = 0;
        if (mouseOver){
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }
    
    public void applyGamestate() {
        Gamestate.state = state;
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
