package com.example.imothep.coinsmash.Render;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
<<<<<<< HEAD

import com.example.imothep.coinsmash.Elements.Button;
=======
import android.widget.Toast;

import com.example.imothep.coinsmash.Elements.Button;
import com.example.imothep.coinsmash.Geometry.Shapes.Basic.D_polygon;
>>>>>>> refs/heads/Linear777

/**
 * Created by Imothep on 21.04.2016.
 */
public class _IgniaSurfaceGlass extends GLSurfaceView implements View.OnTouchListener
{
        private final _IgniaRenderer renderer;
        private Context context;

        public _IgniaSurfaceGlass(Context context)
        {
                super(context);

                this.context = context;

                this.setOnTouchListener(this);
                //Opengl ES 2.0 context
                setEGLContextClientVersion(2);
                renderer = new _IgniaRenderer();

                //Set the renderer for drawing on the SurfaceGlass
                setRenderer(renderer);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
                int action = event.getAction();

                switch(action)
                {
                        case MotionEvent.ACTION_DOWN:

                                float x = event.getX();
                                float y = event.getY();

                                Log.e("Clicked","WAITING");

<<<<<<< HEAD
                                for(Button btn : renderer.buttons)
                                {
                                        if(btn.polygonial.CheckCollision(x,y) == true)
                                        {
                                                btn.effect.getAnimation().Reset();
=======
                                for(D_polygon btn : renderer.button)
                                {
                                        if(btn.CheckCollision(x,y) == true)
                                        {
                                                Toast.makeText(context, btn.name, Toast.LENGTH_SHORT).show();
>>>>>>> refs/heads/Linear777
                                                break;
                                        }
                                }
                        break;

                }

                return false;
        }
}