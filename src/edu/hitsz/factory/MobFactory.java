package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 普通敌机工厂
 *
 * @author ScarecrowLi
 * @date 2022/05/04
 */
public class MobFactory implements AircraftFactory{

    /**
     * 普通模式默认血量30
     */
    int hp = 30;

    @Override
    public AbstractAircraft createAircraft() {
        return new MobEnemy(
                //普通机属性，原为0，10，30
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                5,
                hp);
        //0415修改了敌机速度，原为8  现为5
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
