package com.practice.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.practice.Game;
import com.practice.handlers.TileHandler;
import com.practice.objects.Tile;
import com.practice.ui.LevelEditorBar;
import com.practice.ui.MenuButton;
import com.practice.ui.TileButton;
import com.practice.utilz.ImageLibrary;
import static com.practice.utilz.Constants.*;
import static com.practice.utilz.Constants.UI.Buttons.B_MENU_SCREEN_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_OPTIONS_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_WIDTH;

public class LevelEditor extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private List<TileButton> tileButtons = new ArrayList<>();
    private LevelEditorBar levelEditorBar;
    private TileHandler tileHandler;
    
    public LevelEditor(Game game, ImageLibrary imageLibrary, TileHandler tileHandler) {
        super(game, imageLibrary);
        this.tileHandler = tileHandler;
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(100, Game.GAME_HEIGHT-50, B_MENU_SCREEN_SPRITE, Gamestate.MENU, imageLibrary);
        buttons[1] = new MenuButton( 100 + B_WIDTH, Game.GAME_HEIGHT-50, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[2] = new MenuButton( 3 * 50 + 2*B_WIDTH, Game.GAME_HEIGHT-50, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[3] = new MenuButton( 4 * 50 + 3*B_WIDTH, Game.GAME_HEIGHT-50, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        for (int i=0; i<imageLibrary.tileSpriteLibrary.get("Ground Tiles").size(); i++) {
            tileButtons.add(new TileButton(((i+1)*50 + i*TileButton.TILE_BUTTON_SIZE), Game.GAME_HEIGHT-125, i, imageLibrary));
        }
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
        for (TileButton tb: tileButtons) {
            tb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelEditorBar = new LevelEditorBar(0, Game.GAME_HEIGHT-150, Game.GAME_WIDTH, 150);
        levelEditorBar.draw(g);
        for (MenuButton mb: buttons) {
            mb.draw(g);
        }
        for (TileButton tb: tileButtons) {
            tb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if(isOverButton(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if(isOverButton(e, mb)) {
                if(mb.isMousePressed()) {
                    mb.applyGamestate();
                break;
                }
            }
        }
        resetButtons();
    }

    
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }
        
        for (MenuButton mb : buttons) {
            if(isOverButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }
}
