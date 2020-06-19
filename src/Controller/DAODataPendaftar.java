/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author riel774
 */
public class DAODataPendaftar {

    Connection kon;

    public DAODataPendaftar() {
        kon = new Koneksi().getCon();
    }


    public void insert(String nim, String nama, String progdi, String jk, String agama, String noHP, String riwayat, String baju) {

        try {
            Statement st = kon.createStatement();
            st.execute(
                    "insert into data_pendaftar values('" + getNewNum() + "', '"
                    + nim + "', '"
                    + nama + "', '"
                    + progdi + "', '"
                    + jk + "', '"
                    + agama + "', '"
                    + noHP + "', '"
                    + riwayat + "', '"
                    + baju + "')"
            );
            JOptionPane.showMessageDialog(null, nim+" berhasil terdaftar.\nPassword: " + noHP);
        } catch (SQLException e) {
            System.out.println("insertPendaftar - " + e);
        }
    }
    
    public void update(String nohp, String riwayat, String ukuran, String nim){
        try {
                Statement st = kon.createStatement();
                st.executeUpdate("update data_pendaftar set nomor_hp = '" + nohp + "',  riwayat_penyakit = '" + riwayat + "', ukuran_baju = '"+ukuran+"' where nim = '" + nim + "'");
                st.close();
                JOptionPane.showMessageDialog(null, "Berhasil menyimpan data.");
            } catch (SQLException e) {
                System.out.println(e);
            }
    }

    public int getNewNum() {
        String no = "";
        try {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery("select count(nomor_pendaftaran) as nomor from data_pendaftar");
            if (rs.next()) {
                no += rs.getString("nomor");
            }
        } catch (SQLException e) {
            System.out.println("getNewNum - " + e);
        }
        return no.contains("null") ? 0 : Integer.parseInt(no) + 1;
    }

}
