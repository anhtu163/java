
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBC_Database{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USER = "root";
    static final String PASS = "123456";
    public static void main (String args[]){

        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connect to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Create Statement...");
            stmt = conn.createStatement();
            String sql;
            stmt.executeUpdate("INSERT INTO Employees(id, first, last, age)"+ "VALUES (104, 'Anh', 'Tu', 24) ");
            sql = "SELECT id, first, last, age FROM Employees";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Age: " + age + ", First: " + first + ", Last: " + last);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }
    }
}
