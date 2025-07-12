package com.practice.gamestates;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.practice.Game;
import com.practice.ui.MenuButton;
import com.practice.utilz.ImageLibrary;
import static com.practice.utilz.Constants.UI.Buttons.*;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];
    private int menuX, menuY, menuWidth, menuHeight;



    public Menu(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        loadButtons();
    }

    private void loadButtons() {
        switch (Gamestate.state) {
            case MENU:
                buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (150*Game.SCALE), B_PLAY_SPRITE, Gamestate.PLAYING, imageLibrary);
                buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (220*Game.SCALE), B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
                buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (290*Game.SCALE), B_QUIT_SPRITE, Gamestate.QUIT, imageLibrary);
                break;
            case OPTIONS:
                buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (150*Game.SCALE), B_LEVEL_EDITOR_SPRITE, Gamestate.OPTIONS, imageLibrary);
                buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (220*Game.SCALE), B_KEYBINDS_SPRITE, Gamestate.OPTIONS, imageLibrary);
                buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (290*Game.SCALE), B_MENU_SCREEN_SPRITE, Gamestate.MENU, imageLibrary);
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage backgroundImg = imageLibrary.getUILibrary().get("Menu").get(MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH/2 - menuWidth/2;
        menuY = (int) (45* Game.SCALE);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        for (MenuButton mb : buttons) {
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
                    if(Gamestate.state == Gamestate.OPTIONS || Gamestate.state == Gamestate.MENU) {
                        loadButtons();
                    }
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
