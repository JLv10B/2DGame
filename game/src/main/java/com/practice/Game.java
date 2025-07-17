package com.practice;

import java.awt.Graphics;


import com.practice.gamestates.Gamestate;
import com.practice.gamestates.MapEditor;
import com.practice.gamestates.Menu;
import com.practice.gamestates.Playing;
import com.practice.utilz.ImageLibrary;
import com.practice.handlers.TileHandler;

public class Game implements Runnable{

    private GameWindow gameWindow;
    public GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int USP_SET = 200;
    public static ImageLibrary imageLibrary;
    public TileHandler tileHandler;

    //Gamestate managing
    private Playing playing;
    private Menu menu;
    private MapEditor mapEditor;

    public final static int DEFAULT_TILE_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 40;
    public final static int TILES_IN_HEIGHT = 28;
    public final static int TILES_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    

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
        // run();
        startGameLoop();

    }

    private void initClasses() {
        menu = new Menu(this, imageLibrary);
        mapEditor = new MapEditor(this, imageLibrary, tileHandler);
        playing = new Playing(this, imageLibrary);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
            case OPTIONS:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case MAPEDITOR:
                mapEditor.update();
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

    public void renderMap(Graphics g) {
        // render the background & objects
        switch (Gamestate.state) {
            case MENU:
            case OPTIONS:
                menu.draw(g);
                break;
            case PLAYING:
                playing.drawMap(g);
                break;
            case MAPEDITOR:
                mapEditor.draw(g);
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

        // int frames = 0;
        // int updates = 0;
        // long lastCheck = System.currentTimeMillis();

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

    public MapEditor getMapEditor() {
        return mapEditor;
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
