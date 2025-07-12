package com.practice;

import java.awt.Graphics;

import com.practice.entities.*;
import com.practice.gamestates.Gamestate;
import com.practice.gamestates.Menu;
import com.practice.gamestates.Options;
import com.practice.gamestates.Playing;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.LevelBuilder;
import com.practice.handlers.TileHandler;
import com.practice.ui.LevelEditorBar;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int USP_SET = 200;
    public static ImageLibrary imageLibrary;

    //TODO: Gamestate managing
    private Playing playing;
    private Menu menu;
    private Options options;

    //TODO: edit lvl & TileHandler as needed, currently used for testing:
    // private int[][] lvl;
    public final static int DEFAULT_TILE_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 40;
    public final static int TILES_IN_HEIGHT = 28;
    public final static int TILES_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public TileHandler tileHandler;

    public Game() {
        try {
            imageLibrary = new ImageLibrary();
        } catch (Exception e) {
            System.out.println("Image Library not created");
        }
        tileHandler = new TileHandler(imageLibrary);

        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        run();
        // startGameLoop();

    }

    private void initClasses() {
        menu = new Menu(this, imageLibrary);
        options = new Options(this, imageLibrary);
        playing = new Playing(this, imageLibrary);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
                options.update();
                break;
            default:
                break;
            
        }
    }

    public void renderEntities(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
            
        }
    }

    public void renderLevel(Graphics g) {
        // render the background & objects
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.drawLevel(g);
                break;
            case OPTIONS:
                options.draw(g);
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }    
    }
    
    // public void renderUI(Graphics g) {

    // }

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

    public Menu getMenu() {
        return menu;
    }
    
    public Options getOptions() {
        return options;
    }

    public Playing getPlaying() {
        return playing;
    }

    // // Stops player movement set when deselecting game window
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }
    
}
