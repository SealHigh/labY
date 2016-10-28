package traffic;

import road.Road;
import traffic.vehicle.Ambulance;
import traffic.vehicle.Car;
import traffic.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Martin on 2016-10-25.
 */
public class TrafficGenerator implements Runnable{

    private ArrayList<Road> roads;

    public TrafficGenerator(ArrayList<Road> roads){
        this.roads = roads;
    }


    @Override
    public void run() {//Acts as producer
            while (true) {
                try {
                    int rand = new Random().nextInt(100);
                    Vehicle vehicle;
                    if (rand < 98)
                        vehicle = new Car();
                    else {
                        vehicle = new Ambulance();
                    }
                    roads.get(new Random().nextInt(4)).addCar(vehicle);

                    Thread.sleep(new Random().nextInt(300) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

}
