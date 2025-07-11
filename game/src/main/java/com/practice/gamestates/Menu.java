package com.practice.gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.practice.Game;
import com.practice.ui.MenuButton;
import com.practice.utilz.ImageLibrary;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];
    private static int PLAY_BUTTON = 0;
    private static int OPTIONS_BUTTON = 1;
    private static int QUIT_BUTTON = 2;



    public Menu(Game game, ImageLibrary imageLibrary) {
        super(game, imageLibrary);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (150*Game.SCALE), PLAY_BUTTON, Gamestate.PLAYING, imageLibrary);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (220*Game.SCALE), OPTIONS_BUTTON, Gamestate.OPTIONS, imageLibrary);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (290*Game.SCALE), QUIT_BUTTON, Gamestate.QUIT, imageLibrary);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
        game.renderUI(g);
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

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
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
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
