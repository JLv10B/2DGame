package com.practice.objects;

import static com.practice.Game.TILES_IN_WIDTH;

import com.practice.handlers.TileHandler;

import static com.practice.Game.TILES_IN_HEIGHT;

public class GameMap {
    private int[][] mapTileData;
    private String mapName;
    private TileHandler tileHandler;


    public GameMap(TileHandler tileHandler) {
        this.mapName = "New Map";
        this.mapTileData = loadBlankMap();
        this.tileHandler = tileHandler;
    }

    private static int[][] loadBlankMap() {
        int[][] blankMap = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        for (int i=0; i<TILES_IN_HEIGHT; i++) {
            for (int j=0; j<TILES_IN_WIDTH; j++) {
                blankMap[i][j] = 1;
            }
        }
        return blankMap;
    }

    public void saveMapEdits(int[][] mapEdits) {
        this.mapTileData = mapEdits;
    }

    public void setName(String name) {
        this.mapName = name;
    }

    public int[][] getMapData() {
        return mapTileData;
    }

    public Tile getTile(int x, int y) {
        return tileHandler.getTile(mapTileData[y][x]);
    }

    public String getMapName() {
        return mapName;
    }
}
