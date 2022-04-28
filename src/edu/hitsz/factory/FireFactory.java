package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.FireSupply;

public class FireFactory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        return new FireSupply(locationX, locationY, 3, 3);
    }
}
