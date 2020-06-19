/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Controller.Koneksi;
import Controller.Peserta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author RieL
 */
public class test {
    public static void main(String[] args) {
        Connection kon = new Koneksi().getCon();
        ArrayList<Peserta> export = new ArrayList<>();
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
            ResultSet rs = st.executeQuery("select nomor_pendaftaran as nomor from pendaftaran where tagihan ='0' && kel_berkas = 'KST, KTM, FOTO'");
            while (rs.next()) {
                kel++;
                Statement state = kon.createStatement();
                ResultSet getInf = state.executeQuery("select nim, nama from data_pendaftar where nomor_pendaftaran = '" + rs.getString("nomor") + "'");
                if (getInf.next()) {
                    Statement st1 = kon.createStatement();
                    st1.execute("insert into peserta values('" + getInf.getString("nim") + "', '" + kel + "')");
                    Peserta pes = new Peserta();
                    pes.setNim(getInf.getString("nim"));
                    pes.setNama(getInf.getString("nama"));
                    Statement k = kon.createStatement();
                    ResultSet kk = k.executeQuery("select nama_kelompok as kelompok from kelompok where nomor_kelompok = '" + kel +"'");
                    if(kk.next()){
                        pes.setKelompok(kk.getString("kelompok"));
                    }
                    export.add(pes);
                }
                if (kel == batas) {
                    kel = 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Generate - " + e);
        }
        for(Peserta i : export){
            System.out.println("=======================");
            System.out.println("NIM\t: "+ i.getNim());
            System.out.println("NAMA\t: "+ i.getNama());
            System.out.println("KELOMPOK: "+ i.getKelompok());
        }
    }
}
