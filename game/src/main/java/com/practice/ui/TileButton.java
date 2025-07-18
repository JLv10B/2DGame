package com.practice.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.practice.Game;
import com.practice.objects.Tile;

public class TileButton extends DefaultButton{
    private Tile tile;
    
    public static final int TILE_BUTTON_SIZE = Game.TILES_SIZE;
    
    
    public TileButton(int xPos, int yPos, Tile tile) {
        super(xPos, yPos);
        this.tile = tile;
        this.xOffsetCenter = TILE_BUTTON_SIZE /2;
        this.button_width = TILE_BUTTON_SIZE;
        this.button_height = TILE_BUTTON_SIZE;
        initBounds();
    }
    
    public void update() {
    }
    
    protected void loadImgs() {
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
    
}
