package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private AbstractAircraft hero;

    @BeforeEach
    void setUp() {
        hero = HeroAircraft.getHeroAircraft();

    }

    @AfterEach
    void tearDown() {
        hero = null;
    }

    @Test
    @DisplayName("掉血检测")
    void decreaseHp() {
        int decrease = 2;
        int maxdamege = 99999;
        int hp_full = hero.getHp();//用来表示初始生命值

        hero.decreaseHp(decrease);
        int hp_normalDam = hero.getHp();//用来表示受到正常伤害的生命值

        hero.decreaseHp(maxdamege);
        int hp_deadDam = hero.getHp();//用来表示受到溢出伤害的生命值

        assertAll("掉血检测",
                ()->assertEquals(hp_full-2, hp_normalDam),//检测正常掉血
                ()->assertEquals(0, hp_deadDam)//检测掉血值大于hp时是否归零
        );
    }

    @Test
    @DisplayName("消失检测")
    void vanish() {
        hero.vanish();
        assertTrue(hero.notValid());
    }
}