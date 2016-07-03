package com.example.imothep.coinsmash.IgniaFormats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.imothep.coinsmash.Main_Init;
import com.example.imothep.coinsmash.R;

/**
 * Created by Rony on 6/27/2016.
 */
public class AssetsLoader
{
    public static Bitmap load_texture(int asset_id)
    {
        Bitmap texture=null;
        switch(asset_id)
        {
            case 1:
                texture = BitmapFactory.decodeResource(Main_Init.resources, R.drawable.button_experimental);
                break;

            default:
                //error
        }

        return texture;
    }
}
