package com.practice.utilz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.Game;
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

    // public static ArrayList<Integer> ReadFromFile(File file) {
    //     ArrayList<Integer> list = new ArrayList<>();

    //     try {
    //         Scanner sc = new Scanner(file);

    //         while (sc.hasNextLine()) {
    //             System.out.println(sc.nextLine());
    //             list.add(Integer.parseInt(sc.nextLine()));
    //         }
            
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return list;
    // }

    public static GameMap GetMapData(String name) {
        try {
            File mapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".json");
            System.out.println(mapFile);
            System.out.println(mapFile.getClass());
            ObjectMapper mapper = new ObjectMapper();
            GameMap map = mapper.readValue(mapFile, GameMap.class);
            System.out.println("map: " + map);
            return map;
        } catch (Exception e) {
            System.out.println("File: " + name + " cannot be found");
            e.printStackTrace();
            return null;
        }
    }
    // public static GameMap GetMapData(String name) {
    //     String resourcePath = "game\\src\\main\\resources\\Maps\\" + name + ".json";
    //     try {
    //         File mapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".json");
    //         ObjectMapper mapper = new ObjectMapper();
    //         GameMap map = mapper.readValue(mapFile, GameMap.class);
    //         return map;
    //     } catch (Exception e) {
    //         System.out.println("File: " + name + " cannot be found");
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

}
