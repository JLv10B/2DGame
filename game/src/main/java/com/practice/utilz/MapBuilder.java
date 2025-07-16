package com.practice.utilz;

import static com.practice.Game.TILES_IN_WIDTH;
import static com.practice.Game.TILES_IN_HEIGHT;

public class MapBuilder {
    public static int[][] getMapData() {
        int[][] map = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        for (int i=0; i<TILES_IN_HEIGHT; i++) {
            for (int j=0; j<TILES_IN_WIDTH; j++) {
                map[i][j] = 1;
            }
        }
        return map;
    }

    public static int[][] loadBlankMap() {
        int[][] blankMap = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        for (int i=0; i<TILES_IN_HEIGHT; i++) {
            for (int j=0; j<TILES_IN_WIDTH; j++) {
                blankMap[i][j] = 1;
            }
        }
        return blankMap;
    }
}
