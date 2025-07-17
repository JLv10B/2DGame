package com.practice.objects;

import static com.practice.Game.TILES_IN_WIDTH;
import static com.practice.Game.TILES_IN_HEIGHT;

public class Map {
    private int[][] mapTileData;
    private String mapName;


    public Map() {
        this.mapName = "New Map";
        this.mapTileData = loadBlankMap();
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

    public String getMapName() {
        return mapName;
    }
}
