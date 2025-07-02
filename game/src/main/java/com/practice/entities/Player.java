package com.practice.entities;

import static com.practice.utilz.Constants.PlayerConstants.*;
import com.practice.actions.*;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.Constants.Action;
import com.practice.utilz.Constants.UserInput;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Player extends Entity {
    private String playerChar = "1-Player-Dark Oracle";
    private int aniTick, aniIndex, aniSpeed = 15;
    private String playerAction = IDLE;
    protected HashMap<Integer, Action> keybinds = new HashMap<>();

    public Player(float x, float y, ImageLibrary imageLibrary) {
        super(x, y, imageLibrary);
        // loadAnimations();
        defaultKeybinds();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(imageLibrary.getCharLibrary().get(playerChar).get(playerAction).get(aniIndex), (int)x, (int)y, 100,100,null);
    }

    private void setAnimation() {
        String starting = playerAction;

        if (skillLock) {
            playerAction = skillAction;
        } else if (moving()) {
            playerAction = RUNNNING;
        } else {
            playerAction = IDLE;
        }
   
        if (starting != playerAction) {
            resetAnimationTick();
        }
   }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updateAnimationTick() {
          aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { 
                // System.out.println("aniIndex before reset: " + aniIndex + " for playerAction: " + playerAction);
                aniIndex = 0;
                skillLock = false;
                // System.out.println("skillLock reset: " + skillLock);
            }
        }

    }

    protected void defaultKeybinds() {
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
