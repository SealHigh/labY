package traffic.vehicle;

/**
 * Created by Martin on 2016-10-26.
 */
public class Car extends Vehicle {

    private final int priority = 1;

    public Car(){
        super();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
