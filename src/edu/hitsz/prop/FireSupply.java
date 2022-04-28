package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.Scatter;
import edu.hitsz.strategy.Straight;

public class FireSupply extends AbstractProp{
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {

        Runnable r = () ->{
            try {
                aircraft.setShootStrategy((new Scatter()));
                //保持散射持续10秒
                Thread.sleep(8000);
                aircraft.setShootStrategy(new Straight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r, "FireSupply").start();
    }
}
