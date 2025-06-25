package com.practice;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.practice.inputs.Keyboardinputs;
import com.practice.inputs.Mouseinputs;
import static com.practice.utilz.Constants.Directions.*;
import static com.practice.utilz.Constants.PlayerConstants.*;
public class GamePanel extends JPanel {

    private Mouseinputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img;
    private Map<String, Map<String, List<BufferedImage>>> animationDict;
    private int aniTick, aniIndex, aniSpeed = 7;
    private String playerAction = HIT;
    private int playerDirection = -1;
    private boolean moving = false;

    private HashSet<Integer> tmpMoving = new HashSet<Integer>(); // TODO: testing

    public GamePanel() {

        mouseInputs = new Mouseinputs(this);

        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new Keyboardinputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animationDict = new HashMap<>();
        Map<String, List<BufferedImage>> charState;
        List<BufferedImage> aniFrames;
        File[] spritesList;

        File spritesDir = new File("game\\src\\main\\resources\\Sprites");
        if (!spritesDir.exists() && !spritesDir.isDirectory()) {
            System.err.println("Sprite directory not found");
        } else {
            spritesList = spritesDir.listFiles(file -> file.isDirectory());
            for (int i=0; i< spritesList.length; i++) {
                File[] tmpDir = spritesList[i].listFiles(file -> file.isDirectory());
                String charIdName = spritesList[i].getName();
                charState = new HashMap<String, List<BufferedImage>>();
                // iterate though charater files
                for (int j=0; j< tmpDir.length; j++) { 
                    aniFrames = new ArrayList<BufferedImage>();
                    String aniState = tmpDir[j].getName();
                    File[] tmpFiles = tmpDir[j].listFiles(file -> file.isFile());
                    // iterate through charater states (eg. idle) to get the BufferedImage of each animation frame
                    for (int k=0; k< tmpFiles.length; k++) { 
                        String tempPath = String.format("/Sprites/%s/%s/%s", charIdName, aniState, tmpFiles[k].getName());
                        BufferedImage frame = loadCharFrames(tempPath);
                        aniFrames.add(frame);
                    }
                    charState.put(aniState, aniFrames);
                }
                animationDict.put(charIdName, charState);
            }
            
        }

    }

    private BufferedImage loadCharFrames(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error path: " + path);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return img;
    }


    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Sprites/1-Player-Bomb Guy/1-Idle/1.png");
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    
    private void setAnimation() {
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
        if (tmpMoving.isEmpty()) { // aniIndex is set to 0 at the intiation of any movement animation in order to loop from the intial movement frame
            aniIndex = 0;
        }
        if (!moving) {
            setMoving(true);
        }
        tmpMoving.add(direction);
    }

    public void removeDirection(int direction) {
        tmpMoving.remove(direction);
        if (tmpMoving.isEmpty()) {
            moving = false;
        }
    }

    private void updatePos() {
        if (!tmpMoving.isEmpty()) {
            for (int direction : tmpMoving) {
                switch (direction) {
                    case LEFT:
                        xDelta -= 3;
                        break;
                    case UP:
                        yDelta -= 3;
                        break;
                    case RIGHT:
                        xDelta += 3;
                        break;
                    case DOWN:
                        yDelta += 3;
                        break;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePos();

        g.drawImage(animationDict.get("1-Player-Bomb Guy").get(playerAction).get(aniIndex), (int)xDelta, (int)yDelta, null); 
        //TODO: Animations need to chance with the character model state change
    }

}
