package game;

import java.util.Map;

public class CoordinateGenerator {
    private int minimumValue;
    private int maximumValue;

    public CoordinateGenerator(int minimumValue, int maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public int[] generate(Key key) {
        if (key == Key.C) {
            int[] coordinates = new int[]{(int) (this.minimumValue + Math.random() * (this.maximumValue - this.minimumValue)),
                    (int) (this.minimumValue + Math.random() * (this.maximumValue - this.minimumValue))};
            return coordinates;
        }
        else {
            int[] power = new int[]{(int) (this.minimumValue + Math.random() * (this.maximumValue - this.minimumValue))};
            return power;
        }
    }
}
