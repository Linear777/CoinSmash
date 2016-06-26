package com.example.imothep.coinsmash.Render.Shader;


import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

import com.example.imothep.coinsmash.Main_Init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Imothep on 21.04.2016.
 */
public class _IgniaShader
{
    public static int shader_program;


    public static void addProgram()
    {
        shader_program = GLES20.glCreateProgram();
    }

    public static void finalizeProgram()
    {
        GLES20.glLinkProgram(shader_program);
    }

    public static String LoadRawFile(int id)
    {
        String content = new String();

        try {
            Resources resources = Main_Init.resources;

            InputStream data_stream =  resources.openRawResource(id);

            BufferedReader br = new BufferedReader(new InputStreamReader(data_stream));
            String line = br.readLine();

            while (line != null)
            {
                content += line;
                line = br.readLine();
            }
        }catch(IOException e)
        {
            Log.e("Shader","Couldn't load shader file");
        }
        finally {

        }
        Log.e("Shader","Shader loaded properly");

        return content;
    }


    /*Load Shader method for initialize shader code from raw files*/
    public static int LoadShader(int shader_path,int shader_type)
    {
        String shader_content = LoadRawFile(shader_path);

        if(shader_content == null)
        {
            Log.d("File Loader",shader_content);
            return -1;
        }

        int shader = GLES20.glCreateShader(shader_type);

        GLES20.glShaderSource(shader,shader_content);
        GLES20.glCompileShader(shader);

        GLES20.glAttachShader(shader_program,shader);

        return 0;
    }
}
