import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BApplicationInitializer {
    public void createApplicationInitializer() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();


        Path path = Paths.get(AMain.link + "ApplicationInitializer.java");

        String question = "package com.codegym.cms;\n" +
                "\n" +
                "import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;\n" +
                "\n" +
                "public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {\n" +
                "    @Override\n" +
                "    protected Class<?>[] getRootConfigClasses() {\n" +
                "        return new Class[]{ApplicationConfig.class};\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected Class<?>[] getServletConfigClasses() {\n" +
                "        return new Class[0];\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    protected String[] getServletMappings() {\n" +
                "        return new String[]{\"/\"};\n" +
                "    }\n" +
                "}\n";

        Charset charset = Charset.forName("ISO-8859-1");
        try {
            Files.write(path, question.getBytes());
            System.out.println("ApplicationInitializer created by Dan");
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
