package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    public abstract List<BaseBullet> doShoot(AbstractAircraft aircraft, int shootNum, int power, int type);
}
