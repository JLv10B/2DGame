package com.practice.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.practice.GamePanel;
import com.practice.gamestates.Gamestate;

import static com.practice.utilz.Constants.UserInput;

public class Keyboardinputs implements KeyListener {
    

    private GamePanel gamePanel;

    public Keyboardinputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().keyboardInputProcessor(e, UserInput.KEY_RELEASE);
                break;
            case LEVELEDITOR:
                gamePanel.getGame().getLevelEditor().keyReleased(e);
                break;
            default:
                break;
        }        
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().keyboardInputProcessor(e, UserInput.KEY_PRESS);
                break;
            default:
                break;
        }
              
    }
}
