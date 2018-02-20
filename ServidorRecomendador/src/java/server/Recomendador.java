/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * ServidorRecomendador
 */
package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 * Clase que contiene elos metodos web del proyecto.
 * Dicha clase se encarga de definir los metodos web del servidor.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
@WebService(serviceName = "RecomendadorRutas")
public class Recomendador {    
    
    /**Variable: Contiene un objeto con el grafo usado por el recomendador de rutas basado en tiempo*/
    Graph g = new Graph( );
    /**Variable : Contiene un objeto con el grafo usado por el recomendador de rutas basado en distancia*/
    Graph g2 = new Graph( );
    
    /**
     * Web service operation.
     * Metodo que devuelve la ruta mas corta dado un nodo origen y un nodo destino. 
     * El grafo con los datos esta guardado en un objeto serializado, por lo que 
     * se obtendra mediante la carga de dicho objeto serializado. Posteriormente
     * se llamara al metodo calcular de la clase Dijkstra para obtener las rutas 
     * mas cortas desde el nodo origen al resto de los nodos. Finalmente se 
     * obtiene la ruta mas corta con respecto al nodo destino.
     * @param origen Nodo origen de la ruta.
     * @param destino Nodo origen de la destino.
     * @return Referencia a la ruta mas corta.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "obtenerRutaTiempo")
    public synchronized Vector<String> dijkstra(String origen, String destino) throws FileNotFoundException, IOException, ClassNotFoundException {

        //Crea un flujo de entrada para el fichero datos.dat y leemos el objeto serializado
        /*ObjectInputStream input = new ObjectInputStream(new FileInputStream("baseDatos.bin"));
        g = (Graph) input.readObject();
        input.close();*/

        Dijkstra.calcular(g,origen);
        
        Vector<String> ruta = new Vector<String>();
        ruta = g.getPath(destino, ruta);
        
