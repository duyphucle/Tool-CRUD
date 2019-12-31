import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class BViewcreate {

    public void showcreate() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link1 + dtp.table(i).toLowerCase() + "/create.html");
            String question = "\n" +
                    "<!DOCTYPE html>\n" +
                    "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "\n" +
                    "<head th:replace=\"/layout::head\"></head>\n" +
                    "\n" +
                    "<body id=\"page-top\">\n" +
                    "\n" +
                    "<!-- Page Wrapper -->\n" +
                    "<div id=\"wrapper\">\n" +
                    "\n" +
                    "    <!-- Sidebar -->\n" +
                    "    <th:block th:replace=\"/layout::sidebar\"></th:block>\n" +
                    "    <!-- End of Sidebar -->\n" +
                    "\n" +
                    "    <!-- Content Wrapper -->\n" +
                    "    <div id=\"content-wrapper\" class=\"d-flex flex-column\">\n" +
                    "\n" +
                    "        <!-- Main Content -->\n" +
                    "        <div id=\"content\">\n" +
                    "\n" +
                    "            <!-- Topbar -->\n" +
                    "            <th:block th:replace=\"/layout::topbar\"></th:block>\n" +
                    "            <!-- End of Topbar -->\n" +
                    "\n" +
                    "            <!-- Begin Page Content -->\n" +
                    "            <div class=\"container-fluid\">\n" +
                    "\n" +
                    "                <!-- Page Heading -->\n" +
                    "                <h1 class=\"h3 mb-2 text-gray-800\">" + dtp.table(i) + "</h1>\n" +
                    "\n" +
                    "                <!-- DataTales Example -->\n" +
                    "                <div class=\"card shadow mb-4\">\n" +
                    "                    <div class=\"card-header py-3\">\n" +
                    "                        <h6 class=\"m-0 font-weight-bold text-primary\">Create " + dtp.table(i) + "s</h6>\n" +
                    "\n" +
                    "                        <th:block th:if=\"${message}\">\n" +
                    "                            <div class=\"alert alert-success\" role=\"alert\">\n" +
                    "                                <span class=\"message\" th:text=\"${message}\"></span>\n" +
                    "                            </div>\n" +
                    "                        </th:block>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"card-body\">\n";
            if (AMain.upfiles.contains(dtp.table(i))) {
                question += "                        <form class=\"form-horizontal\" th:action=\"@{/create-" + dtp.table(i).toLowerCase() + "}\" th:object=\"${" + dtp.table(i).toLowerCase() + "UpFile}\"\n" +
                        "                              method=\"post\" enctype=\"multipart/form-data\">\n";
            } else {
                question += "                        <form class=\"form-horizontal\" th:action=\"@{/create-" + dtp.table(i).toLowerCase() + "}\" th:object=\"${" + dtp.table(i).toLowerCase() + "}\"\n" +
                        "                              method=\"post\">\n";
            }

            int jj = 0;
            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                if (!dtp.column(i, j).equals(AMain.columnImage)) {
                    if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                        question += "                            <div class=\"form-group\">\n" +
                                "                                <label class=\"col-sm-3 control-label\">" + dtp.column(i, j) + "</label>\n" +
                                "                                <div class=\"col-sm-12\">\n" +
                                "                                    <input type=\"text\" class=\"form-control\" name=\"" + dtp.column(i, j) + "\" th:field=\"*{" + dtp.inThuongCot(i, j) + "}\">\n" +
                                "                                    <th:block th:if=\"${#fields.hasErrors('" + dtp.inThuongCot(i, j) + "')}\" th:errors=\"*{" + dtp.inThuongCot(i, j) + "}\">" + dtp.column(i, j) + " Error</th:block>\n" +
                                "                                </div>\n" +
                                "                            </div>\n";
                    } else {

                        question += "                            <div class=\"form-group\">\n" +
                                "                                <label class=\"col-sm-3 control-label\">" + dtp.pk(i, jj) + "</label>\n" +
                                "                                <div class=\"col-sm-12\">\n" +
                                "                                    <select name=\"" + dtp.pk(i, jj).toLowerCase() + "\" class=\"form-control\" th:field=\"*{" + dtp.inThuongPK(i, jj) + "}\">\n" +
                                "                                        <th:block th:each=\"" + dtp.pk(i, jj).toLowerCase() + " : ${" + dtp.pk(i, jj).toLowerCase() + "s}\">\n" +
                                "                                            <option th:value=\"${" + dtp.pk(i, jj).toLowerCase() + "." + dtp.pkName(i, 0) + "}\" th:text=\"${" + dtp.pk(i, jj).toLowerCase() + "." + dtp.pkName(i, 0) + "}\"></option>\n" +
                                "                                        </th:block>\n" +
                                "                                    </select>\n" +
                                "                                </div>\n" +
                                "                            </div>\n";
                        jj++;
                    }
                } else {
                    question += "                            <div class=\"form-group\">\n" +
                            "                                <label class=\"col-sm-3 control-label\">" + dtp.column(i, j) + "</label>\n" +
                            "                                <div class=\"col-sm-12\">\n" +
                            "                                    <input type=\"file\" name=\"fileDatas\" th:field=\"*{fileDatas}\"/>\n" +
                            "                                </div>\n" +
                            "                            </div>\n";
                }
            }


            question += "                            <!-- /.box-body -->\n" +
                    "                            <div class=\"box-footer\">\n" +
                    "                                <button type=\"reset\" class=\"btn btn-secondary btn-flat\">Reset</button>\n" +
                    "                                <button type=\"submit\" class=\"btn btn-primary btn-flat float-right\">Create " + dtp.table(i).toLowerCase() + "\n" +
                    "                                </button>\n" +
                    "                            </div>\n" +
                    "                            <!-- /.box-footer -->\n" +
                    "                        </form>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "\n" +
                    "            </div>\n" +
                    "            <!-- /.container-fluid -->\n" +
                    "\n" +
                    "        </div>\n" +
                    "        <!-- End of Main Content -->\n" +
                    "\n" +
                    "        <!-- Footer -->\n" +
                    "        <th:block th:replace=\"/layout::footer\"></th:block>\n" +
                    "        <!-- End of Footer -->\n" +
                    "\n" +
                    "    </div>\n" +
                    "    <!-- End of Content Wrapper -->\n" +
                    "\n" +
                    "</div>\n" +
                    "<!-- End of Page Wrapper -->\n" +
                    "\n" +
                    "<!-- Scroll to Top Button-->\n" +
                    "<th:block th:replace=\"/layout::topbtn\"></th:block>\n" +
                    "\n" +
                    "<!-- Logout Modal-->\n" +
                    "<th:block th:replace=\"/layout::logoutmodal\"></th:block>\n" +
                    "\n" +
                    "<th:block th:replace=\"/layout::jsfiles\"></th:block>\n" +
                    "</body>\n" +
                    "\n" +
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

}





