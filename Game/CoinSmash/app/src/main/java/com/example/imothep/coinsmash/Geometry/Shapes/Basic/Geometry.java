package com.example.imothep.coinsmash.Geometry.Shapes.Basic;

/**
 * Created by Imothep on 21.04.2016.
 */
public abstract class Geometry
{
    int type;

    public Geometry(int type)
    {
        this.type = type;
    }

    public abstract void UseGeometry(int effect);
    public abstract int Initialize();
}
