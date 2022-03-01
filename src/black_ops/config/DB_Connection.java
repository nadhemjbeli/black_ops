package black_ops.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB_Connection {
public Connection getConnection(){
Connection connection=null;
try {
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root", "");

} catch (ClassNotFoundException e) {
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
}
return connection;
}

}