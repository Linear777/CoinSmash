package com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D;

/**
 * Created by Imothep on 02.05.2016.
 */
public interface Collider
{
     float ExecuteCollider(float x,float y,float z);
     float GetDistanceFunction(float x,float y,float z);
     float TryRayCast();
     float TryRayMarch();

}
