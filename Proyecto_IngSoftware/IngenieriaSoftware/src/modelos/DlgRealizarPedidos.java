/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import servicios.Registro_servicio;

/**
 *
 * @author Ahri
 */
public class DlgRealizarPedidos extends javax.swing.JDialog {

    /**
     * Creates new form DlgRealizarPedidos
     */
    void borrarTabla() {
        this.setLocationRelativeTo(null);
        int nroFilas = 0;
        DefaultTableModel modeloDeMiTabla = (DefaultTableModel) tbRegistro.getModel();
        modeloDeMiTabla.addRow(new Object[nroFilas]);
        for (nroFilas = 0; nroFilas < 100; nroFilas++) {
            tbRegistro.setValueAt(false, nroFilas, 0);
            tbRegistro.setValueAt(null, nroFilas, 1);
            tbRegistro.setValueAt(null, nroFilas, 2);
            tbRegistro.setValueAt(null, nroFilas, 3);
        }

    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatoFecha.format(fecha);
    }
    Integer contador = 0;

    public DlgRealizarPedidos(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        int nroFilas = 0;
        Conexion con = new Conexion();
        Connection reg = con.obtenerConexion();
        try {
            PreparedStatement consulta = reg.prepareStatement("SELECT * FROM registro");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                DefaultTableModel modeloDeMiTabla = (DefaultTableModel) tbRegistro.getModel();
                modeloDeMiTabla.addRow(new Object[nroFilas]);
                tbRegistro.setValueAt(false, nroFilas, 0);
                tbRegistro.setValueAt(resultado.getString("nombre"), nroFilas, 1);
                tbRegistro.setValueAt(resultado.getString("medida"), nroFilas, 2);
                tbRegistro.setValueAt(null, nroFilas, 3);
                nroFilas++;
                contador++;
            }
        } catch (SQLException ex) {
        }

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
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnRealizarPedido = new javax.swing.JButton();
        btnConsultarPedido = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistro = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Comentarios"));
        jPanel2.setToolTipText("");

        jLabel1.setText("Medicamento");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1447384412_folder-search.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar por nombre del medicamento");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        btnRealizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1447695803_address-book-add.png"))); // NOI18N
        btnRealizarPedido.setToolTipText("Realizar pedido");
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });

        btnConsultarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1447696235_kpdf.png"))); // NOI18N
        btnConsultarPedido.setToolTipText("Consultar pedidos");
        btnConsultarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarPedidoActionPerformed(evt);
            }
        });

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1446609011_go-back.png"))); // NOI18N
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1446607485_update.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRealizarPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnConsultarPedido, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnRealizarPedido, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Realizar pedidos"));

        tbRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Medicamento", "Medida", "Cantidad Requerida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRegistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRegistro);
        if (tbRegistro.getColumnModel().getColumnCount() > 0) {
            tbRegistro.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbRegistro.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
        // TODO add your handling code here:
        int nroFilas = 0, a2, cont = 0;
        Conexion con = new Conexion();
        Connection reg = con.obtenerConexion();
        String a1, a3, a4 = "Pendiente", usuario = "",unidad="";
        boolean a;

        for (int i = 0; i < contador; i++) {
            a = Boolean.parseBoolean(tbRegistro.getValueAt(nroFilas, 0).toString());
            if (a) {
                cont++;
                a1 = tbRegistro.getValueAt(nroFilas, 1).toString();
                a2 = Integer.parseInt(tbRegistro.getValueAt(nroFilas, 3).toString());
                a3 = tbRegistro.getValueAt(nroFilas, 2).toString();
                try {
                    FileReader f = new FileReader("guardarUsuario.txt");
                    Scanner sf = new Scanner(f);
                    usuario = sf.nextLine();
                    sf.close();
                } catch (IOException e) {
                }
                try{
                    PreparedStatement consulta=reg.prepareStatement("SELECT * FROM usuario");
                    ResultSet resultado1 = consulta.executeQuery();
                        while (resultado1.next()) {
                            if(resultado1.getString("nick_usuario").equals(usuario)){
                                unidad=resultado1.getString("unidad");
                            }
                        }
                }catch(SQLException ex){}
                try {
                    PreparedStatement consulta;
                    consulta = reg.prepareStatement("INSERT INTO pedido(medicamento,cantidad_requerida,medida,estado_entrega,fecha_solicitud,unidad) VALUES( ?, ?,?, ?, ?,?)");
                    consulta.setString(1, a1);
                    consulta.setInt(2, a2);
                    consulta.setString(3, a3);
                    consulta.setString(4, a4);
                    consulta.setString(5, fechaActual());
                    consulta.setString(6,unidad);
                    consulta.executeUpdate();
                } catch (SQLException ex) {
                }
            }
            nroFilas++;
        }
        JOptionPane.showMessageDialog(this, "Se realizaron" + " " + cont + " " + "pedidos");

    }//GEN-LAST:event_btnRealizarPedidoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConsultarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarPedidoActionPerformed
        // TODO add your handling code here:
        DlgMostrarPedidos dmp = new DlgMostrarPedidos(new javax.swing.JDialog(), true);
        dmp.setVisible(true);
        dmp.dispose();
    }//GEN-LAST:event_btnConsultarPedidoActionPerformed

    private void tbRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRegistroMouseClicked
        // TODO add your handling code here:
        //Arreglar esto, los botones para seleccionar no funcionan bn;
    }//GEN-LAST:event_tbRegistroMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        borrarTabla();
        int nroFilas = 0;
        Conexion con = new Conexion();
        Connection reg = con.obtenerConexion();
        try {
            PreparedStatement consulta = reg.prepareStatement("SELECT * FROM registro");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                if (resultado.getString("NOMBRE").equals(txtBusqueda.getText())) {
                    DefaultTableModel modeloDeMiTabla = (DefaultTableModel) tbRegistro.getModel();
                    modeloDeMiTabla.addRow(new Object[nroFilas]);
                    tbRegistro.setValueAt(false, nroFilas, 0);
                    tbRegistro.setValueAt(resultado.getString("nombre"), nroFilas, 1);
                    tbRegistro.setValueAt(resultado.getString("medida"), nroFilas, 2);
                    tbRegistro.setValueAt(null, nroFilas, 3);
                    nroFilas++;
                    contador++;
                }
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        borrarTabla();
        int nroFilas = 0;
        Conexion con = new Conexion();
        Connection reg = con.obtenerConexion();
        try {
            PreparedStatement consulta = reg.prepareStatement("SELECT * FROM registro");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                DefaultTableModel modeloDeMiTabla = (DefaultTableModel) tbRegistro.getModel();
                modeloDeMiTabla.addRow(new Object[nroFilas]);
                tbRegistro.setValueAt(false, nroFilas, 0);
                tbRegistro.setValueAt(resultado.getString("nombre"), nroFilas, 1);
                tbRegistro.setValueAt(resultado.getString("medida"), nroFilas, 2);
                tbRegistro.setValueAt(null, nroFilas, 3);
                nroFilas++;
                contador++;
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DlgRealizarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgRealizarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgRealizarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgRealizarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgRealizarPedidos dialog = new DlgRealizarPedidos(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConsultarPedido;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbRegistro;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}