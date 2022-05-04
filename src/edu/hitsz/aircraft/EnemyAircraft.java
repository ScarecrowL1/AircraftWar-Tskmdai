package edu.hitsz.aircraft;

import edu.hitsz.factory.BombFactory;
import edu.hitsz.factory.FireFactory;
import edu.hitsz.factory.HpFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.ExplodeObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class EnemyAircraft extends AbstractAircraft implements ExplodeObject {
    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void explode() {
        this.vanish();
    }

}
