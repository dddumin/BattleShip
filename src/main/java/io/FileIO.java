package io;

import game.Key;

import java.io.*;

public class FileIO {
    private String fileName;

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    public String[] read() throws IOException {
        String[] settings;
        try(BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            settings = reader.readLine().split(" ");
            return settings;
        } catch (NullPointerException e){
            System.out.println("There should be 4 parameters in the settings");
        }
        return null;
    }

    public void saveResult(Key key, int pointPlayer, int pointComputer) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("gameoutcome.txt"))){
            if (key == Key.H){
                writer.write("Player wins. Fina Score Player (" + pointPlayer + ") and Computer (" + pointComputer + ")");
            }
            else {
                writer.write("Computer wins. Fina Score Computer (" + pointComputer + ") and Player (" + pointPlayer + ")");
            }
        }
    }
}
