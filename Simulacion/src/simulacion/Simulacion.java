/*
 * RECOMENDADOR ADAPTATIVO DE RUTAS DE VIAJE DENTRO DE LA CIUDAD
 * Trabajo Fin de Grado
 * Curso 2015-2016
 * Simulacion
 */
package simulacion;

import data.*;
import controller.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ClassNotFoundException_Exception;
import server.FileNotFoundException_Exception;
import server.IOException_Exception;

/**
 * Clase que contiene el metodo main del proyecto.
 * Dicha clase incluye el JFrame con el diseño de la simulacion.
 * @author Daniel Hernández Bélanger
 * @version 1.0
 */
public class Simulacion extends javax.swing.JFrame {
    
    /**Variable privada: Objeto que contiene el modelo del proyecto*/
    private Model model;
    /**Variable privada: Objeto que contiene el controlador del proyecto*/
    private Controller controller;
    /**Variable privada: Tiempo de generación de un vehiculo*/
    private int tiempoGeneracion=1000; //Tiempo dado en milisegundos
    /**Variable privada: Estado del cliente 1*/
    private boolean cliente1 = false;
    /**Variable privada: Estado del cliente 2*/
    private boolean cliente2 = false;
    /**Variable privada: Estado de la simulacion*/
    private boolean pause= false;
    /**Variable privada: Estado calle*/
    private boolean cortada= false;
    
    /**
     * Constructor Simulacion.
     * Crea un nuevo objeto Simulacion.
     */
    public Simulacion() {
        model = new Model();
        controller = new Controller(model);
        controller.setFrame(this);
        initComponents();
    }

    /**
     * Metodo que devuelve el objeto model que contiene la clase Simulacion 
     * @return Referencia al objeto Model. 
     * @since incluido desde la version 1.0
     */
    public Model getModel() {
        return model;
    }
    
    /**
     * Metodo que devuelve el objeto controller que contiene la clase Simulacion 
     * @return Referencia al objeto Controller. 
     * @since incluido desde la version 1.0
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Metodo que devuelve el tiempo de generacion de un nuevo vehiculo.
     * El tiempo de generacion dependera del tipo de trafico que este 
     * seleccionado. Para obtener un trafico alto el tiempo de generacion debe 
     * ser menor.
     * @return Referencia a la variable tiempoGeneracion.
     * @since incluido desde la version 1.0
     */
    public int getTiempoGeneracion() {
        
        if (traficoBajoButton.isSelected()) {
            tiempoGeneracion=5000;
        } else if (traficoMedioButton.isSelected()) {
            tiempoGeneracion=3000;
        } else if (traficoAltoButton.isSelected()){
            tiempoGeneracion=1000;
        }
        
        return tiempoGeneracion;
    }
    
    /** Metodo que devuelve el estado de la opcion mostrar grafo.
     * Se devuelve true cuando esta seleccionada la opcion de mostrar el grafo 
     * en la simulacion
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    public boolean showGraph(){
        if(grafoButton.isSelected())
            return true;
        else
            return false;
    }
    
    /** Metodo que devuelve el estado del vehiculo cliente.
     * Se devuelve true cuando alguno de los vehiculos clientes aun se esta 
     * ejecutando para indicar que se sigan representando en la simulacion.
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    public boolean showClient(){
        if(cliente1 || cliente2)
            return true;
        else
            return false;
    }
    
    /** Metodo que indica si se encuentra una calle cortad
     * @return Referencia a una variable booleana.
     * @since incluido desde la version 1.0
     */
    public boolean showCalleCortada(){
        if(cortada)
            return true;
        else
            return false;
    }
    
    /** Metodo que devuelve el nodo origen de la calle cortada
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public int getCalleCortadaFrom(){
        return Integer.parseInt(campoOrigen1.getText());
    }
    
    /** Metodo que devuelve el nodo destino de la calle cortada
     * @return Referencia a una variable Node.
     * @since incluido desde la version 1.0
     */
    public int getCalleCortadaTo(){
        return Integer.parseInt(campoDestino1.getText());
    }
    
