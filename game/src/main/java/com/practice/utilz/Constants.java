package com.practice.utilz;

import com.practice.Game;

public class Constants {
    
    public static class UI {
        public static class Buttons {
            public static final int MENU_BUTTONS = 0;
            public static final int MENU_BACKGROUND = 1;
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
            

        }
    }
    
    public enum UserInput {KEY_PRESS, KEY_RELEASE, MOUSE_PRESS};
    
    public enum Action {UP, DOWN, LEFT, RIGHT, SKILL_1, SKILL_2, SKILL_3, CHANGE_KEYBINDS, MENU};

    // public enum CharAnimationConstants {RUNNING, IDLE, SLASHING, RUN_SLASHING, KICKING, SLIDING};
    public static class PlayerConstants {
        public static final String RUNNNING = "Running";
        public static final String IDLE = "Idle";
        public static final String SLASHING = "Slashing";
        public static final String RUN_SLASHING = "Run Slashing";
        public static final String KICKING = "Kicking";
        public static final String SLIDING = "Sliding";
        
    }
}
