package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

public class HpSupply extends AbstractProp{

    private final int value = 50;
    //增加的血量

    public HpSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {
        new MusicThread("src/videos/get_supply.wav").start();
        System.out.println("HpSupply Activate");
        aircraft.decreaseHp(-value);
        //加20血
    }

    public int getValue() {
        return value;
    }
}
