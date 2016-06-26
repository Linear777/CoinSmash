package com.example.imothep.coinsmash.Elements;

<<<<<<< HEAD
import android.opengl.Matrix;

import com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D.Collider;

import java.util.ArrayList;
=======
import com.example.imothep.coinsmash.Physics.PhyCollision.Collision3D.Collider;

import java.util.List;
>>>>>>> refs/heads/Linear777

/**
 * Created by Imothep on 02.05.2016.
 */
<<<<<<< HEAD
public class Menu implements Element
{
    String menu_title;
    ArrayList<Button> btns;
=======
public class Menu
{
    String menu_title;
    List<Button> btns;
>>>>>>> refs/heads/Linear777
    Collider collision_testing;

    public Menu(String menu_title)
    {
        this.menu_title = menu_title;
<<<<<<< HEAD
        btns = new ArrayList<Button>();

    }

    @Override
    public void Draw(float []MVP)
=======

    }

    public void DrawMenu()
>>>>>>> refs/heads/Linear777
    {
        //Drawing buttons
        for (Button button : btns)
        {
<<<<<<< HEAD
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
=======
           button.Draw();
>>>>>>> refs/heads/Linear777
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
