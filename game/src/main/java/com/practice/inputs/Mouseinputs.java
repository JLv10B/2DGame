package com.practice.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.practice.GamePanel;
import com.practice.gamestates.Gamestate;

public class Mouseinputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public Mouseinputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mouseMoved(e);
            default:
                break;
        }  
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mousePressed(e);
                break;
            default:
                break;
        }           
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                // gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mouseReleased(e);
            default:
                break;
        }   
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    
}
