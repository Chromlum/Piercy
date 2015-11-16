
/*
 * Jugador.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
 * Fecha: 16/09/15
 * Descripccion: Clase que representa un sprite del jugador principal (Piercy)
 *
 * 
 */
package com.piercystudio.entities;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.piercystudio.PiercyGame;

public class Jugador extends PiercyObject{

    // Atributos
    private float distancia;
    private Queue<Movimientos> actionQueue; // Cola de acciones
    private boolean actividad;  // Bandera de actividad
    private boolean hasFinished;  // Bandera de ocupado
    private boolean dead;
    private TiledMapTileLayer layer;
    private boolean face;
    float dt;
    Movimientos action;

    /**
     * Clasificacion de los movimientos
     */
    public enum Movimientos{
        DERECHA,
        IZQUIERDA,
        BRINCAR,
        DESTRUIR,
        BRINCARD,
        BRINCARI
    }


    /**
     * Constructor
     * @param sprite Sprite con la textura del jugador
     * @param mapa Mapa de tipo Tile
     */
	public Jugador(Sprite sprite, TiledMap mapa) {
		super(sprite);
        face = false;
        dt = 0;
        layer =(TiledMapTileLayer) mapa.getLayers().get(1);

        // Texturas de las animaciones
        Texture tex = PiercyGame.res.getImage("player");
        TextureRegion[] sprites = new TextureRegion[4];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new TextureRegion(tex, i * 32 + 5, 0, 20, 32);
        }

        // Inicializacion de animacion
        animation = new Animation(1f/4f, sprites);


        // Inicializacion de cola principal de acciones
        actionQueue = new LinkedList<Movimientos>();
        actividad = false;
        hasFinished = true;
        setPosition(25, 110);
        setSize(30, 50);
    }

    /**
     * Metodo que dibuja al jugador
     * @param batch Objeto que dibuja
     */
	@Override
	public void draw(Batch batch) {
        setRegion(animation.getKeyFrame(dt, true));
        flip(face, false);
        super.draw(batch);
	}

    /**
     * Metodo especial para realizar todos los calculos del jugador
     * colisiones, cambios de velocidad, etc.
     * @param dt
     */
	@Override
	public void update(float dt) {

        // Gravedad
        velocidad.y -= g * dt;

        // Limitacion de la velocidad en el eje Y
        if(velocidad.y > velocidadMax){
            velocidad.y = velocidadMax;
        }else if(velocidad.y < -velocidadMax){
            velocidad.y = -velocidadMax;
        }

        // Manejo de cola prinicpal
        if (actividad){
            if (hasFinished){
                action = actionQueue.poll();
                if (action != null){
                    switch (action){
                        case DERECHA: {
                            face = false;
                            velocidad.x = velocidadMax * 0.5f;
                        }break;
                        case IZQUIERDA: {
                            face = true;
                            velocidad.x = -velocidadMax * 0.5f;
                        }break;
                        case BRINCAR: {
                            velocidad.y = velocidadMax;
                        }break;
                        case BRINCARD:{
                            face = false;
                            velocidad.y = velocidadMax;
                            velocidad.x = velocidadMax * 0.5f;
                        }break;
                        case BRINCARI:{
                            face = true;
                            velocidad.y = velocidadMax;
                            velocidad.x = -velocidadMax * 0.5f;
                        }break;
                        default:
                            break;
                    }
                    hasFinished = false;
                }else{
                    actividad = false;
                    hasFinished = true;
                }
            }

        }

        // Tiempo delta para realizar las animaciones de caminado
        if(!hasFinished){
            this.dt += dt;
        }

        // Coordenadas actualez
        float xA = getX();
        float yA = getY();

        // Distancia en el eje x
        distancia += Math.abs(velocidad.x * dt);

        // Mover el jugador
        setX(getX() + velocidad.x * dt);
        setY(getY() + velocidad.y * dt);

        // Colisiones en el eje x
        if(collidesXRight() || collidesXLeft()){
            setX(xA);
            distancia = 0;
            velocidad.x = 0;
            if(!hasFinished)
                hasFinished = true;
        }

        // Colisiones en el eje Y
        if(collidesYBot() || collidesYTop()) {
            setY(yA);
            velocidad.y = 0;
            if(action == Movimientos.BRINCARD || action == Movimientos.BRINCARI || action == Movimientos.BRINCAR) {
                velocidad.x = 0;
                if (!hasFinished)
                    hasFinished = true;
            }
        }

        // Movimientos en el eje x
        if((action == Movimientos.DERECHA || action == Movimientos.IZQUIERDA) && distancia > 32){
            distancia = 0;
            velocidad.x = 0;
            hasFinished = true;
        }

        // Validacion de muerte
        if(getY() < 0){
            dead = true;
        }
	}

    /**
     * Getter para obtener una celda del TileMap
     * @param x coordenada x
     * @param y coordeanada y
     * @return Existe colision
     */
    public boolean getCell(float x, float y){
        Cell celda = layer.getCell((int)(x / layer.getTileHeight()), (int)(y / layer.getTileHeight()) );
        return celda != null && celda.getTile() != null && celda.getTile().getProperties().containsKey("blocked");
    }

    /**
     * Colision en el eje y-abajo
     * @return
     */
	public boolean collidesYBot(){
        for(float particula = 10f; particula < getWidth(); particula += 0.1){
            if(getCell(getX() + particula, getY()))
                return true;
        }
        return false;
    }

    /**
     * Colision en el eje y-arriba
     * @return existe colision
     */
    public boolean collidesYTop(){
        for(float particula = 10f; particula < getWidth(); particula += 0.1){
            if(getCell(getX() + particula, getY() + getHeight()))
                return true;
        }
        return false;
    }

    /**
     * Colision en el eje x izquierda
     * @return existe colision
     */
    public boolean collidesXLeft(){
        for(float particula = 2f; particula < getHeight(); particula += 0.1){
            if(getCell(getX(), getY() + particula))
                return true;
        }
        return false;
    }

    /**
     * Colision en el eje x derecha
     * @return Existe colision
     */
    public boolean collidesXRight(){
        for(float particula = 2f; particula < getHeight(); particula += 0.1){
            if(getCell(getX() + getWidth(), getY() + particula))
                return true;
        }
        return false;
    }

    /**
     * Meotodo para obtener una colision con una moneda
     * @return
     */
    public boolean colisionMoneda(){
        Cell celda = layer.getCell((int)((getX() + getWidth() / 2f) / layer.getTileHeight()),
                (int)((getY() + getHeight() / 2f ) / layer.getTileHeight()) );
        if(celda != null && celda.getTile() != null && celda.getTile().getProperties().containsKey("coin")){
            celda.setTile(null);
            return true;
        }else return false;
    }


    /**
     * Si el personaje esta realizando una accion
     * @return
     */
    public boolean isActive(){
        return actividad;
    }

    /**
     * Aniadir accion al jugador
     * @param s Tipo de movimiento
     */
    public void addAction(Movimientos s){
        actionQueue.add(s);
    }

    /**
     * Activar el estado de trabajo
     * @param activo
     */
    public void setActive(boolean activo){
        actividad = activo;
    }

    /**
     * Si esta muerto el jugador
     * @return
     */
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Cambiar direccion a la que mira el jugador
     * @param izquierda
     */
    public void setFacing(boolean izquierda){
        face = izquierda;
    }

}
