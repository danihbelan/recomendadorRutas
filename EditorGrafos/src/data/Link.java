/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package data;

/**
 * Clase que contiene el objeto Link del proyecto.
 * Dicha clase se encarga de definir el objeto y los metodos correspondientes a
 * los enlaces.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Link {
    
    /**Variable privada: Nodo origen*/
    private Node from;
    /**Variable privada: Nodo destino*/
    private Node to;
    /**Variable privada: Distancia del enlace*/
    private double distance;
    
    /**
     * Constructor Link.
     * Crea un nuevo objeto Link.
     */
    public Link(Node f, Node t){
        from=f;
        to=t;
        distance = Graph.getDistance(from.getX(),from.getY(),to.getX(),to.getY());
    }
    
    /**
     * Metodo que devuelve el nodo origen del enlace.
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public Node getFrom(){
        return from;
    }
    
    /**
     * Metodo que devuelve el nodo destino del enlace.
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public Node getTo(){
        return to;
    }
    
    /**
     * Metodo que devuelve la distancia del enlace.
     * @return Referencia a una variable double.
     * @since incluido desde la version 1.0
     */
    public double getDistance(){
        return distance;
    }
    
    /** Metodo que crea el texto salida con los datos correspondientes a dicho
     * enlace.
     * @return Referencia a una variable String
     * @since incluido desde la version 1.0
     */
    public String toString(){
        String string = from.getLabel()+" "+to.getLabel() +" "+String.valueOf(distance);
        return string;
    }
}
