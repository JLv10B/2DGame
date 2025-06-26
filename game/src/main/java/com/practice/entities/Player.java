package com.practice.entities;

import static com.practice.utilz.Constants.Directions.*;
import static com.practice.utilz.Constants.PlayerConstants.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Player extends Entity {
    private String playerChar = "1-Player-Dark Oracle";
    private Map<String, List<BufferedImage>> animationDict;
    private int aniTick, aniIndex, aniSpeed = 15;
    private String playerAction = IDLE;
    private boolean moving = false;
    private double playerSpeed = 1.5;
    // private String animationState = IDLE;
    private boolean left, right, up, down;
    public boolean movementLock = false;
    // private boolean skillLock = false;
    private boolean newAction = false;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animationDict.get(playerAction).get(aniIndex), (int)x, (int)y, 100,100,null); 
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    private void setAnimation() {
        if (moving) {
            playerAction = RUNNNING;
        } else {
            playerAction = IDLE;
        }
   }

    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= GetSpriteAmount(playerAction) || newAction == true) { 
                aniIndex = 0;
                newAction = false;
                System.out.println("aniIndex: " + aniIndex + " updateAnimationTick newAction: " + newAction);
            }
        }
    }

    private void updatePos() {
        boolean starting = moving;

        if (left == false && right == false && up == false && down == false || movementLock == true) {
            moving = false;
        } else {
            if (left && !right) {
                x -= playerSpeed;
                moving = true;
            } else if (right && !left) {
                x += playerSpeed;
                moving = true;
            }
            if (up && !down) {
                y -= playerSpeed;
                moving = true;
            } else if (down && !up) {
                y+= playerSpeed;
                moving = true;
            }
        }

        if (starting != moving) {
            newAction = true;
            System.out.println("starting: " + starting + " moving: " + moving);
            System.out.println("newAction: " + newAction);
        }
    }
    
    private void loadAnimations() {
        animationDict = new HashMap<>();
        List<BufferedImage> aniFrames;
        File[] spritesList;

        File spritesDir = new File(String.format("game\\src\\main\\resources\\Sprites\\%s", playerChar));
        if (!spritesDir.exists() && !spritesDir.isDirectory()) {
            System.err.println("Sprite directory not found");
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
            // System.err.println("Error path: " + path);
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

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }


}
