package edu.hitsz.application;

/**
 * 困难模式
 *
 * @author ScarecrowLi
 * @date 2022/05/05
 */
public class HardGame extends Game{

    public HardGame() {
        //设置产生boss阈值为200分
        setBossScoreThreshold(200);
        //设置同屏最大敌人数量
        setEnemyMaxNumber(8);
        //设置英雄机射击频率(ms)
        setRuleDuration(800);
        //设置敌机生成和发射频率(ms)
        setEnemyCycleDuration(500);

        //设置精英机生成概率
        setPrElite(0.4);
        //设置普通敌机初始血量
        mobFactory.setHp(45);
        //设置精英敌机初始血量
        eliteFactory.setHp(75);
        //设置Boss初始血量
        bossFactory.setHp(1000);
    }


    @Override
    public void bossCrash() {
        super.bossCrash();
        //困难模式，每次击败boss后，boss血量增加250
        bossFactory.setHp(bossFactory.getHp()+250);
        System.out.println("击败boss，boss的血量上限增加到"+bossFactory.getHp());
    }

    @Override
    public void hardUp() {
        super.hardUp();
        //提升精英机产生概率
        double presentPrRlite = getPrElite()+0.05;
        setPrElite(presentPrRlite);
        System.out.println("精英机产生概率提升到"+ (int)(getPrElite()*100)+"%");

        //提升普通敌机和精英敌机血量
        //困难模式步增4
        if (mobFactory.getHp() < 70) {
            mobFactory.setHp(mobFactory.getHp()+4);
        }
        if(eliteFactory.getHp() < 140){
            eliteFactory.setHp(mobFactory.getHp()+4);
        }
        System.out.println("当前精英敌机血量上限为"+eliteFactory.getHp()+"\n普通敌机血量上限为"+mobFactory.getHp());
    }

}
