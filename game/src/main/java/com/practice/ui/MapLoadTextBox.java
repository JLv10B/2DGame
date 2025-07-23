package com.practice.ui;

import java.awt.event.ActionEvent;

import com.practice.Game;
import com.practice.gamestates.Gamestate;
import com.practice.gamestates.MapEditor;
import com.practice.gamestates.Playing;
import com.practice.utilz.LoadSave;

public class MapLoadTextBox extends InputTextBox {

    public MapLoadTextBox() {
    }

    @Override
    public void actionPerformed (ActionEvent event){
        text = textField.getText();
        System.out.println(text);
        textField.setText("");
        textField.setVisible(false);
        Game.gamePanel.requestFocusInWindow();
        if(Gamestate.state == Gamestate.MAPEDITOR) {
            try {
                MapEditor.loadMap(LoadSave.GetMapData(text));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Gamestate.state == Gamestate.PLAYING) {
            try {
                Playing.loadMap(LoadSave.GetMapData(text));
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }
        text = "";
    }
}
