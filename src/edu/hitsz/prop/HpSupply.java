package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

public class HpSupply extends AbstractProp{

    private int value = 20;
    //增加的血量

    public HpSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(AbstractAircraft aircraft) {
        System.out.println("HpSupply Activate");
        aircraft.decreaseHp(-value);
        //加20血
    }

    public int getValue() {
        return value;
    }
}
