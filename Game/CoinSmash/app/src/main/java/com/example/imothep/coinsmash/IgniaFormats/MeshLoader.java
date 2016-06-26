package com.example.imothep.coinsmash.IgniaFormats;

/**
 * Created by Imothep on 27.04.2016.
 */
public class MeshLoader {
    private static MeshLoader ourInstance = new MeshLoader();

    public static MeshLoader getInstance() {
        return ourInstance;
    }

    private MeshLoader()
    {

    }


    public static void LoadMesh(String path)
    {
        int index = path.lastIndexOf(".");

        String file_format = path.substring(index,path.length());

        //hashmap file_formats
    }


}
