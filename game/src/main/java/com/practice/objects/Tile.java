package com.practice.objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private int tileIndex;
    private boolean isWalkable;

    public Tile(BufferedImage sprite, int tileIndex) {
        this.sprite = sprite;
        this.tileIndex = tileIndex;
        this.isWalkable = true;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
