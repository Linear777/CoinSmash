package com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D;

/**
 * Created by Imothep on 02.05.2016.
 *
 * THIS FUCKING SHIT NEED TO BE IMPROVED AS WELL AS MY MATH
 * MIN[MAX[ (X0 + L0*it)^2 +  (Y0 + L1*it)^2, |Z0 + L2*it| ] , 1 ] - 1 = Epsilone
 *
 *
 */
public class CoinCollider implements Collider
{
    private double Epsilone = 0.001f;
    private double x,y,z;
    private double l0,l1,l2;
    private double it;

    public CoinCollider()
    {



    }


    @Override
    public float ExecuteCollider(float x, float y, float z)
    {

        return 0;
    }

    @Override
    public float GetDistanceFunction(float x, float y, float z)
    {
        double ak0 = (x + l0*it);
        double ak00 = ak0 * ak0;

        double ak1 = (y + l1*it);
        double ak11 = ak1 * ak1;

        double ak22 = Math.abs(z + l2*it);

        return (float)Math.min(Math.max(Math.max(ak00,ak11),ak22),1.0);
    }

    @Override
    public float TryRayCast()
    {


        return 0;
    }

    @Override
    public float TryRayMarch()
    {




        return 0;
    }
}
