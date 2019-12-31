import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BService {
    public void createService() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "service/" + dtp.table(i)+ "Service.java");

            String question = "package com.codegym.cms.service;\n";
            if(  dtp.pkSize(0)>0){
                for (int j= 0;j < dtp.pkSize(i);j++){
                    question += "import com.codegym.cms.model."+dtp.pk(i,j)+";\n";
                }
            }
            question +="import com.codegym.cms.model."+dtp.table(i)+";\n" +
                    "import org.springframework.data.repository.query.Param;\n" +
                    "import java.time.LocalDate;\n" +
                    "public interface "+dtp.table(i)+"Service {\n   " +
                    dtp.table(i)  + " findById("+ dtp.type(i,0)+ " " + dtp.column(i,0) + ");\n" +
                    "   Iterable<" +dtp.table(i)+"> findAllByIsDeletedEquals(int isDeleted);\n";
//            if (dtp.pkSize(0) > 0) {
//                for (int j = 0; j < dtp.pkSize(i); j++) {
//                    question += "   Iterable<" + dtp.table(i) + "> findAllBy" + dtp.pk(i, j) + "AndIsDeletedEquals(" + dtp.pk(i, j) + "  " + dtp.pk(i, j).toLowerCase() + ", int isDeleted);\n" +
//                            "\n";
//                }
//
//            }
            question+= "   void softDelete(@Param(\"deleted_at\") LocalDate deleted_at, @Param(\"deleted_by\") String deleted_by, @Param(\""+dtp.column(i,0)+"\") "+dtp.type(i,0)+" "+dtp.column(i,0)+");\n" +
                    "\n" +
                    "\n" +
                    "   void create(@Param(\""+dtp.column(i,1)+"\") "+dtp.type(i,1)+" "+dtp.column(i,1)+"";
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", @Param(\""+dtp.column(i,j)+"\") "+dtp.type(i,j)+" "+dtp.column(i,j);
            }
            question+= ", @Param(\"created_at\") LocalDate created_at, @Param(\"created_by\") String created_by);\n" +
                    "\n" +
                    "\n" +
                    "   void edit(@Param(\""+dtp.column(i,1)+"\") "+dtp.type(i,1)+" "+dtp.column(i,1)+"";
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", @Param(\""+dtp.column(i,j)+"\") "+dtp.type(i,j)+" "+dtp.column(i,j);
            }
            question+=", @Param(\"updated_at\") LocalDate updated_at, @Param(\"updated_by\") String updated_by, @Param(\"id\") "+dtp.type(i,0)+" "  +dtp.column(i,0)+" );\n" +
                    "\n" +
                    "}\n";










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
