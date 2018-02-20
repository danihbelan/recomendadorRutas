/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package data;

import java.io.FileReader;
import java.io.FileWriter;
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

    /**Variable privada: Lista de nodos que componen el grafo*/
    private List<Node> nodeList;
    /**Variable privada: Lista de enlaces que componen el grafo*/
    private List<Link> linkList;
    
    /**
     * Constructor Graph.
     * Crea un nuevo objeto Graph.
     */
    public Graph(){
        nodeList = new java.util.ArrayList<>();
        linkList = new java.util.ArrayList<>();
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
     * Metodo que devuelve el nodo mas cercano respecto a unas coordenadas.
     * @param x Variable correspondiente a la coordenada x
     * @param y Variable correspondiente a la coordenada x
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public Node findClosestNode(int x, int y){
        Node closestNode=null;
        double minDistance=0.0;
        double distance;
        for(Node node:nodeList){
            if(closestNode==null){
                closestNode=node;
                minDistance=getDistance(x,y,node.getX(),node.getY());
            }
            else{
                distance=getDistance(x,y,node.getX(),node.getY());
                if(distance<minDistance){
                    closestNode=node;
                    minDistance=distance;
                }
            }     
        }
        return closestNode;
    }
    
    /** Metodo que elimina un nodo de la lista de nodos del grafo.
     * Tambien se eliminan todos los enlaces que tenian como nodo origen o 
     * destino a dicho nodo
     * @param node Variable referente al nodo que se quiere eliminar
     * @since incluido desde la version 1.0
     */
    public void removeNode(Node node){
        java.util.Iterator<Link> iterator=linkList.iterator();
        Link link;
        while(iterator.hasNext()){
            link=iterator.next();
            if(link.getFrom()==node || link.getTo()==node){
                iterator.remove();
            }
        }   
        nodeList.remove(node);
        //Reoordenar la lista de nodos
        int label=0;
        for(Node nodo:nodeList){
            nodo.setLabel(String.valueOf(label));
            label++;
        }
    }
    
    /** Metodo que elimina enlace del grafo.
     * @param from Variable referente al nodo origen del enlace
     * @param to Variable referente al nodo destino del enlace
     * @since incluido desde la version 1.0
     */
    public void removeLink(Node from, Node to){
        java.util.Iterator<Link> iterator=linkList.iterator();
        Link link;
        while(iterator.hasNext()){
            link=iterator.next();
            if(link.getFrom()==from && link.getTo()==to){
                iterator.remove();
            }
        } 
     }
    
    /**
     * Metodo que devuelve la distancia entre dos nodos.
     * Se obtiene la distancia que hay entre dos coordenadas del mapa y luego se
     * escala para que dicha distancia corresponda con la distancia real.
     * @param x1 Coordenada x del nodo 1
     * @param y1 Coordenada y del nodo 1
     * @param x2 Coordenada x del nodo 2
     * @param y2 Coordenada y del nodo 2
     * @return Referencia a una variable double correspondiente con a la distancia.
     * @since incluido desde la version 1.0
     */
    public static double getDistance(int x1, int y1, int x2, int y2){
        double scale = 6.147;
        double distance = (Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
        distance = distance*scale;
        return distance;    
    }
    
    /** Metodo que crea el texto salida con los datos correspondientes a los
     * nodos y los enlaces del grafo.
     * @return Referencia a una variable String
     * @since incluido desde la version 1.0
     */
    public String toString(){
        String string="Nodos:\n";
        for(Node node:nodeList){
            string += node.toString() + "\n";
        }
        
        string +="Enlaces:\n";
        for(Link link:linkList){
            string += link.toString() + "\n";
        }
        return string;
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
     
    /**
     * Metodo que guarda los datos referentes al grafo.
     * Se guardann los datos en un archivo de texto. 
     * @since incluido desde la version 1.0
     */
    public void saveData(){
        //Guardamos los datos en un fichero de texto
        FileWriter fichero = null;
        try{
            fichero = new FileWriter("src/Archivos/baseDatos.txt"); 
            //fichero = new FileWriter("lib/baseDatos.txt"); 
            fichero.write(toString());		
            fichero.close();
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
	} 
    }
}
