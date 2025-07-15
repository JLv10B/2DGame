package com.practice.handlers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.practice.objects.Tile;
import com.practice.utilz.ImageLibrary;

public class TileHandler {
    public Tile GRASS, SAND, DIRT;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    private ImageLibrary imageLibrary;


    public TileHandler(ImageLibrary imageLibrary) {
        this.imageLibrary = imageLibrary;
        loadAtlas();
    }

    private void loadAtlas() {
    }

    private void createTiles() {
        
    }

    public BufferedImage getGroundTileSprite(int id) {
        return imageLibrary.getTileLibrary().get("Ground Tiles").get(id);
    }

}
