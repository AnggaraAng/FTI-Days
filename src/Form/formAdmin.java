/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Controller.Koneksi;
import Controller.Peserta;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author riel774
 */
public class formAdmin extends javax.swing.JFrame {

    private String nim, tagihan = "0", uang = "0", noUrut = "";
    private int totalPeserta, totalPendaftar;
    private ArrayList<Peserta> backup = new ArrayList<>();
    boolean cek = false;
    Connection kon;

    public formAdmin() {
        try {
            Image image = ImageIO.read(this.getClass().getResource("../Gambar/admin.png"));
            setIconImage(image);
        } catch (IOException e) {

        }

        initComponents();
        setLocationRelativeTo(this);

        kon = new Koneksi().getCon();

        refreshAllTable();

        ktmCB.setSelected(false);
        kstCB.setSelected(false);
        fotoCB.setSelected(false);

        nimCB.setSelected(true);
        nimCB.setEnabled(false);
    }

    void refreshAllTable() {
        tabelPendaftaran();
        tabelDataPendaftar();
        tabelPeserta();
        tabelKelompok();
        tabelPesertaFull();
    }

    void tabelPendaftaran() {
        DefaultTableModel modelPendaftaran = new DefaultTableModel();
        modelPendaftaran.addColumn("No.");
        modelPendaftaran.addColumn("Tagihan");
        modelPendaftaran.addColumn("Berkas");
        tabelPendaftaran.setModel(modelPendaftaran);

        try {
            Statement st = kon.createStatement();
            ResultSet data1 = st.executeQuery("select * from pendaftaran order by nomor_pendaftaran");
            while (data1.next()) {
                modelPendaftaran.addRow(new Object[]{
                    data1.getString("nomor_pendaftaran"),
                    data1.getString("tagihan"),
                    data1.getString("kel_berkas"),});
                tabelPendaftaran.setModel(modelPendaftaran);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("error table pendaftaran - " + e);
        }
    }

    void tabelDataPendaftar() {
        totalPendaftar = 0;
        DefaultTableModel modelPendaftar = new DefaultTableModel();
        modelPendaftar.addColumn("No.");
        modelPendaftar.addColumn("Nama");
        modelPendaftar.addColumn("NIM");
        modelPendaftar.addColumn("Progdi");
        modelPendaftar.addColumn("Jenis Kelamin");
        modelPendaftar.addColumn("Agama");
        modelPendaftar.addColumn("No. HP");
        modelPendaftar.addColumn("Riwayat Penyakit");
        modelPendaftar.addColumn("Ukuran Baju");
        tabelDataPendaftar.setModel(modelPendaftar);
        try {
            java.sql.Statement st = kon.createStatement();
            ResultSet data = st.executeQuery("select * from data_pendaftar order by nomor_pendaftaran");
            while (data.next()) {
                totalPendaftar++;
                modelPendaftar.addRow(new Object[]{
                    data.getString("nomor_pendaftaran"),
                    data.getString("nama"),
                    data.getString("nim"),
                    data.getString("progdi"),
                    data.getString("jenis_kelamin"),
                    data.getString("agama"),
                    data.getString("nomor_hp"),
                    data.getString("riwayat_penyakit"),
                    data.getString("ukuran_baju"),});
                tabelDataPendaftar.setModel(modelPendaftar);
            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an error : " + e);
        }
        jPendaftar.setText("Jumlah Pendaftar: " + totalPendaftar);
    }

    void tabelPeserta() {
        this.totalPeserta = 0;
        DefaultTableModel modelPeserta = new DefaultTableModel();
        modelPeserta.addColumn("NIM");
        modelPeserta.addColumn("Kelompok");
        tabelPeserta.setModel(modelPeserta);
        try {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery("select * from peserta order by nomor_kelompok");
            while (rs.next()) {
                totalPeserta++;
                modelPeserta.addRow(new Object[]{
                    rs.getString("nim"),
                    rs.getString("nomor_kelompok"),});
                tabelPeserta.setModel(modelPeserta);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("tabelPeserta - " + e);
        }

        jPeserta.setText("Jumlah Peserta: " + this.totalPeserta);
    }

    void tabelKelompok() {
        DefaultTableModel modelKelompok = new DefaultTableModel();
        modelKelompok.addColumn("Nomor");
        modelKelompok.addColumn("Nama Kelompok");
        tabelKelompok.setModel(modelKelompok);
        try {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery("select * from kelompok");
            while (rs.next()) {
                modelKelompok.addRow(new Object[]{
                    rs.getString("nomor_kelompok"),
                    rs.getString("nama_kelompok")
                });
                tabelKelompok.setModel(modelKelompok);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("tabelKelompok - " + e);
        }
    }

    void tabelPesertaFull() {
        backup.clear();
        
        try {
            Statement st = kon.createStatement();
            ResultSet getPeserta = st.executeQuery("select * from peserta order by nim");
            while (getPeserta.next()) {
                Peserta pes = new Peserta();
                pes.setNim(getPeserta.getString("nim"));
                Statement stInf = kon.createStatement();
                ResultSet rsInf = stInf.executeQuery("select * from data_pendaftar where nim = '" + getPeserta.getString("nim") + "'");
                if (rsInf.next()) {
                    pes.setNama(rsInf.getString("nama"));
                    pes.setProgdi(rsInf.getString("progdi"));
                    pes.setJenis_kelamin(rsInf.getString("jenis_kelamin"));
                    pes.setAgama(rsInf.getString("agama"));
                    pes.setNohp(rsInf.getString("nomor_hp"));
                    pes.setRiwayat(rsInf.getString("riwayat_penyakit"));
                    pes.setBaju(rsInf.getString("ukuran_baju"));
                }
                stInf.close();
                Statement stKel = kon.createStatement();
                ResultSet getKel = stKel.executeQuery("select nama_kelompok as kelompok from kelompok where nomor_kelompok = '" + getPeserta.getString("nomor_kelompok") + "'");
                if (getKel.next()) {
                    pes.setKelompok(getKel.getString("kelompok"));
                }
                stKel.close();
                backup.add(pes);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("get backup err - " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nimField = new javax.swing.JTextField();
        tombolCek = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tagihanTxt = new javax.swing.JLabel();
        kstCB = new javax.swing.JCheckBox();
        ktmCB = new javax.swing.JCheckBox();
        fotoCB = new javax.swing.JCheckBox();
        tombolSimpan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPendaftaran = new javax.swing.JTable();
        tombolBatal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDataPendaftar = new javax.swing.JTable();
        jPendaftar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPeserta = new javax.swing.JTable();
        jPeserta = new javax.swing.JLabel();
        tombolGenerate = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelKelompok = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        namaKelompokField = new javax.swing.JTextField();
        tambahKelompok = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tombolBackup = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        nimCB = new javax.swing.JCheckBox();
        namaCB = new javax.swing.JCheckBox();
        progdiCB = new javax.swing.JCheckBox();
        jkCB = new javax.swing.JCheckBox();
        agamaCB = new javax.swing.JCheckBox();
        noHpCB = new javax.swing.JCheckBox();
        riwayatCB = new javax.swing.JCheckBox();
        ukuranCB = new javax.swing.JCheckBox();
        kelompokCB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("NIM");

        nimField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nimField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nimFieldKeyPressed(evt);
            }
        });

        tombolCek.setBackground(new java.awt.Color(255, 255, 255));
        tombolCek.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tombolCek.setText("CEK");
        tombolCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolCekActionPerformed(evt);
            }
        });
        tombolCek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tombolCekKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Tagihan");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("KST");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("KTM");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("FOTO");

        tagihanTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tagihanTxt.setText("tagihan");
        tagihanTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tagihanTxtMouseClicked(evt);
            }
        });

        tombolSimpan.setBackground(new java.awt.Color(255, 255, 255));
        tombolSimpan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tombolSimpan.setText("SIMPAN");
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        tabelPendaftaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No.", "Tagihan", "Kel. Berkas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPendaftaran.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabelPendaftaran);

        tombolBatal.setBackground(new java.awt.Color(255, 255, 255));
        tombolBatal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tombolBatal.setText("BATAL");
        tombolBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(tombolBatal)
                            .addGap(18, 18, 18)
                            .addComponent(tombolSimpan))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(78, 78, 78)
                            .addComponent(nimField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tombolCek)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tagihanTxt)
                            .addComponent(kstCB)
                            .addComponent(ktmCB)
                            .addComponent(fotoCB))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolCek))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ktmCB)
                                .addGap(35, 35, 35))
                            .addComponent(fotoCB)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tagihanTxt))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kstCB)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tombolSimpan)
                            .addComponent(tombolBatal)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pendaftaran", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tabelDataPendaftar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "NIM", "Progdi", "Jenis Kelamin", "Agama", "No. HP", "Riwayat Penyakit", "Kelengkapan Berkas", "Ukuran Baju"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelDataPendaftar.getTableHeader().setResizingAllowed(false);
        tabelDataPendaftar.getTableHeader().setReorderingAllowed(false);
        tabelDataPendaftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDataPendaftarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelDataPendaftar);

        jPendaftar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPendaftar.setText("Jumlah Pendaftar:  jumlah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPendaftar)
                .addContainerGap(817, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(446, Short.MAX_VALUE)
                .addComponent(jPendaftar)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(82, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Data Pendaftar", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tabelPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NIM", "Kelompok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPeserta.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelPeserta);

        jPeserta.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPeserta.setText("Jumlah Peserta: jumlah");

        tombolGenerate.setBackground(new java.awt.Color(255, 255, 255));
        tombolGenerate.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tombolGenerate.setText("GENERATE");
        tombolGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPeserta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tombolGenerate)))
                .addContainerGap(387, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPeserta)
                    .addComponent(tombolGenerate))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Daftar Peserta", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tabelKelompok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelKelompok.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tabelKelompok);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("Nama Kelompok");

        namaKelompokField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        tambahKelompok.setBackground(new java.awt.Color(255, 255, 255));
        tambahKelompok.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tambahKelompok.setText("TAMBAH");
        tambahKelompok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahKelompokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(namaKelompokField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tambahKelompok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(namaKelompokField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(tambahKelompok))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kelompok", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tombolBackup.setBackground(new java.awt.Color(255, 255, 255));
        tombolBackup.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tombolBackup.setText("BACKUP");
        tombolBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolBackupActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Kolom");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("NIM");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setText("Nama");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setText("Agama");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setText("Jenis Kelamin");

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setText("Progdi");

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setText("No. HP");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setText("Riwayat Penyakit");

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel17.setText("Kelompok");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setText("Ukuran Baju");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("Keterangan");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tombolBackup)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kelompokCB)
                            .addComponent(ukuranCB)
                            .addComponent(riwayatCB)
                            .addComponent(noHpCB)
                            .addComponent(agamaCB)
                            .addComponent(jkCB)
                            .addComponent(progdiCB)
                            .addComponent(namaCB)
                            .addComponent(jLabel16)
                            .addComponent(nimCB))))
                .addContainerGap(406, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nimCB)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(namaCB))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(progdiCB))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12))
                            .addComponent(jkCB, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(agamaCB))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(noHpCB))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addComponent(riwayatCB, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(ukuranCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(kelompokCB))
                .addGap(18, 18, 18)
                .addComponent(tombolBackup)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Back Up", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1059, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelDataPendaftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDataPendaftarMouseClicked

    }//GEN-LAST:event_tabelDataPendaftarMouseClicked

    private void tombolCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolCekActionPerformed
        String cariNim = nimField.getText();
        if (!cariNim.equals("")) {
            try {
                Statement st = kon.createStatement();
                ResultSet rs = st.executeQuery("select * from data_pendaftar where nim = '" + cariNim + "'");
                if (rs.next()) {
                    noUrut = rs.getString("nomor_pendaftaran");
                    nim = rs.getString("nim");
                    Statement state = kon.createStatement();
                    ResultSet rS;
                    rS = state.executeQuery("select * from pendaftaran where nomor_pendaftaran = '" + noUrut + "'");
                    if (rS.next()) {
                        nimField.setEnabled(false);
                        this.tagihan = rS.getString("tagihan");
                        tagihanTxt.setText(tagihan);
                        System.out.println(tagihanTxt.getText());
                        if (rS.getString("kel_berkas").contains("KST")) {
                            kstCB.setSelected(true);
                            kstCB.setEnabled(false);
                        }
                        if (rS.getString("kel_berkas").contains("KTM")) {
                            ktmCB.setSelected(true);
                            ktmCB.setEnabled(false);
                        }
                        if (rS.getString("kel_berkas").contains("FOTO")) {
                            fotoCB.setSelected(true);
                            fotoCB.setEnabled(false);
                        }
                        cek = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NIM belum terdaftar.", "Perhatian", JOptionPane.WARNING_MESSAGE);
                }
                st.close();
            } catch (SQLException e) {
                System.out.println("Ambil nomor " + e);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Harap masukkan NIM yang ingin di cari.");
        }
    }//GEN-LAST:event_tombolCekActionPerformed

    private void tagihanTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tagihanTxtMouseClicked
        if (!this.tagihan.equals("LUNAS")) {
            uang = JOptionPane.showInputDialog(rootPane, "Masukkan jumlah uang yang sudah dibayar", "Pembayaran", WIDTH);
            try {
                if (Integer.parseInt(uang) > Integer.parseInt(tagihan)) {
                    JOptionPane.showMessageDialog(rootPane, "Jumlah tidak valid!", "ERROR", HEIGHT);
                    uang = "0";
                } else {
                    tagihanTxt.setText(tagihan + " - " + uang);
                }
            } catch (Exception e) {
                uang = "0";
                JOptionPane.showMessageDialog(rootPane, e, "Input Error", HEIGHT);
            }
        }
    }//GEN-LAST:event_tagihanTxtMouseClicked

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        if (cek) {
            int benar = JOptionPane.showConfirmDialog(rootPane, "Apakah data tersebut sudah benar?", "Perhatian", HEIGHT);
            String total = "";
            try {
                total = Integer.parseInt(tagihan) - Integer.parseInt(uang) + "";
            } catch (NumberFormatException e) {
                System.out.println(e);
                total = "LUNAS";
            }
            uang = "0";
            if (total.equals("0")) {
                total = "LUNAS";
            }
            String kel_berkas = "";
            if (kstCB.isSelected()) {
                kel_berkas += "KST, ";
            }
            if (ktmCB.isSelected()) {
                kel_berkas += "KTM, ";
            }
            if (fotoCB.isSelected()) {
                kel_berkas += "FOTO";
            }
            if (benar == 0) {
                try {
                    Statement st = kon.createStatement();
                    st.executeUpdate("update pendaftaran set tagihan = '" + total + "', kel_berkas = '" + kel_berkas + "' where nomor_pendaftaran = '" + noUrut + "'");
                    st.close();
                    nimField.setText("");
                    tagihanTxt.setText("tagihan");
                    cek = false;
                    kstCB.setSelected(false);
                    ktmCB.setSelected(false);
                    fotoCB.setSelected(false);
                    kstCB.setEnabled(true);
                    ktmCB.setEnabled(true);
                    fotoCB.setEnabled(true);
                    nimField.setEnabled(true);
                } catch (SQLException e) {
                    System.out.println("atur tagihan " + e);
                }
            }

            tabelPendaftaran();
        }
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tambahKelompokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahKelompokActionPerformed
        int nomor;
        if (!namaKelompokField.getText().equals("")) {
            try {
                Statement st = kon.createStatement();
                ResultSet rs = st.executeQuery("select count(nomor_kelompok) as total from kelompok");
                if (rs.next()) {
                    nomor = Integer.parseInt(rs.getString("total")) + 1;
                } else {
                    nomor = 1;
                }
                st.execute("insert into kelompok values('" + nomor + "','" + namaKelompokField.getText() + "')");
                tabelKelompok();
                namaKelompokField.setText("");
            } catch (SQLException e) {
                System.out.println("tambahKelompok - " + e);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Harap masukkan nama kelompok.");
        }
    }//GEN-LAST:event_tambahKelompokActionPerformed

    private void tombolGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolGenerateActionPerformed
        int kel = 0;
        int batas = 0;
        try {
            Statement klp = kon.createStatement();
            ResultSet getKel = klp.executeQuery("select count(nomor_kelompok) as total from kelompok");
            if (getKel.next()) {
                batas = Integer.parseInt(getKel.getString("total"));
            }
            Statement del = kon.createStatement();
            del.execute("delete from peserta");
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery("select nomor_pendaftaran as nomor from pendaftaran where tagihan ='LUNAS' && kel_berkas = 'KST, KTM, FOTO'");
            while (rs.next()) {
                kel++;
                Statement state = kon.createStatement();
                ResultSet getInf = state.executeQuery("select nim, nama from data_pendaftar where nomor_pendaftaran = '" + rs.getString("nomor") + "'");
                if (getInf.next()) {
                    Statement st1 = kon.createStatement();
                    st1.execute("insert into peserta values('" + getInf.getString("nim") + "', '" + kel + "')");
                }
                if (kel == batas) {
                    kel = 0;
                }
            }
            tabelPeserta();
            tabelPesertaFull();
        } catch (SQLException e) {
            System.out.println("Generate - " + e);
        }
    }//GEN-LAST:event_tombolGenerateActionPerformed

    private void tombolBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolBackupActionPerformed
        String backUpNama = "", backUpProgdi = "", backUpJK = "", backUpAgama = "", backUpNoHp = "", backUpRiwayat = "", backUpBaju = "", backUpKelompok = "";

        if (namaCB.isSelected()) {
            backUpNama = "Nama\t";
        }
        if (progdiCB.isSelected()) {
            backUpProgdi = "Progdi\t";
        }
        if (jkCB.isSelected()) {
            backUpJK = "Jenis Kelamin\t";
        }
        if (agamaCB.isSelected()) {
            backUpAgama = "Agama\t";
        }
        if (noHpCB.isSelected()) {
            backUpNoHp = "No. HP\t";
        }
        if (riwayatCB.isSelected()) {
            backUpRiwayat = "Riwayat Penyakit\t";
        }
        if (ukuranCB.isSelected()) {
            backUpBaju = "Ukuran Baju\t";
        }
        if (kelompokCB.isSelected()) {
            backUpKelompok = "Kelompok";
        }

        FileWriter fw;
        JFileChooser choose = new JFileChooser();
        choose.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
        int retrive = choose.showSaveDialog(null);
        if (retrive == JFileChooser.APPROVE_OPTION) {
            try {
                fw = new FileWriter(new File(choose.getSelectedFile() + ".xls"));
                fw.write("NIM\t" + backUpNama + backUpProgdi + backUpJK + backUpAgama + backUpNoHp + backUpRiwayat + backUpBaju + backUpKelompok + "\n");
                for (Peserta i : backup) {
                    String teks = i.getNim();
                    teks += backUpNama.equals("") ? "" : "\t" + i.getNama();
                    teks += backUpProgdi.equals("") ? "" : "\t" + i.getProgdi();
                    teks += backUpJK.equals("") ? "" : "\t" + i.getJenis_kelamin();
                    teks += backUpAgama.equals("") ? "" : "\t" + i.getAgama();
                    teks += backUpNoHp.equals("") ? "" : "\t" + i.getNohp();
                    teks += backUpRiwayat.equals("") ? "" : "\t" + i.getRiwayat();
                    teks += backUpBaju.equals("") ? "" : "\t" + i.getBaju();
                    teks += backUpKelompok.equals("") ? "" : "\t" + i.getKelompok();
                    fw.write(teks + "\n");
                }
                fw.close();
                JOptionPane.showMessageDialog(rootPane, "Berhasil di backup", "Perhatian", WIDTH);
            } catch (IOException e) {
                String err = e + "";
                if (err.contains("is being used by another process")) {
                    JOptionPane.showMessageDialog(rootPane, "Tidak bisa menimpa file, karena file " + choose.getSelectedFile() + ".xls sedang digunakan oleh proses lain", "ERROR", JOptionPane.HEIGHT);
                }
                System.out.println(err.substring(err.indexOf('(') + 1, err.indexOf(')')));
            }
        }
    }//GEN-LAST:event_tombolBackupActionPerformed

    private void tombolBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolBatalActionPerformed
        cek = false;
        kstCB.setSelected(false);
        ktmCB.setSelected(false);
        fotoCB.setSelected(false);
        kstCB.setEnabled(true);
        ktmCB.setEnabled(true);
        fotoCB.setEnabled(true);
        nimField.setEnabled(true);
        nimField.setText("");
        tagihanTxt.setText("tagihan");
    }//GEN-LAST:event_tombolBatalActionPerformed

    private void nimFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nimFieldKeyPressed
        tombolCekKeyPressed(evt);
    }//GEN-LAST:event_nimFieldKeyPressed

    private void tombolCekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tombolCekKeyPressed
        if (evt.getKeyCode() == 10) {
            String cariNim = nimField.getText();
            if (!cariNim.equals("")) {
                try {
                    Statement st = kon.createStatement();
                    ResultSet rs = st.executeQuery("select * from data_pendaftar where nim = '" + cariNim + "'");
                    if (rs.next()) {
                        noUrut = rs.getString("nomor_pendaftaran");
                        nim = rs.getString("nim");
                        Statement state = kon.createStatement();
                        ResultSet rS;
                        rS = state.executeQuery("select * from pendaftaran where nomor_pendaftaran = '" + noUrut + "'");
                        if (rS.next()) {
                            nimField.setEnabled(false);
                            this.tagihan = rS.getString("tagihan");
                            if (this.tagihan.equals("0")) {
                                tagihan = "Lunas";
                            }
                            tagihanTxt.setText(tagihan);
                            System.out.println(tagihanTxt.getText());
                            if (rS.getString("kel_berkas").contains("KST")) {
                                kstCB.setSelected(true);
                                kstCB.setEnabled(false);
                            }
                            if (rS.getString("kel_berkas").contains("KTM")) {
                                ktmCB.setSelected(true);
                                ktmCB.setEnabled(false);
                            }
                            if (rS.getString("kel_berkas").contains("FOTO")) {
                                fotoCB.setSelected(true);
                                fotoCB.setEnabled(false);
                            }
                            cek = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "NIM belum terdaftar.", "Perhatian", JOptionPane.WARNING_MESSAGE);
                    }
                    st.close();
                } catch (SQLException e) {
                    System.out.println("Ambil nomor " + e);
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Harap masukkan NIM yang ingin di cari.");
            }
        }
    }//GEN-LAST:event_tombolCekKeyPressed

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
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox agamaCB;
    private javax.swing.JCheckBox fotoCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jPendaftar;
    private javax.swing.JLabel jPeserta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox jkCB;
    private javax.swing.JCheckBox kelompokCB;
    private javax.swing.JCheckBox kstCB;
    private javax.swing.JCheckBox ktmCB;
    private javax.swing.JCheckBox namaCB;
    private javax.swing.JTextField namaKelompokField;
    private javax.swing.JCheckBox nimCB;
    private javax.swing.JTextField nimField;
    private javax.swing.JCheckBox noHpCB;
    private javax.swing.JCheckBox progdiCB;
    private javax.swing.JCheckBox riwayatCB;
    private javax.swing.JTable tabelDataPendaftar;
    private javax.swing.JTable tabelKelompok;
    private javax.swing.JTable tabelPendaftaran;
    private javax.swing.JTable tabelPeserta;
    private javax.swing.JLabel tagihanTxt;
    private javax.swing.JButton tambahKelompok;
    private javax.swing.JButton tombolBackup;
    private javax.swing.JButton tombolBatal;
    private javax.swing.JButton tombolCek;
    private javax.swing.JButton tombolGenerate;
    private javax.swing.JButton tombolSimpan;
    private javax.swing.JCheckBox ukuranCB;
    // End of variables declaration//GEN-END:variables
}
