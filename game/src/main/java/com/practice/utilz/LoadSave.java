package com.practice.utilz;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.practice.Game;

public class LoadSave {
    public static void CreateFile() {
        File txtFile = new File("game\\src\\main\\resources\\Maps\\testTextFile.txt");
        
        try {
            txtFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateNewMap (String name, int[]idArr) {
        File newMapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".txt");

        if (newMapFile.exists()) {
            System.out.println("File: " + name + " already exists");
            return;
        } else {
            try {
                newMapFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            WriteToFile(newMapFile, idArr);
        }
    }
    
    public static void WriteToFile(File f, int[] idArr) {
        try {
            PrintWriter writer = new PrintWriter(f);
            writer.println("hello from the other side");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
                list.add(Integer.parseInt(sc.nextLine()));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int[][] GetLevelData(String name) {
        File mapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".txt");

        if (mapFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(mapFile);
            return HelperMethods.ArrayListTo2Dint(list, Game.TILES_IN_HEIGHT, Game.TILES_IN_WIDTH);
        } else {
            System.out.println("File: " + name + " cannot be found");
            return null;
        }
    }

}
