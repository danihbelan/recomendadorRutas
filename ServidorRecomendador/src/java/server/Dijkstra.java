/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * ServidorRecomendador
 */
package server;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Clase que contiene el metodo que implementa el algoritmo de Dijkstra
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Dijkstra {

    /**
     * Metodo que implenmeta el algoritmo de Dijkstra.
     * Dado el nodo origen se lleva a cabo la implementacion del algoritmo de 
     * Dijkstra con nuestro grafo. 
     * @param g Variable referente al grafo
     * @param startName Variable referente al vertice origen
     * @since incluido desde la version 1.0
     */
    static public void calcular( Graph g, String startName ){
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );

        Vertex start = g.vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        g.clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < g.vertexMap.size( ) )
        {
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            if( v.scratch != 0 )  // already processed v
                continue;
                
            v.scratch = 1;
            nodesSeen++;

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                    
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                }
            }
        }
    }    
}
