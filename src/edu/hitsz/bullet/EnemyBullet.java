package edu.hitsz.bullet;

import edu.hitsz.prop.ExplodeObject;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements ExplodeObject {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void explode() {
        this.vanish();
    }
}