    /** Metodo que que guarda el estado del cliente1. 
     * Este se pondra a true cuando este se inicializa y a false cuando haya 
     * acabado su ejecución.
     * @param c Variable booleana
     * @since incluido desde la version 1.0
     */
    public void setClient1(boolean c){
        cliente1=c;
    }
    
    /** Metodo que que guarda el estado del cliente2. 
     * Este se pondra a true cuando este se inicializa y a false cuando haya 
     * acabado su ejecución.
     * @param c Variable booleana
     * @since incluido desde la version 1.0
     */
    public void setClient2(boolean c){
        cliente2=c;
    }
    
    /**
     * Metodo que devuelve el Panel Map.
     * @return Referencia a la variable newJPanel1.
     * @since incluido desde la version 1.0
     */
    public PanelMap getPanelMap(){
        return newJPanel1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupMode = new javax.swing.ButtonGroup();
        newJPanel1 = new simulacion.PanelMap(this);
        jPanel2 = new javax.swing.JPanel();
        traficoBajoButton = new javax.swing.JRadioButton();
        traficoMedioButton = new javax.swing.JRadioButton();
        traficoAltoButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        reiniciarButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        grafoButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        campoOrigen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoDestino = new javax.swing.JTextField();
        nuevoVehiculoButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        corteCalleButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        campoOrigen1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campoDestino1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        newJPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        newJPanel1.setPreferredSize(new java.awt.Dimension(1500, 1200));

        javax.swing.GroupLayout newJPanel1Layout = new javax.swing.GroupLayout(newJPanel1);
        newJPanel1.setLayout(newJPanel1Layout);
        newJPanel1Layout.setHorizontalGroup(
            newJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1316, Short.MAX_VALUE)
        );
        newJPanel1Layout.setVerticalGroup(
            newJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        buttonGroupMode.add(traficoBajoButton);
        traficoBajoButton.setSelected(true);
        traficoBajoButton.setText("Bajo");

        buttonGroupMode.add(traficoMedioButton);
        traficoMedioButton.setText("Medio");

        buttonGroupMode.add(traficoAltoButton);
        traficoAltoButton.setText("Alto");

        jLabel1.setText("Tipo de Trafico:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(traficoBajoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(traficoMedioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(traficoAltoButton)
                .addGap(82, 82, 82))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(traficoBajoButton)
                    .addComponent(traficoMedioButton)
                    .addComponent(traficoAltoButton)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        reiniciarButton.setText("Iniciar");
        reiniciarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pausar");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        grafoButton.setText("Mostrar Grafo");

        jLabel3.setText("Origen:");

        jLabel4.setText("Destino");

        nuevoVehiculoButton.setText("Crear Vehiculo");
        nuevoVehiculoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoVehiculoButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Normal:");

        jLabel6.setText("Recomendador:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/cocheVerde.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivos/cocheAzul.png"))); // NOI18N

        corteCalleButton.setText("Cortar calle");
        corteCalleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corteCalleButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Origen:");

        jLabel9.setText("Destino");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(reiniciarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseButton)
                        .addGap(32, 32, 32)
                        .addComponent(grafoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoOrigen1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDestino1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(corteCalleButton)))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoVehiculoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(newJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(campoOrigen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(campoDestino1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(reiniciarButton)
                                .addComponent(pauseButton)
                                .addComponent(grafoButton)
                                .addComponent(jLabel3)
                                .addComponent(campoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(campoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevoVehiculoButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(jLabel5)
                    .addComponent(corteCalleButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /** Metodo referente al evento de pulsar el boton reiniciarButton.
     * Cuando llega un evento de haber pulsado este boton para la actual 
     * simulacion reinicia los diferentes componentes e inicia una nueva 
     * simulacion.
     * @param evt Referencia al evento del interfaz grafico
     * @since incluido desde la version 1.0
     */
    private void reiniciarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarButtonActionPerformed
        // TODO add your handling code here:
        //Llamar al metodo reset que reinicia la simulacion y crea una nueva hebra 
        controller.resetSimulation();
        
        reiniciarButton.setText("Reiniciar");  
        
        controller.getFrame().repaint();  
    }//GEN-LAST:event_reiniciarButtonActionPerformed
    
    /** Metodo referente al evento de pulsar el boton nuevoVehiculoButton.
     * Cuando llega un evento de haber pulsado este boton crea un objeto 
     * cliente1 y un objeto cliente 2 e inicia la simulacion de estos.
     * @param evt Referencia al evento del interfaz grafico
     * @since incluido desde la version 1.0
     */
    private void nuevoVehiculoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoVehiculoButtonActionPerformed
        // TODO add your handling code here:
        cliente1 = true;
        cliente2 = true;
        
        int From = Integer.parseInt(campoOrigen.getText());
        int To = Integer.parseInt(campoDestino.getText());
        Node origen = model.getGraph().getNodeList().get(From);
        Node destino= model.getGraph().getNodeList().get(To);
        
        //Creamos la hebra correspondiente al vehiculo cliente que usa el recomendador 
        VehicleThreadClient vehiculo1 = new VehicleThreadClient(origen,destino,true,controller);
        vehiculo1.reanudar();
        //La añadimos a la lista de vehiculos
        model.getGraph().setVehicle1(vehiculo1);
        
        //Creamos la hebra correspondiente al vehiculo cliente que usa el recomendador 
        VehicleThreadClient vehiculo2 = new VehicleThreadClient(origen,destino,false,controller);
        vehiculo2.reanudar();
        //La añadimos a la lista de vehiculos
        model.getGraph().setVehicle2(vehiculo2);   
    }//GEN-LAST:event_nuevoVehiculoButtonActionPerformed

    /** Metodo referente al evento de pulsar el boton pauseButton.
     * Cuando llega un evento de haber pulsado este boton pausa la simulacion o
     * la reanuda si esta se encontraba en pausa.
     * @param evt Referencia al evento del interfaz grafico
     * @since incluido desde la version 1.0
     */
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
        controller.pauseSimulation();
        
        if(pause){
            pause=false;
            pauseButton.setText("Pausar"); 
        }
        else{
            pause=true;
            pauseButton.setText("Reanudar");
        }
    }//GEN-LAST:event_pauseButtonActionPerformed

    
     /** Metodo referente al evento de pulsar el boton corteCalleButton.
     * Cuando llega un evento de haber pulsado este boton pausa la simulacion o
     * la reanuda si esta se encontraba en pausa.
     * @param evt Referencia al evento del interfaz grafico
     * @since incluido desde la version 1.0
     */
    private void corteCalleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corteCalleButtonActionPerformed
        // TODO add your handling code here:
        String From = campoOrigen1.getText();
        String To = campoDestino1.getText();
        int cost = 0;
        
        if(cortada){
            cortada=false;
            corteCalleButton.setText("Cotar calle"); 
            try {
                 controller.actualizarRuta(From,To,cost);
             } catch (FileNotFoundException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        else{ 
            cortada=true;
            corteCalleButton.setText("Restablecer");
             try {
                 cost = controller.obtenerCosteTiempo(From,To);
                 controller.actualizarRuta(From,To,Integer.MAX_VALUE);
             } catch (FileNotFoundException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException_Exception ex) {
                 Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }//GEN-LAST:event_corteCalleButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupMode;
    private javax.swing.JTextField campoDestino;
    private javax.swing.JTextField campoDestino1;
    private javax.swing.JTextField campoOrigen;
    private javax.swing.JTextField campoOrigen1;
    private javax.swing.JButton corteCalleButton;
    private javax.swing.JRadioButton grafoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private simulacion.PanelMap newJPanel1;
    private javax.swing.JButton nuevoVehiculoButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton reiniciarButton;
    private javax.swing.JRadioButton traficoAltoButton;
    private javax.swing.JRadioButton traficoBajoButton;
    private javax.swing.JRadioButton traficoMedioButton;
    // End of variables declaration//GEN-END:variables
}
