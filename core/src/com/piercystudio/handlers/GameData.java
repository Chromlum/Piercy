/*
 * GameData.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo
 * 16/09/15
 * 
 * Guarda datos del juego.
 * 
 */

package com.piercystudio.handlers;

import java.io.Serializable;
import java.util.HashMap;

public class GameData implements Serializable{
	
	private static final long serialVersionUID = 1;
	public boolean firstRun;
	
	public int currentLevel = 0;
	public int exp;
	public int errores;
	public String resultado;
	
	public HashMap<Integer, Integer>lvlErrors;
	
	public GameData(){
		firstRun = true;
	}
	
	public void init(){
		
		/* Empezar juego en primer nivel */
		currentLevel = 1;
		exp = 0;
		errores = 0;
	}
	
	public int getCurrentLevel(){
		return currentLevel; 
	}
	
	public void setCurrentLevel(int level){
		currentLevel = level;
	}
	
	public boolean getFirstRun(){
		return firstRun;
	}
	
	public void addError(int level){
		errores += 1;
		lvlErrors.put(level, lvlErrors.get(level)+1);
	}
	
	public int getError(){
		return this.errores;
	}
	
	public void setExp(int exp){
		this.exp += exp;
	}
	
	public int getExp(){
		return this.exp;
	}
	
	public String getResultado(){
		if(exp > errores){
			resultado = "Buen trabajo.";
		}else if(errores > exp){
			resultado = "Necesita practicar.";
		}else if(exp == errores){
			resultado = "No hay progreso.";
		}
		return resultado;
	}

}
