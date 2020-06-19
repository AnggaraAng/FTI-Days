/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Controller.DAODataPendaftar;
import Controller.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author riel774
 */
public class formPendaftar extends javax.swing.JFrame {

    public formPendaftar(String nim) {
        initComponents();
        setLocationRelativeTo(this);
        getInfo(nim);
        ktm.setEnabled(false);
        kst.setEnabled(false);
        foto.setEnabled(false);
    }

    Koneksi kon = new Koneksi();

    void getInfo(String nim) {
        String noPendaftaran = "";
        String berkas = "";
        try {
            Statement st = kon.konek.createStatement();
            ResultSet data;
            data = st.executeQuery("select * from data_pendaftar where nim = '" + nim + "'");
            if (data.next()) {
                namaTxt.setText(data.getString("nama"));
                namaPeserta.setText(data.getString("nama"));
                nimTxt.setText(nim);
                progdiTxt.setText(data.getString("progdi"));
                jkTxt.setText(data.getString("jenis_kelamin"));
                agamaTxt.setText(data.getString("agama"));
                nomorHPTxt.setText(data.getString("nomor_hp"));
                riwayatField.setText(data.getString("riwayat_penyakit"));
                String ukuran = data.getString("ukuran_baju");
                ukuranTxt.setText(ukuran);
                noPendaftaran = data.getString("nomor_pendaftaran");
                no.setText(noPendaftaran);
            }
            data = st.executeQuery("select * from pendaftaran where nomor_pendaftaran = '" + noPendaftaran + "'");
            if (data.next()) {
                System.out.println("Pendaftaran");
                tagihanTxt.setText(data.getString("tagihan"));

                berkas = data.getString("kel_berkas");
                if (berkas.contains("KST")) {
                    kst.setSelected(true);
                }
                if (berkas.contains("KTM")) {
                    ktm.setSelected(true);
                }
                if (berkas.contains("FOTO")) {
                    foto.setSelected(true);
                }
            }
            st.close();
        } catch (SQLException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        namaPeserta = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        riwayatField = new javax.swing.JTextArea();
        ktm = new javax.swing.JCheckBox();
        kst = new javax.swing.JCheckBox();
        foto = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        namaTxt = new javax.swing.JLabel();
        nimTxt = new javax.swing.JLabel();
        progdiTxt = new javax.swing.JLabel();
        jkTxt = new javax.swing.JLabel();
        agamaTxt = new javax.swing.JLabel();
        nomorHPTxt = new javax.swing.JLabel();
        tagihanTxt = new javax.swing.JLabel();
        ukuranTxt = new javax.swing.JLabel();
        no = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Info Peserta");

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        namaPeserta.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        namaPeserta.setForeground(new java.awt.Color(255, 255, 255));
        namaPeserta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaPeserta.setText("nama_pendaftar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(namaPeserta, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(namaPeserta)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("NAMA");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("NIM");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("PROGDI");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("JENIS KELAMIN");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("AGAMA");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("NOMOR HP");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("RIWAYAT PENYAKIT");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("KEL. BERKAS");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("UKURAN BAJU");

        riwayatField.setEditable(false);
        riwayatField.setColumns(20);
        riwayatField.setRows(5);
        jScrollPane1.setViewportView(riwayatField);

        ktm.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ktm.setText("FC KTM");

        kst.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        kst.setText("FC KST");

        foto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        foto.setText("FOTO");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("TAGIHAN");

        namaTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        namaTxt.setText("nama");

        nimTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nimTxt.setText("nim");

        progdiTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        progdiTxt.setText("progdi");

        jkTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jkTxt.setText("jk");

        agamaTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        agamaTxt.setText("agama");

        nomorHPTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nomorHPTxt.setText("nomor");

        tagihanTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tagihanTxt.setText("tagihan");

        ukuranTxt.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ukuranTxt.setText("ukuran");

        no.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        no.setText("nomorPendaftaran");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("No. Pendaftaran");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tagihanTxt)
                            .addComponent(nimTxt)
                            .addComponent(namaTxt)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(progdiTxt)
                            .addComponent(jkTxt)
                            .addComponent(agamaTxt)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ktm)
                                .addGap(18, 18, 18)
                                .addComponent(kst)
                                .addGap(18, 18, 18)
                                .addComponent(foto))
                            .addComponent(ukuranTxt)
                            .addComponent(nomorHPTxt)
                            .addComponent(no))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(namaTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nimTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(progdiTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jkTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(agamaTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nomorHPTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagihanTxt)
                    .addComponent(jLabel17))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ktm)
                    .addComponent(kst)
                    .addComponent(foto))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ukuranTxt))
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(formPendaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPendaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPendaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPendaftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new formPendaftar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agamaTxt;
    private javax.swing.JCheckBox foto;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jkTxt;
    private javax.swing.JCheckBox kst;
    private javax.swing.JCheckBox ktm;
    private javax.swing.JLabel namaPeserta;
    private javax.swing.JLabel namaTxt;
    private javax.swing.JLabel nimTxt;
    private javax.swing.JLabel no;
    private javax.swing.JLabel nomorHPTxt;
    private javax.swing.JLabel progdiTxt;
    private javax.swing.JTextArea riwayatField;
    private javax.swing.JLabel tagihanTxt;
    private javax.swing.JLabel ukuranTxt;
    // End of variables declaration//GEN-END:variables
}
