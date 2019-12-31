import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ACreateJDBC {
    public void createInterFace() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "src/service/" + dtp.table(i).toLowerCase() + "/" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service.java");
            String question = "package service." + dtp.table(i).toLowerCase() + ";\n" +
                    "import model." + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + ";\n" +
                    "import java.util.List;\n" +
                    "public interface " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service {\n" +
                    "   List<" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "> findAll();\n\n" +
                    "   void save(" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + ");\n\n   "
                    + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " findById(" + dtp.type(i, 0) + " " + dtp.column(i, 0).toLowerCase() + ");\n\n" +
                    "   void update(" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + ");\n\n" +
                    "   void remove(" + dtp.type(i, 0) + " " + dtp.column(i, 0).toLowerCase() + ", " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + ");\n\n" +
                    "}";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("JDBC Interface created by Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void createService() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "src/service/" + dtp.table(i).toLowerCase() + "/" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl.java");
            String question = "package service." + dtp.table(i).toLowerCase() + ";\n";
            question += "import utils.AMySQLConnUtils;\n";
            question += "import model." + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + ";\n";
            // question += "import javax.servlet.http.HttpSession;\n" +
            question += "import java.sql.*;\n" +
                    "import java.sql.Connection;\n" +
                    // "import java.sql.DriverManager;\n" +
                    "import java.sql.PreparedStatement;\n" +
                    "import java.sql.ResultSet;\n" +
                    "import java.sql.SQLException;\n" +
                    "import java.util.ArrayList;\n" +
                    "import java.util.List;\n" +
                    "import java.time.LocalDate;\n";
            question += "public class " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl implements " +
                    dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service {\n" +
                    "   Connection connection = AMySQLConnUtils.getSqlConnection();\n";
            question += "   private static final String INSERT_" + dtp.table(i).toUpperCase() + "_SQL = \"INSERT INTO `" +
                    dtp.table(i) + "`( ";
            for (int j = 1; j < dtp.columnSize(i); j++) {
                question += "`" + dtp.column(i, j) + "`,";
            }
            question += "`created_at`,`created_by`)VALUES \" + \"(";
            for (int j = 1; j < dtp.columnSize(i); j++) {
                question += "?,";
            }
            question += "?,?);\";\n";
            question += "   private static final String SELECT_" + dtp.table(i).toUpperCase() + "_BY_ID = \"select * " +
                    "from `" + dtp.table(i) + "` where `" + dtp.column(i, 0) + "` =?\";\n";
            question += "   private static final String SELECT_ALL_" + dtp.table(i).toUpperCase() + " " +
                    "= \"select * from `" + dtp.table(i) + "` where `isDeleted` = 0 \" ;\n";
            question += "   private static final String UPDATE_" + dtp.table(i).toUpperCase() + "_SQL = \"update `" + dtp.table(i) + "`" +
                    " set ";
            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
                question += "`" + dtp.column(i, j) + "` = ?,";
            }

            question += "`" + dtp.column(i, dtp.columnSize(i) - 1) + "`  = ? , `updated_at` = ?, `updated_by` = ? where `"
                    + dtp.column(i, 0) + "`= ?;\";\n";
            question += "   private static final String DELETE_" + dtp.table(i).toUpperCase() + "_SQL =" +
                    " \"update `" + dtp.table(i) + "` set `IsDeleted` = 1 ,`deleted_at` = ?, `deleted_by` = ? where `"
                    + dtp.column(i, 0) + "` = ?;\";\n";
            question += "   public " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl() throws SQLException, ClassNotFoundException {\n" +
                    "       }\n";
            question += "   private void printSQLException(SQLException ex) {\n" +
                    "        for (Throwable e : ex) {\n" +
                    "            if (e instanceof SQLException) {\n" +
                    "                e.printStackTrace(System.err);\n" +
                    "                System.err.println(\"SQLState: \" + ((SQLException) e).getSQLState());\n" +
                    "                System.err.println(\"Error Code: \" + ((SQLException) e).getErrorCode());\n" +
                    "                System.err.println(\"Message: \" + e.getMessage());\n" +
                    "                Throwable t = ex.getCause();\n" +
                    "                while (t != null) {\n" +
                    "                    System.out.println(\"Cause: \" + t);\n" +
                    "                    t = t.getCause();\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n";
            question += "   @Override\n";
            question += "   public List <" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "> findAll(){\n";
            question += "      List <" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "> " + dtp.table(i).toLowerCase() + "= new ArrayList<>();\n";
            question += "      try (\n" +
                    "          PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_" + dtp.table(i).toUpperCase() + ");) {\n" +
                    "          System.out.println(preparedStatement);\n" +
                    "          ResultSet rs = preparedStatement.executeQuery();\n" +
                    "          while (rs.next()) {\n";
            for (int j = 0; j < dtp.columnSize(i); j++) {
                switch (dtp.type(i, j)) {
                    case "int":
                        question += "              int " + dtp.column(i, j).toLowerCase() + " = rs.getInt(\"" + dtp.column(i, j) + "\");\n";
                        break;
                    case "String":
                        question += "              String " + dtp.column(i, j).toLowerCase() + " = rs.getString(\"" + dtp.column(i, j) + "\");\n";
                        break;
                    case "double":
                        question += "              double " + dtp.column(i, j).toLowerCase() + " = rs.getDouble(\"" + dtp.column(i, j) + "\");\n";
                        break;
                    default:
                        question += "              LocalDate " + dtp.column(i, j).toLowerCase() + "= LocalDate.parse(rs.getString(\"" + dtp.column(i, j) + "\"));\n";
                        break;
                }
            }
            question += "              " + dtp.table(i).toLowerCase() + ".add (new " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(";
            for (int j = 0; j < dtp.columnSize(i) - 1; j++) {
                question += dtp.column(i, j).toLowerCase() + " , ";
            }
            question += dtp.column(i, dtp.columnSize(i) - 1).toLowerCase() + "));" +
                    "       \n          }\n";

            question += "      } catch (SQLException e) {\n" +
                    "          printSQLException(e);\n" +
                    "      }\n" +
                    "          return " + dtp.table(i).toLowerCase() + ";\n" +
                    "   }\n" +
                    "   @Override\n";
            question += "   public void update(" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + "){\n";
            question += "      boolean rowUpdated;\n" +
                    "      try (\n";
            question += "         PreparedStatement statement = connection.prepareStatement(UPDATE_" + dtp.table(i).toUpperCase() + "_SQL);) {\n";
            int clum = dtp.columnSize(i);
            for (int j = 1; j < dtp.columnSize(i); j++) {

                switch (dtp.type(i, j)) {
                    case "int":
                        question += "         statement.setInt(" + j + "," + dtp.table(i).toLowerCase() + ".get" +
                                dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "String":
                        question += "         statement.setString(" + j + "," + dtp.table(i).toLowerCase() +
                                ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "double":
                        question += "         statement.setDouble(" + j + "," + dtp.table(i).toLowerCase() +
                                ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "LocalDate":
                        question += "         statement.setDate(" + j + ",Date.valueOf(" + dtp.table(i).toLowerCase() + ".get"
                                + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "()));\n";

                        break;
                    default:
                        question += "         Thieu kieu du lieu";
                        break;
                }
            }
            question += "         statement.setDate(" + clum + ", Date.valueOf(" + dtp.table(i).toLowerCase() + ".getUpdated_at()));\n";
            clum++;
            question += "         statement.setString(" + clum + ", " + dtp.table(i).toLowerCase() + ".getUpdated_by());\n";
            clum++;
            question += "         statement.setInt(" + clum + ", " + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() + dtp.column(i, 0).substring(1) + "());";
//
            question += "         rowUpdated = statement.executeUpdate() > 0;\n" +
                    "      } catch (SQLException e) {\n" +
                    "         printSQLException(e);\n" +
                    "      }\n" +
                    "    }\n" +
                    "    @Override\n";
            question += "    public void remove(" + dtp.type(i, 0) + " " + dtp.column(i, 0) + " , " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " "
                    + dtp.table(i).toLowerCase() + "){\n";
            question += "        try (\n" +
                    "            PreparedStatement statement = connection.prepareStatement(DELETE_" + dtp.table(i).toUpperCase() + "_SQL);){\n";
            question += "            statement.setDate(1, Date.valueOf(" + dtp.table(i).toLowerCase() + ".getDeleted_at()));\n" +
                    "            statement.setString(2, " + dtp.table(i).toLowerCase() + ".getDeleted_by());\n";

            switch (dtp.type(i, 0)) {
                case "int":
                    question += "            statement.setInt(" + 3 + ","+dtp.column(i, 0)+ ");\n";
                    break;
                case "String":
                    question += "            statement.setString(" + 3 + "," + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() +
                            dtp.column(i, 0).substring(1) + "());\n";
                    break;
                case "double":
                    question += "            statement.setDouble(" + 3 + "," + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() +
                            dtp.column(i, 0).substring(1) + "());\n";
                    break;

                default:
                    question += "            Chua cap nhan du lieu";
                    break;
            }
            question +="statement.executeUpdate();\n"+
                    "        } catch (SQLException e) {\n" +
                    "           e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n";
            question += "    @Override\n";
            question += "    public void save(" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " "
                    + dtp.table(i).toLowerCase() + "){\n";
            question += "        try (\n" +
                    "           PreparedStatement preparedStatement = connection.prepareStatement(INSERT_" + dtp.table(i).toUpperCase() + "_SQL);){\n";
            int cout = dtp.columnSize(i);
            for (int j = 1; j < dtp.columnSize(i); j++) {

                switch (dtp.type(i, j)) {
                    case "int":
                        question += "           preparedStatement.setInt(" + j + "," + dtp.table(i).toLowerCase() + ".get" +
                                dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "String":
                        question += "           preparedStatement.setString(" + j + "," + dtp.table(i).toLowerCase() +
                                ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "double":
                        question += "           preparedStatement.setDouble(" + j + "," + dtp.table(i).toLowerCase() +
                                ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "());\n";
                        break;
                    case "LocalDate":
                        question += "           preparedStatement.setDate(" + j + ",Date.valueOf(" + dtp.table(i).toLowerCase() + ".get"
                                + dtp.column(i, j).substring(0, 1).toUpperCase() +
                                dtp.column(i, j).substring(1) + "()));\n";

                        break;
                    default:
                        question += "           Thieu kieu du lieu";
                        break;
                }
            }

            question += "           preparedStatement.setDate(" + cout + ", Date.valueOf(" + dtp.table(i).toLowerCase() + ".getCreated_at()));\n";
            cout++;
            question += "           preparedStatement.setString(" + cout + ", " + dtp.table(i).toLowerCase() + ".getCreated_by());\n";
            question += "           preparedStatement.executeUpdate();\n" +
                    "         } catch (SQLException e) {\n" +
                    "           printSQLException(e);\n" +
                    "        }\n" +
                    "    }\n";
            question += "    @Override\n" +
                    "    public " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "" +
                    " findById(" + dtp.type(i, 0) + " " + dtp.column(i, 0).toLowerCase() + "){\n" +
                    "        " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + " = null;\n" +
                    "        try (\n" +
                    "            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_" + dtp.table(i).toUpperCase() + "_BY_ID)) {\n";
            switch (dtp.type(i, 0)) {
                case "int":
                    question += "            preparedStatement.setInt(1, " + dtp.column(i, 0).toLowerCase() + ");\n";
                    break;
                case "String":
                    question += "            preparedStatement.setString(1, " + dtp.column(i, 0).toLowerCase() + ");\n";
                    break;
                case "double":
                    question += "            preparedStatement.setDouble(1, " + dtp.column(i, 0).toLowerCase() + ");\n";
                    break;
                default:
                    question += "            Thieu du lieu";
                    break;

            }
            question += "           ResultSet rs = preparedStatement.executeQuery();\n" +
                    "\n" +
                    "         while (rs.next()) {\n";
            for (int j = 1; j < dtp.columnSize(i); j++) {
                switch (dtp.type(i, j)) {
                    case "int":
                        question += "           int " + dtp.column(i, j).toLowerCase() + " = rs.getInt(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        break;
                    case "String":
                        question += "           String " + dtp.column(i, j).toLowerCase() + " = rs.getString(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        break;
                    case "double":
                        question += "           double " + dtp.column(i, j).toLowerCase() + " = rs.getDouble(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        break;
                    default:
                        question += "           LocalDate " + dtp.column(i, j).toLowerCase() + "= LocalDate.parse(rs.getString(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                }
            }
            question += "           " + dtp.table(i).toLowerCase() + "= new " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(";
            for (int j = 0; j < dtp.columnSize(i) - 1; j++) {
                question += dtp.column(i, j).toLowerCase() + ",";
            }
            question += dtp.column(i, dtp.columnSize(i) - 1).toLowerCase() + ");}\n";

            question += "        } catch (SQLException e) {\n" +
                    "           printSQLException(e);\n" +
                    "        }\n" +
                    "           return " + dtp.table(i).toLowerCase() + ";\n" +
                    "   }\n" +
                    "}\n";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Service Hacked By Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
