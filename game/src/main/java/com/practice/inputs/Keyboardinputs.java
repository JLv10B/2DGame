package com.practice.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.practice.GamePanel;
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
        gamePanel.getGame().getPlayer().inputProcessor(e, UserInput.KEY_RELEASE);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.getGame().getPlayer().inputProcessor(e, UserInput.KEY_PRESS);
              
    }
}
