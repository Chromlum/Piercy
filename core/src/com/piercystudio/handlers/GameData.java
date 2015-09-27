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

public class GameData implements Serializable{
	
	private static final long serialVersionUID = 1;
	public boolean firstRun;
	
	public int currentLevel = 0;
	public int exp;
	
	public GameData(){
		firstRun = true;
	}
	
	public void init(){
		
		/* Empezar juego en primer nivel */
		currentLevel = 1;
		exp = 0;
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
	
	

}
