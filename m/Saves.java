package m;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import C.User;
import C_ITEMS.*;
import Screens.FightScreen;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Saves {
    public static String line = "";
    public static Scanner sc;
    public static void readSave(String filePath) throws FileNotFoundException
    {
        sc = new Scanner(new File(filePath));
        read();
        switch (line) {
            case "0":
                User.getCharPanel().currentFolderOrder = 0;
                break;
            case "1":
                User.getCharPanel().currentFolderOrder = 1;
                break;
            case "2":
                User.getCharPanel().currentFolderOrder = 2;
                break;
            case "3":
                User.getCharPanel().currentFolderOrder = 3;
                break;
            case "4":
                User.getCharPanel().currentFolderOrder = 4;
                break;
        }
        read();
        FightScreen.wonAgainstEnemies = Integer.parseInt(line);
        if(!read().equals("x")) User.getUser().buyItem(new Armor(Integer.parseInt(line)));
        read();
        switch (line) {
            case "0":
                break;
            case "1":
                User.getUser().buyItem(new Cat());
                break;
        }
        if(!read().equals("x")) User.getUser().buyItem(new Helmet(Integer.parseInt(line)));
        if(!read().equals("x")) User.getUser().buyItem(new Leggings(Integer.parseInt(line)));
        if(!read().equals("x")) User.getUser().buyItem(new Shield(Integer.parseInt(line)));
        if(!read().equals("x")) User.getUser().buyItem(new Sword(Integer.parseInt(line)));
        if(!read().equals("x")) User.getUser().setCoins(Integer.parseInt(line));
        sc.close();
    }
    private static String read()
    {
        line = sc.nextLine();
        line = line.substring(0, 1);
        return line;
    }
    public static void saveGame(String path)
    {
        try (FileWriter writer = new FileWriter(path)) { // true = append mode
            writer.write
            (User.getCharPanel().currentFolderOrder + " currentFolderOrder" + System.lineSeparator()
            + FightScreen.wonAgainstEnemies + " wonAgainstEnemies" + System.lineSeparator()
            + (User.getUser().hasArmor() ? (User.getUser().getArmor().getLevel()) : "x") + " Armor" + System.lineSeparator()
            + (User.getUser().hasCat() ? ("1") : ("0")) + " Cat (0/1)" + System.lineSeparator()
            + (User.getUser().hasHelmet() ? (User.getUser().getHelmet().getLevel()) : "x") + " Helmet" + System.lineSeparator()
            + (User.getUser().hasLeggings() ? (User.getUser().getLeggings().getLevel()) : "x") + " Leggings" + System.lineSeparator()
            + (User.getUser().hasShield() ? (User.getUser().getShield().getLevel()) : "x") + " Shield" + System.lineSeparator()
            + (User.getUser().hasSword() ? (User.getUser().getSword().getLevel()) : "x") + " Sword" + System.lineSeparator()
            + FightScreen.wonAgainstEnemies + " coins"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAllSaves() {
        ArrayList<String> allSaves = new ArrayList<>();
        File folder = new File("games/");
        File[] files = folder.listFiles((_, name) -> name.toLowerCase().endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                allSaves.add("games/" + (file.getName()));//.substring(0, file.getName().length() - 4)));
            }
        }
        return allSaves;
    }

}
