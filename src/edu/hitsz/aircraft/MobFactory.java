package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobFactory implements AircraftFactory{
    @Override
    public AbstractAircraft createAircraft() {
        return new MobEnemy(
                //普通机属性，原为0，10，30
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                5,
                30);
        //0415修改了敌机速度，原为8  现为5
    }
}
