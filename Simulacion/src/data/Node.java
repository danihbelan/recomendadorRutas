/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
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
     * Metodo que devuelve la etiqueta que identifica el nodo.
     * @return Referencia a una variable String.
     * @since incluido desde la version 1.0
     */    
    public String getLabel(){
        return label;
    } 
}