        return ruta;
    }
    
    /**
     * Web service operation.
     * Metodo que devuelve la ruta mas corta dado un nodo origen y un nodo destino. 
     * El grafo con los datos esta guardado en un objeto serializado, por lo que 
     * se obtendra mediante la carga de dicho objeto serializado. Posteriormente
     * se llamara al metodo calcular de la clase Dijkstra para obtener las rutas 
     * mas cortas desde el nodo origen al resto de los nodos. Finalmente se 
     * obtiene la ruta mas corta con respecto al nodo destino.
     * @param origen Nodo origen de la ruta.
     * @param destino Nodo origen de la destino.
     * @return Referencia a la ruta mas corta.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "obtenerRutaDistancia")
    public synchronized Vector<String> dijkstra2(String origen, String destino) throws FileNotFoundException, IOException, ClassNotFoundException {

        //Crea un flujo de entrada para el fichero datos.dat y leemos el objeto serializado
        /* ObjectInputStream input = new ObjectInputStream(new FileInputStream("grafo.bin"));
        g2 = (Graph) input.readObject();
        input.close();*/

        Dijkstra.calcular(g2, origen);

        Vector<String> ruta = new Vector<String>();
        ruta = g2.getPath(destino, ruta);

        return ruta;
    }
    
    /**
     * Web service operation.
     * Metodo que actualiza el coste de un enlace entre dos nodos.
     * Dados un nodo origen, un nodo destino y el nuevo valor de coste entre ambos
     * nodos se actualizara el coste en el grafo. Se debe cargar el objeto serializado
     * actualizar el valor y volver a serializar el objeto.
     * @param origen Nodo origen de la ruta.
     * @param destino Nodo origen de la destino.
     * @param coste Coste del enlace.
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "actualizarRuta")
    public synchronized boolean actualizar(String origen, String destino, int coste) throws IOException, FileNotFoundException, ClassNotFoundException {

        //Crea un flujo de entrada para el fichero datos.dat y leemos el objeto serializado
        /*ObjectInputStream input = new ObjectInputStream(new FileInputStream("baseDatos.bin"));
        g = (Graph) input.readObject();
        input.close();*/

        //Actualizamos coste entre dos nodos
        g.updateEdge(origen, destino, coste);

        // Crea un flujo de salida para el fichero datos.dat y escribimos el objeto en el fichero
        /* ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("baseDatos.bin"));
        output.writeObject(g);
        output.close();*/

        return true;
    }
    
    /**
     * Web service operation.
     * Metodo que actualiza la base de datos. Dado un documento de texto con los 
     * datos los leera, creara una variable Graph con los datos de entrada y 
     * guardara el objeto serializandolo de forma que luego puedan acceder a el 
     * los diferentes metodos. Esto se hara para dos bases de datos, 
     * la correspondiente al grafo basado en tiempos y al grafo basado en distancias.
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "actualizarBaseDatos")
    public boolean actualizarBaseDatos() throws FileNotFoundException, IOException, ClassNotFoundException {

        //Leemos los datos de entrada de un fichero con la base de datos
        FileReader fin = new FileReader("/Users/danihbelan/Documents/I.T/4/2 Cuatrimestre/TFG/ProgramasTFG/ServidorRecomendador/src/java/baseDatos/baseDatos.txt");
        Scanner graphFile = new Scanner(fin);
        // Read the edges and insert
        String line;
        while (graphFile.hasNextLine()) {
            line = graphFile.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            try {
                if (st.countTokens() != 3) {
                    System.err.println("Skipping ill-formatted line " + line);
                    continue;
                }
                String source = st.nextToken();
                String dest = st.nextToken();
                double cost = Double.parseDouble(st.nextToken());
                g.addEdge(source, dest, cost);
            } catch (NumberFormatException e) {
                System.err.println("Skipping ill-formatted line " + line);
            }
        }
        // Crea un flujo de salida para el fichero baseDatos.bin y escribimos el objeto en el fichero
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("baseDatos.bin"));
        output.writeObject(g);
        output.close();
    
        
        //Leemos los datos de entrada de un fichero con la base de datos
        fin = new FileReader("/Users/danihbelan/Documents/I.T/4/2 Cuatrimestre/TFG/ProgramasTFG/ServidorRecomendador/src/java/baseDatos/grafo.txt");
        graphFile = new Scanner(fin);
        // Read the edges and insert
        
        while (graphFile.hasNextLine()) {
            line = graphFile.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            try {
                if (st.countTokens() != 3) {
                    System.err.println("Skipping ill-formatted line " + line);
                    continue;
                }
                String source = st.nextToken();
                String dest = st.nextToken();
                double cost = Double.parseDouble(st.nextToken());
                g2.addEdge(source, dest, cost);
            } catch (NumberFormatException e) {
                System.err.println("Skipping ill-formatted line " + line);
            }
        }
        // Crea un flujo de salida para el fichero baseDatos.bin y escribimos el objeto en el fichero
        output = new ObjectOutputStream(new FileOutputStream("grafo.bin"));
        output.writeObject(g2);
        output.close();
        
        return true;
    }
    
    /**
     * Web service operation.
     * Metodo que carga las bases de datos a partir de un objeto serializado.
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "cargarDatos")
    public boolean loadData() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        //Crea un flujo de entrada para el fichero datos.dat y leemos el objeto serializado
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("baseDatos.bin"));
        g = (Graph) input.readObject();
        input.close();
        
        //Crea un flujo de entrada para el fichero datos.dat y leemos el objeto serializado
        input = new ObjectInputStream(new FileInputStream("grafo.bin"));
        g2 = (Graph) input.readObject();
        input.close();
        
        return true;
    } 
    
    /**
     * Web service operation.
     * Metodo que guarda la base de datos en un objeto serializado.
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "gurdarDatos")
    public boolean saveData() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        // Crea un flujo de salida para el fichero datos.dat y escribimos el objeto en el fichero
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("baseDatos.bin"));
        output.writeObject(g);
        output.close();
        
        // Crea un flujo de salida para el fichero baseDatos.bin y escribimos el objeto en el fichero
        output = new ObjectOutputStream(new FileOutputStream("grafo.bin"));
        output.writeObject(g2);
        output.close();
        
        return true;
    }      
    
    /**
     * Web service operation.
     * Metodo que devuelve el coste en tiempo entre dos nodos.
     * @param origen Nodo origen de la ruta
     * @param destino Nodo destino de la ruta
     * @return Referencia al coste de la ruta
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "obtenerCosteTiempo")
    public int getCost(String origen, String destino) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        dijkstra(origen, destino);
        int coste = (int) g.cost;
        return coste;
    }   
    
    /**
     * Web service operation.
     * Metodo que devuelve el coste en tiempo entre dos nodos.
     * @param origen Nodo origen de la ruta
     * @param destino Nodo destino de la ruta
     * @return Referencia al coste de la ruta
     * @since incluido desde la version 1.0
     */
    @WebMethod(operationName = "obtenerCosteDistancia")
    public int getCost2(String origen, String destino) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        dijkstra2(origen, destino);
        int coste = (int) g2.cost;
        return coste;
    } 
}
