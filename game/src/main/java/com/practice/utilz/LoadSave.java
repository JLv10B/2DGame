package com.practice.utilz;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.objects.GameMap;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveMapFile(String newName, GameMap map) {
        File oldFile = new File("game\\src\\main\\resources\\Maps\\" + map.getMapName() + ".json");
        File newFile = new File("game\\src\\main\\resources\\Maps\\" + newName + ".json");

        try {
            oldFile.renameTo(newFile);
            map.setMapName(newName);
            WriteToFile(newFile, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
