import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BController {
    public void createController() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "controller/" + dtp.table(i) + "Controller.java");

            String question = " package com.codegym.cms.controller; \n ";

            if (dtp.pkSize(0) > 0) {
                for (int j = 0; j < dtp.pkSize(i); j++) {
                    question += "import com.codegym.cms.model." + dtp.pk(i, j) + ";\n" +
                            "import com.codegym.cms.service." + dtp.pk(i, j) + "Service;\n";
                }
            }
            question += "import com.codegym.cms.model." + dtp.table(i) + ";\n" +
                    "import com.codegym.cms.service." + dtp.table(i) + "Service;\n" +
                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import org.springframework.stereotype.Controller;\n" +
                    "import org.springframework.validation.BindingResult;\n" +
                    "import org.springframework.web.bind.annotation.*;\n" +
                    "import org.springframework.web.servlet.ModelAndView;\n" +
                    "\n" +
                    "import javax.validation.Valid;\n" +
                    "import java.time.LocalDate;\n" +
                    "\n";
            if (AMain.upfiles.contains(dtp.table(i))) {
                question +=
                        "import com.codegym.cms.model." + dtp.table(i) + "UpFile;\n" +
                                "import org.springframework.web.bind.WebDataBinder;\n" +
                                "import org.springframework.web.multipart.commons.CommonsMultipartFile;\n" +
                                "import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;\n" +
                                "import javax.servlet.http.HttpServletRequest;\n" +
                                "import java.io.BufferedOutputStream;\n" +
                                "import java.io.File;\n" +
                                "import java.io.FileOutputStream;\n" +
                                "\n";
            }
            question +=
                    "@Controller\n" +
                            "public class " + dtp.table(i) + "Controller {\n" +
                            "\n" +
                            "    @Autowired\n" +
                            "    private " + dtp.table(i) + "Service " + dtp.table(i).toLowerCase() + "Service;\n" +
                            "\n";
            for (int j = 0; j < dtp.pkSize(i); j++) {
                question += "@Autowired\n    private " + dtp.pk(i, j) + "Service " + dtp.pk(i, j).toLowerCase() + "Service;\n" +
                        "    @ModelAttribute(\"" + dtp.pk(i, j).toLowerCase() + "s" + "\")\n" +
                        "    public Iterable<" + dtp.pk(i, j) + "> " + dtp.pk(i, j).toLowerCase() + "s" + "() {\n" +
                        "        return " + dtp.pk(i, j).toLowerCase() + "Service.findAllByIsDeletedEquals(0);\n" +
                        "    }\n";
            }

            question += "\n" +
                    "@GetMapping(\"/" + dtp.table(i).toLowerCase() + "s" + "\")\n" +
                    "    public ModelAndView list" + dtp.table(i) + "s" + "(@RequestParam(value = \"s\", required = false) String s) {\n" +
                    "        Iterable<" + dtp.table(i) + "> " + dtp.table(i).toLowerCase() + "s;\n" +
                    "            " + dtp.table(i).toLowerCase() + "s = " + dtp.table(i).toLowerCase() + "Service.findAllByIsDeletedEquals(0);\n" +
                    "\n" +
                    "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/list\");\n" +
                    "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "s\", " + dtp.table(i).toLowerCase() + "s);\n" +
                    "        return modelAndView;\n" +
                    "    }\n" +
                    "\n";

            if (AMain.upfiles.contains(dtp.table(i))) {
                question += "    @GetMapping(\"/create-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView showCreateForm() {\n" +
                        "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "UpFile\", new " + dtp.table(i) + "UpFile());\n" +
                        "        return modelAndView;\n" +
                        "    }\n" +
                        "@PostMapping(\"/create-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView checkValidation(@Valid @ModelAttribute(\"" + dtp.table(i).toLowerCase() + "\") " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + ", BindingResult bindingResult, HttpServletRequest request, @ModelAttribute(\"" + dtp.table(i).toLowerCase() + "UpFile\") " + dtp.table(i) + "UpFile " + dtp.table(i).toLowerCase() + "UpFile) {\n" +
                        "        if (bindingResult.hasFieldErrors()) {\n" +
                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "            return modelAndView;\n" +
                        "        } else {\n" +
                        " // Thư mục gốc upload file.\n" +
                        "            String uploadRootPath = request.getServletContext().getRealPath(\"upload\");\n" +
                        "            //        System.out.println(\"uploadRootPath=\" + uploadRootPath);\n" +
                        "            File uploadRootDir = new File(uploadRootPath);\n" +
                        "            // Tạo thư mục gốc upload nếu nó không tồn tại.\n" +
                        "            if (!uploadRootDir.exists()) {\n" +
                        "                uploadRootDir.mkdirs();\n" +
                        "            }\n" +
                        "\n" +
                        "            CommonsMultipartFile[] fileDatas = " + dtp.table(i).toLowerCase() + "UpFile.getFileDatas();\n" +
                        "            for (CommonsMultipartFile fileData : fileDatas) {\n" +
                        "                // Tên file gốc tại Client.\n" +
                        "                String name = fileData.getOriginalFilename();\n" +
                        "//            System.out.println(\"Client File Name = \" + name);\n" +
                        "                if (name != null && name.length() > 0) {\n" +
                        "                    try {\n" +
                        "                        // Tạo file tại Server.\n" +
                        "                        File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);\n" +
                        "                        // Luồng ghi dữ liệu vào file trên Server.\n" +
                        "                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));\n" +
                        "                        stream.write(fileData.getBytes());\n" +
                        "                        stream.close();\n" +
                        "                        " + dtp.table(i).toLowerCase() + ".set" + AMain.columnImage.substring(0, 1).toUpperCase() + AMain.columnImage.substring(1) + "(uploadRootDir.getName() + File.separator + name);\n" +
                        "                    } catch (Exception e) {\n" +
                        "                        System.out.println(\"Error Write file: \" + name);\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            " + dtp.table(i).toLowerCase() + "Service.create(";
                for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                    if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                        question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "(), ";
                    } else {
                        question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaPk(i, dtp.fk(i).indexOf(dtp.column(i, j))) + "().get" + dtp.inHoaCot(i, 0) + "(), ";
                    }
                }

                question += " LocalDate.now(), \"Phuc\");\n" +
                        "            }\n" +
                        "\n" +


                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "            modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", new " + dtp.table(i) + "());\n" +
                        "            modelAndView.addObject(\"message\", \"New " + dtp.table(i).toLowerCase() + " created successfully\");\n" +
                        "            return modelAndView;\n" +
                        "        }\n" +
                        "    }\n\n";

                question += "    // Phương thức này được gọi mỗi lần có Submit.\n" +
                        "    @InitBinder\n" +
                        "    public void initBinder(WebDataBinder dataBinder) {\n" +
                        "        Object target = dataBinder.getTarget();\n" +
                        "        if (target == null) {\n" +
                        "            return;\n" +
                        "        }\n" +
                        "//        System.out.println(\"Target=\" + target);\n" +
                        "\n" +
                        "        if (target.getClass() == " + dtp.table(i) + "UpFile.class) {\n" +
                        "            // Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]\n" +
                        "            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());\n" +
                        "        }\n" +
                        "    }";


                question += "@GetMapping(\"/edit-" + dtp.table(i).toLowerCase() + "/{id}\")\n" +
                        "    public ModelAndView showEditForm(@PathVariable Long id) {\n" +
                        "        " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + " = " + dtp.table(i).toLowerCase() + "Service.findById(id);\n" +
                        "        if (" + dtp.table(i).toLowerCase() + " != null) {\n" +

                        "        " + dtp.table(i) + "UpFile " + dtp.table(i).toLowerCase() + "UpFile = new " + dtp.table(i) + "UpFile();\n";
                for (int j = 0; j < dtp.columnSize(i) - 7; j++) {
                    if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                        question += "        " + dtp.table(i).toLowerCase() + "UpFile.set" + dtp.inHoaCot(i, j) + "(" + dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "());\n";
                    } else {
                        question += "        " + dtp.table(i).toLowerCase() + "UpFile.set" + dtp.inHoaPk(i, dtp.fk(i).indexOf(dtp.column(i, j))) + "(" + dtp.table(i).toLowerCase() + ".get" + dtp.inHoaPk(i, dtp.fk(i).indexOf(dtp.column(i, j))) + "());\n";
                    }

                }
                question +=

                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/edit\");\n" +
                                "            modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "UpFile\", " + dtp.table(i).toLowerCase() + "UpFile);\n" +
                                "            return modelAndView;\n" +
                                "\n" +
                                "        } else {\n" +
                                "            ModelAndView modelAndView = new ModelAndView(\"/error.404\");\n" +
                                "            return modelAndView;\n" +
                                "        }\n" +
                                "    }\n";

                question += "@PostMapping(\"/edit-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView update" + dtp.table(i) + "(HttpServletRequest request, @ModelAttribute(\"" + dtp.table(i).toLowerCase() + "UpFile\") " + dtp.table(i) + "UpFile " + dtp.table(i).toLowerCase() + "UpFile) {\n" +
                        " // Thư mục gốc upload file.\n" +
                        "            String uploadRootPath = request.getServletContext().getRealPath(\"upload\");\n" +
                        "            //        System.out.println(\"uploadRootPath=\" + uploadRootPath);\n" +
                        "            File uploadRootDir = new File(uploadRootPath);\n" +
                        "            // Tạo thư mục gốc upload nếu nó không tồn tại.\n" +
                        "            if (!uploadRootDir.exists()) {\n" +
                        "                uploadRootDir.mkdirs();\n" +
                        "            }\n" +
                        "\n" +
                        "            CommonsMultipartFile[] fileDatas = " + dtp.table(i).toLowerCase() + "UpFile.getFileDatas();\n" +
                        "            for (CommonsMultipartFile fileData : fileDatas) {\n" +
                        "                // Tên file gốc tại Client.\n" +
                        "                String name = fileData.getOriginalFilename();\n" +
                        "//            System.out.println(\"Client File Name = \" + name);\n" +
                        "                if (name != null && name.length() > 0) {\n" +
                        "                    try {\n" +
                        "                        // Tạo file tại Server.\n" +
                        "                        File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);\n" +
                        "                        // Luồng ghi dữ liệu vào file trên Server.\n" +
                        "                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));\n" +
                        "                        stream.write(fileData.getBytes());\n" +
                        "                        stream.close();\n" +
                        "                        " + dtp.table(i).toLowerCase() + "UpFile.set" + AMain.columnImage.substring(0, 1).toUpperCase() + AMain.columnImage.substring(1) + "(uploadRootDir.getName() + File.separator + name);\n" +
                        "                    } catch (Exception e) {\n" +
                        "                        System.out.println(\"Error Write file: \" + name);\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            " + dtp.table(i).toLowerCase() + "Service.edit(";
                for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                    if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                        question += dtp.table(i).toLowerCase() + "UpFile.get" + dtp.inHoaCot(i, j) + "(), ";
                    } else {
                        question += dtp.table(i).toLowerCase() + "UpFile.get" + dtp.inHoaPk(i, dtp.fk(i).indexOf(dtp.column(i, j))) + "().get" + dtp.inHoaCot(i, 0) + "(), ";
                    }
                }

                question += " LocalDate.now(), \"Phuc\"," + dtp.table(i).toLowerCase() + "UpFile.get" + dtp.inHoaCot(i, 0) + "());\n" +
                        "}\n" +
                        "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/edit\");\n" +
                        "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "UpFile\", " + dtp.table(i).toLowerCase() + "UpFile);\n" +
                        "        modelAndView.addObject(\"message\", \"" + dtp.table(i) + " updated successfully\");\n" +
                        "        return modelAndView;\n" +
                        "    }\n\n";

            } else {
                question += "    @GetMapping(\"/create-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView showCreateForm() {\n" +
                        "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", new " + dtp.table(i) + "());\n" +
                        "        return modelAndView;\n" +
                        "    }\n" +
                        "@PostMapping(\"/create-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView checkValidation(@Valid @ModelAttribute(\"" + dtp.table(i).toLowerCase() + "\") " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + ", BindingResult bindingResult) {\n" +
                        "        if (bindingResult.hasFieldErrors()) {\n" +
                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "            return modelAndView;\n" +
                        "        } else {\n" +
                        "            " + dtp.table(i).toLowerCase() + "Service.create(";

                for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                    if (dtp.pkSize(i) > 0) {
                        for (int x = 0; x < dtp.pkSize(i); x++) {
                            if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                                question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "(),";
                                break;
                            } else {
                                if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
                                    question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaPk(i, x) + "().get" + dtp.inHoaCot(i, 0) + "(),";
                                }
                            }

                        }
                    } else {
                        question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "(),";

                    }
                }

                question += " LocalDate.now(), \"Phuc\");\n" +
                        "\n" +
                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/create\");\n" +
                        "            modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", new " + dtp.table(i) + "());\n" +
                        "            modelAndView.addObject(\"message\", \"New " + dtp.table(i).toLowerCase() + " created successfully\");\n" +
                        "            return modelAndView;\n" +
                        "        }\n" +
                        "    }\n\n";


                question += "@GetMapping(\"/edit-" + dtp.table(i).toLowerCase() + "/{id}\")\n" +
                        "    public ModelAndView showEditForm(@PathVariable Long id) {\n" +
                        "        " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + " = " + dtp.table(i).toLowerCase() + "Service.findById(id);\n" +
                        "        if (" + dtp.table(i).toLowerCase() + " != null) {\n" +
                        "            ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/edit\");\n" +
                        "            modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n" +
                        "            return modelAndView;\n" +
                        "\n" +
                        "        } else {\n" +
                        "            ModelAndView modelAndView = new ModelAndView(\"/error.404\");\n" +
                        "            return modelAndView;\n" +
                        "        }\n" +
                        "    }\n";
                question += "@PostMapping(\"/edit-" + dtp.table(i).toLowerCase() + "\")\n" +
                        "    public ModelAndView update" + dtp.table(i) + "(@ModelAttribute(\"" + dtp.table(i).toLowerCase() + "\") " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + ") {\n" +
                        "        " + dtp.table(i).toLowerCase() + "Service.edit(";
                for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                    if (dtp.pkSize(i) > 0) {
                        for (int x = 0; x < dtp.pkSize(i); x++) {
                            if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                                question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "(),";
                                break;
                            } else {
                                if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
                                    question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaPk(i, x) + "().get" + dtp.inHoaCot(i, 0) + "(),";
                                }
                            }

                        }
                    } else {
                        question += dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, j) + "(),";

                    }
                }
                question += " LocalDate.now(), \"Phuc\"," + dtp.table(i).toLowerCase() + ".get" + dtp.inHoaCot(i, 0) + "());\n\n" +
                        "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/edit\");\n" +
                        "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n" +
                        "        modelAndView.addObject(\"message\", \"" + dtp.table(i) + " updated successfully\");\n" +
                        "        return modelAndView;\n" +
                        "    }\n\n";
            }


            question += "@GetMapping(\"/delete-" + dtp.table(i).toLowerCase() + "/{id}\")\n" +
                    "    public String showDeleteForm(@PathVariable Long id) {\n" +
                    "        " + dtp.table(i).toLowerCase() + "Service.softDelete(LocalDate.now(), \"Phuc3\", id);\n" +
                    "        return \"redirect:/" + dtp.table(i).toLowerCase() + "s\";\n" +
                    "    }\n";
            question += "@GetMapping(\"/view-" + dtp.table(i).toLowerCase() + "/{id}\")\n" +
                    "    public ModelAndView view" + dtp.table(i) + "(@PathVariable(\"id\") Long id) {\n" +
                    "        " + dtp.table(i) + " " + dtp.table(i).toLowerCase() + " = " + dtp.table(i).toLowerCase() + "Service.findById(id);\n" +
                    "        if (" + dtp.table(i).toLowerCase() + " == null) {\n" +
                    "            return new ModelAndView(\"/error.404\");\n" +
                    "        }\n" +
                    "\n" +
                    "        ModelAndView modelAndView = new ModelAndView(\"/" + dtp.table(i).toLowerCase() + "/view\");\n" +
                    "        modelAndView.addObject(\"" + dtp.table(i).toLowerCase() + "\", " + dtp.table(i).toLowerCase() + ");\n" +
                    "        return modelAndView;}\n";
            question += "}";

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
