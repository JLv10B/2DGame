package com.practice.utilz;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoadSave {
    public static void CreateFile() {
        File txtFile = new File("game\\src\\main\\resources\\Maps\\testTextFile.txt");
        
        try {
            txtFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateMaps (String name, int[]idArr) {
        File newMapFile = new File("game\\src\\main\\resources\\Maps\\" + name + ".txt");

    }
    
    public static void WriteToFile() {
        File txtFile = new File("game\\src\\main\\resources\\Maps\\testTextFile.txt");

        try {
            PrintWriter printWriter = new PrintWriter(txtFile);
            printWriter.println("hello from the other side");
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ReadFromFile() {
        File txtFile = new File("game\\src\\main\\resources\\Maps\\testTextFile.txt");
        try {
            Scanner sc = new Scanner(txtFile);

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
