package edu.hitsz.prop;

import edu.hitsz.UI.MainPanel;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

public class BombSupply extends AbstractProp{
    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {
        new MusicThread("src/videos/bomb_explosion.wav").start();
        System.out.println("BombSupply Activate");
    }
}
