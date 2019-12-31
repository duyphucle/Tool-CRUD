import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BViewdelete {

    public void showdelete() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link + "web/WEB-INF/" + dtp.table(i).toLowerCase() + "/delete.html");
            String question = "\n<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Delete "+dtp.table(i)+"</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>Delete "+dtp.table(i)+"</h1>\n" +
                    "    <h2>Are you sure?</h2>\n" +
                    "    <p>\n" +
                    "        <a href=\"/"+dtp.table(i).toLowerCase()+"\">"+dtp.table(i)+" list</a>\n" +
                    "    </p>\n" +
                    "    <form th:action=\"@{/delete-"+dtp.table(i).toLowerCase()+"}\" th:object=\"${"+dtp.table(i).toLowerCase()+"}\" method=\"post\">\n" +
                    "        <input th:type=\"hidden\" name=\"id\" th:field=\"*{id}\">\n" +
                    "        <table>\n";
            for(int j=1;j<dtp.columnSize(i)-7;j++){
                question+="            <tr>\n" +
                        "                <td>"+dtp.column(i,j)+":</td>\n" +
                        "                <td th:text=\"${"+dtp.table(i).toLowerCase()+"."+dtp.column(i,j)+"}\"></td>\n" +
                        "            </tr>\n";
            }

            question+="            <tr>\n" +
                    "                <td></td>\n" +
                    "                <td><input type=\"submit\" value=\"Delete "+dtp.table(i).toLowerCase()+"\"></td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "    </form>\n" +
                    "</body>\n" +
                    "</html>";


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



