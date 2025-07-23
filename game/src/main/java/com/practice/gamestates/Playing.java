package com.practice.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.practice.Game;
import com.practice.entities.Player;
import com.practice.objects.GameMap;
import com.practice.utilz.ImageLibrary;

import static com.practice.Game.TILES_SIZE;

public class Playing extends State implements Statemethods{
    private Player player;
    public static GameMap currentMap;

    public Playing(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        initClasses();
        
    }
    
    private void initClasses() {
        //TODO: load correct map for the player's save point
        currentMap = new GameMap(null, null);
        player = new Player(200, 200, (int)(128*Game.SCALE), (int)(128*Game.SCALE), Game.imageLibrary);
    }

    
    public static void loadMap(GameMap gameMap) {
        currentMap = gameMap;
    }

    @Override
    public void update() {
        player.update();
    }
    
    @Override
    public void draw(Graphics g) {
        player.render(g);
    }

    public void drawMap(Graphics g) {
        for (int y=0; y<currentMap.getMapData().length; y++) {
            for (int x=0; x<currentMap.getMapData()[0].length; x++) {
                int id = currentMap.getMapData()[y][x];
                g.drawImage(Game.tileHandler.getGroundTileSprite(id), x*TILES_SIZE, y*TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Stops player movement set when deselecting game window
    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    
    public Player getPlayer() {
        return player;
    }
}
