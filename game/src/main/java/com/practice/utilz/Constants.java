package com.practice.utilz;

public class Constants {
    
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    public static class PlayerConstants {
        public static final String RUNNNING = "Running";
        public static final String IDLE = "Idle";
        public static final String SLASHING = "Slashing";
        public static final String WALKING = "Walking";
        
        //TODO: this needs to be dynamic to adapt to how many frames are in different character animation states
        // Set to 7-Player-Dark Oracle
        public static int GetSpriteAmount(String player_action) { 
            switch(player_action) {
                case RUNNNING:
                    return 12;
                case IDLE:
                    return 18;
                case SLASHING:
                    return 12;
                case WALKING:
                    return 24;
                default:
                    return 1;
            }
        }
        
    }
}
