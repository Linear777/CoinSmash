package com.example.imothep.coinsmash.EventDirecter;

/**
 * Created by Imothep on 11.05.2016.
 */
public interface IgniaEvent
{
    void onTouch();
    void onHold();
    void onUp();
    void onMove();
    void onDrag();
}
