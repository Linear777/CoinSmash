package com.example.imothep.coinsmash.Effect;

/**
 * Created by Imothep on 11.05.2016.
 */
public class SpriteAnimation extends Animation
{
    public int TileWidth;
    public int TileHeight;

    public int ResourceWidth;
    public int ResourceHeight;

    public SpriteAnimation(String name) {
        super(name);
    }


    @Override
    public  void Tick(float deltaTime)
    {
        if(repeatable == false && continuance > 1)
        {
            return;
        }
        continuance = continuance + deltaTime;
    }
}
