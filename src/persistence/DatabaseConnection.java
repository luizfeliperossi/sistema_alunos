package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection con = null;

    private static void criarConexao() {
        String url = "jdbc:mysql://localhost:3306/aula";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {

            if (con == null || con.isClosed()) {
                criarConexao();
            }
            return con;
        } catch (SQLException ex) {
            System.out.println("\nErro1");
            return null;
        }
    }
}