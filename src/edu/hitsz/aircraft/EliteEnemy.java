package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class  EliteEnemy extends EnemyAircraft{

    private int shootNum = 1;
    //子弹一次发射数量
    private int power = 30;
    //子弹伤害
    private int direction = 1;
    //子弹射击方向 (向上发射：1，向下发射：-1)

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.doShoot(this, shootNum, power);
    }


    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
