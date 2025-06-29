package com.practice.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.practice.GamePanel;
import com.practice.entities.*;
import static com.practice.utilz.Constants.KeyTiming;

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
        gamePanel.getGame().getPlayer().inputProcessor(e, KeyTiming.RELEASED);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.getGame().getPlayer().inputProcessor(e, KeyTiming.PRESSED);
              
    }
}
