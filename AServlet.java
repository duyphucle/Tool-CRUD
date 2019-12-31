import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class AServlet {

    public void createSerlet() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "src/controller/" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Servlet.java");
            String question = "package controller;\n";
            question += "import model." + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + ";\n"
                    + "import service." + dtp.table(i).toLowerCase() + "." + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl;\n"
                    + "import service." + dtp.table(i).toLowerCase() + "." + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service;\n"
                    + "import javax.servlet.RequestDispatcher;\n"
                    + "import javax.servlet.ServletException;\n"
                    + "import javax.servlet.annotation.WebServlet;\n"
                    + "import javax.servlet.http.HttpServlet;\n"
                    + "import javax.servlet.http.HttpServletRequest;\n"
                    + "import javax.servlet.http.HttpServletResponse;\n"
                    + "import java.io.IOException;\n"
                    + "import java.sql.SQLException;\n"
                    + "import java.time.LocalDate;\n"
                    + "import java.util.List;\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "import model." + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + ";\n"
                        + "import service." + dtp.pk(i, j).toLowerCase() + "." + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + "JDBCServiceImpl;\n"
                        + "import service." + dtp.pk(i, j).toLowerCase() + "." + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + "Service;\n";
            }
            question += "@WebServlet(name = \"" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Servlet\",urlPatterns =\"/" + dtp.table(i).toLowerCase() + "\")\n"
                    + "public class " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Servlet extends HttpServlet {\n"
                    + "private " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service " + dtp.table(i).toLowerCase() + "Service" + "= new " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl();\n"
                    + "public " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Servlet()" + " throws SQLException, ClassNotFoundException {}\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "     private " + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + "Service " + dtp.pk(i, j).toLowerCase() + "Service = new " + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + "JDBCServiceImpl();\n\n";
            }

            question += "     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n"
                    + "          String action = request.getParameter(\"action\");\n"
                    + "          if(action == null){\n"
                    + "               action = \"\";" +
                    "          }\n"
                    + "          switch (action){\n" +
                    "               case \"create\":\n" +
                    "                       create" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(request, response);\n" +
                    "                       break;\n" +
                    "               case \"edit\":\n" +
                    "                       update" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(request, response);\n" +
                    "                       break;\n" +
                    "               case \"delete\":\n" +
                    "                       delete" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(request, response);\n" +
                    "                       break;\n" +
                    "               default:\n" +
                    "                       break;\n" +
                    "               }\n" +
                    "          }\n"
                    + "     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n"
                    + "          String action = request.getParameter(\"action\");\n"
                    + "          if(action == null){\n"
                    + "               action = \"\";        \n" +
                    "          }\n"
                    + "          switch (action){\n" +
                    "               case \"create\":\n" +
                    "                       showCreateForm(request, response);\n" +
                    "                       break;\n" +
                    "               case \"edit\":\n" +
                    "                       showEditForm(request, response);\n" +
                    "                       break;\n" +
                    "               case \"delete\":\n" +
                    "                       showDeleteForm(request, response);\n" +
                    "                       break;\n" +
                    "               case \"view\":\n" +
                    "                       showViewForm(request, response);\n" +
                    "                       break;\n" +
                    "               default:\n " +
                    "                       listForm(request, response);\n" +
                    "                       break;\n" +
                    "          }\n" +
                    "     }\n"
                    + "     private void listForm(HttpServletRequest request, HttpServletResponse response) {\n" +
                    "          List <" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "> " + dtp.table(i).toLowerCase() + "= this." + dtp.table(i).toLowerCase() + "Service.findAll();\n" +
                    "          request.setAttribute(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n" +
                    "          RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/list.jsp\");\n" +
                    "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          }\n" +
                    "     }\n";
            question += "     private void showViewForm (HttpServletRequest request, HttpServletResponse response) {\n" +
                    "          RequestDispatcher dispatcher;\n";

            switch (dtp.type(i, 0)) {
                case "int":
                    question += "int " + dtp.column(i, 0).toLowerCase() + " = Integer.parseInt(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "String":
                    question += "String " + dtp.column(i, 0).toLowerCase() + " = request.getParameter(" + dtp.column(i, 0).toLowerCase() + "\");";
                    break;
                default:
                    question += "Duoc cap nhat phien ban tra phi";
                    break;
            }

            question += "" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + "= this." + dtp.table(i).toLowerCase() + "Service.findById(" + dtp.column(i, 0).toLowerCase() + ");\n";
            question += "if(" + dtp.table(i).toLowerCase() + " == null){\n" +
                    "dispatcher = request.getRequestDispatcher(\"error-404.jsp\");\n" +
                    "} else {\n";
            question += "request.setAttribute(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n"
                    + "dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/view.jsp\");"
                    + "}\n" +
                    "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          }\n" +
                    "     }\n";


            question += "     private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "List<" + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + ">"
                        + " " + dtp.pk(i, j).toLowerCase() + " = this."
                        + dtp.pk(i, j).toLowerCase() + "Service.findAll();\n"
                        + "request.setAttribute(\"" + dtp.pk(i, j).toLowerCase() + "s\", " + dtp.pk(i, j).toLowerCase() + ");";
            }
