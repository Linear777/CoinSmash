package com.example.imothep.coinsmash.Geometry.Shapes.Basic;

import android.opengl.Matrix;

/**
 * Created by Imothep on 01.05.2016.
 */

public class Transformation
{
    public float[] translation = new float[16];
    public float[] rotation = new float[16];
    public float[] scale = new float[16];
    public float[] mixed = new float[16];


    public Transformation()
    {
        Matrix.setIdentityM(translation,0);
        Matrix.setIdentityM(rotation,0);
        Matrix.setIdentityM(scale,0);
        Matrix.setIdentityM(mixed,0);

        Update();
    }

    public void Update()
    {
    }


    public void rotateByEuler(float x,float y,float z)
    {
        Matrix.setRotateEulerM(mixed,0,x,y,z);
    }

    public void translateByDVector(float x,float y,float z)
    {
        Matrix.translateM(mixed, 0, x, y, z);
    }

    public void scaleByVector(float x,float y,float z)
    {
        Matrix.scaleM(mixed,0,x,y,z);
    }

    public void rotateByQuaternion()
    {
    }
<<<<<<< HEAD
=======

>>>>>>> refs/heads/Linear777
}
