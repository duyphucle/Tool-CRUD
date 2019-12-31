import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BList {
    public void listInterFace() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link1 + dtp.table(i).toLowerCase() + "/list.html");

            String question = "<!DOCTYPE html>\n" +
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
                    "                <h1 class=\"h3 mb-2 text-gray-800\">" + dtp.table(i) + "s</h1>\n" +
                    "\n" +
                    "                <!-- DataTales Example -->\n" +
                    "                <div class=\"card shadow mb-4\">\n" +
                    "                    <div class=\"card-header py-3\">\n" +
                    "                        <h6 class=\"m-0 font-weight-bold text-primary\">" + dtp.table(i) + "s</h6>\n" +
                    "                        <div class=\"col-lg-3 mt-3\">\n" +
                    "                            <a href=\"/create-" + dtp.table(i).toLowerCase() + "\" class=\"btn btn-block btn-primary btn-flat\">Create</a>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"card-body\">\n" +
                    "                        <div class=\"table-responsive\">\n" +
                    "                            <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n" +
                    "                                <thead>\n" +
                    "                                <tr class=\"text-center\">\n";
            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                question += "                                    <th>" + dtp.column(i, j) + "</th>\n";

            }
//            for (int j = 0; j < dtp.fk(i).size(); j++) {
//                question += "                                    <th>" + dtp.fkName(i, j) + "</th>\n";
//            }

            question += "                                    <th>Edit</th>\n" +
                    "                                    <th>Delete</th>\n" +
                    "                                </tr>\n" +
                    "                                </thead>\n" +
                    "\n" +
                    "                                <tbody>\n" +
                    "                                <th:block th:each=\"" + dtp.table(i).toLowerCase() + " : ${" + dtp.table(i).toLowerCase() + "s}\">\n" +
                    "                                    <tr>\n";
//            for(int j=0;j<dtp.columnSize(i)-7;j++){
//                for (int k=0;k<dtp.fk(i).size();k++) {
//                    if (!dtp.column(i, j).equals(dtp.fkName(i, k))){
//                        question += "                                    <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.column(i, j) + "}\"></td>\n";
//                    } else {
//
//                    }
//                }
//            }

            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                if (!dtp.column(i, j).equals(AMain.columnImage)) {
                    if (dtp.pkSize(i) > 0) {
                        for (int x = 0; x < dtp.pkSize(i); x++) {
                            if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                                if (dtp.column(i, j).equals(dtp.column(i, 1))) {
                                    question += "<td><a th:href=\"@{/view-" + dtp.table(i).toLowerCase() + "/__${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 0) + "}__ }\" th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 1) + "}\"></a></td>\n";

                                } else {
                                    question += "                                    <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, j) + "}\"></td>\n";

                                }
                                break;
                            } else {
                                if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
                                    question += "                                <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongPK(i, x) + "." + dtp.inThuongCot(i, 0) + "}\"></td>\n";
                                    break;
                                }
                            }

                        }
                    } else {
//                        question += "                                    <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, j) + "}\"></td>\n";
                        if (dtp.column(i, j).equals(dtp.column(i, 1))) {
                            question += "<td><a th:href=\"@{/view-" + dtp.table(i).toLowerCase() + "/__${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 0) + "}__ }\" th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 1) + "}\"></a></td>\n";

                        } else {
                            question += "                                    <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, j) + "}\"></td>\n";

                        }
                    }
                } else {
                    question += "<td height=50 width=200><img th:if=\"${" + dtp.table(i).toLowerCase() + "." + AMain.columnImage + "}\" th:src=\"${" + dtp.table(i).toLowerCase() + "." + AMain.columnImage.substring(0, 1).toLowerCase() + AMain.columnImage.substring(1) + "}\" height=50 width=200></td>";
                }

            }

//            for (int j = 0; j < dtp.fk(i).size()-7; j++) {
//                question += "                                       <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.pk(i,j).toLowerCase() + "." + dtp.column(i, 0) + "}\"></td>\n" ;
//            }

            question += "                                        <td><a th:href=\"@{/edit-" + dtp.table(i).toLowerCase() + "/__${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 0) + "}__ }\"\n" +
                    "                                               class=\"btn btn-block btn-primary btn-flat\">Edit</a></td>\n" +
                    "                                        <td><a th:href=\"@{/delete-" + dtp.table(i).toLowerCase() + "/__${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, 0) + "}__ }\"\n";

            question += "                                               class=\"btn btn-block btn-primary btn-flat\"\n" +
                    "                                               onclick=\"return confirm('Are you sure?')\">Delete</a></td>\n" +
                    "                                    </tr>\n" +
                    "                                </th:block>\n" +
                    "                                </tbody>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
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
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("JDBC Interface created by Tran Huu Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
