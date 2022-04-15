package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.ArrayList;
import java.util.List;

public class Straight implements ShootStrategy{
    @Override
    public List<BaseBullet> doShoot(AbstractAircraft aircraft, int shootNum, int power, int type) {
        List<BaseBullet> bullets = new ArrayList<>();
        int direction = aircraft.getDirection();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + direction*20;
        //0324：更改了子弹的速度，原为*5.
        //0415：更改了子弹的速度，上一次为*15

        //生成子弹，
        // type == 1 英雄机子弹
        // type == 2 敌机子弹
        for(int i=0; i<shootNum; i++) {
            if(type == 1) {
                bullets.add(new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
            }
            if(type == 2){
                speedY = aircraft.getSpeedY() + direction*10;
                //敌机子弹不能过快
                bullets.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
            }
        }

        return bullets;
    }
}
