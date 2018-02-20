/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */
package controller;

import simulacion.Simulacion;
import data.*;
import server.ClassNotFoundException_Exception;
import server.FileNotFoundException_Exception;
import server.IOException_Exception;

/**
 * Clase que contiene el objeto Controller del proyecto.
 * Dicha clase actua de mediador entre las clases que contienen el interfaz 
 * grafico y la clase que contiene el modelo del proyecto.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Controller {
    
    /**Variable privada: Contiene el objeto con el frame del interfaz grafico*/
    private Simulacion frame;
    /**Variable privada: Contiene el objeto con el modelo del proyecto*/
    private Model model;
    /**Variable privada: Estado de la simulacion*/
    private boolean pausa=false;
    
    /**
     * Constructor Controller.
     * Crea un nuevo objeto Controller.
     */
    public Controller(Model m) {
        model = m;
    }
   
    /**
     * Metodo que reinicia la simulacion.
     * Borra todos los datos actuales y crea y lanza una nueva hebra 
     * setSimulationThread.
     * @since incluido desde la version 1.0
     */
    public void resetSimulation() {
        //Teminamos la hebra actual, si la hay
        if(model.getSimulationThread()!=null)
            model.getSimulationThread().terminar();
        
        //Borra todos los datos de la actual simulacion
        model.getGraph().getVehicleList().clear();  
        
        //Crea una nueva hebra la guarda en el modelo
        SimulationThread simulation = new SimulationThread(this);
        simulation.reanudar();
        model.setSimulationThread(simulation);
    }
    
    /**
     * Metodo que pausa o reanuda la simulacion.
     * @since incluido desde la version 1.0
     */
    public void pauseSimulation() {
        if (pausa) {
            pausa = false;
            //Ponemos todas las hebras a pausa
            model.getSimulationThread().reanudar();
            for (VehicleThread vehicleThread : model.getGraph().getVehicleList()) {
                vehicleThread.reanudar();
            }
            if(model.getGraph().getVehicle1()!=null){
                model.getGraph().getVehicle1().reanudar();
                model.getGraph().getVehicle2().reanudar();
            }
        } else {
            pausa = true;
            //Ponemos todas las hebras a pausa
            model.getSimulationThread().suspender();
            for (VehicleThread vehicleThread : model.getGraph().getVehicleList()) {
                vehicleThread.suspender();
            }
            if(model.getGraph().getVehicle1()!=null){
                model.getGraph().getVehicle1().suspender();
                model.getGraph().getVehicle2().suspender();
            }
        }  
    }
    
    /**
     * Metodo que guarda una referencia al objeto frame.
     * @param f Variable referente al frame
     * @since incluido desde la version 1.0
     */
    public void setFrame(Simulacion f) {
        frame = f;
    }
    
    /**
     * Metodo que devuelve el objeto referente al frame del proyecto
     * @return Referencia a una variable Simulacion.
     * @since incluido desde la version 1.0
     */
    public Simulacion getFrame(){
        return frame;
    }
    
    /**
     * Metodo que devuelve el objeto referente al modelo del proyecto
     * @return Referencia a una variable Model.
     * @since incluido desde la version 1.0
     */
    public Model getModel(){
        return model;
    }

    /** Metodo web que se comunica con el servidor Recomendador para actualizar 
     * el coste de un enlace
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @param arg2 Variable referente al coste
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    public static boolean actualizarRuta(java.lang.String arg0, java.lang.String arg1, int arg2) throws FileNotFoundException_Exception, ClassNotFoundException_Exception, IOException_Exception {
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();
        return port.actualizarRuta(arg0, arg1, arg2);
    }

    /** Metodo web que se comunca con el servidor Recomendador para obtener 
     * el coste de tiempo de un enlace
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    public static int obtenerCosteTiempo(java.lang.String arg0, java.lang.String arg1) throws IOException_Exception, ClassNotFoundException_Exception, FileNotFoundException_Exception {
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();
        return port.obtenerCosteTiempo(arg0, arg1);
    }
    
}
