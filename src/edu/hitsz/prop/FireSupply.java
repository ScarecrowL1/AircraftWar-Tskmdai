package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;
import edu.hitsz.strategy.Scatter;
import edu.hitsz.strategy.Straight;

public class FireSupply extends AbstractProp{
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {
        new MusicThread("src/videos/get_supply.wav").start();
        Runnable r = () ->{
            try {
                aircraft.setShootStrategy((new Scatter()));
                //保持散射持续4秒
                Thread.sleep(4000);
                aircraft.setShootStrategy(new Straight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r, "FireSupply").start();
    }
}
