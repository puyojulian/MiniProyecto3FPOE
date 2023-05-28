/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.univalle.miniproyecto3.vista;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jpContenido = new javax.swing.JPanel();
        jpOpciones = new javax.swing.JPanel();
        btnRecursos = new javax.swing.JRadioButton();
        btnUsuarios = new javax.swing.JRadioButton();
        btnPrestamos = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        txtBuscar = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnBusqueda = new javax.swing.JButton();
        jpBusquedaAvanzada = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 153, 153));
        setBounds(new java.awt.Rectangle(0, 0, 600, 450));
        setForeground(java.awt.Color.gray);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 450));

        jpContenido.setPreferredSize(new java.awt.Dimension(600, 425));

        jpOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECURSOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 153, 51))); // NOI18N

        buttonGroup1.add(btnRecursos);
        btnRecursos.setText("LIBROS");

        buttonGroup1.add(btnUsuarios);
        btnUsuarios.setText("USUARIOS");

        buttonGroup1.add(btnPrestamos);
        btnPrestamos.setText("PRESTAMOS");

        javax.swing.GroupLayout jpOpcionesLayout = new javax.swing.GroupLayout(jpOpciones);
        jpOpciones.setLayout(jpOpcionesLayout);
        jpOpcionesLayout.setHorizontalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrestamos)
                    .addComponent(btnUsuarios)
                    .addComponent(btnRecursos))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnRecursos)
                .addGap(84, 84, 84)
                .addComponent(btnUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnPrestamos)
                .addGap(73, 73, 73))
        );

        jScrollPane1.setViewportView(jList);

        btnAgregar.setText("AGREGAR");

        btnActualizar.setText("ACTUALIZAR");

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");

        btnBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnBusqueda.setText("BUSQUEDA AVANZADA");
        btnBusqueda.setPreferredSize(new java.awt.Dimension(147, 23));

        javax.swing.GroupLayout jpContenidoLayout = new javax.swing.GroupLayout(jpContenido);
        jpContenido.setLayout(jpContenidoLayout);
        jpContenidoLayout.setHorizontalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenidoLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addGap(57, 57, 57)
                        .addComponent(btnEliminar))
                    .addComponent(jScrollPane1)
                    .addGroup(jpContenidoLayout.createSequentialGroup()
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContenidoLayout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addGap(32, 32, 32)))))
                .addContainerGap())
        );
        jpContenidoLayout.setVerticalGroup(
            jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenidoLayout.createSequentialGroup()
                .addGroup(jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpContenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContenidoLayout.createSequentialGroup()
                        .addGroup(jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpContenidoLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpContenidoLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(jpContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnActualizar)
                            .addComponent(btnEliminar))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jpBusquedaAvanzada.setMinimumSize(new java.awt.Dimension(200, 200));
        jpBusquedaAvanzada.setPreferredSize(new java.awt.Dimension(200, 200));
        jpBusquedaAvanzada.setVisible(false);

        javax.swing.GroupLayout jpBusquedaAvanzadaLayout = new javax.swing.GroupLayout(jpBusquedaAvanzada);
        jpBusquedaAvanzada.setLayout(jpBusquedaAvanzadaLayout);
        jpBusquedaAvanzadaLayout.setHorizontalGroup(
            jpBusquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jpBusquedaAvanzadaLayout.setVerticalGroup(
            jpBusquedaAvanzadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(jpContenido, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jpBusquedaAvanzada, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnBusqueda() {
        return btnBusqueda;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JRadioButton getBtnPrestamos() {
        return btnPrestamos;
    }

    public JRadioButton getBtnRecursos() {
        return btnRecursos;
    }

    public JRadioButton getBtnUsuarios() {
        return btnUsuarios;
    }

    public void setjList(DefaultListModel List) {
        jList.setModel(List);
    }

    public JList<String> getjList() {
        return jList;
    }

    public JPanel getJpBusquedaAvanzada() {
        return jpBusquedaAvanzada;
    }
        
    public void addBtnRecursos(ActionListener listener){
        btnRecursos.addActionListener(listener);
    }
    
    public void addBtnUsuarios(ActionListener listener){
        btnUsuarios.addActionListener(listener);
    }
    
    public void addBtnBuscar(ActionListener listener){
        btnBuscar.addActionListener(listener);
    }
    
    public void addBtnBusqueda(ActionListener listener){
        btnBusqueda.addActionListener(listener);
    }
    
    public void addBtnAgregar(ActionListener listener){
        btnAgregar.addActionListener(listener);
    }
    
    public void addBtnActualizar(ActionListener listener){
        btnActualizar.addActionListener(listener);
    }
    
    public void addBtnEliminar(ActionListener listener){
        btnEliminar.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JRadioButton btnPrestamos;
    private javax.swing.JRadioButton btnRecursos;
    private javax.swing.JRadioButton btnUsuarios;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpBusquedaAvanzada;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}