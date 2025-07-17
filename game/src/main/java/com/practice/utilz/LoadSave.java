package com.practice.utilz;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.Game;
import com.practice.objects.Map;

public class LoadSave {
    public static void CreateFile() {
        File txtFile = new File("game\\src\\main\\resources\\Maps\\testTextFile.txt");
        
        try {
            txtFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateNewMap (Map map) {
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
    
    public static void WriteToFile(File f, Map map) {
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

    public static Map GetMapData(String name) {
        try {
            File mapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".json");
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(mapFile, Map.class);
            return map;
        } catch (Exception e) {
            System.out.println("File: " + name + " cannot be found");
            e.printStackTrace();
            return null;
        }
    }

}
