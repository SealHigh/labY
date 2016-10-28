package trafficlight;

/**
 * Created by Martin on 2016-10-25.
 */
public class TrafficLight {

    private LightState state;
    private int cycleSinceGreen = 0;

    public TrafficLight() {
        this.state = LightState.Red;
        cycleSinceGreen++;
    }

    public TrafficLight(LightState state) {
        this.state = state;

    }


    public LightState getState() {
        return state;
    }

    public void addCycle(){
        cycleSinceGreen++;
    }

    public void nextState() {
        if(state == LightState.Red)
            state = LightState.Yellow;

        else if(state == LightState.Green)
            state = LightState.Yellow;

        else if(state == LightState.Yellow){
            if(cycleSinceGreen == 0) {
                state = LightState.Red;
                cycleSinceGreen++;
            }
            else {
                state = LightState.Green;
                cycleSinceGreen = 0;
            }
        }

    }

    public void setYellow() {
        this.state = LightState.Green;
    }

    public void setGreen() {
        this.state = LightState.Green;
        cycleSinceGreen = 0;
    }

    public void setRed() {
        this.state = LightState.Red;
        cycleSinceGreen++;
    }

    public int getCycleSinceGreen() {
        return cycleSinceGreen;
    }
}
