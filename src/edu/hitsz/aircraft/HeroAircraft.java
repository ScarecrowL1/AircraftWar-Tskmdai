package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.strategy.Straight;

import java.util.List;

/**
 * 英雄飞机
 * 英雄飞机，游戏玩家操控
 *
 * @author hitsz
 * @date 2022/05/04
 */
public class HeroAircraft extends AbstractAircraft {

    private volatile static HeroAircraft heroAircraft;

    /** 攻击方式 */
    private int shootNum = 3;
    /**
     * 英雄机子弹伤害
     */
    private int power = 30;
    /**
     * 方向
     */
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    /**
     * 单例模式创建英雄机
     *
     * @return {@link HeroAircraft}
     */
    public static HeroAircraft getHeroAircraft(){
        if (heroAircraft == null){
            synchronized (HeroAircraft.class){
                if(heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0,
                            0,
                            1000);
                    heroAircraft.setShootStrategy(new Straight());
                }
            }
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    /**
     * 射击
     *
     * @return {@link List}<{@link BaseBullet}>
     */
    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.doShoot(heroAircraft, shootNum, power);
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }
}
