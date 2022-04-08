package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.HpSupply;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private  List<BaseBullet> bullets;
    private  AbstractAircraft Elite;

    @BeforeEach
    void setUp() {
        Elite = new EliteEnemy(1,1,1,1,50);
    }

    @AfterEach
    void tearDown() {
        Elite = null;
    }

    @Test
    @DisplayName("掉落道具检测")
    void dropProps() {
        assertTrue(Elite.dropProps() instanceof AbstractProp);
    }

    @Test
    @DisplayName("子弹射出检测")
    void shoot() {
        bullets = Elite.shoot();
        assertFalse(bullets.isEmpty());
            }
}