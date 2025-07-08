package com.practice;

import java.awt.Graphics;

import com.practice.entities.*;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.LevelBuilder;
import com.practice.handlers.TileHandler;
import com.practice.ui.LevelEditorBar;
import com.practice.ui.MyButton;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int USP_SET = 200;
    private Player player;
    public ImageLibrary imageLibrary;

    //TODO: edit lvl & TileHandler as needed, currently used for testing:
    private int[][] lvl;
    public final static int DEFAULT_TILE_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 40;
    public final static int TILES_IN_HEIGHT = 28;
    public final static int TILES_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    private TileHandler tileHandler;
    private LevelEditorBar levelEditorBar;
    private MyButton buttonPlay, buttonEditor, buttonQuit;

    public Game() {
        try {
            imageLibrary = new ImageLibrary();
        } catch (Exception e) {
            System.out.println("Image Library not created");
        }
        //TODO: edit lvl and TileHandler as needed, currently used for testing;
        lvl = LevelBuilder.getLevelData();
        tileHandler = new TileHandler(imageLibrary);
        levelEditorBar = new LevelEditorBar(0, GAME_HEIGHT-100, GAME_WIDTH, 100);

        initClasses();
        initButtons();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        run();
        // startGameLoop();

    }

    private void initClasses() {
        player = new Player(200, 200, (int)(128*SCALE), (int)(128*SCALE), lvl, imageLibrary);
    }

    private void initButtons() {
        buttonPlay = new MyButton("Play", 100, 700, 100, 20);
        buttonEditor = new MyButton("Level Editor", 300, 700, 100, 20);
        buttonQuit = new MyButton("Quit", 500, 700, 100, 20);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void renderEntities(Graphics g) {
        player.render(g);
    }

    public void renderLevel(Graphics g) {
        // render the level & objects
        for (int y=0; y<lvl.length; y++) {
            for (int x=0; x<lvl[0].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileHandler.getGroundTileSprite(id), x*DEFAULT_TILE_SIZE, y*DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, null);
            }
        }    
    }
    
    public void renderUI(Graphics g) {
        levelEditorBar.draw(g);
        buttonPlay.draw(g);
        buttonEditor.draw(g);
        buttonQuit.draw(g);
    }
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / USP_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
                deltaU--;
                // updates++;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
                // frames++;
            }

            // Print FPS & UPS
            // if (System.currentTimeMillis() - lastCheck >= 1000) {
            //     lastCheck = System.currentTimeMillis();
            //     System.out.println("FPS: " + frames + " | UPS: " + updates);
            //     frames = 0;
            //     updates = 0;
            // }
        }
    }

    // Stops player movement set when deselecting game window
    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    
    public Player getPlayer() {
        return player;
    }
    
}
