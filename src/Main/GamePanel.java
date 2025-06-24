package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.Keyboardinputs;
import Inputs.Mouseinputs;

public class GamePanel extends JPanel {

    private Mouseinputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img;
    private BufferedImage[][] animations; // TODO: Consider what the best data structure is best for this. I need to store Char type, Animation state, and image number
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

        File[] spritesList;
        File spritesDir = new File("/Resources/Sprites");
        if (!spritesDir.exists() && !spritesDir.isDirectory()) {
            System.err.println("Sprite directory not found");
        } else {
            spritesList = spritesDir.listFiles(file -> file.isDirectory()); // spritesList contains sprites for all characters
            animations = new BufferedImage[spritesList.length][26]; // TODO: Make 2nd variable dynamic to accomidate for animation directories with more files

            for (int j=0; j<animations.length; j++) {
                String tempFilePath = String.format("/Resources/Sprites/%s/%d.png", spritesList[j].getName(), i+1);
                for (int i=0; i<animations[0].length; i++) {
                    String idlePNGPath = String.format("/Resources/Sprites/1-Player-Bomb Guy/1-Idle/%d.png", i+1);
                    BufferedImage frame = loadCharFrames(idlePNGPath);
                    animations[j][i] = frame;
                }
            }
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
        InputStream is = getClass().getResourceAsStream("/Resources/Sprites/1-Player-Bomb Guy/1-Idle/1.png");
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
            if (aniIndex >= animations.length) {
                aniIndex = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        // g.drawImage(idleAni[1], (int)xDelta, (int)yDelta, 40,40, null);
        g.drawImage(animations[aniIndex][], (int)xDelta, (int)yDelta, null);
    }
}