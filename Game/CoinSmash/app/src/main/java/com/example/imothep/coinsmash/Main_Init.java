package com.example.imothep.coinsmash;

/*
* IGNIA FRAMEWORK ON THE WAY
* COIN SMASH MAIN METHOD
* */

import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.imothep.coinsmash.Render._IgniaSurfaceGlass;


public class Main_Init extends AppCompatActivity
{
    private GLSurfaceView view;
    public static Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        resources = getResources();

        //Create SurfaceGlass with renderer for drawings
        view = new _IgniaSurfaceGlass(this);
        setContentView(view);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__init, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
