package road;

import trafficlight.LightState;
import trafficlight.TrafficLight;
import traffic.vehicle.Ambulance;
import traffic.vehicle.Car;
import traffic.vehicle.Vehicle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Martin on 2016-10-25.
 */
public class Road implements Runnable{
    private BlockingQueue<Vehicle> vehicles = new LinkedBlockingQueue<Vehicle>();
    private RoadDirection direction;
    private TrafficLight trafficLight ;

    public Road(RoadDirection direction, LightState state){
        this.direction = direction;
        this.trafficLight = new TrafficLight(state);
        vehicles.add(new Car());
    }

    public Road(RoadDirection direction){
        this.direction = direction;
        this.trafficLight = new TrafficLight();
        vehicles.add(new Car());
    }

    public void addCar(Vehicle vehicle) throws InterruptedException {
        vehicles.put(vehicle);
    }

    public void removeCar() throws InterruptedException {
        vehicles.take(); //Take only removes an object if it exists otherwise it kindly waits
    }

    public TrafficLight getTrafficLight(){
        return trafficLight;
    }

    public int getPriority(){
        if(vehicles.size()>0){
            int tempPriority=0;

            for (Vehicle vehicle: vehicles
                    ) {
                tempPriority += vehicle.getPriority();

            }
            return tempPriority + trafficLight.getCycleSinceGreen();
        }

        else
            return 0;
    }

    public RoadDirection getDirection() {
        return direction;
    }

    public int getNrOfVehicles(){
        return vehicles.size();
    }

    public int getNrOfAmbulances(){
        int nrOfAmbulances=0;
        for (Vehicle v: vehicles) {
            if(v instanceof Ambulance)
                nrOfAmbulances++;

        }
        return nrOfAmbulances;
    }

    public int getNrOfCars(){
        int nrOfCars=0;
        for (Vehicle v: vehicles) {
            if(v instanceof Car)
                nrOfCars++;
        }
        return nrOfCars;
    }


    @Override
    public void run() { //Acts as consumer

        while (true) {
            try {
                if (trafficLight.getState() == LightState.Green) {
                    removeCar();
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