//            for (int j = 1; j < dtp.pkSize(i); j++) {
//                question += "request.setAttribute(\"" + dtp.pk(i, j).toLowerCase() + "\", " + dtp.pk(i, j).toLowerCase() + ");";
//            }
            question += "\n          RequestDispatcher dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/create.jsp\");\n" +
                    "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          }\n" +
                    "     }\n"
                    + "     private void showEditForm(HttpServletRequest request, HttpServletResponse response) {\n" +
                    "          RequestDispatcher dispatcher;\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "List<" + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + ">"
                        + " " + dtp.pk(i, j).toLowerCase() + " = this."
                        + dtp.pk(i, j).toLowerCase() + "Service.findAll();\n";
            }

            switch (dtp.type(i, 0)) {
                case "int":
                    question += "int " + dtp.column(i, 0).toLowerCase() + " = Integer.parseInt(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "String":
                    question += "String " + dtp.column(i, 0).toLowerCase() + " = request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\");";
                    break;
                case "LocalDate":
                    question += "LocalDate " + dtp.column(i, 0).toLowerCase() + "= LocalDate.parse(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "double":
                    question += "double " + dtp.column(i, 0).toLowerCase() + " = Double.valueOf(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                default:
                    question += "Duoc cap nhat phien ban tra phi";
                    break;
            }
            question += "          " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + " = this." + dtp.table(i).toLowerCase() + "Service.findById(" + dtp.column(i, 0).toLowerCase() + ");\n";

            question += "          if(" + dtp.table(i).toLowerCase() + " == null){\n" +
                    "               dispatcher = request.getRequestDispatcher(\"error-404.jsp\");\n" +
                    "          } else {\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "request.setAttribute(\"" + dtp.pk(i, j).toLowerCase() + "s\", " + dtp.pk(i, j).toLowerCase() + ");\n";
            }
            question += "               request.setAttribute(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n";

            question += "\n               dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/edit.jsp\");\n" +
                    "        }\n" +
                    "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          }\n" +
                    "     }\n"
                    + "     private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {\n" +
                    "\n          RequestDispatcher dispatcher;\n";
            switch (dtp.type(i, 0)) {
                case "int":
                    question += "int " + dtp.column(i, 0).toLowerCase() + " = Integer.parseInt(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "String":
                    question += "String " + dtp.column(i, 0).toLowerCase() + " = request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\");";
                    break;
                case "LocalDate":
                    question += "LocalDate " + dtp.column(i, 0).toLowerCase() + "= LocalDate.parse(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "double":
                    question += "double " + dtp.column(i, 0).toLowerCase() + " = Double.valueOf(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                default:
                    question += "Duoc cap nhat phien ban tra phi";
                    break;
            }
            question += "          " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + " = this." + dtp.table(i).toLowerCase() + "Service.findById(" + dtp.column(i, 0).toLowerCase() + ");\n" +
                    "          if(" + dtp.table(i).toLowerCase() + " == null){\n" +
                    "               dispatcher = request.getRequestDispatcher(\"error-404.jsp\");\n" +
                    "          } else {\n" +
                    "               request.setAttribute(\"" + dtp.table(i).toLowerCase() + "s\", " + dtp.table(i).toLowerCase() + ");\n" +
                    "               dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/delete.jsp\");\n" +
                    "          }\n" +
                    "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "           }\n" +
                    "     }\n";

            question += "private void delete" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(HttpServletRequest request, HttpServletResponse response) {\n";
            switch (dtp.type(i, 0)) {
                case "int":
                    question += "int " + dtp.column(i, 0).toLowerCase() + " = Integer.parseInt(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "String":
                    question += "String " + dtp.column(i, 0).toLowerCase() + " = request.getParameter(" + dtp.column(i, 0).toLowerCase() + "\");";
                    break;
                case "LocalDate":
                    question += "LocalDate " + dtp.column(i, 0).toLowerCase() + "= LocalDate.parse(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                case "double":
                    question += "double " + dtp.column(i, 0).toLowerCase() + " = Double.valueOf(request.getParameter(\"" + dtp.column(i, 0).toLowerCase() + "\"));\n";
                    break;
                default:
                    question += "Duoc cap nhat phien ban tra phi";
                    break;
            }
            question += "" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + " = this." + dtp.table(i).toLowerCase() + "Service.findById(" + dtp.column(i, 0).toLowerCase() + ");\n";
            question += "RequestDispatcher dispatcher;\n"
                    + "            if(" + dtp.table(i).toLowerCase() + " == null){\n"
                    + "               dispatcher = request.getRequestDispatcher(\"error-404.jsp\");\n"
                    + "            } else {\n"
                    + "               this." + dtp.table(i).toLowerCase() + "Service.remove(" + dtp.column(i, 0).toLowerCase() + "," + dtp.table(i).toLowerCase() + ");\n"

                    + "          try {\n" +
                    "               response.sendRedirect(\"" + dtp.table(i).toLowerCase() + "\");\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "           }\n" +
                    "      }\n" +
                    "      }\n";


            question += "private void create" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(HttpServletRequest request, HttpServletResponse response) {"
                    + "\nRequestDispatcher dispatcher;\n";
            for (int j = 1; j < dtp.columnSize(i); j++) {
                switch (dtp.type(i, j)) {
                    case "String":
                        question += "String " + dtp.column(i, j).toLowerCase() + "=request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        break;
                    case "int":
                        question += "int " + dtp.column(i, j).toLowerCase() + "=Integer.parseInt(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    case "LocalDate":
                        question += "LocalDate " + dtp.column(i, j).toLowerCase() + "= LocalDate.parse(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    case "double":
                        question += "double " + dtp.column(i, j).toLowerCase() + " = Double.valueOf(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    default:
                        question += "Nang cap phien ban tra phi \n";
                        break;
                }

            }
            question += dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + "= new " + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " ( ";
            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
                question += dtp.column(i, j).toLowerCase() + " , ";
            }
