/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */

package data;

import controller.Controller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ClassNotFoundException_Exception;
import server.FileNotFoundException_Exception;
import server.IOException_Exception;

/**
 * Clase que implementa la interfaz Runnable.
 * Dicha clase se encarga de la hebra que controla un vehiculo cliente
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class VehicleThreadClient implements Runnable{
    
    /**Variable privada: Contiene el objeto controlador del proyecto*/
    private Controller controller;
    /**Variable privada: Valor del delay*/
    private final int delay=500;
    /**Variable privada: Valor del coste*/
    private int cost;
    /**Variable privada: Valor del offset*/
    private int offset;
    /**Variable privada: Define que tipo de recomendador usa*/
    boolean recomendador;
    /**Variable privada: Define si la hebra ha terminado*/
    private boolean continuar=true;
    /**Variable privada: Contien el estado de la hebra*/
    private boolean suspendFlag=true;
    /**Variable privada: Nodo origen*/
    private Node from;
    /**Variable privada: Nodo destino*/
    private Node to;
    /**Variable privada: Siguiente nodo en la ruta*/
    private Node next;
    /**Variable privada: Identificador de la hebra*/
    private String label;
    /**Variable privada: Coordenada x donde se encuentra el vehiculo*/
    private double coorx;
    /**Variable privada: Coordenada y donde se encuentra el vehiculo*/
    private double coory;
   
    /**
     * Constructor VehicleThread.
     * Crea un nuevo objeto VehicleThread.
     */
    public VehicleThreadClient(Node f, Node d, boolean r, Controller c){
        from=f;
        to=d;
        recomendador=r;
        controller=c;
        Thread t=new Thread(this);
        t.start();
    }
    
    /**
     * Metodo que se encarga del movimiento del vehiculos clinte. 
     * Tiene en cuenta el origen, el destino y la velocidad a la que debe ir en 
     * cada momento.
     * @since incluido desde la version 1.0
     */
    public void run(){
        try{
           
            while(continuar){
                              
                //Obtenemos el siguiente nodo de la ruta y el coste del tramo.
                getNext();
                coorx = from.getX();
                coory = from.getY();
                for(int i=0; i<offset; i++){
                    synchronized(this){
                        while(suspendFlag){
                            wait();
                        }
                    }  
                    //Esperamos el tiempo de retardo que simula lo que tardaria en recorer el vehiculo dicho tramo
                    Thread.sleep(delay);
                    setXY();                    
                    controller.getFrame().repaint();  
                }             
                //Actualizamos el coste del tramo que acabamos de recorer
                if(recomendador)
                    actualizar();   
                //Avanzamos al siguiente nodo, el cual pasa a ser el nodo origen 
                from=next;

                //Definir cuando se tiene que terminar la hebra
                if(from.equals(to)){
                    terminar();
                    //Indicamos que ha acabado
                    if(recomendador)
                        controller.getFrame().setClient1(false);
                    else
                        controller.getFrame().setClient2(false);
                    
                    controller.getFrame().repaint();
                }
            }// end while(continuar)        
        } catch(InterruptedException e){
            System.out.println("Hilo VehiculoThread interrumpido");
        } catch (ClassNotFoundException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Metodo que devuelve la coordenada x de la posicion actual del vehiculo.
     * @return Referencia a una variable double.
     * @since incluido desde la version 1.0
     */ 
    public double getX(){
        return coorx;
    }
    
    /** Metodo que devuelve la coordenada y de la posicion actual del vehiculo.
     * @return Referencia a una variable double.
     * @since incluido desde la version 1.0
     */
    public double getY(){
        return coory;
    }
    
    /** Metodo que actualiza las coordenadas en las que se encuentra el vehiculo.
     * @since incluido desde la version 1.0
     */
    public void setXY(){
        coorx -= (coorx-next.getX())/offset;
        coory -= (coory-next.getY())/offset;
    }
    
    /** Metodo que devuelve el identificador del vehiculo.
     * @return Referencia a una variable double.
     * @since incluido desde la version 1.0
     */
    public String getLabel(){
        return label;
    }
    
    /** Metodo que obtiene el siguiente nodo al que debe dirigirse el vehiculo.
     * Se define tambien el coste y el offset que se tiene en ese tramo
     * @since incluido desde la version 1.0
     */
    private void getNext() throws ClassNotFoundException_Exception, FileNotFoundException_Exception, IOException_Exception {
        //Definir siguiente nodo
        String origen=from.getLabel();
        String destino=to.getLabel();
        List<String> ruta;
        
        if(recomendador)
            ruta = obtenerRutaTiempo(origen,destino);
        else
            ruta = obtenerRutaDistancia(origen,destino);
        
        int siguiente = Integer.parseInt(ruta.get(1));
        next = controller.getModel().getGraph().getNodeList().get(siguiente);
        cost = controller.getModel().getGraph().getCost(from,next);
        offset = cost/delay;
    }
    
    /** Metodo que manda el coste que ha tenido en recorer un enlace al servidor.
     * @since incluido desde la version 1.0
     */
    private void actualizar() throws ClassNotFoundException_Exception, FileNotFoundException_Exception, IOException_Exception {
        String origen = from.getLabel();
        String destino = next.getLabel();
        int coste = cost/1000;  //Convertir coste de milesegundos a segundos
        actualizarRuta(origen,destino,coste);  
    }
    
    /** Metodo que devuelve el nodo origen del vehiculo.
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public Node getOrigen(){
        return from;
    }
    
    /** Metodo que devuelve el nodo siguiente al que se dirigue el vehiculo.
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public Node getDestino(){
        return next;
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

    /** Metodo web que se comunca con el servidor Recomendador para obtener 
     * la ruta de menor coste teniendo en cuenta el tiempo.
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @return Referencia a una variable Ruta.
     * @since incluido desde la version 1.0
     */
    private static java.util.List<java.lang.String> obtenerRutaTiempo(java.lang.String arg0, java.lang.String arg1) throws ClassNotFoundException_Exception, FileNotFoundException_Exception, IOException_Exception{
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();

        return port.obtenerRutaTiempo(arg0, arg1);   
    }

    /** Metodo web que se comunica con el servidor Recomendador para obtener 
     * la ruta de menor coste teniendo en cuenta la distancia.
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @return Referencia a una variable Ruta.
     * @since incluido desde la version 1.0
     */
    private static java.util.List<java.lang.String> obtenerRutaDistancia(java.lang.String arg0, java.lang.String arg1) throws ClassNotFoundException_Exception, IOException_Exception, FileNotFoundException_Exception {
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();
        return port.obtenerRutaDistancia(arg0, arg1);
    }
    
    /** Metodo web que se comunica con el servidor Recomendador para actualizar 
     * el coste de un enlace
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @param arg2 Variable referente al coste
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    private static boolean actualizarRuta(java.lang.String arg0, java.lang.String arg1, int arg2) throws ClassNotFoundException_Exception, FileNotFoundException_Exception, IOException_Exception {
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();
        
        try{
            return port.actualizarRuta(arg0, arg1, arg2);      
        }
        finally {
            return true;
        }
       
    }
}
