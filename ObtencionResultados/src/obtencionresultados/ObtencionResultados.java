/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * ObtencionResultados
 */
package obtencionresultados;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danihbelan
 */
public class ObtencionResultados {

    /**
     * Metodo que contiene el main del programa.
     * Se encarga de crear las hebras y lanzarlas.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int delay;
        String origen;
        String destino;
        int i = 0;
        int numeroIteraciones=200;

        //Borramos los datos anteriores
        //borrarResultados();
        
        while (i<numeroIteraciones) {
            i++;
            origen = String.valueOf((int) (Math.random()*(320-27)+27)); 
            destino = String.valueOf((int) (Math.random()*(320-27)+27));
            while (destino == origen) {
                destino = String.valueOf(Math.random()*320);
            }
            System.out.println(origen +" " +destino);
            //Creamos una nueva hebra y la inicializamos 
            VehicleThreadClient vehiculo = new VehicleThreadClient(origen, destino, String.valueOf(i));
        }
    }
    
    /**
     * Metodo que se encarga de guardar los resultados en los ficheros correspondientes.
     * Se encarga de crear las hebras y lanzarlas.
     * @param coste1 Valor que contiene el coste de la ruta obtenida con el recomendador
     * @param coste2 Valor que contiene el coste de la ruta basada en distancia
     */
    public static synchronized void guardarResultados(int coste1, int coste2) throws IOException{
            //Guardamos los datos en un fichero de texto
        FileWriter fichero = null;
        try{
            fichero = new FileWriter("src/Archivos/coste1.txt", true); 
            fichero.write(coste1+"\n");		
            fichero.close();
            
            
            fichero = new FileWriter("src/Archivos/coste2.txt",true); 
            fichero.write(coste2+"\n");		
            fichero.close();
            
            fichero = new FileWriter("src/Archivos/diferencias.txt",true); 
            fichero.write((coste2-coste1)+"\n");		
            fichero.close();
            
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	} 
    }
}
