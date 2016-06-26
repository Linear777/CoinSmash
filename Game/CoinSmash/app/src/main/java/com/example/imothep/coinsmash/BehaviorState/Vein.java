package com.example.imothep.coinsmash.BehaviorState;

import com.example.imothep.coinsmash.Elements.Element;

import java.util.ArrayList;

/**
 * Created by Imothep on 11.05.2016.
 */
public abstract class Vein implements Element
{
    public ArrayList<Element> elements;
    public ArrayList<Element> menu;

    public Vein()
    {
        elements = new ArrayList<Element>();
        menu = new ArrayList<Element>();
    }


    public void setElement(Element e)
    {
        elements.add(e);
    }


    public ArrayList<Element> getElemements() {
        return elements;
    }

    public void setElemements(ArrayList<Element> elemements) {
        this.elements = elemements;
    }

    public ArrayList<Element> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Element> menu) {
        this.menu = menu;
    }
}
