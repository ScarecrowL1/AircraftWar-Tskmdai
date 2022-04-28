package edu.hitsz.strategy;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    List<BaseBullet> doShoot(HeroAircraft aircraft, int shootNum, int power);
    List<BaseBullet> doShoot(EnemyAircraft aircraft, int shootNum, int power);
}
