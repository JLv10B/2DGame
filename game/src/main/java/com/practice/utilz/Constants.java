package com.practice.utilz;

public class Constants {
    
    public enum UserInput {KEY_PRESS, KEY_RELEASE, MOUSE_PRESS};
    
    public enum Action {UP, DOWN, LEFT, RIGHT, SKILL_1, SKILL_2, SKILL_3, CHANGE_KEYBINDS};
    public static class PlayerConstants {
        public static final String RUNNNING = "Running";
        public static final String IDLE = "Idle";
        public static final String SLASHING = "Slashing";
        public static final String RUNSLASHING = "Run Slashing";
        
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
                case RUNSLASHING:
                    return 12;
                default:
                    System.out.println("No sprite count stored for this action");
                    return 1;
            }
        }
        
    }
}
