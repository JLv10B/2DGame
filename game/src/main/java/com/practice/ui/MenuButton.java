package com.practice.ui;

import java.awt.image.BufferedImage;

import com.practice.gamestates.Gamestate;
import com.practice.utilz.ImageLibrary;
import static com.practice.utilz.Constants.UI.Buttons.*;

public class MenuButton extends DefaultButton {
    
    public MenuButton(int xPos, int yPos, int spirteIndex, Gamestate state, ImageLibrary imageLibrary) {
        super(xPos, yPos, spirteIndex, state, imageLibrary);
    }
    
    @Override
    protected void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = imageLibrary.getUILibrary().get("Menu").get(spirteIndex);
        for (int i=0; i<imgs.length; i++) {
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT, 0, B_WIDTH_DEFAULT , B_HEIGHT_DEFAULT);
        }
    }

}
