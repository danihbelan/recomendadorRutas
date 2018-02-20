/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package data;

/**
 * Clase que contiene el objeto Node del proyecto.
 * Dicha clase se encarga de definir el objeto y los metodos correspondientes a
 * los nodos.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Node {
    
    /**Variable privada: Coordenada x del nodo*/
    private int coorx;
    /**Variable privada: Coordenada y del nodo*/
    private int coory;
    /**Variable privada: Identificador del nodo*/
    private String label;
    
    /**
     * Constructor Node.
     * Crea un nuevo objeto Node.
     */
    public Node(int x, int y, String l){
        coorx=x;
        coory=y;
        label=l;
    }
    
    /**
     * Metodo que devuelve la coordenada x del nodo.
     * @return Referencia a una variable entera.
     * @since incluido desde la version 1.0
     */ 
    public int getX(){
        return coorx;
    }
    
    /**
     * Metodo que devuelve la coordenada y del nodo.
     * @return Referencia a una variable entera.
     * @since incluido desde la version 1.0
     */ 
    public int getY(){
        return coory;
    }
    
    /**
     * Metodo que actualiza las coordenadas del nodo.
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */ 
    public void setXY(int x, int y){
        coorx = x;
        coory = y;
    }
    
    /**
     * Metodo que devuelve la etiqueta que identifica el nodo.
     * @return Referencia a una variable String.
     * @since incluido desde la version 1.0
     */
    public String getLabel(){
        return label;
    }
    
    /**
     * Metodo que actualiza la etiqueta del nodo.
     * @param l Variable referente a la etiqueta
     * @since incluido desde la version 1.0
     */
    public void setLabel(String l){
        label=l;
    }
    
    /** Metodo que crea el texto salida con los datos correspondientes a dicho
     * nodo.
     * @return Referencia a una variable String
     * @since incluido desde la version 1.0
     */
    public String toString(){
        String string = label+" "+String.valueOf(coorx)+" "+String.valueOf(coory);
        return string;
    }
}
