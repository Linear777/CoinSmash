package com.example.imothep.coinsmash.Effect;

import android.util.Log;

/**
 * Created by Imothep on 10.05.2016.
 */
public abstract class Animation
{
    public int id;
    public String name;
    public float start;
    public float end;
    public float continuance;

    public boolean repeatable;

    public float Ax,Ay;

    public Animation(String name)
    {
        this.name = name;
        start = end = continuance = Ax = Ay = 0;
        repeatable = false;
    }

    public void Interval(float s, float e)
    {
        start = s;
        end = e;
    }

    public void Reset()
    {
        continuance = 0;
    }

    public abstract void Tick(float deltaTime);

    public float GetEffect()
    {
        return continuance;
    }

}
