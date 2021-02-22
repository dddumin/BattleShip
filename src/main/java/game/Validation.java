package game;

import io.FileIO;

import java.io.IOException;

public class Validation {
    public static boolean validationName(String name){
        if (name.length() < 3 || name.length() > 15){
            System.out.println("The name must be 3 to 15 characters long");
            return false;
        }
            return true;
    }

    public static boolean validationCoordinate(Integer coordinate, int size) {
        if (coordinate == null){
            System.out.println("Ship Position Must Be Numeric");
            return false;
        }
        else if (coordinate > size || coordinate < 0) {
            System.out.println("Ship position Must Be between 0 and " + (size));
            return false;
        }
        return true;
    }

    public static boolean validationSettings(String[] settings){
        if (settings == null){
            return false;
        }
        if (settings.length < 4){
            System.out.println("There should be 4 parameters in the settings");
            return false;
        }
        int count = 0;
        try {
            int fieldSize = Integer.parseInt(settings[0]);
            if (fieldSize <= 0){
                System.out.println("Please Enter a positive number in the field settings");
                count++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please Enter a positive number in the field settings");
            count++;
        }
        if (!settings[1].equals("true") && !settings[1].equals("false")){
            System.out.println("Second value in the settings must be boolean");
            count++;
        }
        if (!settings[2].equals("true") && !settings[2].equals("false")){
            System.out.println("Third value in the settings must be boolean");
            count++;
        }
        try {
            int countShips = Integer.parseInt(settings[3]);
            if (countShips <= 0){
                System.out.println("Please Enter a positive number in the count ships settings");
                count++;
            }
        } catch (NumberFormatException e){
            System.out.println("Please Enter a positive number in the count ships settings");
            count++;
        }
        if (count == 0)
            return true;
        return false;
    }
}
