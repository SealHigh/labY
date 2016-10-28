package trafficlight;

import road.Road;
import road.RoadDirection;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-10-25.
 */
public class TrafficLightManager implements Runnable{

    private ArrayList<Road> roads;

    public TrafficLightManager(ArrayList<Road> roads){
        this.roads = roads;
    }

    public void addRoad(Road road){
        roads.add(road);
    }

    private Road getRoadFromDirection(RoadDirection direction){
        for (Road road:roads) {
            if(road.getDirection() == direction) {
                return road;
            }
        }
        return null;
    }

    private void changeState(){
        for (Road road:roads) {
            road.getTrafficLight().nextState();
        }
    }

    private void addCycleToRoads(RoadDirection r1, RoadDirection r2){
        getRoadFromDirection(r1).getTrafficLight().addCycle();
        getRoadFromDirection(r2).getTrafficLight().addCycle();
    }

    public void handleTraffic(){
        Road tempRoad = null;
        int tempPriority = 0;
        for (Road road:roads) {
            if(tempPriority<road.getPriority()) {
                tempPriority = road.getPriority();
                tempRoad = road;
            }
        }
        if(tempRoad != null){
            if(tempRoad.getDirection() == RoadDirection.N ||tempRoad.getDirection() == RoadDirection.S) {
                if(tempRoad.getTrafficLight().getState() != LightState.Green )
                    changeState();
                else
                    addCycleToRoads(RoadDirection.E, RoadDirection.W);
            }
            else {
                if(tempRoad.getTrafficLight().getState() != LightState.Green )
                    changeState();
                else
                    addCycleToRoads(RoadDirection.N, RoadDirection.S);
            }
        }
    }

    @Override
    public void run() {
        synchronized (roads){
            while (roads.size() > 0) {
                try {
                    handleTraffic();
                    roads.notifyAll();
                    roads.wait(4000);
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
