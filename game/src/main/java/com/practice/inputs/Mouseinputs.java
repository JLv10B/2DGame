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
        switch (Gamestate.state) {
            // case MENU:
            // case OPTIONS:
            //     gamePanel.getGame().getMenu().mouseDragged(e);
            //     break;
            case MAPEDITOR:
                gamePanel.getGame().getMapEditor().mouseDragged(e);
                break;
            // case PLAYING:
            //     gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
            //     break;
            default:
                break;
        }    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
            case OPTIONS:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case MAPEDITOR:
                gamePanel.getGame().getMapEditor().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
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
            case OPTIONS:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case MAPEDITOR:
                gamePanel.getGame().getMapEditor().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
            default:
                break;
        }           
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
            case OPTIONS:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case MAPEDITOR:
                gamePanel.getGame().getMapEditor().mouseReleased(e);
                break;
            case PLAYING:
                // gamePanel.getGame().getPlaying().getPlayer().mouseInputProcessor(e);
                break;
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
