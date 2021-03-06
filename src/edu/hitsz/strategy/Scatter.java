package edu.hitsz.strategy;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 散射，并且发射频率为直射的2倍
 *
 * @author ScarecrowLi
 * @date 2022/05/05
 */
public class Scatter implements ShootStrategy{
    @Override
    public List<BaseBullet> doShoot(HeroAircraft aircraft, int shootNum, int power) {
        List<BaseBullet> bullets = new ArrayList<>();
        int direction = -1;
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX ;
        int speedY = aircraft.getSpeedY() + direction*20;
        //0324：更改了子弹的速度，原为*5.
        //0415：更改了子弹的速度，上一次为*15

        //英雄机散射子弹威力2倍
        power *= 2;

        int cnt = 0;
        //offset:偏移量
        int offset = 3;

        //英雄机散射时，发射频率为正常值的二分之一
        Game.setHeroShootDuration(Game.getInhanceDuration());

        for(int i=0; i<shootNum; i++) {
            speedX = 0;
            if(shootNum > 2){
                if(cnt < shootNum/2){
                    speedX -= offset;
                }
                else{
                    speedX += offset;
                }
                if(cnt == shootNum/2){
                    if(shootNum %2 != 0) {
                        speedX = 0;
                    }
                }
                cnt++;
            }
            bullets.add(new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
        }
        return bullets;
    }

    @Override
    public List<BaseBullet> doShoot(EnemyAircraft aircraft, int shootNum, int power) {
        List<BaseBullet> bullets = new ArrayList<>();
        int direction = 1;
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction * 2;
        int speedX;
        int speedY;
        //0324：更改了子弹的速度，原为*5.
        //0415：更改了子弹的速度，上一次为*15

        int cnt = 0;
        //offset:偏移量
        int offset = 3;
        for (int i = 0; i < shootNum; i++) {
            speedX = 0;
            if (shootNum > 2) {
                if (cnt < shootNum / 2) {
                    speedX -= offset;
                } else {
                    speedX += offset;
                }
                if (cnt == shootNum / 2) {
                    if (shootNum % 2 != 0) {
                        speedX = 0;
                    }
                }
                cnt++;
            }
            speedY = aircraft.getSpeedY() + direction * 10;
            //敌机子弹不能过快，为了平衡
            bullets.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
        }

        return bullets;
    }
    }

