/*
 * Save.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Crea un archivo de texto plano para guardar los datos del juego.
 * 
 */

package com.piercystudio.handlers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import java.io.File;

public class Save {
	
	public static GameData gd;
	
	public static void save(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("savefile.op")
			);
			out.writeObject(gd);
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static void load(){
		try{
			if(!saveFileExists()){
				init();
				return;
			}
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("savefile.op")
			);
			gd = (GameData) in.readObject();
			in.close();
		} catch (Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static boolean saveFileExists(){
		File f = new File("savefile.op");
		return f.exists();
	}
	
	public static void init(){
		gd = new GameData();
		gd.init();
		save();
	}

}
