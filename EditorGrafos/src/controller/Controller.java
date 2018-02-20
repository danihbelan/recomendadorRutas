/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * EditorGrafos
 */
package controller;

import editorgrafos.*;
import data.*;

/**
 * Clase que contiene el objeto Controller del proyecto.
 * Dicha clase actua de mediador entre las clases que contienen el interfaz 
 * grafico y la clase que contiene el modelo del proyecto.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Controller {
    
    /**Variable privada: Objeto que contiene el frame del interfaz grafico*/
    private EditorGrafos frame;
    /**Variable privada: Objeto que contiene el modelo del proyecto*/
    private Model model;
    /**Variable publica: Distancia maxima para seleccionar un nodo*/
    public static int MAX_DISTANCE_TO_SELECT = 20;

    /**
     * Constructor Controller.
     * Crea un nuevo objeto Controller.
     */
    public Controller(Model m) {
        model = m;
    }

    /**
     * Metodo que guarda una referencia al objeto frame.
     * @param f Variable referente al frame
     * @since incluido desde la version 1.0
     */
    public void setFrame(EditorGrafos f) {
        frame = f;
    }

    /** Metodo que que llama al metodo addNode del grafo.
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void addNode(int x, int y) {
        model.getGraph().addNode(x, y);
    }

    /** Metodo que que llama al metodo addLink del grafo.
     * Se tiene en cuenta que el nodo seleccionado este a una distancia minima 
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void addLink(int x, int y) {
         Node node;
        if (model.getSelectedNode() == null) {
            node = model.getGraph().findClosestNode(x, y);
            if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
                model.setSelectedNode(node);
            }
        } else { // Si había un nodo ya seleccionado
            node = model.getGraph().findClosestNode(x, y);

            if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
                if (node != model.getSelectedNode()) {
                    model.getGraph().addLink(model.getSelectedNode(), node);
                } else {
                    System.out.println("ERROR: has seleccionado el mismo nodo como origen y destino del enlace");
                }
            }
            model.setSelectedNode(null);
        }
    }

    /** Metodo que permite cambiar la posicion de un nodo en el mapa.
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void moveNode(int x, int y) {
        if (model.getSelectedNode() == null) {
            Node node = model.getGraph().findClosestNode(x, y);
            if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
                model.setSelectedNode(node);
            }
            //model.setSelectedNode(model.getGraph().findClosestNode(x,y));
        } else { // Si había un nodo ya seleccionado
            model.getSelectedNode().setXY(x, y);
            model.setSelectedNode(null);
        }
    }

    /** Metodo que llama al metodo deleteNode del grafo.
     * Se tiene en cuenta que el nodo seleccionado este a una distancia minima 
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void deleteNode(int x, int y) {
        Node node = model.getGraph().findClosestNode(x, y);
        if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
            model.getGraph().removeNode(node);
        }
    }
    
    /** Metodo que llama al metodo deleteLink del grafo.
     * Se tiene en cuenta que el nodo seleccionado este a una distancia minima 
     * @param x Variable referente a la coordenada x
     * @param y Variable referente a la coordenada y
     * @since incluido desde la version 1.0
     */
    public void deleteLink(int x, int y) {
         Node node;
        if (model.getSelectedNode() == null) {
            node = model.getGraph().findClosestNode(x, y);
            if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
                model.setSelectedNode(node);
            }
        } else { // Si había un nodo ya seleccionado
            node = model.getGraph().findClosestNode(x, y);

            if (Graph.getDistance(x, y, node.getX(), node.getY()) < MAX_DISTANCE_TO_SELECT) {
                if (node != model.getSelectedNode()) {
                    model.getGraph().removeLink(model.getSelectedNode(), node);
                } else {
                    System.out.println("ERROR: has seleccionado el mismo nodo como origen y destino del enlace");
                }
            }
            model.setSelectedNode(null);
        }
    }
}
