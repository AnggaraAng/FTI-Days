package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riel774
 */
public class Koneksi {

    public Connection konek;

    public Koneksi(){
        String Driver, url, user, pass;
        Driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/ftidays?zeroDateTimeBehavior=convertToNull";
        user = "root";
        pass = "";
        try {
            Class.forName(Driver);
            konek = DriverManager.getConnection(url, user, pass);
            System.out.println("Berhasi terhubung ke database");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getCon() {
        return this.konek;
    }
}
