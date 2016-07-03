package com.example.imothep.coinsmash.Elements;


import android.opengl.Matrix;

import com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D.Collider;

import java.util.ArrayList;
import com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D.Collider;

import java.util.List;

/**
 * Created by Imothep on 02.05.2016.
 */
public class Menu implements Element
{

    String menu_title;
    List<Button> btns;
    Collider collision_testing;

    public Menu(String menu_title)
    {
        this.menu_title = menu_title;
        btns = new ArrayList<Button>();

    }

    @Override
    public void Draw(float []MVP)
    {

    }

    public void DrawMenu(float []MVP)
    {
        //Drawing buttons
        for (Button button : btns)
        {
           button.Draw(MVP);
        }
    }

    @Override
    public void Update() {

    }

    public Button getBtn(int index) {
        return btns.get(index);
    }

    @Override
    public void Preprocess() {

    }

    public void Preprocess(int width,int height,float []mMVPMatrix,float []mProjectionMatrix,float []mViewMatrix)
    {
        float []mFinalMatrix = new float[16];

        for(Button btn : btns)
        {
            //Real NDC coordinates
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            Matrix.multiplyMM(mFinalMatrix, 0, mMVPMatrix,0,btn.polygonial.transformation.mixed,0);

            //Preprocessing our collision coordinates
            btn.polygonial.PreprocessBoundary(mFinalMatrix, width, height);
            btn.Draw();
        }
    }

    public void AddButton(Button btn)
    {
        btns.add(btn);
    }

    public int getNumberOfButtons()
    {
        return btns.size();
    }

}
