package io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public static String inputString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static Integer inputInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            int a = scanner.nextInt();
            return a;
        } catch (InputMismatchException e) {
            return null;
        }
    }
}
