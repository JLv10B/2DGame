package com.practice.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
    private int x, y, width, height;
    private String label;

    private Rectangle bounds; // Checks if mouse is within bounds

    public MyButton(String label, int x, int y, int width, int height) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        // Body
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        // Border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        // Text
        drawText(g);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(label);
        int h = g.getFontMetrics().getHeight();
        g.drawString(label, x-w/2 + width/2, y+h/2 + height/2);

    }

    public Rectangle getBounds() {
        return bounds;
    }
}
