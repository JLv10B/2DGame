package com.practice;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameBlah extends JPanel{
    private BufferedImage img;

    public GameBlah(String path) {
        BufferedImage tmpimg = null;
        InputStream is = getClass().getResourceAsStream(path);
        try {
            tmpimg = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.img = tmpimg;
    }

    public BufferedImage getImg() {
        return this.img;
    }
}
