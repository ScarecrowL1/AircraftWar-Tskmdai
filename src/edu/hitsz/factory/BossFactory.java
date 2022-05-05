package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.Boss;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.Scatter;

/**
 * Boss工厂
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public class BossFactory implements AircraftFactory{
    /**
     * Boss血量，初始值800
     */
    int hp = 800;
    @Override
    public AbstractAircraft createAircraft() {
        Boss boss = new Boss(
                Main.WINDOW_WIDTH / 2,
                100,
                5,
                0,
                hp);
        boss.setShootStrategy(new Scatter());
        return boss;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
