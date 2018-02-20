/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * ServidorRecomendador
 */
package server;

import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Vector;
import weiss.nonstandard.PairingHeap;

// Used to signal violations of preconditions for
// various shortest path algorithms.
class GraphException extends RuntimeException implements java.io.Serializable
{
    public GraphException( String name )
    {
        super( name );
    }
}

// Represents an edge in the graph.
class Edge implements java.io.Serializable
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
}

// Represents an entry in the priority queue for Dijkstra's algorithm.
class Path implements Comparable<Path>, java.io.Serializable
{
    public Vertex     dest;   // w
    public double     cost;   // d(w)
    
    public Path( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.cost;
        
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}

// Represents a vertex in the graph.
class Vertex implements java.io.Serializable
{
    public String     name;   // Vertex name
    public List<Edge> adj;    // Adjacent vertices
    public double     dist;   // Cost
    public Vertex     prev;   // Previous vertex on shortest path
    public int        scratch;// Extra variable used in algorithm

    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); reset( ); }

    public void reset( )
      { dist = Graph.INFINITY; prev = null; pos = null; scratch = 0; }    
      
    public PairingHeap.Position<Path> pos;  // Used for dijkstra
}

/**
 * Clase que contiene el Grafo del proyecto.
 * Dicha clase se encarga de construir el grafo y los metodos correspondientes
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Graph implements java.io.Serializable {

    /**Variable publica: Variable que representa un valor infinito*/
    public static final double INFINITY = Double.MAX_VALUE;
    /**Variable publica: Contiene el mapa de vertices del grafo*/
    public Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    /**Variable: Contiene el coste de la ruta*/
    double cost;
  
    /** Metodo que añade un nuevo enlace al grafo.
     * @param sourceName Variable referente al nodo origen del enlace
     * @param destName Variable referente al nodo origen del enlace
     * @param cost Variable referente al coste del enlace
     * @since incluido desde la version 1.0
     */
    public void addEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        v.adj.add(new Edge(w, cost));
    }
    
    /**
     * Metodo que actualiza un enlace del grafo.
     * @param sourceName Variable referente al nodo origen del enlace
     * @param destName Variable referente al nodo origen del enlace
     * @param cost Variable referente al coste del enlace
     * @since incluido desde la version 1.0
     */
    public void updateEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        
        int i=0;
        boolean nuevo=true;
        while(i<v.adj.size()){
            if (w.equals(v.adj.get(i).dest)){
                v.adj.set(i, new Edge(w, cost));
                nuevo=false;
                break;
            }
            i++;
        }
        
        if (nuevo)
            v.adj.add(new Edge(w, cost));
                  
    }

    /**
     * Metodo que nos devuelve el vertexName del vertexMap. 
     * Si no esta, lo añade.
     * @param vertexName Variable referente al vertexName
     * @return Referencia al vertexName
     * @since incluido desde la version 1.0
     */
    private Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }
    
    /**
     * Metodo que inicializa la informacion de "vertex output" antes de ejecutar 
     * el algorito de Dijkstra.
     * @since incluido desde la version 1.0
     */
    public void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }
    
    /**
     * Metodo que se encarga de obtener la ruta con el camino mas corto.
     * Obtiene el vertex correspondiente al nodo destino y devuelvera la ruta a 
     * traves de la llamada al metodo Path. Ademas tambien obtiene el coste 
     * de la ruta mas corta obtenida.
     * @param destName Variable referente al vertice origen
     * @param ruta Variable referente a la ruta
     * @return Referencia a la ruta mas corta.
     * @since incluido desde la version 1.0
     */
    public Vector<String> getPath( String destName, Vector ruta)
    {
        Vertex w = vertexMap.get( destName );
        
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            cost=w.dist;
            ruta = Path(w,ruta);
        }
  
        return ruta;
    }

    /**
     * Metodo que va obteniendo de forma recursiva la ruta despues de haber 
     * ejecutado el algoritmo de Dijkstra.
     * @param dest Variable referente al certice destino
     * @param ruta Variable referente a la ruta
     * @return Referencia a la ruta mas corta.
     * @since incluido desde la version 1.0
     */
    private Vector<String> Path( Vertex dest, Vector ruta){
        if( dest.prev != null )
        {
            Path( dest.prev,ruta );
        }
        ruta.add(dest.name);
        return ruta;
    }
}
