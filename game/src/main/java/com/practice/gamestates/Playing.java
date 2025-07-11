package com.practice.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.practice.Game;
import com.practice.entities.Player;
import com.practice.handlers.TileHandler;
import com.practice.ui.LevelEditorBar;
import com.practice.ui.MyButton;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.LevelBuilder;

import static com.practice.Game.DEFAULT_TILE_SIZE;

public class Playing extends State implements Statemethods{
    private Player player;

    //TODO: edit lvl & TileHandler as needed, currently used for testing:
    public int[][] lvl;
    private TileHandler tileHandler;

    public Playing(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        lvl = LevelBuilder.getLevelData();
        initClasses();
      
    }

    private void initClasses() {
        player = new Player(200, 200, (int)(128*Game.SCALE), (int)(128*Game.SCALE), lvl, Game.imageLibrary);
    }

    
    @Override
    public void update() {
        player.update();
    }
    
    @Override
    public void draw(Graphics g) {
        player.render(g);
    }

    public void drawLevel(Graphics g) {
        for (int y=0; y<lvl.length; y++) {
            for (int x=0; x<lvl[0].length; x++) {
                int id = lvl[y][x];
                g.drawImage(game.tileHandler.getGroundTileSprite(id), x*DEFAULT_TILE_SIZE, y*DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, null);
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
