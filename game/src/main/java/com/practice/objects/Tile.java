package com.practice.objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private int tileIndex;

    public Tile(BufferedImage sprite, int tileIndex) {
        this.sprite = sprite;
        this.tileIndex = tileIndex;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    public int getTileIndex() {
        return tileIndex;
    }
}
