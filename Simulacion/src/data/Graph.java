/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */
package data;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase que contiene el Grafo del proyecto.
 * Dicha clase se encarga de dibujar el mapa y los diferentes componentes.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Graph {
    
    /**Variable privada: Lista de nodos que forman el grafo*/
    private List<Node> nodeList;
    /**Variable privada: Lista de enlaces que forman el grafo*/
    private List<Link> linkList;
    /**Variable privada: Lista de vehiculos que forman la simulacion*/
    private List<VehicleThread> vehicleList;
    /**Variable privada: Vehiculo 1*/
    private VehicleThreadClient vehicle1;
    /**Variable privada: Vehiculo 2*/
    private VehicleThreadClient vehicle2;
    
    /**
     * Constructor Graph.
     * Crea un nuevo objeto Graph.
     */
    public Graph(){
        nodeList = new java.util.ArrayList<>();
        linkList = new java.util.ArrayList<>();
        vehicleList = new java.util.ArrayList<>();
    }
    
    /** Metodo que crea un nuevo nodo y lo añade a la lista de nodos del grafo.
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void addNode(int x, int y){
        Node node=new Node(x, y, String.valueOf(nodeList.size()));
        nodeList.add(node);
    }
    
    /** Metodo que crea un nuevo enlace entre dos nodos y lo añade a la lista 
     * de enlaces del grafo.
     * @param node1 Variable referente al nodo origen
     * @param node2 Variable referente al nodo destino
     * @since incluido desde la version 1.0
     */
    public void addLink(Node node1, Node node2){
        Link link=new Link(node1, node2);
        linkList.add(link);
    }
   
    /** Metodo que añade un nuevo vehiculo simulado a la lista de vehiculos 
     * del grafo.
     * @param vehicle Variable referente al vehiculo
     * @since incluido desde la version 1.0
     */
    public void addVehicle(VehicleThread vehicle){
        vehicleList.add(vehicle);
    }
    
    /**
     * Metodo que devuelve la lista de nodos del grafo.
     * @return Referencia a la variable nodeList.
     * @since incluido desde la version 1.0
     */
    public List<Node> getNodeList(){
        return nodeList;
    }
    
    /**
     * Metodo que devuelve la lista de enlaces del grafo.
     * @return Referencia a la variable linkList.
     * @since incluido desde la version 1.0
     */
    public List<Link> getLinkList(){
        return linkList;
    }
    
    /**
     * Metodo que devuelve la lista de vehiculos simulados del grafo.
     * @return Referencia a la variable vehicleList.
     * @since incluido desde la version 1.0
     */
    public List<VehicleThread> getVehicleList(){
        return vehicleList;
    }
    
    /** Metodo que crea un nuevo vehiculo referente al cliente1.
     * @param v Variable referente a un objeto 
     * @since incluido desde la version 1.0
     */
    public void setVehicle1(VehicleThreadClient v){
        vehicle1 = v;
    }
    
    /** Metodo que crea un nuevo vehiculo referente al cliente2.
     * @param v Variable referente a un objeto 
     * @since incluido desde la version 1.0
     */
    public void setVehicle2(VehicleThreadClient v){
        vehicle2 = v;
    }
    
    /**
     * Metodo que devuelve el objeto referente al vehicle1.
     * @return Referencia a la variable vehicle1.
     * @since incluido desde la version 1.0
     */
    public VehicleThreadClient getVehicle1(){
        return vehicle1;
    }
    
    /**
     * Metodo que devuelve el objeto referente al vehicle2.
     * @return Referencia a la variable vehicle2.
     * @since incluido desde la version 1.0
     */
    public VehicleThreadClient getVehicle2(){
        return vehicle2;
    }
    
    /**
     * Metodo que devuelve el coste entre dos nodos.
     * La velocidad que define el tiempo entre dos nodos dependera de si el
     * vehiculo se encuentre en la autovia o en la calle y del numero de 
     * vehiculos que haya en el mismo enlace.
     * @param f Nodo origen
     * @param t Nodo destino
     * @return Referencia a una variable entera correspondiente con el coste.
     * @since incluido desde la version 1.0
     */
    public int getCost(Node f, Node t){
        double distance = getDistance(f.getX(), f.getY(), t.getX(), t.getY());
        //Definir la velocidad segun el numero de vehiculos 
        int velocidad = 50; //Velocidad dada en km/h
        int numeroVehiculos = 0;
        int nodoAutovia = 26; //Los nodos por debajo de este valor corresponden a la autovia.
        
        //Ver cuantos vehiculos hay en el mismo enlace y si estamos en autovia
        for(VehicleThread vehicleThread:vehicleList){
            if(vehicleThread.getOrigen().equals(f) && vehicleThread.getDestino().equals(t))
            numeroVehiculos++;
        }
        
        try{
            if (vehicle1.getOrigen().equals(f) && vehicle1.getDestino().equals(t)) {
                numeroVehiculos++;
            }

            if (vehicle2.getOrigen().equals(f) && vehicle2.getDestino().equals(t)) {
                numeroVehiculos++;
            }  
        }catch(Exception e){
        }
        
        if(numeroVehiculos<2)
            if(Integer.parseInt(f.getLabel())<nodoAutovia && Integer.parseInt(t.getLabel())<nodoAutovia)
                velocidad = 120;
            else
                velocidad=50;
        else if (numeroVehiculos<5)
            if(Integer.parseInt(f.getLabel())<nodoAutovia && Integer.parseInt(t.getLabel())<nodoAutovia)
                velocidad = 110;
            else
                velocidad=40;
        else if (numeroVehiculos<10)
            if(Integer.parseInt(f.getLabel())<nodoAutovia && Integer.parseInt(t.getLabel())<nodoAutovia)
                velocidad = 100;
            else
                velocidad=30;
        else
            if(Integer.parseInt(f.getLabel())<nodoAutovia && Integer.parseInt(t.getLabel())<nodoAutovia)
                velocidad = 90;
            else
                velocidad=20;
        int cost=(int) (distance*3600/(velocidad*1000)*1000);  //Coste dado en milisegundos
        return cost;    
    }
    
    /**
     * Metodo que devuelve la distancia entre dos nodos.
     * Se obtiene la distancia que hay entre dos coordenadas del mapa y luego se
     * escala para que dicha distancia corresponda con la distancia real.
     * @param x1 Coordenada x del nodo origen
     * @param y1 Coordenada y del nodo origen
     * @param x2 Coordenada x del nodo destino
     * @param y2 Coordenada y del nodo destino
     * @return Referencia a una variable double correspondiente con a la distancia.
     * @since incluido desde la version 1.0
     */
    public static double getDistance(int x1, int y1, int x2, int y2){
        double scale = 6.147; 
        double distance = (Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
        distance = distance*scale;  //Distancia dada en metros  
        return distance;   
    }
    
    /**
     * Metodo que carga los datos referentes al grafo.
     * Se obtienen los datos a partir de un archivo de texto donde se encuentra
     * la base de datos con todos los nodos y enlaces correspondientes
     * @since incluido desde la version 1.0
     */
    public void loadData(){
        
        try {
            //Leemos los datos de entrada de un fichero con la base de datos
            FileReader file = new FileReader("src/Archivos/baseDatos.txt");
            //FileReader file = new FileReader("lib/baseDatos.txt");
            Scanner File = new Scanner(file);
            //Completamos las listas de nodos y enlaces a partir de los datos del fichero
            String line = File.nextLine();
            String label;
            int x;
            int y;
            Node from;
            Node to;
            boolean nodos=true;
            while (File.hasNextLine()) {
                line = File.nextLine();
                if(line.contains("Enlaces")){
                    nodos=false;
                    line = File.nextLine();
                }
                if(nodos){
                    StringTokenizer st = new StringTokenizer (line);
                    label=st.nextToken();
                    x=Integer.parseInt(st.nextToken());
                    y=Integer.parseInt(st.nextToken());
                    addNode(x,y);
                }else{
                    StringTokenizer st = new StringTokenizer (line);
                    int labelFrom=Integer.parseInt(st.nextToken());
                    int labelTo=Integer.parseInt(st.nextToken());
                    from=getNodeList().get(labelFrom); 
                    to=getNodeList().get(labelTo); 
                    addLink(from, to);
                }
            }
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	} 
    }
}
