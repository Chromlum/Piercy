/*
 * Save.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
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
	
	public static GameData gd; //Objeto que contiene datos del juego
	
	/* Guardar datos en objeto y luego guardar objeto en archivo de texto plano */
	public static void save(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("savefile.op") // Crea el savefile del juego
			);
			out.writeObject(gd); // escribe el objeto en el archivo
			out.close();
		} catch (Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	/* Carga el archivo de texo y luego carga el objeto con los datos */
	public static void load(){
		try{
			/* Si no existe el archivo, lo crea */
			if(!saveFileExists()){
				init();
				return;
			}
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("savefile.op") // carga archivo
			);
			gd = (GameData) in.readObject(); // cast a tipo GameData de lo que lea en el archivo
			in.close();
		} catch (Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	/* Revisa si existe archivo */
	public static boolean saveFileExists(){
		File f = new File("savefile.op");
		return f.exists();
	}
	
	/* Si no existe el archivo se crea con este metodo */
	public static void init(){
		gd = new GameData(); // nuevo objeto para guardar datos
		gd.init(); // inicializo el objeto que guarda los datos
		save(); // guardo el archivo
	}

}
