import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class AUtils {
    public void createUtils() throws SQLException,ClassNotFoundException{
        DTP dtp = new DTP();
        dtp.setFullDTP();



            Path path = Paths.get(AMain.link+"src/utils/AMySQLConnUtils.java");
            String question = "package utils;\n" +
                    "import java.sql.Connection;\n" +
                    "import java.sql.DriverManager;\n" +
                    "import java.sql.SQLException;\n" +
                    "\n" +
                    "public class AMySQLConnUtils {\n" +
                    "    public static Connection getSqlConnection()\n" +
                    "            throws ClassNotFoundException, SQLException {\n" +
                    "        String hostName = \""+ AMain.hostName+"\";\n" +
                    "        String dbName = \""+ AMain.dbName+"\";\n" +
                    "        String userName = \""+ AMain.userName+"\";\n" +
                    "        String password = \""+ AMain.password+"\";\n" +
                    "        return getSqlConnection(hostName, dbName, userName, password);\n" +
                    "    }\n" +
                    "\n" +
                   "public static Connection getSqlConnection(String hostName, String dbName, String userName, String password) throws SQLException,\n" +
                    "            ClassNotFoundException {\n" +
                    "        Class.forName(\"com.mysql.jdbc.Driver\");\n" +
                    "        String connectionURL = \"jdbc:mysql://\" + hostName + \":3306/\" + dbName;\n" +
                    "\n" +
                    "        Connection connection = DriverManager.getConnection(connectionURL, userName, password);\n" +
                    "        return connection;\n" +
                    "    }\n" +
                    "\n" +
                    "}";




            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Connection MySQL created by Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

