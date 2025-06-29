package com.practice.entities;

import static com.practice.utilz.Constants.PlayerConstants.*;
import com.practice.actions.*;
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


public class Player extends Entity {
    private String playerChar = "1-Player-Dark Oracle";
    private Map<String, List<BufferedImage>> animationDict;
    private int aniTick, aniIndex, aniSpeed = 15;
    private String playerAction = IDLE;
    protected HashMap<Integer, Action> keybinds = new HashMap<>();

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
        defaultKeybinds();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animationDict.get(playerAction).get(aniIndex), (int)x, (int)y, 100,100,null);
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

    public void inputProcessor(KeyEvent key, UserInput userInput) {
        try {
            Action input = keybinds.get(key.getKeyCode());
            if (userInput == UserInput.KEY_PRESS){
                keybindOutput(input);     
            } else if (userInput == UserInput.KEY_RELEASE){
                movementKeyRelease(input);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    private void loadAnimations() {
        animationDict = new HashMap<>();
        List<BufferedImage> aniFrames;
        File[] spritesList;

        File spritesDir = new File(String.format("game\\src\\main\\resources\\Sprites\\%s", playerChar));
        if (!spritesDir.exists() && !spritesDir.isDirectory()) {
        } else {
            spritesList = spritesDir.listFiles(file -> file.isDirectory());
            for (int i=0; i<spritesList.length; i++) {
                String aniState = spritesList[i].getName();
                aniFrames = new ArrayList<BufferedImage>();
                File[] tmpFiles = spritesList[i].listFiles(file -> file.isFile());
                // iterate through charater states (eg. idle) to get the BufferedImage of each animation frame
                for (int j=0; j< tmpFiles.length; j++) { 
                    String tempPath = String.format("/Sprites/%s/%s/%s", playerChar, aniState, tmpFiles[j].getName());
                    BufferedImage frame = loadCharFrames(tempPath);
                    aniFrames.add(frame);
                }
                animationDict.put(aniState, aniFrames);
            }
        }
    }

    private BufferedImage loadCharFrames(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(animationDict);
            }
        }
        return img;
    }



}
