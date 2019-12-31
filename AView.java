import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class AView {

    public void showcreate() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/create.jsp");
            String question = "";
            question += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <title>Create " + dtp.table(i) + "</title>\n" +
                    "    <%@ include file=\"../layout/head.jsp\" %>\n" +
                    "    <%@ include file=\"../layout/css.jsp\" %>\n" +
                    "</head>\n" +
                    "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                    "<div class=\"wrapper\">\n" +
                    "\n" +
                    "    <%@ include file=\"../layout/header.jsp\" %>\n" +
                    "    <!-- Left side column. contains the logo and sidebar -->\n" +
                    "    <%@ include file=\"../layout/sidebar.jsp\" %>\n" +
                    "    <!-- Content Wrapper. Contains page content -->\n" +
                    "    <div class=\"content-wrapper\">\n" +
                    "        <!-- Content Header (Page header) -->\n" +
                    "        <section class=\"content-header\">\n" +
                    "            <h1>\n" +
                    "                Create " + dtp.table(i) + "\n" +
                    "            </h1>\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                    "                <li><a href=\"#\">" + dtp.table(i) + "</a></li>\n" +
                    "                <li class=\"active\">Create</li>\n" +
                    "            </ol>\n" +
                    "        </section>\n" +
                    "\n" +
                    "        <!-- Main content -->\n" +
                    "        <section class=\"content\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12\">\n" +
                    "                    <div class=\"box\">\n" +
                    "                        <!-- /.box-header -->\n" +
                    "                        <div class=\"box-header\">\n" +
                    "                            <c:if test='${requestScope[\"message\"] != null}'>\n" +
                    "                                <div class=\"alert alert-success\" role=\"alert\">\n" +
                    "                                    <span class=\"message\">${requestScope[\"message\"]}</span>\n" +
                    "                                </div>\n" +
                    "                            </c:if>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"box-body\">\n" +
                    "                            <!-- /.box-header -->\n" +
                    "                            <!-- form start -->\n" +
                    "                            <form class=\"form-horizontal\" method=\"post\">\n";

            List<String> array = dtp.column(i);
            for (int x = 0; x < dtp.pkSize(i); x++) {
                array.remove(dtp.fkName(i, x));
            }


            for (int j = 1; j < array.size(); j++) {
                question += "                                <div class=\"form-group\">\n" +
                        "                                    <label class=\"col-sm-1 control-label\">" + dtp.column(i, j).toLowerCase() + "</label>\n" +
                        "                                    <div class=\"col-sm-11\">\n" +
                        "                                        <input type=\"text\" class=\"form-control\" name=\"" + dtp.column(i, j).toLowerCase() + "\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n";
            }

            for (int z = 0; z < dtp.pkSize(i); z++) {
                question += "                                <div class=\"form-group\">\n" +
                        "                                    <label class=\"col-sm-1 control-label\">" + dtp.fkName(i, z).toLowerCase() + "</label>\n" +
                        "                                    <div class=\"col-sm-11\">\n" +
                        "                                        <select name=\"" + dtp.fkName(i, z).toLowerCase() + "\" class=\"form-control\">\n" +
                        "                                            <c:forEach items='${requestScope[\"" + dtp.pk(i, z).toLowerCase() + "s\"]}' var=\"" + dtp.pk(i, z).toLowerCase() + "\">\n" +
                        "                                                <option value=\"${" + dtp.pk(i, z).toLowerCase() + ".get" + dtp.getColumns(dtp.pk(i, z)).get(0).substring(0, 1).toUpperCase() + dtp.getColumns(dtp.pk(i, z)).get(0).substring(1) + "()}\">${" + dtp.pk(i, z).toLowerCase() + ".get" + dtp.getColumns(dtp.pk(i, z)).get(0).substring(0, 1).toUpperCase() + dtp.getColumns(dtp.pk(i, z)).get(0).substring(1) + "()}</option>\n" +
                        "                                            </c:forEach>\n" +
                        "                                        </select>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n";
            }

            question += "                                <!-- /.box-body -->\n" +
                    "                                <div class=\"box-footer\">\n" +
                    "                                    <button type=\"reset\" class=\"btn btn-default btn-flat\">Reset</button>\n" +
                    "                                    <button type=\"submit\" class=\"btn btn-danger btn-flat pull-right\">Create customer\n" +
                    "                                    </button>\n" +
                    "                                </div>\n" +
                    "                                <!-- /.box-footer -->\n" +
                    "                            </form>\n" +
                    "                            <!-- /.box-body -->\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box -->\n" +
                    "                    </div>\n" +
                    "                    <!-- /.col -->\n" +
                    "                </div>\n" +
                    "                <!-- /.row -->\n" +
                    "        </section>\n" +
                    "        <!-- /.content -->\n" +
                    "    </div>\n" +
                    "    <!-- /.content-wrapper -->\n" +
                    "    <%@ include file=\"../layout/footer.jsp\" %>\n" +
                    "\n" +
                    "    <!-- Control Sidebar -->\n" +
                    "    <%@ include file=\"../layout/control-sidebar.jsp\" %>\n" +
                    "</div>\n" +
                    "<!-- ./wrapper -->\n" +
                    "<%@ include file=\"../layout/script.jsp\" %>\n" +
                    "</body>\n" +
                    "</html>\n";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Views Hacked By Le Duy Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void showEdit() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/edit.jsp");
            String question = "";
            question += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <title>Edit " + dtp.table(i) + "</title>\n" +
                    "    <%@ include file=\"../layout/head.jsp\" %>\n" +
                    "    <%@ include file=\"../layout/css.jsp\" %>\n" +
                    "</head>\n" +
                    "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                    "<div class=\"wrapper\">\n" +
                    "\n" +
                    "    <%@ include file=\"../layout/header.jsp\" %>\n" +
                    "    <!-- Left side column. contains the logo and sidebar -->\n" +
                    "    <%@ include file=\"../layout/sidebar.jsp\" %>\n" +
                    "    <!-- Content Wrapper. Contains page content -->\n" +
                    "    <div class=\"content-wrapper\">\n" +
                    "        <!-- Content Header (Page header) -->\n" +
                    "        <section class=\"content-header\">\n" +
                    "            <h1>\n" +
                    "                Edit " + dtp.table(i) + "\n" +
                    "            </h1>\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                    "                <li><a href=\"#\">" + dtp.table(i) + "</a></li>\n" +
                    "                <li class=\"active\">Edit</li>\n" +
                    "            </ol>\n" +
                    "        </section>\n" +
                    "\n" +
                    "        <!-- Main content -->\n" +
                    "        <section class=\"content\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12\">\n" +
                    "                    <div class=\"box\">\n" +
                    "                        <!-- /.box-header -->\n" +
                    "                        <div class=\"box-header\">\n" +
                    "                            <c:if test='${requestScope[\"message\"] != null}'>\n" +
                    "                                <div class=\"alert alert-success\" role=\"alert\">\n" +
                    "                                    <span class=\"message\">${requestScope[\"message\"]}</span>\n" +
                    "                                </div>\n" +
                    "                            </c:if>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"box-body\">\n" +
                    "                            <!-- /.box-header -->\n" +
                    "                            <!-- form start -->\n" +
                    "                            <form class=\"form-horizontal\" method=\"post\">\n";

            List<String> array = dtp.column(i);
            for (int x = 0; x < dtp.pkSize(i); x++) {
                array.remove(dtp.fkName(i, x));
            }

            for (int j = 1; j < array.size(); j++) {
                question += "                                <div class=\"form-group\">\n" +
                        "                                    <label class=\"col-sm-1 control-label\">" + dtp.column(i, j).toLowerCase() + "</label>\n" +
                        "                                    <div class=\"col-sm-11\">\n" +
                        "                                        <input type=\"text\" class=\"form-control\" name=\"" + dtp.column(i, j).toLowerCase() + "\" value=\"${requestScope[\"" + dtp.table(i).toLowerCase() + "\"].get" + dtp.column(i, j).substring(0, 1).toUpperCase() + dtp.column(i, j).substring(1) + "()}\">\n" +
                        "                                    </div>\n" +
                        "                                </div>\n";
            }

            for (int z = 0; z < dtp.pkSize(i); z++) {
                question += "                                <div class=\"form-group\">\n" +
                        "                                    <label class=\"col-sm-1 control-label\">" + dtp.fkName(i, z).toLowerCase() + "</label>\n" +
                        "                                    <div class=\"col-sm-11\">\n" +
                        "                                        <select name=\"" + dtp.fkName(i, z).toLowerCase() + "\" class=\"form-control\">\n" +
                        "                                            <c:forEach items='${requestScope[\"" + dtp.pk(i, z).toLowerCase() + "s\"]}' var=\"" + dtp.pk(i, z).toLowerCase() + "\">\n" +
                        "                                                <option value=\"${" + dtp.pk(i, z).toLowerCase() + ".get" + dtp.getColumns(dtp.pk(i, z)).get(0).substring(0, 1).toUpperCase() +
                        dtp.getColumns(dtp.pk(i, z)).get(0).substring(1) + "()}\"\n" +
                        "                                                    ${requestScope[\"" + dtp.table(i).toLowerCase() + "\"].get" + dtp.fkName(i, z).substring(0, 1).toUpperCase()
                        + dtp.fkName(i, z).substring(1) + "() == " + dtp.pk(i, z).toLowerCase() + ".get" + dtp.getColumns(dtp.pk(i, z)).get(0).substring(0, 1).toUpperCase() +
                        dtp.getColumns(dtp.pk(i, z)).get(0).substring(1) + "() ? \"selected\" : \"\"}>\n" +
                        "                                                        ${" + dtp.pk(i, z).toLowerCase() + ".get" +
                        dtp.getColumns(dtp.pk(i, z)).get(0).substring(0, 1).toUpperCase() +
                        dtp.getColumns(dtp.pk(i, z)).get(0).substring(1) + "()}\n" +
                        "                                                </option>\n" +
                        "                                            </c:forEach>\n" +
                        "                                        </select>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n";
            }

            question += "                                <!-- /.box-body -->\n" +
                    "                                <div class=\"box-footer\">\n" +
                    "                                    <button type=\"reset\" class=\"btn btn-default btn-flat\">Reset</button>\n" +
                    "                                    <button type=\"submit\" class=\"btn btn-danger btn-flat pull-right\">Edit customer\n" +
                    "                                    </button>\n" +
                    "                                </div>\n" +
                    "                                <!-- /.box-footer -->\n" +
                    "                            </form>\n" +
                    "                            <!-- /.box-body -->\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box -->\n" +
                    "                    </div>\n" +
                    "                    <!-- /.col -->\n" +
                    "                </div>\n" +
                    "                <!-- /.row -->\n" +
                    "        </section>\n" +
                    "        <!-- /.content -->\n" +
                    "    </div>\n" +
                    "    <!-- /.content-wrapper -->\n" +
                    "    <%@ include file=\"../layout/footer.jsp\" %>\n" +
                    "\n" +
                    "    <!-- Control Sidebar -->\n" +
                    "    <%@ include file=\"../layout/control-sidebar.jsp\" %>\n" +
                    "</div>\n" +
                    "<!-- ./wrapper -->\n" +
                    "<%@ include file=\"../layout/script.jsp\" %>\n" +
                    "</body>\n" +
                    "</html>\n";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("WEB EDIT Hacked By Nguyen Ngoc Linh Dan");
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public void showDelete() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/delete.jsp");
            String question = "\n";
            question += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <title>Delete " + dtp.table(i) + "</title>\n" +
                    "    <%@ include file=\"../layout/head.jsp\" %>\n" +
                    "    <%@ include file=\"../layout/css.jsp\" %>\n" +
                    "</head>\n" +
                    "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                    "<div class=\"wrapper\">\n" +
                    "\n" +
                    "    <%@ include file=\"../layout/header.jsp\" %>\n" +
                    "    <!-- Left side column. contains the logo and sidebar -->\n" +
                    "    <%@ include file=\"../layout/sidebar.jsp\" %>\n" +
                    "    <!-- Content Wrapper. Contains page content -->\n" +
                    "    <div class=\"content-wrapper\">\n" +
                    "        <!-- Content Header (Page header) -->\n" +
                    "        <section class=\"content-header\">\n" +
                    "            <h1>\n" +
                    "                Delete " + dtp.table(i) + "\n" +
                    "            </h1>\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                    "                <li><a href=\"#\">" + dtp.table(i) + "</a></li>\n" +
                    "                <li class=\"active\">Delete</li>\n" +
                    "            </ol>\n" +
                    "        </section>\n" +
                    "\n" +
                    "        <!-- Main content -->\n" +
                    "        <section class=\"content\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12\">\n" +
                    "                    <div class=\"box\">\n" +
                    "                        <!-- /.box-header -->\n" +
                    "                        <div class=\"box-body\">\n" +
                    "                            <!-- /.box-header -->\n" +
                    "                            <!-- form start -->\n" +
                    "                            <form method=\"post\">\n" +
                    "                                <table id=\"example1\" class=\"table table-bordered table-striped\">\n";

            for (int j = 0; j < dtp.columnSize(i); j++) {
                question += "                                    <tr>\n" +
                        "                                        <th>" + dtp.column(i, j) + ":</th>\n" +
                        "                                        <td>${requestScope[\"" + dtp.table(i).toLowerCase() + "s\"].get" + dtp.column(i, j).substring(0, 1).toUpperCase()
                        + dtp.column(i, j).substring(1) + "()}</td>\n" +
                        "                                    </tr>\n";
                ;
            }
            question += "                                </table>\n" +
                    "\n" +
                    "                                <div class=\"box-footer\">\n" +
                    "                                    <a href=\"/" + dtp.table(i).toLowerCase() + "\" class=\"btn btn-default btn-flat\">Back to " + dtp.table(i).toLowerCase() + " list</a>\n" +
                    "                                    <button type=\"submit\" class=\"btn btn-danger btn-flat pull-right\"\n" +
                    "                                            onclick=\"return confirm('Are you sure?')\">Delete customer\n" +
                    "                                    </button>\n" +
                    "                                </div>\n" +
                    "                                <!-- /.box-footer -->\n" +
                    "                            </form>\n" +
                    "                            <!-- /.box-body -->\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box -->\n" +
                    "                    </div>\n" +
                    "                    <!-- /.col -->\n" +
                    "                </div>\n" +
                    "                <!-- /.row -->\n" +
                    "        </section>\n" +
                    "        <!-- /.content -->\n" +
                    "    </div>\n" +
                    "    <!-- /.content-wrapper -->\n" +
                    "    <%@ include file=\"../layout/footer.jsp\" %>\n" +
                    "\n" +
                    "    <!-- Control Sidebar -->\n" +
                    "    <%@ include file=\"../layout/control-sidebar.jsp\" %>\n" +
                    "</div>\n" +
                    "<!-- ./wrapper -->\n" +
                    "<%@ include file=\"../layout/script.jsp\" %>\n" +
                    "</body>\n" +
                    "</html>\n";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Views Hacked By Dan");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void showList() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/list.jsp");
            String question = "";

            question += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <title>" + dtp.table(i) + "</title>\n" +
                    "    <%@ include file=\"../layout/head.jsp\" %>\n" +
                    "    <%@ include file=\"../layout/css.jsp\" %>\n" +
                    "</head>\n" +
                    "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                    "<div class=\"wrapper\">\n" +
                    "\n" +
                    "    <%@ include file=\"../layout/header.jsp\" %>\n" +
                    "    <!-- Left side column. contains the logo and sidebar -->\n" +
                    "    <%@ include file=\"../layout/sidebar.jsp\" %>\n" +
                    "    <!-- Content Wrapper. Contains page content -->\n" +
                    "    <div class=\"content-wrapper\">\n" +
                    "        <!-- Content Header (Page header) -->\n" +
                    "        <section class=\"content-header\">\n" +
                    "            <h1>\n" +
                    "                " + dtp.table(i) + " List\n" +
                    "            </h1>\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                    "                <li><a href=\"#\">" + dtp.table(i) + "</a></li>\n" +
                    "                <li class=\"active\">List</li>\n" +
                    "            </ol>\n" +
                    "        </section>\n" +
                    "\n" +
                    "        <!-- Main content -->\n" +
                    "        <section class=\"content\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12\">\n" +
                    "                    <div class=\"box\">\n" +
                    "                        <div class=\"box-header\">\n" +
                    "                            <div class=\"col-lg-3\">\n" +
                    "                                <a href=\"/" + dtp.table(i).toLowerCase() + "?action=create\" class=\"btn btn-block btn-danger btn-flat\">Create</a>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box-header -->\n" +
                    "                        <div class=\"box-body\">\n" +
                    "                            <table id=\"example1\" class=\"table table-bordered table-striped table-hover\">\n" +
                    "                                <thead>\n" +
                    "                                <tr>\n";
            for (int j = 0; j < dtp.columnSize(i); j++) {
                question += "<th class=\"text-center\">" + dtp.column(i, j) + "</th>\n";
            }
            question += "                                    <th class=\"text-center\">Edit</th>\n" +
                    "                                    <th class=\"text-center\">Delete</th>\n" +
                    "                                </tr>\n" +
                    "                                </thead>\n" +
                    "                                <tbody>\n" +
                    "                                <c:forEach items='${requestScope[\"" + dtp.table(i).toLowerCase() + "\"]}' var=\"" + dtp.table(i).toLowerCase() + "\">\n" +
                    "                                    <tr>\n";
            for (int j = 0; j < 1; j++) {
                question += "                                        <td class=\"text-center center\">\n" +
                        "                                            <a href=\"/" + dtp.table(i).toLowerCase() + "?action=view&" + dtp.column(i, 0).toLowerCase() + "=${" + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() + dtp.column(i, 0).substring(1) + "()}\">${" + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() + dtp.column(i, j).substring(1) + "()}</a>\n" +
                        "                                        </td>\n";
            }
            for (int j = 1; j < dtp.columnSize(i); j++) {
                question += "                                        <td class=\"text-center center\">${" + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, j).substring(0, 1).toUpperCase() + dtp.column(i, j).substring(1) + "()}</td>\n";

            }
            question += "                                        <td class=\"text-center center\"><a\n" +
                    "                                                href=\"/" + dtp.table(i).toLowerCase() + "?action=edit&" + dtp.column(i, 0).toLowerCase() + "=${" + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() + dtp.column(i, 0).substring(1) + "()}\"" +
                    "                                                class=\"btn btn-block btn-danger btn-flat\">Edit</a></td>\n" +
                    "                                        <td class=\"text-center align-middle\"><a href=\"/" + dtp.table(i).toLowerCase() + "?action=delete&" + dtp.column(i, 0).toLowerCase() + "=${" + dtp.table(i).toLowerCase() + ".get" + dtp.column(i, 0).substring(0, 1).toUpperCase() + dtp.column(i, 0).substring(1) + "()}\"\n" +
                    "                                                              class=\"btn btn-block btn-danger btn-flat\">Delete</a>\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "                                </c:forEach>\n" +
                    "                                </tbody>\n" +
                    "                                <tfoot>\n" +
                    "                                </tfoot>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box-body -->\n" +
                    "                    </div>\n" +
                    "                    <!-- /.box -->\n" +
                    "                </div>\n" +
                    "                <!-- /.col -->\n" +
                    "            </div>\n" +
                    "            <!-- /.row -->\n" +
                    "        </section>\n" +
                    "        <!-- /.content -->\n" +
                    "    </div>\n" +
                    "    <!-- /.content-wrapper -->\n" +
                    "    <%@ include file=\"../layout/footer.jsp\" %>\n" +
                    "\n" +
                    "    <!-- Control Sidebar -->\n" +
                    "    <%@ include file=\"../layout/control-sidebar.jsp\" %>\n" +
                    "</div>\n" +
                    "<!-- ./wrapper -->\n" +
                    "<%@ include file=\"../layout/script.jsp\" %>\n" +
                    "</body>\n" +
                    "</html>\n";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Hack By Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void showViews() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/view.jsp");
            String question = "\n";
            question += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <title>" + dtp.table(i) + " Detail</title>\n" +
                    "    <%@ include file=\"../layout/head.jsp\" %>\n" +
                    "    <%@ include file=\"../layout/css.jsp\" %>\n" +
                    "</head>\n" +
                    "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                    "<div class=\"wrapper\">\n" +
                    "\n" +
                    "    <%@ include file=\"../layout/header.jsp\" %>\n" +
                    "    <!-- Left side column. contains the logo and sidebar -->\n" +
                    "    <%@ include file=\"../layout/sidebar.jsp\" %>\n" +
                    "    <!-- Content Wrapper. Contains page content -->\n" +
                    "    <div class=\"content-wrapper\">\n" +
                    "        <!-- Content Header (Page header) -->\n" +
                    "        <section class=\"content-header\">\n" +
                    "            <h1>\n" +
                    "                " + dtp.table(i) + " Detail\n" +
                    "            </h1>\n" +
                    "            <ol class=\"breadcrumb\">\n" +
                    "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>Home</a></li>\n" +
                    "                <li><a href=\"#\">" + dtp.table(i) + "</a></li>\n" +
                    "                <li class=\"active\">Detail</li>\n" +
                    "            </ol>\n" +
                    "        </section>\n" +
                    "\n" +
                    "        <!-- Main content -->\n" +
                    "        <section class=\"content\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-xs-12\">\n" +
                    "                    <div class=\"box\">\n" +
                    "                        <!-- /.box-header -->\n" +
                    "                        <div class=\"box-body\">\n" +
                    "                            <!-- /.box-header -->\n" +
                    "                            <!-- form start -->\n" +
                    "                            <form method=\"post\">\n" +
                    "                                <table id=\"example1\" class=\"table table-bordered table-striped\">\n";

            for (int j = 0; j < dtp.columnSize(i); j++) {
                question += "                                    <tr>\n" +
                        "                                        <th>" + dtp.column(i, j) + ":</th>\n" +
                        "                                        <td>${requestScope[\"" + dtp.table(i).toLowerCase() + "\"].get" + dtp.column(i, j).substring(0, 1).toUpperCase()
                        + dtp.column(i, j).substring(1) + "()}</td>\n" +
                        "                                    </tr>\n";
            }

            question += "                                </table>\n" +
                    "\n" +
                    "                                <div class=\"box-footer\">\n" +
                    "                                    <a href=\"/" + dtp.table(i).toLowerCase() + "\" class=\"btn btn-default btn-flat\">Back to " + dtp.table(i).toLowerCase() + " list</a>\n" +
                    "                                </div>\n" +
                    "                                <!-- /.box-footer -->\n" +
                    "                            </form>\n" +
                    "                            <!-- /.box-body -->\n" +
                    "                        </div>\n" +
                    "                        <!-- /.box -->\n" +
                    "                    </div>\n" +
                    "                    <!-- /.col -->\n" +
                    "                </div>\n" +
                    "                <!-- /.row -->\n" +
                    "        </section>\n" +
                    "        <!-- /.content -->\n" +
                    "    </div>\n" +
                    "    <!-- /.content-wrapper -->\n" +
                    "    <%@ include file=\"../layout/footer.jsp\" %>\n" +
                    "\n" +
                    "    <!-- Control Sidebar -->\n" +
                    "    <%@ include file=\"../layout/control-sidebar.jsp\" %>\n" +
                    "</div>\n" +
                    "<!-- ./wrapper -->\n" +
                    "<%@ include file=\"../layout/script.jsp\" %>\n" +
                    "</body>\n" +
                    "</html>\n";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Hack By Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void sidebarmini() throws SQLException, ClassNotFoundException {
        Path path = Paths.get(AMain.link + "web/WEB-INF/layout/sidebarmini.jsp");
        String question = "\n";
        question += "<li>\n" +
                "        <a href=\"/home\">\n" +
                "                <i class=\"fa fa-dashboard\"></i> <span>Home</span>\n" +
                "        </a>\n" +
                "    </li>\n";

        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            question += "<li class=\"treeview\">\n" +
                    "        <a href=\"#\">\n" +
                    "                <i class=\"fa fa-edit\"></i>\n" +
                    "                <span>" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "</span>\n" +
                    "                <span class=\"pull-right-container\">\n" +
                    "                        <i class=\"fa fa-angle-left pull-right\"></i>\n" +
                    "                </span>\n" +
                    "        </a>\n" +
                    "        <ul class=\"treeview-menu\">\n" +
                    "                <li><a href=\"/" + dtp.table(i).toLowerCase() + "\"><i class=\"fa fa-circle-o\"></i>List</a></li>\n" +
                    "                <li><a href=\"/" + dtp.table(i).toLowerCase() + "?action=create\"><i class=\"fa fa-circle-o\"></i>Create</a></li>\n" +
                    "        </ul>\n" +
                    "    </li>\n";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Sidebar By Dan \\[0o0]/");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void home() throws SQLException, ClassNotFoundException {
        Path path = Paths.get(AMain.link + "web/WEB-INF/home.jsp");
        String question = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <title>AutoDTP Ver1| Home</title>\n" +
                "    <%@ include file=\"layout/head.jsp\" %>\n" +
                "</head>\n" +
                "<body class=\"hold-transition skin-blue sidebar-mini\">\n" +
                "<div class=\"wrapper\">\n" +
                "\n" +
                "    <%@ include file=\"layout/header.jsp\" %>\n" +
                "    <!-- Left side column. contains the logo and sidebar -->\n" +
                "    <%@ include file=\"layout/sidebar.jsp\" %>\n" +
                "\n" +
                "    <!-- Content Wrapper. Contains page content -->\n" +
                "    <div class=\"content-wrapper\">\n" +
                "        <!-- Content Header (Page header) -->\n" +
                "        <section class=\"content-header\">\n" +
                "            <h1>\n" +
                "                Home\n" +
                "            </h1>\n" +
                "            <ol class=\"breadcrumb\">\n" +
                "                <li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                "            </ol>\n" +
                "        </section>\n" +
                "\n" +
                "        <!-- Main content -->\n" +
                "        <section class=\"content\">\n" +
                "        </section>\n" +
                "        <!-- /.content -->\n" +
                "    </div>\n" +
                "    <!-- /.content-wrapper -->\n" +
                "    <%@ include file=\"layout/footer.jsp\" %>\n" +
                "\n" +
                "    <!-- Control Sidebar -->\n" +
                "    <%@ include file=\"layout/control-sidebar.jsp\" %>\n" +
                "</div>\n" +
                "<!-- ./wrapper -->\n" +
                "<%@ include file=\"layout/script.jsp\" %>\n" +
                "</body>\n" +
                "</html>\n";

        Charset charset = Charset.forName("ISO-8859-1");
        try {
            Files.write(path, question.getBytes());
            System.out.println("Home By Dan \\[0o0]/");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}



