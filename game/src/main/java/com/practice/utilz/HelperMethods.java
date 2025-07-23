package com.practice.utilz;

import static com.practice.Game.GAME_WIDTH;
import static com.practice.Game.GAME_HEIGHT;
import static com.practice.Game.TILES_SIZE;
import static com.practice.Game.tileHandler;

import java.util.ArrayList;

import com.practice.objects.GameMap;
import com.practice.objects.Tile;

public class HelperMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, GameMap mapData) {
        // Checks if user can move to (x,y) position, should be checked prior to player being moved
        if (!isSolid(x, y, mapData)) {
            if (!isSolid(x+width, y+height, mapData)) {
                if (!isSolid(x+width, y, mapData)) {
                    if (!isSolid(x, y+height, mapData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, GameMap mapData) {
        if (x<0 || x >= GAME_WIDTH) {
            return true;
        }
        if (y<0 || y >= GAME_HEIGHT) {
            return true;
        }

        int xIndex = (int)x/TILES_SIZE;
        int yIndex = (int)y/TILES_SIZE;

        Tile tile = tileHandler.getTile(mapData.getTileId(xIndex, yIndex));

        if (tile.isWalkable()) {
            return false;
        } else {
            return true;
        }
    }

    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArr = new int[ySize][xSize];

        for (int i=0; i<newArr.length; i++) {
            for (int j=0; j<newArr[0].length; j++) {
                int index = i*ySize + j;
                newArr[i][j] = list.get(index);
            }
        }
        return newArr;
    }
}
