package edu.hitsz.application;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.factory.MobFactory;

public class EasyGame extends Game{
    public EasyGame() {
        //简单模式不添加boss
        setCanBuildBoss(false);
        //设置同屏最大敌人数量
        setEnemyMaxNumber(10);

        //设置英雄机射击频率(ms)
        setRuleDuration(400);
        //设置敌机生成和发射频率(ms)
        setEnemyCycleDuration(600);

        //设置精英机生成概率
        setPrElite(0.3);
        //设置普通敌机血量
        mobFactory.setHp(30);
        //设置精英敌机血量
        eliteFactory.setHp(60);

    }


    @Override
    public void hardUp() {
        //简单模式不执行难度增加
        ;
    }
}
