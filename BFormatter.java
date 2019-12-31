import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BFormatter {
    public void createFormatter() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "formatter/" + dtp.table(i)+ "Formatter.java");

            String question = "package com.codegym.cms.formatter;\n" +
                    "\n" +
                    "import com.codegym.cms.model."+dtp.table(i)+";\n" +
                    "import com.codegym.cms.service."+dtp.table(i)+"Service;\n" +
                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import org.springframework.format.Formatter;\n" +
                    "import org.springframework.stereotype.Component;\n" +
                    "\n" +
                    "import java.text.ParseException;\n" +
                    "import java.util.Locale;\n" +
                    "\n" +
                    "@Component\n" +
                    "public class "+dtp.table(i)+"Formatter implements Formatter<"+dtp.table(i)+"> {\n" +
                    "\n" +
                    "    private "+dtp.table(i)+"Service "+dtp.table(i).toLowerCase()+"Service;\n" +
                    "\n" +
                    "    @Autowired\n" +
                    "    public "+dtp.table(i)+"Formatter("+dtp.table(i)+"Service "+dtp.table(i).toLowerCase()+"Service) {\n" +
                    "        this."+dtp.table(i).toLowerCase()+"Service = "+dtp.table(i).toLowerCase()+"Service;\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public "+dtp.table(i)+" parse(String text, Locale locale) throws ParseException {\n" +
                    "        return "+dtp.table(i).toLowerCase()+"Service.findById(Long.parseLong(text));\n" +
                    "    }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public String print("+dtp.table(i)+" object, Locale locale) {\n" +
                    "        return null;  }\n" +
                    "}\n \n";









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
