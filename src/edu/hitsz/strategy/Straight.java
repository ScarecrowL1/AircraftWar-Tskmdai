package edu.hitsz.strategy;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.ArrayList;
import java.util.List;

public class Straight implements ShootStrategy{


    @Override
    public List<BaseBullet> doShoot(HeroAircraft aircraft, int shootNum, int power) {
        List<BaseBullet> bullets = new ArrayList<>();
        int direction = -1;
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + direction*20;
        //0324：更改了子弹的速度，原为*5.
        //0415：更改了子弹的速度，上一次为*15

        //英雄机直射时，使用默认频率
        Game.setHeroShootDuration(Game.getRuleDuration());

        //生成子弹，
        for(int i=0; i<shootNum; i++) {
            bullets.add(new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
        }

        return bullets;
    }


@Override
    public List<BaseBullet> doShoot(EnemyAircraft aircraft, int shootNum, int power) {
        List<BaseBullet> bullets = new ArrayList<>();
        int direction = 1;
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX = 0;
        int speedY ;
        //0324：更改了子弹的速度，原为*5.
        //0415：更改了子弹的速度，上一次为*15

        //生成子弹，
        for(int i=0; i<shootNum; i++) {
            speedY = aircraft.getSpeedY() + direction*10;
            //敌机子弹不能过快
            bullets.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
        }

        return bullets;
    }
}
