package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.MusicThread;

import java.util.ArrayList;
import java.util.List;

public class BombSupply extends AbstractProp{

    List<ExplodeObject> explodeObjectList = new ArrayList<>();

    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {
        new MusicThread("src/videos/bomb_explosion.wav").start();
        notifyBombObject();
        System.out.println("BombSupply Activate");
    }

    public void addBombObject(ExplodeObject explodeObject){
        explodeObjectList.add(explodeObject);
    }


    public void notifyBombObject(){
        for(ExplodeObject explodeObject : explodeObjectList){
            explodeObject.explode();
        }
    }
}
