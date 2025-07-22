package com.practice.utilz;

import static com.practice.Game.TILES_IN_HEIGHT;
import static com.practice.Game.TILES_IN_WIDTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.Game;
import com.practice.handlers.TileHandler;
import com.practice.objects.GameMap;
import com.practice.objects.Tile;

public class LoadSave {
    
    public static void CreateNewMap (GameMap map) {
        File newMapFile = new File("game\\src\\main\\resources\\Maps\\" + map.getMapName() + ".json");

        if (newMapFile.exists()) {
            System.out.println("File: " + map.getMapName() + " already exists");
            return;
        } else {
            try {
                newMapFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        WriteToFile(newMapFile, map);
    }
    
    public static void WriteToFile(File f, GameMap map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(f, map);
            System.out.println("File written");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GameMap SaveMapFile(String newName, GameMap map) {
        File oldFile = new File("game\\src\\main\\resources\\Maps\\" + map.getMapName() + ".json");
        File newFile = new File("game\\src\\main\\resources\\Maps\\" + newName + ".json");

        try {
            oldFile.renameTo(newFile);
            map.setMapName(newName);
            WriteToFile(newFile, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
        
    }


    public static GameMap GetMapData(String name) { 
        try {
            File mapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".json");
            ObjectMapper mapper = new ObjectMapper();
            GameMap map = mapper.readValue(mapFile, GameMap.class);
            return map;
        } catch (Exception e) {
            System.out.println("File: " + name + " cannot be found");
            e.printStackTrace();
            return new GameMap(name, null); //TODO: this should not be returning a new GameMap
        }
    }

}
