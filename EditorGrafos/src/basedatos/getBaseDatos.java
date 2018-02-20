/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package basedatos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase que transforma los datos obtenidos del editor de grafos para adaptarlos
 * a la entrada del simulador y del servidor.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class getBaseDatos {
    
    /**
     * Metodo main de la clase getBaseDatos.
     * Se len los datos obtenidos en la base de datos del editor de grafos y se
     * adaptan para guardarlos en la base de datos del servidor y de la 
     * simulacion.
     * @since incluido desde la version 1.0
     */
    public static void main(String[] args) throws IOException {
        
        //Copiamos la base de datos del EditorGrafos al directorio de la Simulacion
        copyFile();
        
        //Adaptamos la base de datos para el servidor
        FileWriter fichero = null;
        try {
            //Leemos los datos de entrada de un fichero con la base de datos
            FileReader file = new FileReader("src/Archivos/baseDatos.txt");
            
            Scanner File = new Scanner(file);
            String line;
            int from;
            int to;
            int nodoAutovia = 26; //Los nodos por debajo de este valor corresponden a la autovia.
            double distancia;
            double coste;
            double velocidad;
            double velocidadCiudad=40; //Velocidad media suponemos 40 km/h
            double velocidadAutovia=100; //Velocidad media suponemos 100 km/h
            String text = "";
            String text2 = "";
            
            boolean enlaces=false;
            
            while (File.hasNextLine()) {
                
                line = File.nextLine();
                if(line.contains("Enlaces")){
                    enlaces=true;
                    line = File.nextLine();  
                }
                if(enlaces){
                    StringTokenizer st = new StringTokenizer (line);
                    from=Integer.parseInt(st.nextToken());
                    to=Integer.parseInt(st.nextToken());
                    distancia=Double.parseDouble(st.nextToken());

                    if(from<nodoAutovia && to<nodoAutovia){
                        velocidad=velocidadAutovia;
                    }
                    else
                        velocidad=velocidadCiudad;
                    
                    coste = distancia*3600/(velocidad*1000); // el coste viene dado en segundos
                    text+=from +" " +to +" " +coste +"\n";
                    text2+=from +" " +to +" " +distancia +"\n";
                    //Hacemos las calles de doble sentido
                    text+=to +" " +from +" " +coste +"\n";
                    text2+=to +" " +from +" " +distancia +"\n";
                }   
            }
            fichero = new FileWriter("../ServidorRecomendador/src/java/baseDatos/baseDatos.txt"); 
            fichero.write(text);		
            fichero.close();
            
            fichero = new FileWriter("../ServidorRecomendador/src/java/baseDatos/grafo.txt"); 
            fichero.write(text2);		
            fichero.close();
            
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	}  
    }
    
    /**
     * Metodo que copia un archivo de un directorio a otro.
     * @since incluido desde la version 1.0
     */
    public static void copyFile() throws IOException {
        Path FROM = Paths.get("src/Archivos/baseDatos.txt");
        Path TO = Paths.get("../Simulacion/src/Archivos/baseDatos.txt");
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
}
