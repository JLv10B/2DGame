package com.practice.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import static com.practice.utilz.Constants.UI.Buttons.*;

public abstract class DefaultButton {
    protected int xPos, yPos, button_width, button_height;
    protected int xOffsetCenter = B_WIDTH /2;
    protected boolean mouseOver, mousePressed;
    protected Rectangle buttonBounds;

    public DefaultButton(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.button_width = B_WIDTH;
        this.button_height = B_HEIGHT;
    }
    
    abstract void loadImgs();
   
    abstract void update();
    
    abstract void draw(Graphics g);
    
    protected void initBounds() {
        buttonBounds = new Rectangle(xPos - xOffsetCenter, yPos, button_width, button_height);
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
