package edu.hitsz.application;

public class NomalGame extends Game{
    public NomalGame() {

        //设置产生boss阈值为500分
        setBossScoreThreshold(500);
        //设置同屏最大敌人数量
        setEnemyMaxNumber(5);

        //设置英雄机射击频率(ms)
        setRuleDuration(600);
        //设置敌机生成和发射频率(ms)
        setEnemyCycleDuration(600);

        //设置精英机初始生成概率
        setPrElite(0.3);
        //设置普通敌机血量
        mobFactory.setHp(35);
        //设置精英敌机血量
        eliteFactory.setHp(65);
        //设置Boss血量
        bossFactory.setHp(800);
    }


    @Override
    public void hardUp() {
        super.hardUp();
        //提升精英机产生概率
        double presentPrRlite = getPrElite()+0.05;
        setPrElite(presentPrRlite);
        System.out.println("精英机产生概率提升到"+ (int)(getPrElite()*100)+"%");

        //提升普通敌机和精英敌机血量
        //普通模式步增2
        if (mobFactory.getHp() < 60) {
            mobFactory.setHp(mobFactory.getHp()+2);
        }
        if(eliteFactory.getHp() < 120){
            eliteFactory.setHp(mobFactory.getHp()+2);
        }
        System.out.println("当前精英敌机血量上限为"+eliteFactory.getHp()+"\n普通敌机血量上限为"+mobFactory.getHp());
    }
}
