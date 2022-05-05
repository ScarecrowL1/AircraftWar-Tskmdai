package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.MusicThread;
import edu.hitsz.strategy.Scatter;
import edu.hitsz.strategy.Straight;

/**
 * 火力道具
 *
 * @author ScarecrowLi
 * @date 2022/05/05
 */
public class FireSupply extends AbstractProp{
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 火力道具生效
     * 变为散射
     * 效果保持5s
     * @param aircraft 飞机
     */
    @Override
    public void activate(HeroAircraft aircraft) {
        System.out.println("FireSupply Activated");
        System.out.println("子弹威力加倍！发射频率加倍！");
        new MusicThread("src/videos/get_supply.wav").start();
        Runnable r = () ->{
            try {
                aircraft.setShootStrategy((new Scatter()));
                //保持散射持续4秒
                Thread.sleep(5000);
                aircraft.setShootStrategy(new Straight());
                System.out.println("FireSupply Discativated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r, "FireSupply").start();
    }
}
