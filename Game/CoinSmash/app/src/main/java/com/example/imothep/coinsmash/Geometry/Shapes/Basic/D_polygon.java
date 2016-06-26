package com.example.imothep.coinsmash.Geometry.Shapes.Basic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;

import com.example.imothep.coinsmash.Main_Init;
import com.example.imothep.coinsmash.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Vector;

/**
 * Created by Imothep on 21.04.2016.
 * Class representing polygonial
 */

public class D_polygon extends Geometry
{
<<<<<<< HEAD
=======

>>>>>>> refs/heads/Linear777
    class Vector3
    {
        public float x = 0;
        public float y = 0;
        public float z = 0;
    }

<<<<<<< HEAD
=======

>>>>>>> refs/heads/Linear777
    public String name;
    public FloatBuffer vertices;
    public ShortBuffer indices;
    public FloatBuffer textCoords;
    public int textures[];


    //Preprocessed rational coordinates
    public Vector3[] boundary = new Vector3[4];

    public Transformation transformation;

    private final float coords[] =
            {
                -1.0f,1.0f,0.0f,
                -1.0f,-1.0f,0.0f,
                1.0f,-1.0f,0.0f,
                1.0f,1.0f,0.0f
            };

    private final float tCoords[] =
            {
                0.0f,0.0f,
                0.0f,1.0f,
                1.0f,1.0f,
                1.0f,0.0f
            };

    private final short order[] = {0,1,2,2,3,0};

    public D_polygon()
    {
        super(4);
        Initialize();

        transformation = new Transformation();
<<<<<<< HEAD
    }


    @Override
    public void UseGeometry(int effect)
    {
        //Locations in Vertex & Fragment shaders
        int texture_pos = GLES20.glGetAttribLocation(effect, "texturePosition");
        int vertex_pos = GLES20.glGetAttribLocation(effect, "vPosition");

        //Enable VertexAttrib arrays for use
        GLES20.glEnableVertexAttribArray(texture_pos);
        GLES20.glEnableVertexAttribArray(vertex_pos);

        //Assembly shader definition
        GLES20.glVertexAttribPointer(vertex_pos, 3, GLES20.GL_FLOAT, false, 12, vertices);
        GLES20.glVertexAttribPointer(texture_pos, 2, GLES20.GL_FLOAT, false, 8, textCoords);

        //Active textures
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glUniform1i(GLES20.glGetUniformLocation(effect, "button_texture"), 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);



        //Draw Call
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, indices);

        GLES20.glDisableVertexAttribArray(texture_pos);
        GLES20.glDisableVertexAttribArray(vertex_pos);

=======
>>>>>>> refs/heads/Linear777
    }

    @Override
    public int Initialize()
    {
        ByteBuffer b_vertices = ByteBuffer.allocateDirect(coords.length * 4);
        b_vertices.order(ByteOrder.nativeOrder());

        ByteBuffer b_indices = ByteBuffer.allocateDirect(order.length * 2);
        b_indices.order(ByteOrder.nativeOrder());

        ByteBuffer t_vertices = ByteBuffer.allocateDirect(tCoords.length * 4);
        t_vertices.order(ByteOrder.nativeOrder());

        vertices = b_vertices.asFloatBuffer();
        vertices.put(coords);

        indices = b_indices.asShortBuffer();
        indices.put(order);

        textCoords = t_vertices.asFloatBuffer();
        textCoords.put(tCoords);

        indices.position(0);
        vertices.position(0);
        textCoords.position(0);

<<<<<<< HEAD
        textures = new int[10];
=======
        textures = new int[1];
>>>>>>> refs/heads/Linear777

        Bitmap button = BitmapFactory.decodeResource(Main_Init.resources, R.drawable.button_experimental);

        GLES10.glGenTextures(1, textures, 0);
        GLES10.glBindTexture(GLES10.GL_TEXTURE_2D, textures[0]);

        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MAG_FILTER, GLES10.GL_LINEAR);
        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MIN_FILTER, GLES10.GL_LINEAR);

        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_S, GLES10.GL_REPEAT);
        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_T, GLES10.GL_REPEAT);

        GLUtils.texImage2D(GLES10.GL_TEXTURE_2D, 0, button, 0);

        GLES10.glBindTexture(GLES10.GL_TEXTURE_2D,0);

<<<<<<< HEAD
=======

>>>>>>> refs/heads/Linear777
        for(int i = 0;i<4;i++)
        {
            boundary[i] = new Vector3();
        }

        return 0;
    }

    //This is for Menu event clickable collision
    public void PreprocessBoundary(float[] ProjectionViewMatrix,int width,int height)
    {
        Log.e("Calling preprocess","Now !");
        for(int vertexes = 0; vertexes < 4; vertexes++)
        {
            float[] temp_vect = new float[4];
            float[] temp_final = new float[4];

            temp_vect[3] = 1;   //Je to bod nie vektor

            for (int c = 0; c < 3; c++)
            {
                temp_vect[c] = coords[c + vertexes * 3];
            }

            Matrix.multiplyMV(temp_final,0,ProjectionViewMatrix,0,temp_vect,0);

            //The real MVP
            boundary[vertexes].x = 0.5f * (temp_final[0] + 1.0f);
            boundary[vertexes].y = 0.5f * (1.0f - temp_final[1]);
            boundary[vertexes].z = temp_final[2]; //We dont need it really

            //Ok this is the real MVP :)
            boundary[vertexes].x *= width;
            boundary[vertexes].y *= height;

            Log.e("MVP","X : "+boundary[vertexes].x + " Y : "+boundary[vertexes].y);
        }

        Log.e("Calling done","Done!");
    }

    public boolean CheckCollision(float x,float y)
    {
        if((x >= boundary[0].x) && ( x <= boundary[2].x) && ( y <= boundary[2].y ) && (y >= boundary[0].y))
        {
            return true;
        }

        return false;
    }


}
