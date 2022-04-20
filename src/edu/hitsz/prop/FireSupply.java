package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.Scatter;
import edu.hitsz.aircraft.Straight;

public class FireSupply extends AbstractProp{
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(HeroAircraft aircraft) {
        //增加子弹数目
        int shootNum = aircraft.getShootNum() + 1 ;
        //改变子弹发射方式的概率prChange
        double prChange = 0.5;
        if(Math.random() < prChange){
            //变为直射
            aircraft.setShootStrategy(new Straight());
        }
        else {
            //变为散射
            aircraft.setShootStrategy(new Scatter());
        }
        aircraft.setShootNum(shootNum);
        System.out.println("FireSupply Activate");
    }
}
