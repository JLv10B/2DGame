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
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.practice.inputs.Keyboardinputs;
import com.practice.inputs.Mouseinputs;

public class GamePanel extends JPanel {

    private Mouseinputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img;
    private Map<String, Map<String, List<BufferedImage>>> animationDict;
    private int aniTick, aniIndex, aniSpeed = 8;

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

        File spritesDir = new File("/Sprites");
        if (!spritesDir.exists() && !spritesDir.isDirectory()) {
            System.err.println("Sprite directory not found");
        } else {
            spritesList = spritesDir.listFiles(file -> file.isDirectory());
            for (int i=0; i< spritesList.length; i++) {
                File[] tmpDir = spritesList[i].listFiles(file -> file.isDirectory());
                String charIdName = spritesList[i].getName();
                charState = new HashMap<String, List<BufferedImage>>();

                for (int j=0; j< tmpDir.length; j++) { // iterate though charater files
                    aniFrames = new ArrayList<BufferedImage>();
                    String aniState = tmpDir[j].getName();
                    File[] tmpFiles = tmpDir[j].listFiles(file -> file.isFile());

                    for (int k=0; k< tmpFiles.length; k++) { // iterate through charater states (eg. idle) to get the BufferedImage of each animation frame
                        String tempPath = tmpFiles[k].getAbsolutePath();
                        BufferedImage frame = loadCharFrames(tempPath);
                        aniFrames.add(frame);
                    }
                    charState.put(aniState, aniFrames);
                }
                animationDict.put(charIdName, charState);
            }

            // animations = new BufferedImage[spritesList.length][26]; // TODO: Make 2nd variable dynamic to accomidate for animation directories with more files

            // for (int j=0; j<animations.length; j++) {
            //     String tempFilePath = String.format("/Resources/Sprites/%s/%d.png", spritesList[j].getName()); // TODO: fix this and below
            //     for (int i=0; i<animations[0].length; i++) {
            //         String idlePNGPath = String.format("/Resources/Sprites/1-Player-Bomb Guy/1-Idle/%d.png", i+1);
            //         BufferedImage frame = loadCharFrames(idlePNGPath);
            //         animations[j][i] = frame;
            
        }

    }

    private BufferedImage loadCharFrames(String path) {
        InputStream is = getClass().getResourceAsStream(path);
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

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    
    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex ++;
            if (aniIndex >= 26) { // TODO: fix this to be dynamic
                aniIndex = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        // g.drawImage(idleAni[1], (int)xDelta, (int)yDelta, 40,40, null);
        g.drawImage(animationDict.get("1-Player-Bomb Guy").get("1-Idle").get(aniIndex), (int)xDelta, (int)yDelta, null); //TODO: Fix animations[][]
    }
}