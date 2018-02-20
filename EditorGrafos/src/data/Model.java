/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package data;

/**
 * Clase que contiene el objeto Model del proyecto.
 * Dicha clase contiene el estado actual de los datos. En este caso contiene 
 * una referencia al objeto Graph del proyecto y al nodo qu esta seleccionado.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Model {
    
    /**Variable privada: Objeto que contiene el grafo del proyecto*/
    private Graph graph;
    /**Variable privada: Nodo seleccionado en el grafo*/
    private Node selectedNode=null;
    
    /**
     * Constructor Model.
     * Crea un nuevo objeto Model.
     */
    public Model(){
        graph = new Graph();
    }
    
    /**
     * Metodo que devuelve el objeto referente al grafo del proyecto
     * @return Referencia a una variable Graph.
     * @since incluido desde la version 1.0 
     */
    public Graph getGraph(){
        return graph;
    }
    
    /**
     * Metodo que devuelve una referencia al nodo seleccionado
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0 
     */
    public Node getSelectedNode(){
        return selectedNode;
    }
    
    /**
     * Metodo que actualiza la variable referente al nodo seleccionado
     * @param n Variable referente al nodo seleccionado
     * @since incluido desde la version 1.0 
     */
    public void setSelectedNode(Node n){
        selectedNode = n;
    }   
}