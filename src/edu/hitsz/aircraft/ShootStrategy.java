package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    List<BaseBullet> doShoot(HeroAircraft aircraft, int shootNum, int power);
    List<BaseBullet> doShoot(EliteEnemy aircraft, int shootNum, int power);
}
