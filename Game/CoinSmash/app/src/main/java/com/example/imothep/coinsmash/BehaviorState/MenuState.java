package com.example.imothep.coinsmash.BehaviorState;

import com.example.imothep.coinsmash.Elements.Element;

/**
 * Created by Imothep on 11.05.2016.
 */
public class MenuState extends Vein
{
    public MenuState()
    {

    }

    @Override
    public void Draw(float[] MVP)
    {
        for(Element e : elements)
        {
            e.Draw(MVP);
        }
    }

    @Override
    public void Update() {
        for(Element e : elements)
        {
            e.Update();
        }
    }

    @Override
    public void Preprocess() {
        for(Element e : elements)
        {
            e.Preprocess();
        }
    }
}
