/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */
package simulacion;

import data.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que contiene el JPanel del proyecto.
 * Dicha clase se encarga de dibujar el mapa y los diferentes componentes.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class PanelMap extends javax.swing.JPanel {
    
    /**Variable publica: Objeto que contiene el frame del interfaz grafico*/
    public Simulacion frame;
    /**Variable privada: Anchura del nodo*/
    private static int NODE_WIDTH=10;
    /**Variable privada: Imagen que contiene el mapa de la ciudad*/
    private java.awt.Image imagen;
    /**Variable privada: Imagen que contiene el icono del coche negro*/
    private java.awt.Image cocheNegro;
    /**Variable privada: Imagen que contiene el icono del coche verde*/
    private java.awt.Image cocheVerde;
    /**Variable privada: Imagen que contiene el icono del coche azul*/
    private java.awt.Image cocheAzul;

    /**
     * Constructor PanelMap.
     * Crea un nuevo objeto PanelMap.
     */
    public PanelMap() {
        initComponents();
    }
    
    /**
     * Constructor PanelMap.
     * Crea un nuevo objeto PanelMap.
     */
    public PanelMap(Simulacion fr){
        this();
        frame = fr;
        imagen=java.awt.Toolkit.getDefaultToolkit().getImage("src/Archivos/planoGranada.png");
        cocheNegro=java.awt.Toolkit.getDefaultToolkit().getImage("src/Archivos/cocheNegro.png");
        cocheVerde=java.awt.Toolkit.getDefaultToolkit().getImage("src/Archivos/cocheVerde.png");
        cocheAzul=java.awt.Toolkit.getDefaultToolkit().getImage("src/Archivos/cocheAzul.png");
        setSize(imagen.getWidth(this),imagen.getHeight(this));
        //Cargamos los datos de la base de datos
        frame.getModel().getGraph().loadData();
        repaint();
    }
    
    /**
     * Metodo que pinta los componentes en el JFrame 
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(imagen,0,0,this);
        
        if(frame!=null && frame.getModel()!=null){
            if(frame.showGraph()){
                drawNodes(g);
                drawLinks(g);
            }
            
            if(frame.showCalleCortada()){
                drawCalleCortada(g);
            }
            
            drawVehicles(g);
            if(frame.showClient()){
                drawClient(g);
            }
        }
    }
    
    /**
     * Metodo que dibuja los nodos sobre el mapa. 
     * Dibujara los nodos como circulos de color rojo y sobre ellos 
     * pintara tambien el numero de su etiqueta.
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    public void drawNodes(Graphics g){
        for(Node node:frame.getModel().getGraph().getNodeList()){
            g.setColor(Color.RED);           
            g.fillArc(node.getX()-NODE_WIDTH/2, node.getY()-NODE_WIDTH/2, NODE_WIDTH, NODE_WIDTH, 0, 360);
            g.setColor(Color.BLACK);
            if(Integer.parseInt(node.getLabel())<10)
                g.drawString(node.getLabel(), node.getX()-4, node.getY()+5);
            else if (Integer.parseInt(node.getLabel())<100)
                g.drawString(node.getLabel(), node.getX()-8, node.getY()+5);
            else
                g.drawString(node.getLabel(), node.getX()-12, node.getY()+5); 
        }
    }
    
    /**
     * Metodo que dibuja los enlaces sobre el mapa. 
     * Dibujara los enlaces como lineas de color azul
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    public void drawLinks(Graphics g){
        g.setColor(Color.BLUE);
        for(Link link:frame.getModel().getGraph().getLinkList()){
            g.drawLine(link.getFrom().getX(), link.getFrom().getY(), 
                    link.getTo().getX(), link.getTo().getY());
        }
    }
    
    /**
     * Metodo que dibuja una calle cortada. 
     * Dibujara la calle con una linea de color rojo
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    public void drawCalleCortada(Graphics g){
        g.setColor(Color.RED);
        
        int from = frame.getCalleCortadaFrom();
        int to = frame.getCalleCortadaTo();
        
        Node origen = frame.getModel().getGraph().getNodeList().get(from);
        Node destino = frame.getModel().getGraph().getNodeList().get(to);
        g.fillArc(origen.getX()-NODE_WIDTH/2, origen.getY()-NODE_WIDTH/2, NODE_WIDTH, NODE_WIDTH, 0, 360);
        g.fillArc(destino.getX()-NODE_WIDTH/2, destino.getY()-NODE_WIDTH/2, NODE_WIDTH, NODE_WIDTH, 0, 360);
        
        g.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());      
        
        //Dibujamos la punto de la fleacha
        double tx=(origen.getX()-destino.getX())*1.0;
        double ty=-(origen.getY()-destino.getY())*1.0;
        double ang=Math.atan (ty/tx);

        if(tx<0)
            ang+=Math.PI;
    
        int f1x=(int)(destino.getX()+15*Math.cos (ang-Math.toRadians (25.0)));
        int f1y=(int)(destino.getY()-15*Math.sin (ang-Math.toRadians (25.0)));
        int f2x=(int)(destino.getX()+15*Math.cos (ang+Math.toRadians (25.0)));
        int f2y=(int)(destino.getY()-15*Math.sin (ang+Math.toRadians (25.0)));
        g.drawLine (f1x,f1y,destino.getX(),destino.getY());
        g.drawLine (f2x,f2y,destino.getX(),destino.getY());   
    }
    
    /**
     * Metodo que dibuja los vehiculos simulados sobre el mapa. 
     * Dibujara los vehiculos a partir de un icono de un vehiculo.
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    public void drawVehicles(Graphics g){
        g.setColor(Color.BLACK);
        for(VehicleThread vehicleThread:frame.getModel().getGraph().getVehicleList()){
        //g.fillArc((int) vehicleThread.getX()-NODE_WIDTH/2, (int) (vehicleThread.getY()-NODE_WIDTH/2), NODE_WIDTH, NODE_WIDTH, 0, 360);
        g.drawImage(cocheNegro, (int) vehicleThread.getX()-NODE_WIDTH/2, (int) (vehicleThread.getY()-NODE_WIDTH/2), this);
        } 
    }
    
    /**
     * Metodo que dibuja los vehiculos clientes sobre el mapa. 
     * Dibujara los vehiculos a partir de un icono de un vehiculo. El vehiculo 
     * que usa el recomendaro (cliente1) sera de color verde y el vehiculo que 
     * usa una ruta solo basada en distancia (cliente2) sera de color azul. 
     * @param g Graphics donde se dibujara
     * @since incluido desde la version 1.0
     */
    public void drawClient(Graphics g){
        VehicleThreadClient vehicle1=frame.getModel().getGraph().getVehicle1();
        VehicleThreadClient vehicle2=frame.getModel().getGraph().getVehicle2();
        g.drawImage(cocheVerde, (int) vehicle1.getX()-NODE_WIDTH/2, (int) (vehicle1.getY()-NODE_WIDTH/2), this);
        g.drawImage(cocheAzul, (int) vehicle2.getX(), (int) (vehicle2.getY()-NODE_WIDTH), this);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMap.this.mouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
