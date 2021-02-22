package model;

public class Ship {
    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;

    public Ship(String shipName, int xPos, int yPos, int noOfHitsNeeded) {
        this.shipName = shipName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.noOfHitsNeeded = noOfHitsNeeded;
    }

    public boolean hit(int x, int y){
        if (this.xPos == x && this.yPos == y)
            return true;
        return false;
    }

    public int getNoOfHitsMade() {
        return noOfHitsMade;
    }

    public int getNoOfHitsNeeded() {
        return noOfHitsNeeded;
    }

    public void setCountHit(){
        this.noOfHitsMade += 1;
    }


    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean life(){
        return this.getNoOfHitsMade() < this.getNoOfHitsNeeded();
    }
}
