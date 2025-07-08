package com.practice.ui;

import java.awt.Color;
import java.awt.Graphics;

public class LevelEditorBar {
    private int x, y, width, height;
    
    public LevelEditorBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
    }
}
