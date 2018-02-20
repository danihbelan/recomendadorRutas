/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * ObtencionResultados
 */

package obtencionresultados;

import java.io.IOException;
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
    
    /**Variable privada: Valor del coste*/
    private int cost;
    /**Variable privada: Valor del coste*/
    private int coste1;
    /**Variable privada: Valor del coste*/
    private int coste2;
    /**Variable privada: Valor del offset*/
    private boolean continuar=true;
    /**Variable privada: Contien el estado de la hebra*/
    private boolean suspendFlag=true;
    /**Variable privada: Nodo origen*/
    private String from;
    /**Variable privada: Nodo origen*/
    private String from1;
    /**Variable privada: Nodo origen*/
    private String from2;
    /**Variable privada: Nodo destino*/
    private String to;
    /**Variable privada: Siguiente nodo en la ruta*/
    private String next;
    /**Variable privada: Identificador de la hebra*/
    private String label;
    
   
    /**
     * Constructor VehicleThread.
     * Crea un nuevo objeto VehicleThread.
     */
    public VehicleThreadClient(String f, String d, String l){
        from=f;
        to=d;
        label=l;
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
           
            from1=from;
            coste1=0;
            from2=from;
            coste2=0;
            
            while(continuar){               
                //Obtenemos el siguiente nodo de la ruta y el coste del tramo.
               
                List<String> ruta = obtenerRutaTiempo(from1,to);
                next = ruta.get(1);
                cost = obtenerCosteTiempo(from1,next);
                
                //Actualizamos coste total de la ruta
                coste1+=cost;
                
                //Avanzamos al siguiente nodo, el cual pasa a ser el nodo origen 
                from1=next;
                
                //Definir cuando se tiene que terminar la hebra
                if(from1.equals(to)){
                    continuar=false;
                }
            }// end while(continuar)   
            
            continuar =true;
            while(continuar){               
                //Obtenemos el siguiente nodo de la ruta y el coste del tramo.
               
                List<String> ruta = obtenerRutaDistancia(from2,to);
                next = ruta.get(1);
                cost = obtenerCosteTiempo(from2,next);
                
                //Actualizamos coste total de la ruta
                coste2+=cost;
                
                //Avanzamos al siguiente nodo, el cual pasa a ser el nodo origen 
                from2=next;
                
                //Definir cuando se tiene que terminar la hebra
                if(from2.equals(to)){
                    continuar=false;
                }
            }// end while(continuar)   
            
            String resultado =(coste1 +" " +coste2);
            int diferencia = coste2-coste1;
            
            //Guardar en un fichero
            if(coste2-coste1>10)
                ObtencionResultados.guardarResultados(coste1,coste2);
            
        } catch (ClassNotFoundException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VehicleThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    

    /** Metodo web que se comunca con el servidor Recomendador para obtener 
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

     /** Metodo web que se comunca con el servidor Recomendador para obtener 
     * la ruta el coste en tiempo entre dos nodos.
     * @param arg0 Variable referente al nodo origen
     * @param arg1 Variable referente al nodo destino
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    private static int obtenerCosteTiempo(java.lang.String arg0, java.lang.String arg1) throws FileNotFoundException_Exception, IOException_Exception, ClassNotFoundException_Exception {
        server.RecomendadorRutas service = new server.RecomendadorRutas();
        server.Recomendador port = service.getRecomendadorPort();
        return port.obtenerCosteTiempo(arg0, arg1);
    }
    
}
