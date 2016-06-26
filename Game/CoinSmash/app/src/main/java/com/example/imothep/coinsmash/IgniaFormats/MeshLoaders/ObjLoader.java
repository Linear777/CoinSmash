package com.example.imothep.coinsmash.IgniaFormats.MeshLoaders;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by Imothep on 02.05.2016.\
 *  OBJ loader by Imothep there is no flour
 */

public class ObjLoader
{
    class Vertex
    {
        public float vertex_X;
        public float vertex_Y;
        public float vertex_Z;
    }

    class TexCoordinate
    {
        public float vertex_U;
        public float vertex_V;
    }

    class Mesh
    {
        Vertex[] v;
        Vertex[] n;
        TexCoordinate[] t;
    }

    private int mesh_count = 0;
    public int all_vertexes = 0;
    private String path;
    private Context context;
    private ArrayList<Vertex> Vertexes;
    private ArrayList<TexCoordinate> TexCoords;
    private ArrayList<Vertex> Normals;
    private ArrayList<Mesh> Meshes;



    public FloatBuffer vertex_bufferl;
    public FloatBuffer normal_buffer;

    public ObjLoader(Context context)
    {
        this.context = context;

        Vertexes = new ArrayList<Vertex>();
        TexCoords = new ArrayList<TexCoordinate>();
        Normals = new ArrayList<Vertex>();
        Meshes = new ArrayList<Mesh>();

        InitialiseLoader();
    }

    private void InitialiseLoader()
    {
       /* LoadVertices();
        LoadIndices();
        LoadMeshes();*/

        try {
            InputStream inputStream = null; //context.getResources().openRawResource(R.raw.tris_body_mesh);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader r = new BufferedReader(inputReader);

            String line;
            while((line = r.readLine()) != null)
            {
                if(line.startsWith("vn"))
                {
                    Normals.add(getVertex(line));
                }
                else if(line.startsWith("vt"))
                {
                    TexCoords.add(getTextureCoords(line));
                }
                else if(line.startsWith("v"))
                {
                    Vertexes.add(getVertex(line));
                }
                else if(line.startsWith("f"))
                {
                    Meshes.add(getMesh(line));
                    mesh_count++;

                }else continue;
            }

            float[] vertexes = new float[Meshes.size()*9];
            float[] normals = new float[Meshes.size()*9];

            int i = 0;
            for(int c = 0;c<Meshes.size();c++)
            {
                i = c*9;

                vertexes[i]   = Meshes.get(c).v[0].vertex_X;
                vertexes[i+1] = Meshes.get(c).v[0].vertex_Y;
                vertexes[i+2] = Meshes.get(c).v[0].vertex_Z;

                vertexes[i+3] = Meshes.get(c).v[1].vertex_X;
                vertexes[i+4] = Meshes.get(c).v[1].vertex_Y;
                vertexes[i+5] = Meshes.get(c).v[1].vertex_Z;

                vertexes[i+6] = Meshes.get(c).v[2].vertex_X;
                vertexes[i+7] = Meshes.get(c).v[2].vertex_Y;
                vertexes[i+8] = Meshes.get(c).v[2].vertex_Z;

                normals[i]   = Meshes.get(c).n[0].vertex_X;
                normals[i+1] = Meshes.get(c).n[0].vertex_Y;
                normals[i+2] = Meshes.get(c).n[0].vertex_Z;

                normals[i+3] = Meshes.get(c).n[1].vertex_X;
                normals[i+4] = Meshes.get(c).n[1].vertex_Y;
                normals[i+5] = Meshes.get(c).n[1].vertex_Z;

                normals[i+6] = Meshes.get(c).n[2].vertex_X;
                normals[i+7] = Meshes.get(c).n[2].vertex_Y;
                normals[i+8] = Meshes.get(c).n[2].vertex_Z;

            }

            ByteBuffer bb = ByteBuffer.allocateDirect(vertexes.length*4);
            ByteBuffer bn = ByteBuffer.allocateDirect(vertexes.length*4);

            bb.order(ByteOrder.nativeOrder());
            bn.order(ByteOrder.nativeOrder());

            vertex_bufferl = bb.asFloatBuffer();
            vertex_bufferl.put(vertexes);
            vertex_bufferl.position(0);

            normal_buffer = bn.asFloatBuffer();
            normal_buffer.put(normals);
            normal_buffer.position(0);

            all_vertexes = Meshes.size()*3;

            System.out.println("Loading done");
        } catch (Exception e) {
            System.out.println("Errorrrrr");
        }

    }

    private Vertex getVertex(String data)
    {
        String[] pole = data.split("\\ ");
        Vertex v = new Vertex();

        v.vertex_X = Float.parseFloat(pole[1]);
        v.vertex_Y = Float.parseFloat(pole[2]);
        v.vertex_Z = Float.parseFloat(pole[3]);

        return v;
    }

    private TexCoordinate getTextureCoords(String data)
    {
        String[] pole = data.split("\\ ");
        TexCoordinate coord = new TexCoordinate();

        coord.vertex_U = Float.parseFloat(pole[1]);
        coord.vertex_V = Float.parseFloat(pole[2]);

        return coord;
    }


    private Mesh getMesh(String data)
    {
        String[] fronts = data.split("\\ ");


        String[] t_1 = fronts[1].split("\\/");
        String[] t_2 = fronts[2].split("\\/");
        String[] t_3 = fronts[3].split("\\/");

        Mesh mesh = new Mesh();

        mesh.v = new Vertex[3];
        mesh.n = new Vertex[3];
        mesh.t = new TexCoordinate[3];

        mesh.v[0] = Vertexes.get(Integer.parseInt(t_1[0])-1);
        mesh.v[1] = Vertexes.get(Integer.parseInt(t_2[0])-1);
        mesh.v[2] = Vertexes.get(Integer.parseInt(t_3[0])-1);

        mesh.n[0] = Normals.get(Integer.parseInt(t_1[2])-1);
        mesh.n[1] = Normals.get(Integer.parseInt(t_2[2])-1);
        mesh.n[2] = Normals.get(Integer.parseInt(t_3[2])-1);

        mesh.t[0] = TexCoords.get(Integer.parseInt(t_1[1])-1);
        mesh.t[1] = TexCoords.get(Integer.parseInt(t_2[1])-1);
        mesh.t[2] = TexCoords.get(Integer.parseInt(t_3[1])-1);

        return mesh;
    }
}
