/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */

package data;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulacion.Simulacion;

/**
 * Clase que implementa la interfaz Runnable.
 * Dicha clase se encarga de la hebra que controla la creacion de nuevas hebras
 * correspondientes a los vehiculos simualdos.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class SimulationThread implements Runnable{
    /**Variable privada: Objeto que contiene el controlador del proyecto*/
    private Controller controller;
    /**Variable privada: Define si la hebra ha terminado*/
    private boolean continuar=true;
    /**Variable privada: Contiene el estado de la hebra*/
    private boolean suspendFlag=true;

    /**
     * Constructor SimulationThread.
     * Crea un nuevo objeto SimulatorThread.
     */
    public SimulationThread(Controller c){
        controller = c;
        Thread t=new Thread(this);
        t.start();
    }
    
    /**
     * Metodo que se encarga de la creacion de las hebras correspondientes a los
     * vehiculos. 
     * @since incluido desde la version 1.0
     */
    public void run() {
        int identificador = 0;
        int delay;
        Node origen;
        Node destino;
        continuar = true;

        while (continuar) {
            synchronized(this){
                while(suspendFlag){
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SimulationThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }  
            
            origen = getNode(0.4);
            destino = getNode(0.8);

            while (destino == origen) {
                destino = getNode(0.8);
            }

            //Creamos una nueva hebra y la inicializamos 
            VehicleThread vehiculo = new VehicleThread(origen, destino, String.valueOf(identificador++), controller);
            vehiculo.reanudar();
            //La añadimos a la lista de vehiculos
            controller.getModel().getGraph().addVehicle(vehiculo);

            //Definir el tiempo cada cuanto se genera un vehiculo
            delay = controller.getFrame().getTiempoGeneracion();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodo que devuelve un nodo del grafo para definir el origen y el destino
     * de una ruta. Se obtiene de forma aleatoria aunque se condicionaran
     * algunos nodos a tener mayor probabilidad de ser un nodo origen o destino.
     * @param p variable que contiene la probabilidad p
     * @return Referencia a un objeto Nodo.
     * @since incluido desde la version 1.0
     */
    private Node getNode(double p) {
        //Definir los nodos con mayor probabilidad de ser nodos origen o destino
        int[] nodosProbables = {37,46,60,211,316,256,245,
                                243,73,326,225,229};

        int label;
        int nAleatorio;
        
        //El porcentaje p de los destinos seran a los nodos que definamos como mas probables
        //Descartamos los nodos correspondientes a la autovia (del 0 al 25)
        if (Math.random() < p) {
            nAleatorio = (int) (Math.random() * nodosProbables.length);
            label = nodosProbables[nAleatorio];
        } else {
            int nNodos = controller.getModel().getGraph().getNodeList().size() -1;
            label = (int) (Math.random()*(nNodos-26+1) + 26);  
        }

        Node node = controller.getModel().getGraph().getNodeList().get(label);
        return node;
    }
    
    /**
     * Detiene momentaneamente la ejecución de la hebra.
     * @since incluido desde la version 1.0
     */
    synchronized public void suspender(){
        suspendFlag=true;
    }
    
    /**
     * Reanuda el movimiento de la hebra.
     * @since incluido desde la version 1.0
     */
    public synchronized void reanudar(){
        suspendFlag = false;
        notify();
    }
    
    /**
     * Termina la ejecución de la hebra.
     * @since incluido desde la version 1.0
     */
    public void terminar(){
        continuar=false;
    }
}
