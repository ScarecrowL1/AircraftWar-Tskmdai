package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HpSupplyTest {

    private HeroAircraft hero;
    private HpSupply HpProp;

    @BeforeEach
    void setUp() {
        hero = HeroAircraft.getHeroAircraft();
        HpProp = new HpSupply(1,1,1,1);
    }

    @AfterEach
    void tearDown() {
        hero = null;
        HpProp = null;
    }

    @Test
    @DisplayName("碰撞检测")
    void crash() {

        boolean Crashed1 = HpProp.crash(hero); //Crashed1应为false

        //生成和英雄机重合的道具
        HpProp = new HpSupply(
                hero.getLocationX(),
                hero.getLocationY(),
                0,
                hero.getSpeedY()
        );

        boolean Crashed2 = HpProp.crash(hero);  ////Crashed2应为true
        assertFalse(Crashed1);
        assertTrue(Crashed2);
    }

    @Test
    @DisplayName("HP道具生效检测")
    void activate() {
        int value = HpProp.getValue();       //得到hp道具的属性
        int expectHp = value + hero.getHp(); //计算出hp道具生效后的hp值
        HpProp.activate(hero);
        assertEquals(expectHp, hero.getHp());
    }
}