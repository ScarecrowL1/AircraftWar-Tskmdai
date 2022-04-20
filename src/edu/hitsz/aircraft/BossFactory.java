package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossFactory implements AircraftFactory{
    @Override
    public AbstractAircraft createAircraft() {
        Boss boss = new Boss(
                Main.WINDOW_WIDTH / 2,
                100,
                5,
                0,
                1000);
        boss.setShootStrategy(new Scatter());
        return boss;
    }

}
