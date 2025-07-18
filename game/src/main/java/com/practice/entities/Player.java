package com.practice.entities;

import com.practice.objects.GameMap;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.Constants.Action;
import com.practice.utilz.Constants.UserInput;

import java.util.HashMap;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Player extends Entity {
    protected HashMap<Integer, Action> keybinds = new HashMap<>();


    public Player(float x, float y, int width, int height, GameMap mapData, ImageLibrary imageLibrary) {
        super(x, y, width, height, mapData, imageLibrary);
        this.charModel = "1-Player-Dark Oracle";
        defaultKeybinds();
        initHitbox(x, y, (int) (width), (int) (height));
    }


    protected void defaultKeybinds() {
        keybinds.put(KeyEvent.VK_ESCAPE, Action.MENU);
        keybinds.put(KeyEvent.VK_W, Action.UP);
        keybinds.put(KeyEvent.VK_S, Action.DOWN);
        keybinds.put(KeyEvent.VK_A, Action.LEFT);
        keybinds.put(KeyEvent.VK_D, Action.RIGHT);
        keybinds.put(MouseEvent.BUTTON1, Action.SKILL_1);
        keybinds.put(MouseEvent.BUTTON3, Action.SKILL_2);
        keybinds.put(KeyEvent.VK_V, Action.SKILL_3);
    }
    
    //TODO: Update changeKeybinds() to allow players to customize keybinds
    protected void changeKeybinds() {
        keybinds.put(KeyEvent.VK_Y, Action.UP);
        keybinds.put(KeyEvent.VK_H, Action.DOWN);
        keybinds.put(KeyEvent.VK_G, Action.LEFT);
        keybinds.put(KeyEvent.VK_J, Action.RIGHT);
        keybinds.remove(KeyEvent.VK_W, Action.UP);
        keybinds.remove(KeyEvent.VK_S, Action.DOWN);
        keybinds.remove(KeyEvent.VK_A, Action.LEFT);
        keybinds.remove(KeyEvent.VK_D, Action.RIGHT);
    }

    public void keyboardInputProcessor(KeyEvent key, UserInput userInput) {
        try {
            Action input = keybinds.get(key.getKeyCode());
            if (userInput == UserInput.KEY_PRESS){
                keybindOutput(input);     
            } else if (userInput == UserInput.KEY_RELEASE){
                movementKeyRelease(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mouseInputProcessor(MouseEvent key) {
        try {
            Action input = keybinds.get(key.getButton());
            keybindOutput(input);     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
