package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class Boss extends EnemyAircraft{

    private int shootNum = 3;
    private int power = 30;

    public Boss(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    @Override
    public List<BaseBullet> shoot() {
        return shootStrategy.doShoot(this, shootNum, power);
    }

    //boss不爆炸
    @Override
    public void explode() {
    }
}
