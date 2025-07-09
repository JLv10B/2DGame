package com.practice;



import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import com.practice.inputs.Keyboardinputs;
import com.practice.inputs.Mouseinputs;
import static com.practice.utilz.Constants.PlayerConstants.*;
import static com.practice.Game.GAME_HEIGHT;
import static com.practice.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Mouseinputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new Mouseinputs(this);
        setPanelSize();
        addKeyListener(new Keyboardinputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.renderLevel(g);
        game.renderEntities(g);
    }

    public Game getGame() {
        return game;
    }
}
