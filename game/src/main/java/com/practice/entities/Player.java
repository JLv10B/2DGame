package com.practice.entities;

import static com.practice.utilz.Constants.Directions.*;
import static com.practice.utilz.Constants.PlayerConstants.*;
import com.practice.actions.*;

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
    private String playerChar = "1-Player-Dark Oracle";
    private Map<String, List<BufferedImage>> animationDict;
    public Skill[] playerSkillBar = new Skill[3]; // TODO: playerSkillBar should be a fixed length array
    private int aniTick, aniIndex, aniSpeed = 15;
    private String playerAction = IDLE;
    public boolean moving = false;
    private double playerSpeed = 1;
    private boolean left, right, up, down;
    public boolean movementLock = false;
    private boolean skillLock = false;
    private String skillAction;


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
        String starting = playerAction;

        if (skillLock) {
            playerAction = skillAction;
        } else if (moving) {
            playerAction = RUNNNING;
        } else {
            playerAction = IDLE;
        }
   
        if (starting != playerAction) {
            System.out.println("Action change");
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

    private void updatePos() {

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

    }
    
    //TODO: Currently skillActivation only activates the first skill in playerSkillBar for testing.
    public void skillActivation() {
        playerSkillBar[0] = new Slash();
        skillAction = playerSkillBar[0].animation(this);
        skillLock = true;
        // System.out.println("Slash used, playerAction: " + skillAction + " skillLock: " + skillLock);
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
