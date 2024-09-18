package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection(){
        Connection conexion = null;
        var baseDatos="zona_fit_db";
        var url= "jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario = "root";
        var password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario ,password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la BD "+ e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion= Conexion.getConnection();
        if (conexion!=null)
            System.out.println("Conexion Exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}
