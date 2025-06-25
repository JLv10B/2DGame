package com.practice.utilz;

public class Constants {
    
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        public static final int JUMP = 4;
    }
    
    public static class PlayerConstants {
        public static final String RUN = "2-Run";
        public static final String IDLE = "1-Idle";
        public static final String JUMP = "4-Jump";
        public static final String FALL = "5-Fall";
        public static final String GROUND = "6-Ground";
        public static final String HIT = "7-Hit";

        public static int GetSpriteAmount(String player_action) {
            switch(player_action) {
                case RUN:
                    return 14;
                case IDLE:
                    return 26;
                case JUMP:
                    return 4;
                case FALL:
                    return 2;
                case GROUND:
                    return 3;
                case HIT:
                    return 8;
                default:
                    return 1;
            }
        }
        
    }
}
