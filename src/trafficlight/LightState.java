package trafficlight;

/**
 * Created by Martin on 2016-10-25.
 */
public enum LightState {
    Red(0),Yellow(1),Green(2);

    public int state;

    LightState(int i) {
        this.state= i;
    }

    public int getState(){
        return state;
    }
}
