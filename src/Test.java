import road.Road;
import road.RoadDirection;
import traffic.TrafficDisplay;
import traffic.TrafficGenerator;
import traffic.TrafficRemover;
import trafficlight.LightState;
import trafficlight.TrafficLightManager;
import java.util.ArrayList;

/**
 * Created by Martin on 2016-10-25.
 */
public class Test {

    private ArrayList<Road> roads = new ArrayList<>();
    public void testRun() {


        roads.add(new Road(RoadDirection.N, LightState.Green));
        roads.add(new Road(RoadDirection.S,LightState.Green));
        roads.add(new Road(RoadDirection.E));
        roads.add(new Road(RoadDirection.W));


        Thread trafficLights = new Thread(new TrafficLightManager(roads));
        Thread generateTraffic = new Thread(new TrafficGenerator(roads));
        Thread trafficRemover= new Thread(new TrafficRemover(roads));
        Thread displayTraffic = new Thread(new TrafficDisplay(roads));

        trafficRemover.start();
        generateTraffic.start();
        trafficLights.start();
        displayTraffic.start();
    }
}
