# BattleShip
 
The game will allow the player to place a number of ships onto the playing grid based on x and y 
coordinates entered by the user. The computer opponent will also do the same randomly. Each ship 
will only occupy a single coordinate, as such, two ships cannot be placed at the same set of 
coordinates. To keep things interesting, each ship when places is given a random number between 1 –
5 which indicates its overall strength. This means that the same ship has to be hit that many times in 
order for it to be destroyed. Once a ship has been destroyed it is taken out of play on the grid. Each hit 
on a ship get points for the player or the computer. The game ends when all the ships are destroyed.

## Game play
The following section outlines the game play for each of the involved participants.

![1](https://user-images.githubusercontent.com/79397536/121020898-79b15f80-c7a9-11eb-880e-01f9c345db14.PNG)

### The Playing Grid
The playing grid will be of a fixed size for the duration of the game. On starting the game, the game 
will load the following preferences from a file called “gamesettings.txt”.
1) Grid Width and Height: 5
2) Multiple Hits Allowed: true
3) Computer Ships Visible: false
4) No of Ships : 3

The file will represent this default data as follows: 5,true,false,3

Your program must work based on the parameters specified in this file. (Note: default values not 
used)

![2](https://user-images.githubusercontent.com/79397536/121020900-7a49f600-c7a9-11eb-8592-70b604964bdb.PNG)

#### Manoeuvring the Grid:

To move the various elements along the grid, the grid is to be represented as coordinates. Each 
position on the grid is given a coordinate in the format x,y.

##### No two ships can occupy the SAME coordinate at any given time. 
This is only applicable to each player. For e.g. the player can place a ship at 2,3 and the computer can 
place its ship at 2,3. But both the player and computer cannot place another ship at the same 
coordinate.
### The Grid
The grid will use the following notation:

~ – to indicate water and an unoccupied grid. The computer ships until hit will also display this 
symbol.

O – to indicate a player ship to the player if one is placed at those coordiantes.

D – a ship which has been hit at least once to indicate a damaged ship.

X – a destroyed ship.

The computer’s ships MUST be made visible or be hidden from the player based on the flag set in the 
settings file above. When the computer ships are hidden, the D and X status of each ship MUST be 
shown on screen when hit or destroyed. However undamaged ships are shown as ~ symbol. 

### Game Setup:
After loading the game settings from the file, the system will then ask the user to enter the following 
information for each ship:
1. Ship Name – String between 3 and 15 characters long.
2. x Coordinate – X coordinate position between 0 and the maximum grid size from the settings
file.
3. y Coordinate – y coordinate position between 0 and the maximum grid size from the settings
file
4. If any coordinate is incorrect (e.g. alphabetic or outside range of grid) an error message
should be displayed.

![3](https://user-images.githubusercontent.com/79397536/121020903-7ae28c80-c7a9-11eb-9f84-c6104233b00a.PNG)

The number of ships set is determined from the game settings file.

After the player ships are set, the computer ships are automatically generated.

### Turn Based Game Play:
Note: For demo, default grid size is not used, and enemy ships are shown on the screen.

Turn 1: The player is asked to make a guess, the system will then check if the player hits a computer 
ships or misses completely. The system will then display the appropriate message to the user.

![4](https://user-images.githubusercontent.com/79397536/121020904-7ae28c80-c7a9-11eb-936a-a35d418b346a.PNG)

After the players guess, the computer is asked to make a guess, and the system checks to see if the 
computer makes a hit or misses the player ships. 

Turn 2:

The player is again asked to continue making a guess at the coordinates. Appropriate validations must 
be done as before for the entered coordinates.

![5](https://user-images.githubusercontent.com/79397536/121020907-7ae28c80-c7a9-11eb-94c0-16a34dd79b18.PNG)

After the player guess, the computer is asked to make a guess and the system validates the guess to 
check for a hit or a miss.

Turn 3:
The player makes a hit.

![6](https://user-images.githubusercontent.com/79397536/121020909-7b7b2300-c7a9-11eb-8874-d11eceb742d5.PNG)

The system displays the appropriate message on the screen and at the start of the next turn, the grid layout is changed as follows:

![7](https://user-images.githubusercontent.com/79397536/121020911-7b7b2300-c7a9-11eb-957e-8033ea454881.PNG)

Turn x:

The player finally destroys an enemy ship.

![8](https://user-images.githubusercontent.com/79397536/121020913-7c13b980-c7a9-11eb-84d6-fbcb18352acb.PNG)

Two Ships Destroyed:

![11](https://user-images.githubusercontent.com/79397536/121023062-977fc400-c7ab-11eb-8a3f-3c9c7e31b123.PNG)

All Ships Destoryed:

![9](https://user-images.githubusercontent.com/79397536/121020916-7c13b980-c7a9-11eb-8656-cf3d7de4d3c1.PNG)

On completing the game, the system will write the outcome to a file called “gameoutcome.txt”.
The outcome file will have the following output:
Player wins. Final Score Player (110) and Computer (0)

## Game Rules
The following game rules MUST be adhered to when playing the game:

• No two ships can occupy the same X coordinate and Y coordinate for each player

• Guesses must be within the specified range

• Each hit gets a score of 10 points

• Each player gets one guess per turn

• The hull strength of each ship is determined randomly between 1 – 5. This indicates the number of hits required for the ship to be destroyed.

## Program design
The objective of this assignment is for students to understand coding, as well as to get the basics of 
design. The following class diagram is proposed for this assignment.

![10](https://user-images.githubusercontent.com/79397536/121020895-79b15f80-c7a9-11eb-98cb-cc20ad07f89f.PNG)

Your program MUST consist of the following classes:
1. Game
2. ShipList
3. CoordinateGenerator
4. Ship
The following classes are desirable and will score marks if implemented. But if object interaction is 
still challenging, these classes can be integrated into the other 4 classes mentioned above.
5. FileIO
6. Input
7. Validation

You MUST follow good programming practices and use loops where required to ensure good 
program design.

Design changes to the above must be discussed with your tutor prior to proceeding. 
### Game class
The Game class will specify the attributes and behaviours of the game. An object of the Game class 
will have the following fields only:

• playerShips – (ShipList) an object of the ShipList class.

• computerShips – (ShipList) an object of the ShipList class.

This class is responsible for initiating the game, reading the file, loading the settings, interacting with 
the other classes, and writing to the file when the game ends. Reading and Writing to the file must 
only occur ONCE.

### ShipList class
The ShipList class will specify the attributes and behaviours of all the ships within the game. An 
object of the ShipList class will have the following fields only:

• ships – (ArrayList<Ship>) – stores objects of the class Ship within an ArrayList collection.

This class is responsible for creating an arraylist which stores each ship within the player grid for the 
respective player or computer.

### CoordinateGenerator Class
The CoordinateGenerator class will specify the attributes and behaviours of generating random 
coordinates which will be used by the Game class. An object of the CoordinateGenerator class will 
have the following fields only:

• minimumValue – (int) stores the lower limit of the number to be generated.

• maximumValue – (int) stores the upper limit of the number to be generated.

This class is responsible for generating a random number which can be used for deciding the X and Y 
coordinates for each ship and even for deciding the random hull strength of each ship within the 
game.

###Ship Class

The Ship class will specify the attributes and behaviours of all ships within the game. An object of the Ship class will have the following fields only:

• shipName – (String) stores the name of the ship between 3 – 15 characters.

• xPos – (int) stores the x position of the ship on the grid.

• yPos – (int) stores the y position of the ship on the grid. 

• noOfHitsMade – (int) stores the number of times the ship has been hit.

• noOfHitsNeeded – (int) stores the number of times the ship needs to be hit to be destroyed. This number if randomly generated by the game for each ship for both the player and the computer. This class is responsible for recording a new ship added to the grid.

#### Additional Classes:
### FileIO Class
The FileIO class will specify the attributes and behaviours for reading and writing to a file. An object 
of the FileIO class will have the following fields only:

• filename – (String) the name of the file to be read or written to.

This class is responsible for reading and writing to a file only. The data read should be passed to the calling class to handle. 
### Input Class
The Input class will specify the attributes and behaviours for reading input from the user via the keyboard. An object of the Input class will have no fields. However, all methods included can be made class methods.

### Validation Class
The Validation class will allow the system to validate all user inputs accepted via the keyboard from the user. An object of the Validation class will have no fields. 
