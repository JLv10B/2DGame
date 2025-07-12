package com.practice.gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.practice.Game;
import com.practice.ui.MenuButton;
import com.practice.ui.OptionButton;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.Constants.*;
import static com.practice.utilz.Constants.UI.Buttons.*;

public class Options extends State implements Statemethods {

    private OptionButton[] buttons = new OptionButton[3];
    private int menuX, menuY, menuWidth, menuHeight;


    public Options(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new OptionButton(Game.GAME_WIDTH/2, (int) (150*Game.SCALE), LEVEL_EDITOR, Gamestate.OPTIONS, imageLibrary);
        buttons[1] = new OptionButton(Game.GAME_WIDTH/2, (int) (220*Game.SCALE), KEYBINDS, Gamestate.OPTIONS, imageLibrary);
        buttons[2] = new OptionButton(Game.GAME_WIDTH/2, (int) (290*Game.SCALE), MENU_SCREEN, Gamestate.MENU, imageLibrary);
    }

    @Override
    public void update() {
        for (OptionButton mb : buttons) {
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
        for (OptionButton mb : buttons) {
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (OptionButton mb : buttons) {
            if(isOverButtonOption(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (OptionButton mb : buttons) {
            if(isOverButtonOption(e, mb)) {
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
        for (OptionButton mb : buttons) {
            mb.setMouseOver(false);
        }
        
        for (OptionButton mb : buttons) {
            if(isOverButtonOption(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: modify keyEvent.VK_Enter, was strictly used for testing
        // if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        //     Gamestate.state = Gamestate.PLAYING;
        // }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    private void resetButtons() {
        for (OptionButton mb : buttons) {
            mb.resetBools();
        }
    }
}
