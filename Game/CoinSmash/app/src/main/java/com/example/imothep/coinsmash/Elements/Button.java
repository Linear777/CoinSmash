package com.example.imothep.coinsmash.Elements;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.example.imothep.coinsmash.Effect.EffectClass;
import com.example.imothep.coinsmash.Geometry.Shapes.Basic.D_polygon;
import com.example.imothep.coinsmash.Geometry.Shapes.Basic.Geometry;
import com.example.imothep.coinsmash.Render._IgniaRenderer;

/**
 * Created by Imothep on 21.04.2016.
 */
public class Button extends Event_element implements Element
{
    //Geometry of collisions
    Geometry geometry;
    public D_polygon polygonial;
    float[] mFinalMatrix;

    int program;
    int texture;

    public EffectClass effect;

    public Button(String label,int eid)
    {
        //Initialize basic info [Label,eid]
        super(eid,label);
        polygonial = new D_polygon();
        effect = null;

        mFinalMatrix = new float[16];
    }


    public void AppendTexture(int texture)
    {
        this.texture = texture;
    }

    @Override
    public void Draw(float[] mMVPMatrix)
    {
        float time = _IgniaRenderer.time;
        float effect_f = 0;

        if(effect != null)
        {
            effect.tick(time);
            effect_f = effect.getEffect();
        }

        program = effect.UseEffect();

        //Final matrix
        int finMatrix = GLES20.glGetUniformLocation(program, "mMVPMatrix");
        //Time uniform
        int time_loc = GLES20.glGetUniformLocation(program, "time");
        GLES20.glUniform1f(time_loc, effect_f);

        //Get NDC coordinates for shader input
        Matrix.multiplyMM(mFinalMatrix, 0, mMVPMatrix, 0, polygonial.transformation.mixed, 0);
        GLES20.glUniformMatrix4fv(finMatrix, 1, false, mFinalMatrix, 0);


        polygonial.UseGeometry(program);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Preprocess()
    {

    }


    public void Draw()
    {
        
    }

    @Override
    public int actionEvent(int type)
    {
        Log.e("Event","Button event pressed");
        return 0;
    }


    @Override
    public void addCollisionBorder(Geometry geometry)
    {
        this.geometry = geometry;
    }


}
