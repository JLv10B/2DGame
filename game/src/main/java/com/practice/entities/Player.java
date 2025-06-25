package com.practice.entities;

import static com.practice.utilz.Constants.Directions.*;
import static com.practice.utilz.Constants.Directions.JUMP;
import static com.practice.utilz.Constants.PlayerConstants.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Player extends Entity {
    private String playerChar = "1-Player-Bomb Guy";
    private Map<String, List<BufferedImage>> animationDict;
    private int aniTick, aniIndex, aniSpeed = 15;
    private String playerAction = IDLE;
    private boolean moving = false;
    private HashSet<Integer> playerDirection = new HashSet<Integer>();
    private double playerSpeed = 1.5;

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
        g.drawImage(animationDict.get(playerAction).get(aniIndex), (int)x, (int)y, null); 
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    
    private void setAnimation() {
        if (playerDirection.isEmpty()) {
            moving = false;
        }
        if (moving) {
            playerAction = RUN;
        } else {
            playerAction = IDLE;
        }
   }

    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { 
                aniIndex = 0;
            }
        }
    }
    
    public void addDirection(int direction) {
        if (playerDirection.isEmpty()) { // aniIndex is set to 0 at the intiation of any movement animation in order to loop from the intial movement frame
            aniIndex = 0;
        }
        if (!moving) {
            setMoving(true);
        }
        playerDirection.add(direction);
    }

    public void removeDirection(int direction) {
        playerDirection.remove(direction);
    }

    private void updatePos() {
        if (!playerDirection.isEmpty()) {
            for (int direction : playerDirection) {
                switch (direction) {
                    case LEFT:
                        x -= playerSpeed;
                        break;
                    case UP:
                        y -= playerSpeed;
                        break;
                    case RIGHT:
                        x += playerSpeed;
                        break;
                    case DOWN:
                        y += playerSpeed;
                        break;
                    // case JUMP:
                    //     y -= playerSpeed;
                    //     break;
                }
            }
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
                // System.out.println(animationDict);
            }
        }
        return img;
    }
}
