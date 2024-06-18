package Base;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectionFabric {
    private static final String DRIVE= "com.mysql.cj.jdbc.Driver";
    private static final String URL= "jdbc:mysql://localhost:3306/estoque";
    private static final String USER= "root" ;
    private static final String PASS= "2004";

    public static Connection getConection() {
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(ClassNotFoundException | SQLException e){
        	System.out.println(e);
            throw new RuntimeException("erro na conexao com o banco de dados: ");
        }
    }

    public static void Closeconection(Connection con) {
        try {
            if(con!=null) {
                con.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Closeconection(Connection con, PreparedStatement stmt) {
        try {
            if(stmt != null) {
                stmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void Closeconection(Connection con, PreparedStatement stmt, ResultSet rs) {
        Closeconection(con, stmt);
        try {
            if(rs != null) {
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}