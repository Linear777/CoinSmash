package com.example.imothep.coinsmash.Elements;

import com.example.imothep.coinsmash.Geometry.Shapes.Basic.Geometry;

/**
 * Created by Imothep on 21.04.2016.
 */
public abstract class Event_element
{
    public int eid;
    public String name;

    public Event_element(int eid,String name)
    {
        this.eid = eid;
        this.name = name;
    }
    public abstract int actionEvent(int type);
    public abstract void addCollisionBorder(Geometry geometry);
}
