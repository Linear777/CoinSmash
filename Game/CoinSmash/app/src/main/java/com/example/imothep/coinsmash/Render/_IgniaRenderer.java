package com.example.imothep.coinsmash.Render;

import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.example.imothep.coinsmash.BehaviorState.MenuState;
import com.example.imothep.coinsmash.BehaviorState.Vein;
import com.example.imothep.coinsmash.Effect.ButtonClick;
import com.example.imothep.coinsmash.Effect.EffectClass;
import com.example.imothep.coinsmash.Elements.Button;
import com.example.imothep.coinsmash.Elements.Menu;
import android.util.Log;

import com.example.imothep.coinsmash.Geometry.Shapes.Basic.D_polygon;
import com.example.imothep.coinsmash.R;
import com.example.imothep.coinsmash.Render.Shader._IgniaShader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Imothep on 21.04.2016.
 */


public class _IgniaRenderer implements GLSurfaceView.Renderer
{
    public Button[] buttons;
    public Menu MainMenu;
    public Vein renderState;

   // public int width,height;

    public static float time;
    public D_polygon[] button;

    public int width,height;


    private final float[] mFinalMatrix = new float[16];
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private final float[] rVector = new float[]{1.0f,0.0f,0.0f,1.0f};
    private final float[] rPosition = new float[4];

    public _IgniaRenderer()
    {
        time = 0;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            /*Load shaders*/

        _IgniaShader.addProgram();
        _IgniaShader.LoadShader(R.raw.vertex_shader, GLES20.GL_VERTEX_SHADER);
        _IgniaShader.LoadShader(R.raw.fragment_shader, GLES20.GL_FRAGMENT_SHADER);
        _IgniaShader.finalizeProgram();

        int effect_shader = _IgniaShader.shader_program;


        _IgniaShader.addProgram();
        _IgniaShader.LoadShader(R.raw.vertex_shader, GLES20.GL_VERTEX_SHADER);
        _IgniaShader.LoadShader(R.raw.fragment_btn, GLES20.GL_FRAGMENT_SHADER);
        _IgniaShader.finalizeProgram();

        int btn_shader = _IgniaShader.shader_program;

        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glCullFace(GLES20.GL_BACK);


        MainMenu = new Menu("main menu");
        buttons = new Button[4];

        String[] labels = new String[]{"Single Player","Multi Player","Options","Exit"};

        for(int i = 0;i < 4;i++)
        {
            EffectClass effects = new EffectClass();
            effects.setGLES_Program(effect_shader);

            ButtonClick animation = new ButtonClick("Button shine");
            animation.Interval(0, 80);
            effects.setAnimation(animation);

            buttons[i] = new Button(labels[i],i);
            buttons[i].polygonial.transformation.translateByDVector(0.0f, 0.7f - 0.3f * i, 0.0f);
            buttons[i].polygonial.transformation.scaleByVector(0.8f, 0.1f, 1.0f);
            buttons[i].effect = effects;

            MainMenu.AddButton(buttons[i]);
        }

        MainMenu.getBtn(0).effect.setGLES_Program(btn_shader);


        MenuState menuGlass = new MenuState();
        menuGlass.setElement(MainMenu);

        renderState = menuGlass;

        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glCullFace(GLES20.GL_BACK);

        button = new D_polygon[4];

        for(int i = 0;i < 4;i++)
        {
            button[i] = new D_polygon();
            button[i].transformation.translateByDVector(0.0f,0.7f - 0.3f * i,0.0f);
            button[i].transformation.scaleByVector(0.8f,0.1f,1.0f);
        }
        button[0].name = "Single Player";
        button[1].name = "Multi Player";
        button[2].name = "Options";
        button[3].name = "Exit";
        GLES20.glCullFace(GLES20.GL_BACK);

       // button = new D_polygon();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        this.width = width;
        this.height = height;

        float aspect_ratio = (float) width / (float) height;
        Matrix.orthoM(mProjectionMatrix,0,-1.0f,1.0f,-1.0f,1.0f,0.0f,7.0f);
        Matrix.setIdentityM(mViewMatrix, 0);
        Matrix.setIdentityM(mFinalMatrix,0);

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        /*Initialize preprocessing of menu items*/
        MainMenu.Preprocess(width,height,mMVPMatrix,mProjectionMatrix,mViewMatrix);

        /*Initialize preprocessing of menu items*/

        for(D_polygon btn : button)
        {

            Matrix.multiplyMM(mMVPMatrix,0, mProjectionMatrix,0,mViewMatrix, 0);
            Matrix.multiplyMM(mFinalMatrix, 0, mMVPMatrix,0,btn.transformation.mixed,0);

            //Preprocessing our collision coordinates
            btn.PreprocessBoundary(mFinalMatrix,width,height);
        }

    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        long t1 = SystemClock.uptimeMillis();

        //Camera view matrix
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        renderState.Draw(mMVPMatrix);

        time = (float)( SystemClock.uptimeMillis() - t1) / 100;
        //Camera view matrix
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        GLES20.glUseProgram(_IgniaShader.shader_program);
        int texture_pos = GLES20.glGetAttribLocation(_IgniaShader.shader_program,"texturePosition");

        GLES20.glEnableVertexAttribArray(texture_pos);

        GLES20.glActiveTexture(GLES10.GL_TEXTURE0);
        GLES20.glBindTexture(GLES10.GL_TEXTURE_2D, button[0].textures[0]);
        GLES20.glUniform1i(GLES20.glGetUniformLocation(_IgniaShader.shader_program, "button_texture"), 0);
        GLES20.glVertexAttribPointer(texture_pos, 2, GLES20.GL_FLOAT, false, 8, button[0].textCoords);


        int mvp_position = GLES20.glGetUniformLocation(_IgniaShader.shader_program,"mMVPMatrix");

        GLES20.glUniformMatrix4fv(mvp_position,1,false,mMVPMatrix,0);
        int vertex_pos = GLES20.glGetAttribLocation(_IgniaShader.shader_program, "vPosition");
        int color_pos = GLES20.glGetUniformLocation(_IgniaShader.shader_program, "vColor");

        GLES20.glEnableVertexAttribArray(vertex_pos);
        GLES20.glVertexAttribPointer(vertex_pos, 3, GLES20.GL_FLOAT, false, 12, button[0].vertices);

        GLES20.glUniform4f(color_pos, 0.5f, 0.0f, 0.0f, 1.0f);

        //Drawing menu items
        for(int i = 0;i<4;i++)
        {
            Matrix.multiplyMM(mMVPMatrix,0,mProjectionMatrix,0,mViewMatrix, 0);
            Matrix.multiplyMM(mFinalMatrix, 0, mMVPMatrix, 0,button[i].transformation.mixed,0);

            //Log.e("Multi-result","X: "+rPosition[0]+" Y: "+rPosition[1]+" Z: "+rPosition[2]);

            GLES20.glUniformMatrix4fv(mvp_position,1,false,mFinalMatrix,0);
            GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, button[0].indices);
        }
        GLES20.glDisableVertexAttribArray(vertex_pos);
    }
}
