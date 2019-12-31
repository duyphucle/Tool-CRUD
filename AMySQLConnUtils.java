import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AMySQLConnUtils {
    public static Connection getSqlConnection()
            throws ClassNotFoundException, SQLException {
        String hostName = AMain.hostName;
        String dbName = AMain.dbName;
        String userName = AMain.userName;
        String password = AMain.password;

        return getSqlConnection(hostName, dbName, userName, password);
    }

    public static Connection getSqlConnection(String hostName, String dbName, String userName, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

}
