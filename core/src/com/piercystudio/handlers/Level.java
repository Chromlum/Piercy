package com.piercystudio.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.piercystudio.entities.Box;
import com.piercystudio.entities.Moneda;

import java.io.*;

/**
 * Created by Chromz on 10/18/2015.
 */
public abstract class Level implements Serializable{
    private String name;
    private String map;
    private Dialog[] dialogs;
    private Moneda[] coins;
    private Box[] boxes;
    private Vector2 posInit;

    public Level(){

    }

    public Vector2 getPosInit(){
        return posInit;
    }

    public Moneda[] getMonedas(){
        return coins;
    }

    public Dialog[] getDialogs(){
        return dialogs;
    }

    public void writeDownFile(){
        try {
            FileOutputStream fos = new FileOutputStream(name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);

        }catch (FileNotFoundException ex){


        }catch (IOException ex){

        }

    }



}
