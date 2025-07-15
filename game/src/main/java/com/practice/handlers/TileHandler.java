package com.practice.handlers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.practice.objects.Tile;
import com.practice.utilz.ImageLibrary;

public class TileHandler {
    public ArrayList<Tile> tiles = new ArrayList<>();
    private ImageLibrary imageLibrary;


    public TileHandler(ImageLibrary imageLibrary) {
        this.imageLibrary = imageLibrary;
        loadAtlas();
    }

    private void loadAtlas() {
        for (int i=0; i<imageLibrary.tileSpriteLibrary.get("Ground Tiles").size(); i++) {
            tiles.add(new Tile(imageLibrary.getTileLibrary().get("Ground Tiles").get(i), i));
        }
    }

    public ArrayList<Tile> getTileList() {
        return tiles;
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getGroundTileSprite(int id) {
        return tiles.get(id).getSprite();
    }

}
