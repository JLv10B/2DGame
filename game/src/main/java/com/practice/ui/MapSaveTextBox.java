package com.practice.ui;

import java.awt.event.ActionEvent;

import com.practice.objects.GameMap;
import com.practice.utilz.LoadSave;

public class MapSaveTextBox extends InputTextBox {
    private GameMap gameMap;

    public MapSaveTextBox() {
    }

    //TODO: Need to handle if savemapfile fails
    @Override
    public void actionPerformed (ActionEvent event){
        text = textField.getText();
        System.out.println(text);
        textField.setText("");
        textField.setVisible(false);
        try {
            LoadSave.SaveMapFile(text, gameMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = "";
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
    
    public GameMap getGameMap() {
        return gameMap;
    }
}
