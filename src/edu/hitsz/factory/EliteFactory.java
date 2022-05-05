package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.Straight;

/**
 * 精英工厂
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public class EliteFactory implements AircraftFactory{

    /**
     * 普通模式默认血量60
     */
    int hp = 60;

    @Override
    public AbstractAircraft createAircraft() {
        EliteEnemy eliteEnemy = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                2,
                2,
                hp);
        eliteEnemy.setShootStrategy(new Straight());
        return eliteEnemy;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
