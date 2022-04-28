package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;

public interface PropFactory {
    public abstract AbstractProp createProp(int locationX, int locationY);
}

//push
