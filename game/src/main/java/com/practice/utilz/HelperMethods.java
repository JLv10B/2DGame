package com.practice.utilz;

import static com.practice.Game.GAME_WIDTH;
import static com.practice.Game.GAME_HEIGHT;
import static com.practice.Game.DEFAULT_TILE_SIZE;

public class HelperMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        // Checks if user can move to (x,y) position, should be checked prior to player being moved
        if (!isSolid(x, y, levelData)) {
            if (!isSolid(x+width, y+height, levelData)) {
                if (!isSolid(x+width, y, levelData)) {
                    if (!isSolid(x, y+height, levelData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x<0 || x >= GAME_WIDTH) {
            return true;
        }
        if (y<0 || y >= GAME_HEIGHT) {
            return true;
        }

        int xIndex = (int)x/DEFAULT_TILE_SIZE;
        int yIndex = (int)y/DEFAULT_TILE_SIZE;

        int value = levelData[yIndex][xIndex];

        // TODO edit what is a valid sprite once tile sprites are set
        if (value >=6 || value < 0) {
            return true;
        } else {
            return false;
        }
    }
}
