package com.practice.gamestates;

import java.awt.event.MouseEvent;

import com.practice.Game;
import com.practice.ui.DefaultButton;
import com.practice.utilz.ImageLibrary;

public class State {
    protected Game game;
    protected ImageLibrary imageLibrary;

    public State(Game game, ImageLibrary imageLibrary) {
        this.game = game;
        this.imageLibrary = imageLibrary;
    }

    public boolean isOverButton(MouseEvent e, DefaultButton mb) {
        return mb.getButtonBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }
}
