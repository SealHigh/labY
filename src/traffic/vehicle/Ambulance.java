package traffic.vehicle;

/**
 * Created by Martin on 2016-10-26.
 */
public class Ambulance extends Vehicle {

    private final int priority = 1000;

    public Ambulance(){
        super();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
