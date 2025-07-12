package com.practice.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.practice.Game;
import com.practice.ui.LevelEditorBar;
import com.practice.ui.MenuButton;
import com.practice.utilz.ImageLibrary;
import static com.practice.utilz.Constants.*;
import static com.practice.utilz.Constants.UI.Buttons.B_MENU_SCREEN_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_OPTIONS_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_WIDTH;

public class LevelEditor extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private LevelEditorBar levelEditorBar;
    
    public LevelEditor(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(100, Game.GAME_HEIGHT-75, B_MENU_SCREEN_SPRITE, Gamestate.MENU, imageLibrary);
        buttons[1] = new MenuButton( 100 + B_WIDTH, Game.GAME_HEIGHT-75, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[2] = new MenuButton( 3 * 50 + 2*B_WIDTH, Game.GAME_HEIGHT-75, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[3] = new MenuButton( 4 * 50 + 3*B_WIDTH, Game.GAME_HEIGHT-75, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        System.out.println(buttons);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelEditorBar = new LevelEditorBar(0, Game.GAME_HEIGHT-100, Game.GAME_WIDTH, 100);
        levelEditorBar.draw(g);
        for (MenuButton mb: buttons) {
            mb.draw(g);
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
