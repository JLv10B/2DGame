package com.practice.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.practice.gamestates.Gamestate;
import com.practice.utilz.ImageLibrary;
import static com.practice.utilz.Constants.UI.Buttons.*;

public class LevelEditorButton extends DefaultButton {
    protected int spirteIndex, index;
    protected BufferedImage[] imgs;
    protected ImageLibrary imageLibrary;

    public LevelEditorButton(int xPos, int yPos, int spirteIndex, ImageLibrary imageLibrary) {
        super(xPos, yPos);
        this.spirteIndex = spirteIndex;
        this.imageLibrary = imageLibrary;
        loadImgs();
        initBounds();
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

    @Override
    protected void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = imageLibrary.getUILibrary().get("Menu").get(spirteIndex);
        for (int i=0; i<imgs.length; i++) {
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT, 0, B_WIDTH_DEFAULT , B_HEIGHT_DEFAULT);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public int getFuction() {
        return spirteIndex;
    }
}
