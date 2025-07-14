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
    //    GRASS = new Tile(imageLibrary.getTileLibrary().get("Ground Tiles").get(3));
    //    DIRT = new Tile(imageLibrary.getTileLibrary().get("Ground Tiles").get(0));
    //    SAND = new Tile(imageLibrary.getTileLibrary().get("Ground Tiles").get(4));
    }

    private void createTiles() {
        
    }

    public BufferedImage getGroundTileSprite(int id) {
        return imageLibrary.getTileLibrary().get("Ground Tiles").get(id);
    }

}
