/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Controller.Koneksi;
import java.awt.Image;
import java.io.IOException;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class loginPeserta extends javax.swing.JFrame {

    /**
     * Creates new form loginPeserta
     */
    public loginPeserta() {
        initComponents();
        setLocationRelativeTo(this);
        try {
            Image image = ImageIO.read(this.getClass().getResource("../Gambar/user.png"));
            setIconImage(image);
        } catch (IOException e) {

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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nimField = new javax.swing.JTextField();
        tombolLogin = new javax.swing.JButton();
        tombolBatal = new javax.swing.JButton();
        passField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Pendaftar");

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PASSWORD");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("NIM");

        nimField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tombolLogin.setBackground(new java.awt.Color(255, 255, 255));
        tombolLogin.setText("LOGIN");
        tombolLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tombolLoginKeyPressed(evt);
            }
        });

        tombolBatal.setBackground(new java.awt.Color(255, 255, 255));
        tombolBatal.setText("BATAL");
        tombolBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolBatalActionPerformed(evt);
            }
        });

        passField.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        passField.setEchoChar('*');
        passField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(tombolBatal)
                        .addGap(18, 18, 18)
                        .addComponent(tombolLogin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nimField, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(passField))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3))
                    .addComponent(nimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tombolBatal)
                    .addComponent(tombolLogin))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tombolBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_tombolBatalActionPerformed

    private void passFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passFieldKeyPressed
        if (evt.getKeyCode() == 10) {
            tombolLoginKeyPressed(evt);
        }
    }//GEN-LAST:event_passFieldKeyPressed

    private void tombolLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tombolLoginKeyPressed
        String nim = nimField.getText();
        String pass = passField.getText();
        if (!nim.equals("")) {
            Koneksi kon = new Koneksi();
            try {
                java.sql.Statement st = kon.konek.createStatement();
                ResultSet data;
                do {
                    data = st.executeQuery("SELECT * FROM data_pendaftar WHERE nim = '" + nim + "'");
                    if (data.next()) {
                        String cPass = data.getString("nomor_hp");
                        if (pass.equals(cPass)) {
                            JOptionPane.showMessageDialog(rootPane, nimField.getText() + " berhasil login", "Attention", JOptionPane.HEIGHT);
                            new formPendaftar(nim).setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Maaf, password anda salah.", "WARNING", JOptionPane.WARNING_MESSAGE);
                            passField.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Maaf, NIM anda belum terdaftar.\nSilahkan mendaftar terlebih dahulu.", "Perhatian", JOptionPane.WARNING_MESSAGE);
                        this.dispose();
                        new Home().setVisible(true);
                        break;
                    }
                } while (data.next());
            } catch (Exception e) {
                System.out.println("There is an error : " + e);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Harap masukkan NIM", "Perhatian", JOptionPane.HEIGHT);
            nimField.requestFocus();
        }
    }//GEN-LAST:event_tombolLoginKeyPressed

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
            java.util.logging.Logger.getLogger(loginPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nimField;
    private javax.swing.JPasswordField passField;
    private javax.swing.JButton tombolBatal;
    private javax.swing.JButton tombolLogin;
    // End of variables declaration//GEN-END:variables
}