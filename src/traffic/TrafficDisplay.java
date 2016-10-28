package traffic;

import road.Road;
import trafficlight.TrafficLightManager;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-10-27.
 */
public class TrafficDisplay implements Runnable {

    private ArrayList<Road> roads = new ArrayList<>();
    public TrafficDisplay(ArrayList<Road> roads){
        this.roads = roads;
    }

    @Override
    public void run() {

        synchronized (roads){
            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                for (Road r : roads) {
                    System.out.println("road.Road: " + r.getDirection());
                    System.out.println("Cars: " + r.getNrOfCars());
                    System.out.println("Ambulances: " + r.getNrOfAmbulances());
                    System.out.println("Trafficllight: " + r.getTrafficLight().getState());
                    System.out.println("---------------------------");

                }
                try {
                    roads.notifyAll();
                    roads.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
