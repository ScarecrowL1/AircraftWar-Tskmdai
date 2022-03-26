package edu.hitsz.aircraft;

import edu.hitsz.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

public class Boss extends AbstractAircraft{
    public Boss(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    //暂时
    @Override
    public List<AbstractBullet> shoot() {
        return new LinkedList<>();
    }
}