//            List<Employee> employees = this.employeesService.findAll();
//            request.setAttribute("employees", employees);
            question += dtp.column(i, dtp.columnSize(i) - 1).toLowerCase() + ");\n";
            question += " this." + dtp.table(i).toLowerCase() + "Service.save(" + dtp.table(i).toLowerCase() + ");\n"
                    + " dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/create.jsp\");\n";

            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "List<" + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + ">"
                        + " " + dtp.pk(i, j).toLowerCase() + " = this."
                        + dtp.pk(i, j).toLowerCase() + "Service.findAll();\n"
                        + "request.setAttribute(\"" + dtp.pk(i, j).toLowerCase() + "s\", " + dtp.pk(i, j).toLowerCase() + ");";
            }

            question += "request.setAttribute(\"message\", \"Thêm mớithành công\");\n"
                    + "          try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "          } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "          } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "           }\n" +
                    "      }\n";


            question += "      private void update" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "(HttpServletRequest request, HttpServletResponse response) {\n"
                    + "           RequestDispatcher dispatcher;\n";
            for (int j = 0; j < dtp.columnSize(i); j++) {
                switch (dtp.type(i, j)) {
                    case "String":
                        question += "String " + dtp.column(i, j).toLowerCase() + "=request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        break;
                    case "int":
                        question += "int " + dtp.column(i, j).toLowerCase() + "=Integer.parseInt(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    case "LocalDate":
                        question += "LocalDate " + dtp.column(i, j).toLowerCase() + "= LocalDate.parse(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    case "double":
                        question += "double " + dtp.column(i, j).toLowerCase() + " = Double.valueOf(request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\"));\n";
                        break;
                    default:
                        question += "String " + dtp.column(i, j).toLowerCase() + "=request.getParameter(\"" + dtp.column(i, j).toLowerCase() + "\");\n";
                        ;
                }

            }


            question += dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + " " + dtp.table(i).toLowerCase() + "= this." + dtp.table(i).toLowerCase() + "Service.findById(" + dtp.column(i, 0).toLowerCase() + ");";

            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "List<" + dtp.pk(i, j).substring(0, 1).toUpperCase() + dtp.pk(i, j).substring(1) + ">"
                        + " " + dtp.pk(i, j).toLowerCase() + " = this."
                        + dtp.pk(i, j).toLowerCase() + "Service.findAll();\n";
            }


            question += "if(" + dtp.table(i).toLowerCase() + " == null){\n" +
                    "dispatcher = request.getRequestDispatcher(\"error-404.jsp\");\n" +
                    "           } else {\n";
            for (int j = 1; j < dtp.columnSize(i); j++) {
                question += "" + dtp.table(i).toLowerCase() + ".set" + dtp.column(i, j).substring(0, 1).toUpperCase() + dtp.column(i, j).substring(1)
                        + "(" + dtp.column(i, j).toLowerCase() + ");\n";
            }
            question += "            this." + dtp.table(i).toLowerCase() + "Service.update(" + dtp.table(i).toLowerCase() + ");\n"
                    + "            request.setAttribute(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n";

            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "request.setAttribute(\"" + dtp.pk(i, j).toLowerCase() + "s\", " + dtp.pk(i, j).toLowerCase() + ");";
            }

            question += "            request.setAttribute(\"message\", \"Thông tin đã được cập nhật\");\n"
                    + "            dispatcher = request.getRequestDispatcher(\"/WEB-INF/" + dtp.table(i).toLowerCase() + "/edit.jsp\");"
                    + "           }\n" +
                    "           try {\n" +
                    "               dispatcher.forward(request, response);\n" +
                    "           } catch (ServletException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "           } catch (IOException e) {\n" +
                    "               e.printStackTrace();\n" +
                    "           }\n" +
                    "      }\n" +
                    "      }\n";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Controller Created By Le DUy Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void homeServlet() throws SQLException, ClassNotFoundException {
        Path path = Paths.get(AMain.link + "src/controller/HomeServlet.java");
        String question = "package controller;\n" +
                "\n" +
                "import javax.servlet.RequestDispatcher;\n" +
                "import javax.servlet.ServletException;\n" +
                "import javax.servlet.annotation.WebServlet;\n" +
                "import javax.servlet.http.HttpServlet;\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import java.io.IOException;\n" +
                "\n" +
                "@WebServlet(name = \"HomeServlet\", urlPatterns = \"/home\")\n" +
                "public class HomeServlet extends HttpServlet {\n" +
                "\n" +
                "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"WEB-INF/home.jsp\");\n" +
                "\n" +
                "        dispatcher.forward(request, response);\n" +
                "    }\n" +
                "}\n";

        Charset charset = Charset.forName("ISO-8859-1");
        try {
            Files.write(path, question.getBytes());
            System.out.println("HomeServlet Created By 0.0 ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
