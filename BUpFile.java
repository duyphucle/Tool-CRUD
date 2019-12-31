import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BUpFile {
    public void createUpFile() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < AMain.upfiles.size(); i++) {

            Path path = Paths.get(AMain.link + "model/" + AMain.upfiles.get(i) + "UpFile.java");

            String question = "package com.codegym.cms.model;\n" +
                    "\n" +
                    "import org.springframework.web.multipart.commons.CommonsMultipartFile;\n" +
                    "\n" +
                    "public class " + AMain.upfiles.get(i) + "UpFile extends " + AMain.upfiles.get(i) + " {\n" +
                    "    private CommonsMultipartFile[] fileDatas;\n" +
                    "\n" +
                    "    public " + AMain.upfiles.get(i) + "UpFile() {\n" +
                    "    }\n" +
                    "\n" +
                    "    public CommonsMultipartFile[] getFileDatas() {\n" +
                    "        return fileDatas;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setFileDatas(CommonsMultipartFile[] fileDatas) {\n" +
                    "        this.fileDatas = fileDatas;\n" +
                    "    }\n" +
                    "}\n";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("UpFile created by Dan");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

