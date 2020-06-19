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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author riel774
 */
public class DAOPendaftaran {

    Connection kon;

    public DAOPendaftaran() {
        kon = new Koneksi().getCon();
    }

    public void insert() {
        try {
            Statement st = kon.createStatement();
            st.execute("insert into pendaftaran values('" + getNewNum() + "', '120000', '-')"
            );
            st.close();
        } catch (SQLException e) {
            System.out.println("insertPendaftaran - " + e);
        }
    }

    public void update(String no, String tagihan, String berkas) {
        try {
            Statement st = kon.createStatement();
            st.executeUpdate("update pendaftaran set tagihan = '" + tagihan
                    + "', kel_berkas = '" + berkas
                    + "' where nomor_pendaftaran = '" + no + "'"
            );
            JOptionPane.showMessageDialog(null, "Berhasil menyimpan data.", "Perhatian", JOptionPane.HEIGHT);
        } catch (SQLException e) {
            System.out.println("updatePendaftaran - " + e);
        }
    }

    public int getNewNum() {
        String no = "";
        try {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery("select count(nomor_pendaftaran) as nomor from pendaftaran");
            if (rs.next()) {
                no += rs.getString("nomor");
            }
        } catch (SQLException e) {
            System.out.println("getNewNum - " + e);
        }
        return no.contains("null") ? 0 : Integer.parseInt(no)+1;
    }
}
