package road;

/**
 * Created by Martin on 2016-10-25.
 */
public enum RoadDirection {
     N(0),S(1),E(2),W(3);

    private int direction;
    RoadDirection(int i) {
        this.direction= i;
    }

    public int getDirection(){
        return direction;
    }


}
