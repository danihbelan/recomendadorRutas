/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */
package data;

/**
 * Clase que contiene el objeto Model del proyecto.
 * Dicha clase contiene el estado actual de los datos. En este caso contiene 
 * una referencia al objeto Graph del proyecto.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Model {
    
    /**Variable privada: Contiene elobjeto con el grafo del proyecto*/
    private Graph graph;
    /**Variable privada: hebra que se encarga de controlar la simulacion*/
    private SimulationThread simulationThread;
    
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
     * Metodo que guarda la referencia de la hebra simulationThread en el modelo
     * @param s variable referente a la hebra SimulationThread
     * @since incluido desde la version 1.0 
     */
    public void setSimulationThread(SimulationThread s){
       simulationThread = s;
    }
    
    /**
     * Metodo que devuelve el objeto referente a la hebra simulationThread
     * @return Referencia a una variable SimulationThread.
     * @since incluido desde la version 1.0 
     */
    public SimulationThread getSimulationThread(){
        return simulationThread;
    }
}