package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.BombFactory;
import edu.hitsz.factory.FireFactory;
import edu.hitsz.factory.HpFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.*;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    protected int dierction;
    protected ShootStrategy shootStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }


    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

    /**
     * 掉落道具方法
     * @return
     */
    public List<AbstractProp> dropProps(){
        List<AbstractProp> res = new LinkedList<>();
        PropFactory propFactory;
        AbstractProp prop = null;
        Random rand = new Random();
        int type = rand.nextInt(3);
        //生成0、1、2三者其一
        //生成血量道具
        if(type == 0){
            propFactory = new HpFactory();
            prop = propFactory.createProp(locationX, locationY);
        }
        //生成火力道具
        if(type == 1){
            propFactory = new FireFactory();
            prop = propFactory.createProp(locationX, locationY);
        }
        //生成炸弹道具
        if(type == 2){
            propFactory = new BombFactory();
            prop = propFactory.createProp(locationX, locationY);
        }
        res.add(prop);
        return  res;
    }

    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }


}


