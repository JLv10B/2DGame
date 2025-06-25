package com.practice.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.practice.GamePanel;
import com.practice.entities.*;
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
                gamePanel.getGame().getPlayer().removeDirection(UP);
                break;
            case KeyEvent.VK_A:;
                gamePanel.getGame().getPlayer().removeDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().removeDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().removeDirection(RIGHT);
                break;
            // case KeyEvent.VK_SPACE:
            //     gamePanel.getGame().getPlayer().removeDirection(JUMP);
            //     break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().addDirection(UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().addDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().addDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().addDirection(RIGHT);
                break;
            // case KeyEvent.VK_SPACE:
            //     gamePanel.getGame().getPlayer().addDirection(JUMP);
            //     break;
        }        
    }
}
