package com.example.imothep.coinsmash.Effect;

import android.opengl.GLES20;

/**
 * Created by Imothep on 11.05.2016.
 * This class is used to make effects with united shaders + animation class
 * It makes sense for grouping some elements
 */

public class EffectClass
{
    private int GLES_Program;
    private Animation animation;


    public EffectClass() {}

    public EffectClass(Animation animation, int program)
    {
        this.animation = animation;
        this.GLES_Program = program;
    }

    public void tick(float deltaTime)
    {
        animation.Tick(deltaTime);
    }


    /**
    *   It will use appended shaders
    * */

    public int UseEffect()
    {
        GLES20.glUseProgram(GLES_Program);

        return GLES_Program;
    }

    public float getEffect()
    {
        return animation.GetEffect();
    }


    public Animation getAnimation() {
        return animation;
    }

    public int getGLES_Program() {
        return GLES_Program;
    }

    public void setGLES_Program(int GLES_Program) {
        this.GLES_Program = GLES_Program;
    }

    public void setAnimation(Animation anim)
    {
        this.animation = anim;
    }
}
