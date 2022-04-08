package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

public class FireSupply extends AbstractProp{
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(AbstractAircraft aircraft) {
        System.out.println("FireSupply Activate");
    }
}
