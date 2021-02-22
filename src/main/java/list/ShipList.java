package list;

import model.Ship;

import java.util.ArrayList;

public class ShipList {
    private ArrayList<Ship> ships;

    public ShipList() {
        this.ships = new ArrayList<>();
    }

    public void add(Ship ship){
        this.ships.add(ship);
    }

    public Ship get(int i){
        return this.ships.get(i);
    }

    public Ship freeCell(int x, int y){
        for (Ship ship : this.ships) {
            if (ship.hit(x, y)) {
                return ship;
            }
        }
        return null;
    }

    public int countShips(){
        return this.ships.size();
    }

    public int destroyedShips(){
        int count = 0;
        for (int i = 0; i < this.ships.size(); i++) {
            if (this.ships.get(i).getNoOfHitsNeeded() == this.ships.get(i).getNoOfHitsMade())
                count++;
        }
        return count;
    }

}
