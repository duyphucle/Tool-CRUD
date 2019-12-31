import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BLayout {
    public void createLayout() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        Path path = Paths.get(AMain.link1 + "layout.html");

        String question = "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head th:fragment=\"head\">\n" +
                "\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <meta name=\"description\" content=\"\">\n" +
                "    <meta name=\"author\" content=\"\">\n" +
                "\n" +
                "    <title>SB Admin 2 - Tables</title>\n" +
                "\n" +
                "    <!-- Custom fonts for this template -->\n" +
                "    <link href=\"/sb2/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Custom styles for this template -->\n" +
                "    <link href=\"/sb2/css/sb-admin-2.min.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Custom styles for this page -->\n" +
                "    <link href=\"/sb2/vendor/datatables/dataTables.bootstrap4.min.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<th:block th:fragment=\"sidebar\">\n" +
                "    <ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">\n" +
                "\n" +
                "        <!-- Sidebar - Brand -->\n" +
                "        <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"/customers\">\n" +
                "            <div class=\"sidebar-brand-icon rotate-n-15\">\n" +
                "                <i class=\"fas fa-laugh-wink\"></i>\n" +
                "            </div>\n" +
                "            <div class=\"sidebar-brand-text mx-3\">SB Admin <sup>2</sup></div>\n" +
                "        </a>\n" +
                "\n" +
                "        <!-- Divider -->\n" +
                "        <hr class=\"sidebar-divider my-0\">\n" +
                "\n" +
                "        <!-- Nav Item - Dashboard -->\n" +
                "        <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"#\">\n" +
                "                <i class=\"fas fa-fw fa-tachometer-alt\"></i>\n" +
                "                <span>Dashboard</span></a>\n" +
                "        </li>\n" +
                "\n" +
                "        <!-- Divider -->\n" +
                "        <hr class=\"sidebar-divider\">\n" +
                "\n" +
                "        <!-- Nav Item - Pages Collapse Menu -->\n";

        for (int i = 0; i < dtp.tableSize(); i++) {
            question +=
                    "        <li class=\"nav-item\">\n" +
                            "            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapse" + dtp.table(i) + "\" aria-expanded=\"true\" aria-controls=\"collapse" + dtp.table(i) + "\">\n" +
                            "                <i class=\"fas fa-fw fa-table\"></i>\n" +
                            "                <span>" + dtp.table(i) + "</span>\n" +
                            "            </a>\n" +
                            "            <div id=\"collapse" + dtp.table(i) + "\" class=\"collapse\" aria-labelledby=\"heading" + dtp.table(i) + "\" data-parent=\"#accordionSidebar\">\n" +
                            "                <div class=\"bg-white py-2 collapse-inner rounded\">\n" +
                            "                    <h6 class=\"collapse-header\">Custom " + dtp.table(i) + ":</h6>\n" +
                            "                    <a class=\"collapse-item\" href=\"/" + dtp.table(i).toLowerCase() + "s\">List</a>\n" +
                            "                    <a class=\"collapse-item\" href=\"/create-" + dtp.table(i).toLowerCase() + "\">Create</a>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </li>\n";
        }

        question +=
                "        <!-- Divider -->\n" +
                        "        <hr class=\"sidebar-divider d-none d-md-block\">\n" +
                        "\n" +
                        "        <!-- Sidebar Toggler (Sidebar) -->\n" +
                        "        <div class=\"text-center d-none d-md-inline\">\n" +
                        "            <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </ul>\n" +
                        "</th:block>\n" +
                        "\n" +
                        "<th:block th:fragment=\"topbar\">\n" +
                        "    <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">\n" +
                        "\n" +
                        "        <!-- Sidebar Toggle (Topbar) -->\n" +
                        "        <button id=\"sidebarToggleTop\" class=\"btn btn-link d-md-none rounded-circle mr-3\">\n" +
                        "            <i class=\"fa fa-bars\"></i>\n" +
                        "        </button>\n" +
                        "\n" +
                        "        <!-- Topbar Search -->\n" +
                        "        <form class=\"d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search\">\n" +
                        "            <div class=\"input-group\">\n" +
                        "                <input type=\"text\" class=\"form-control bg-light border-0 small\" placeholder=\"Search for...\"\n" +
                        "                       aria-label=\"Search\" aria-describedby=\"basic-addon2\">\n" +
                        "                <div class=\"input-group-append\">\n" +
                        "                    <button class=\"btn btn-primary\" type=\"button\">\n" +
                        "                        <i class=\"fas fa-search fa-sm\"></i>\n" +
                        "                    </button>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </form>\n" +
                        "\n" +
                        "        <!-- Topbar Navbar -->\n" +
                        "        <ul class=\"navbar-nav ml-auto\">\n" +
                        "\n" +
                        "            <!-- Nav Item - Search Dropdown (Visible Only XS) -->\n" +
                        "            <li class=\"nav-item dropdown no-arrow d-sm-none\">\n" +
                        "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"searchDropdown\" role=\"button\"\n" +
                        "                   data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                        "                    <i class=\"fas fa-search fa-fw\"></i>\n" +
                        "                </a>\n" +
                        "                <!-- Dropdown - Messages -->\n" +
                        "                <div class=\"dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in\"\n" +
                        "                     aria-labelledby=\"searchDropdown\">\n" +
                        "                    <form class=\"form-inline mr-auto w-100 navbar-search\">\n" +
                        "                        <div class=\"input-group\">\n" +
                        "                            <input type=\"text\" class=\"form-control bg-light border-0 small\"\n" +
                        "                                   placeholder=\"Search for...\" aria-label=\"Search\"\n" +
                        "                                   aria-describedby=\"basic-addon2\">\n" +
                        "                            <div class=\"input-group-append\">\n" +
                        "                                <button class=\"btn btn-primary\" type=\"button\">\n" +
                        "                                    <i class=\"fas fa-search fa-sm\"></i>\n" +
                        "                                </button>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </form>\n" +
                        "                </div>\n" +
                        "            </li>\n" +
                        "\n" +
                        "            <!-- Nav Item - Alerts -->\n" +
                        "            <li class=\"nav-item dropdown no-arrow mx-1\">\n" +
                        "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\"\n" +
                        "                   data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                        "                    <i class=\"fas fa-bell fa-fw\"></i>\n" +
                        "                    <!-- Counter - Alerts -->\n" +
                        "                    <span class=\"badge badge-danger badge-counter\">3+</span>\n" +
                        "                </a>\n" +
                        "                <!-- Dropdown - Alerts -->\n" +
                        "                <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n" +
                        "                     aria-labelledby=\"alertsDropdown\">\n" +
                        "                    <h6 class=\"dropdown-header\">\n" +
                        "                        Alerts Center\n" +
                        "                    </h6>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"mr-3\">\n" +
                        "                            <div class=\"icon-circle bg-primary\">\n" +
                        "                                <i class=\"fas fa-file-alt text-white\"></i>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"small text-gray-500\">December 12, 2019</div>\n" +
                        "                            <span class=\"font-weight-bold\">A new monthly report is ready to download!</span>\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"mr-3\">\n" +
                        "                            <div class=\"icon-circle bg-success\">\n" +
                        "                                <i class=\"fas fa-donate text-white\"></i>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"small text-gray-500\">December 7, 2019</div>\n" +
                        "                            $290.29 has been deposited into your account!\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"mr-3\">\n" +
                        "                            <div class=\"icon-circle bg-warning\">\n" +
                        "                                <i class=\"fas fa-exclamation-triangle text-white\"></i>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"small text-gray-500\">December 2, 2019</div>\n" +
                        "                            Spending Alert: We've noticed unusually high spending for your account.\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Show All Alerts</a>\n" +
                        "                </div>\n" +
                        "            </li>\n" +
                        "\n" +
                        "            <!-- Nav Item - Messages -->\n" +
                        "            <li class=\"nav-item dropdown no-arrow mx-1\">\n" +
                        "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"messagesDropdown\" role=\"button\"\n" +
                        "                   data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                        "                    <i class=\"fas fa-envelope fa-fw\"></i>\n" +
                        "                    <!-- Counter - Messages -->\n" +
                        "                    <span class=\"badge badge-danger badge-counter\">7</span>\n" +
                        "                </a>\n" +
                        "                <!-- Dropdown - Messages -->\n" +
                        "                <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n" +
                        "                     aria-labelledby=\"messagesDropdown\">\n" +
                        "                    <h6 class=\"dropdown-header\">\n" +
                        "                        Message Center\n" +
                        "                    </h6>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"dropdown-list-image mr-3\">\n" +
                        "                            <img class=\"rounded-circle\" src=\"https://source.unsplash.com/fn_BT9fwg_E/60x60\"\n" +
                        "                                 alt=\"\">\n" +
                        "                            <div class=\"status-indicator bg-success\"></div>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"font-weight-bold\">\n" +
                        "                            <div class=\"text-truncate\">Hi there! I am wondering if you can help me with a\n" +
                        "                                problem I've been having.\n" +
                        "                            </div>\n" +
                        "                            <div class=\"small text-gray-500\">Emily Fowler · 58m</div>\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"dropdown-list-image mr-3\">\n" +
                        "                            <img class=\"rounded-circle\" src=\"https://source.unsplash.com/AU4VPcFN4LE/60x60\"\n" +
                        "                                 alt=\"\">\n" +
                        "                            <div class=\"status-indicator\"></div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"text-truncate\">I have the photos that you ordered last month, how would\n" +
                        "                                you like them sent to you?\n" +
                        "                            </div>\n" +
                        "                            <div class=\"small text-gray-500\">Jae Chun · 1d</div>\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"dropdown-list-image mr-3\">\n" +
                        "                            <img class=\"rounded-circle\" src=\"https://source.unsplash.com/CS2uCrpNzJY/60x60\"\n" +
                        "                                 alt=\"\">\n" +
                        "                            <div class=\"status-indicator bg-warning\"></div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"text-truncate\">Last month's report looks great, I am very happy with the\n" +
                        "                                progress so far, keep up the good work!\n" +
                        "                            </div>\n" +
                        "                            <div class=\"small text-gray-500\">Morgan Alvarez · 2d</div>\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n" +
                        "                        <div class=\"dropdown-list-image mr-3\">\n" +
                        "                            <img class=\"rounded-circle\" src=\"https://source.unsplash.com/Mv9hjnEUHR4/60x60\"\n" +
                        "                                 alt=\"\">\n" +
                        "                            <div class=\"status-indicator bg-success\"></div>\n" +
                        "                        </div>\n" +
                        "                        <div>\n" +
                        "                            <div class=\"text-truncate\">Am I a good boy? The reason I ask is because someone told\n" +
                        "                                me that people say this to all dogs, even if they aren't good...\n" +
                        "                            </div>\n" +
                        "                            <div class=\"small text-gray-500\">Chicken the Dog · 2w</div>\n" +
                        "                        </div>\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Read More Messages</a>\n" +
                        "                </div>\n" +
                        "            </li>\n" +
                        "\n" +
                        "            <div class=\"topbar-divider d-none d-sm-block\"></div>\n" +
                        "\n" +
                        "            <!-- Nav Item - User Information -->\n" +
                        "            <li class=\"nav-item dropdown no-arrow\">\n" +
                        "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"\n" +
                        "                   data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                        "                    <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\">Valerie Luna</span>\n" +
                        "                    <img class=\"img-profile rounded-circle\" src=\"https://source.unsplash.com/QAB-WJcbgJk/60x60\">\n" +
                        "                </a>\n" +
                        "                <!-- Dropdown - User Information -->\n" +
                        "                <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n" +
                        "                     aria-labelledby=\"userDropdown\">\n" +
                        "                    <a class=\"dropdown-item\" href=\"#\">\n" +
                        "                        <i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>\n" +
                        "                        Profile\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item\" href=\"#\">\n" +
                        "                        <i class=\"fas fa-cogs fa-sm fa-fw mr-2 text-gray-400\"></i>\n" +
                        "                        Settings\n" +
                        "                    </a>\n" +
                        "                    <a class=\"dropdown-item\" href=\"#\">\n" +
                        "                        <i class=\"fas fa-list fa-sm fa-fw mr-2 text-gray-400\"></i>\n" +
                        "                        Activity Log\n" +
                        "                    </a>\n" +
                        "                    <div class=\"dropdown-divider\"></div>\n" +
                        "                    <a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModal\">\n" +
                        "                        <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>\n" +
                        "                        Logout\n" +
                        "                    </a>\n" +
                        "                </div>\n" +
                        "            </li>\n" +
                        "\n" +
                        "        </ul>\n" +
                        "\n" +
                        "    </nav>\n" +
                        "</th:block>\n" +
                        "\n" +
                        "<th:block th:fragment=\"footer\">\n" +
                        "    <footer class=\"sticky-footer bg-white\">\n" +
                        "        <div class=\"container my-auto\">\n" +
                        "            <div class=\"copyright text-center my-auto\">\n" +
                        "                <span>Copyright &copy; Your Website 2019</span>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </footer>\n" +
                        "</th:block>\n" +
                        "\n" +
                        "<th:block th:fragment=\"topbtn\">\n" +
                        "    <a class=\"scroll-to-top rounded\" href=\"#page-top\">\n" +
                        "        <i class=\"fas fa-angle-up\"></i>\n" +
                        "    </a>\n" +
                        "</th:block>\n" +
                        "\n" +
                        "<th:block th:fragment=\"logoutmodal\">\n" +
                        "    <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"\n" +
                        "         aria-hidden=\"true\">\n" +
                        "        <div class=\"modal-dialog\" role=\"document\">\n" +
                        "            <div class=\"modal-content\">\n" +
                        "                <div class=\"modal-header\">\n" +
                        "                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>\n" +
                        "                    <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                        "                        <span aria-hidden=\"true\">×</span>\n" +
                        "                    </button>\n" +
                        "                </div>\n" +
                        "                <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>\n" +
                        "                <div class=\"modal-footer\">\n" +
                        "                    <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n" +
                        "                    <a class=\"btn btn-primary\" href=\"/sb2/login.html\">Logout</a>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</th:block>\n" +
                        "\n" +
                        "<th:block th:fragment=\"jsfiles\">\n" +
                        "    <!-- Bootstrap core JavaScript-->\n" +
                        "    <script src=\"/sb2/vendor/jquery/jquery.min.js\"></script>\n" +
                        "    <script src=\"/sb2/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n" +
                        "\n" +
                        "    <!-- Core plugin JavaScript-->\n" +
                        "    <script src=\"/sb2/vendor/jquery-easing/jquery.easing.min.js\"></script>\n" +
                        "\n" +
                        "    <!-- Custom scripts for all pages-->\n" +
                        "    <script src=\"/sb2/js/sb-admin-2.min.js\"></script>\n" +
                        "\n" +
                        "    <!-- Page level plugins -->\n" +
                        "    <script src=\"/sb2/vendor/datatables/jquery.dataTables.min.js\"></script>\n" +
                        "    <script src=\"/sb2/vendor/datatables/dataTables.bootstrap4.min.js\"></script>\n" +
                        "\n" +
                        "    <!-- Page level custom scripts -->\n" +
                        "    <script src=\"/sb2/js/demo/datatables-demo.js\"></script>\n" +
                        "</th:block>\n" +
                        "</body>\n" +
                        "</html>\n";

        Charset charset = Charset.forName("ISO-8859-1");
        try {
            Files.write(path, question.getBytes());
            System.out.println("Layout created by Dan");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
