package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.HpSupply;

public class HpFactory implements PropFactory{

    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new HpSupply(locationX, locationY, 3, 3);
    }

}

