package com.practice.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.practice.GamePanel;
import static com.practice.utilz.Constants.Directions.*;

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
                
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.removeDirection(UP);
                break;
            case KeyEvent.VK_A:;
                gamePanel.removeDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.removeDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.removeDirection(RIGHT);
                break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.addDirection(UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.addDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.addDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.addDirection(RIGHT);
                break;
        }        
    }
}
