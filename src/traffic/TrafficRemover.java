package traffic;

import road.Road;
import trafficlight.LightState;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-10-28.
 */
public class TrafficRemover implements Runnable {

    private ArrayList<Road> roads;

    public TrafficRemover(ArrayList<Road> roads){
        this.roads = roads;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Road road: roads) {
                    if (road.getTrafficLight().getState() == LightState.Green) {
                        road.removeCar();
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
