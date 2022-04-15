package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteFactory implements AircraftFactory{
    @Override
    public AbstractAircraft createAircraft() {
        EliteEnemy eliteEnemy = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                2,
                2,
                60);
        eliteEnemy.setShootStrategy(new Straight());
        return eliteEnemy;
    }
}
