package game;


import io.FileIO;
import io.Input;
import list.ShipList;
import model.Ship;

import java.io.IOException;
import java.util.Scanner;

import static io.Input.inputInt;

public class Game {
    private ShipList playerShips;
    private ShipList computerShips;
    private String[] settings;

    public Game() {
        this.playerShips = new ShipList();
        this.computerShips = new ShipList();
        try {
            this.settings = new FileIO("gamesettings.txt").read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addShips() {
        int sizeField = Integer.parseInt(this.settings[0]);
        for (int i = 0; i < Integer.parseInt(this.settings[3]); i++) {
            System.out.println("Please enter the details for the " + (i + 1) + " ship:");
            String name;
            Integer xPos;
            Integer yPos;
            do {
                System.out.println("Ship Name:");
                name = Input.inputString();
            } while (!Validation.validationName(name));
            while (true) {
                do {
                    System.out.println("Ship x Position (0 - " + (sizeField - 1) + "):");
                    xPos = inputInt();
                } while (!Validation.validationCoordinate(xPos, sizeField - 1));
                do {
                    System.out.println("Ship y Position (0 - " + (sizeField - 1) + "):");
                    yPos = inputInt();
                } while (!Validation.validationCoordinate(yPos, sizeField - 1));
                if (this.playerShips.freeCell(xPos, yPos) != null) {
                    System.out.println("There is already a ship, enter other coordinates!");
                    continue;
                }
                break;
            }
            CoordinateGenerator powerGenerator = new CoordinateGenerator(1, 5);
            this.playerShips.add(new Ship(name, xPos, yPos, powerGenerator.generate(Key.H)[0]));
            CoordinateGenerator coordinateGenerator = new CoordinateGenerator(0, sizeField);
            int xPosComp;
            int yPosComp;
            while (true) {
                int[] coordinates = coordinateGenerator.generate(Key.C);
                xPosComp = coordinates[0];
                yPosComp = coordinates[1];
                if (this.computerShips.freeCell(xPosComp, yPosComp) != null)
                    continue;
                break;
            }
            this.computerShips.add(new Ship("computerShip", xPosComp,
                    yPosComp, powerGenerator.generate(Key.H)[0]));
        }
        System.out.println("Loading computer settings:");
        System.out.println("Computer settings generated!");
        System.out.println("Press any key to continue...");
    }

    public String[][] fieldFill() {
        String[][] field = new String[Integer.parseInt(this.settings[0])][Integer.parseInt(this.settings[0])];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = "~";
            }
        }
        return field;
    }

    public String[][] gameField(Key key) {
        String[][] field = this.fieldFill();
        if (key == Key.H) {
            for (int i = 0; i < this.playerShips.countShips(); i++) {
                field[this.playerShips.get(i).getxPos()][this.playerShips.get(i).getyPos()] = "O";
                if (this.playerShips.get(i).getNoOfHitsMade() > 0)
                    field[this.playerShips.get(i).getxPos()][this.playerShips.get(i).getyPos()] = "D";
                if (this.playerShips.get(i).getNoOfHitsMade() == this.playerShips.get(i).getNoOfHitsNeeded())
                    field[this.playerShips.get(i).getxPos()][this.playerShips.get(i).getyPos()] = "X";
            }
        }
        if (key == Key.C) {
            for (int i = 0; i < this.computerShips.countShips(); i++) {
                if (Boolean.parseBoolean(this.settings[2])) {
                    field[this.computerShips.get(i).getxPos()][this.computerShips.get(i).getyPos()] = "O";
                }
                if (this.computerShips.get(i).getNoOfHitsMade() > 0) {
                    field[this.computerShips.get(i).getxPos()][this.computerShips.get(i).getyPos()] = "D";
                }
                if (this.computerShips.get(i).getNoOfHitsMade() == this.computerShips.get(i).getNoOfHitsNeeded())
                    field[this.computerShips.get(i).getxPos()][this.computerShips.get(i).getyPos()] = "X";
            }
        }
        return field;
    }

    public void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    public boolean playerTurn() {
        int sizeField = Integer.parseInt(this.settings[0]);
        Integer xPos;
        Integer yPos;
        do {
            System.out.println("Ship x Position (0 - " + (sizeField - 1) + "):");
            xPos = Input.inputInt();
        } while (!Validation.validationCoordinate(xPos, sizeField - 1));
        do {
            System.out.println("Ship y Position (0 - " + (sizeField - 1) + "):");
            yPos = Input.inputInt();
        } while (!Validation.validationCoordinate(yPos, sizeField - 1));
        if (this.computerShips.freeCell(xPos, yPos) != null
                && this.computerShips.freeCell(xPos, yPos).life()) {
            this.computerShips.freeCell(xPos, yPos).setCountHit();
            System.out.println();
            System.out.println("PLAYER HITTTTT!!!!");
            return true;
        }
        System.out.println();
        System.out.println("PLAYER MISSSSS!!!!");
        return false;
    }

    public boolean computerTurn(){
        int sizeField = Integer.parseInt(this.settings[0]);
        CoordinateGenerator coordinateGenerator = new CoordinateGenerator(0, sizeField);
        int[] coordinates = coordinateGenerator.generate(Key.C);
        System.out.println("Computer x guess:" + coordinates[0]);
        System.out.println("Computer y guess:" + coordinates[1]);
        if (this.playerShips.freeCell(coordinates[0], coordinates[1]) != null
                && this.playerShips.freeCell(coordinates[0], coordinates[1]).life()) {
            this.playerShips.freeCell(coordinates[0], coordinates[1]).setCountHit();
            System.out.println();
            System.out.println("COMPUTER HITTTTT!!!!");
            return true;
        }
        System.out.println();
        System.out.println("COMPUTER MISSSSS!!!!");
        return false;
    }

    public void start() {
        if (!Validation.validationSettings(this.settings))
            return;
        Scanner scanner = new Scanner(System.in);
        this.addShips();
        scanner.nextLine();
        int round = 1;
        int playerScore = 0;
        int computerScore = 0;
        while (true) {
            System.out.println("Beginning Round: " + round);
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            this.printField(this.gameField(Key.H));
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println();
            System.out.println("Displaying the Computer Grid");
            this.printField(this.gameField(Key.C));
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Player to make a guess");
            if (this.playerTurn()) {
                playerScore += 10;
            }
            System.out.println();
            System.out.println("Computer to make a guess");
            System.out.println();
            if (this.computerTurn()){
                computerScore += 10;
            }
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            round++;
            if (this.computerShips.destroyedShips() == Integer.parseInt(this.settings[3])) {
                System.out.println("Congratulations! Player Wins");
                FileIO fileIO = new FileIO("gameoutcome.txt");
                try {
                    fileIO.saveResult(Key.H, playerScore, computerScore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            if (this.playerShips.destroyedShips() == Integer.parseInt(this.settings[3])) {
                System.out.println("Congratulations! Computer Wins");
                FileIO fileIO = new FileIO("gameoutcome.txt");
                try {
                    fileIO.saveResult(Key.C, playerScore, computerScore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

    }
}
