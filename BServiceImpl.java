import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BServiceImpl {
    public void createServiceImpl() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "service/impl/" + dtp.table(i)+ "ServiceImpl.java");

            String question = "package com.codegym.cms.service.impl;\n";
            if(  dtp.pkSize(0)>0){
                for (int j= 0;j < dtp.pkSize(i);j++){
                    question += "import com.codegym.cms.model."+dtp.pk(i,j)+";\n";
                }
            }
            question +="import com.codegym.cms.model."+dtp.table(i)+";\n" +
                    "import com.codegym.cms.repository."+dtp.table(i)+"Repository;\n" +
                    "import com.codegym.cms.service."+dtp.table(i)+"Service;\n" +
                    "import org.springframework.data.repository.query.Param;\n" +
                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import java.time.LocalDate;\n" +
                    "public class "+dtp.table(i)+"ServiceImpl implements "+dtp.table(i)+"Service {\n" +
                    "   @Autowired\n" +
                    "   private "+dtp.table(i)+"Repository "+dtp.table(i).toLowerCase()+"Repository;\n" +
                    "\n" +
                    "   @Override\n" +
                    "   public " +
                    dtp.table(i)  + " findById("+ dtp.type(i,0)+ " " + dtp.column(i,0) + "){\n" +
                    "       return "+dtp.table(i).toLowerCase()+"Repository.findById("+dtp.column(i,0)+").get();" +
                    "\n" +
                    "}" +
                    "\n";


//            if(  dtp.pkSize(0)>0) {
//                for (int j = 0; j < dtp.pkSize(i); j++) {
//                    question += "   @Override\n" +
//                            "   public" +
//                            " Iterable<" + dtp.table(i) + "> findAllBy" + dtp.pk(i, j) + "AndIsDeletedEquals(" + dtp.pk(i, j) + "  " + dtp.pk(i, j).toLowerCase() + ", int isDeleted)\n" +
//                            "{\n" +
//                            "       return " + dtp.table(i).toLowerCase() + "Repository.findAllBy" + dtp.pk(i, j) + "AndIsDeletedEquals(" + dtp.pk(i, j).toLowerCase() + ", isDeleted);\n" +
//                            "    }\n";
//                }
//            }
                question+="   @Override\n" +
                    "   public Iterable<"+ dtp.table(i) +"> findAllByIsDeletedEquals(int isDeleted) {\n" +
                    "       return "+ dtp.table(i).toLowerCase() +"Repository.findAllByIsDeletedEquals(isDeleted);\n" +
                    "   }\n";

            question+= "   @Override\n" +
                    "    public void softDelete(@Param(\"deleted_at\") LocalDate deleted_at, @Param(\"deleted_by\") String deleted_by, @Param(\""+dtp.column(i,0)+"\") "+dtp.type(i,0)+" "+dtp.column(i,0)+"){\n" +
                    "       "+ dtp.table(i).toLowerCase() +"Repository.softDelete(deleted_at, deleted_by, id);\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void create(@Param(\""+dtp.column(i,1)+"\") "+dtp.type(i,1)+" "+dtp.column(i,1)+"";
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", @Param(\""+dtp.column(i,j)+"\") "+dtp.type(i,j)+" "+dtp.column(i,j);
            }
            question+= ", @Param(\"created_at\") LocalDate created_at, @Param(\"created_by\") String created_by){\n" +
                    "       "+ dtp.table(i).toLowerCase() +"Repository.create(" + dtp.column(i,1);
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", "  + dtp.column(i,j);
            }
            question+= ", created_at, created_by);}\n";
            question +="   @Override\n" +
                    "    public void edit(@Param(\""+dtp.column(i,1)+"\") "+dtp.type(i,1)+" "+dtp.column(i,1)+"";
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", @Param(\""+dtp.column(i,j)+"\") "+dtp.type(i,j)+" "+dtp.column(i,j);
            }
            question+=", @Param(\"updated_at\") LocalDate updated_at, @Param(\"updated_by\") String updated_by, @Param(\"id\") "+dtp.type(i,0)+ "   "+dtp.column(i,0)+"){\n       "
                    + dtp.table(i).toLowerCase() +"Repository.edit(" + dtp.column(i,1);
            for(int j =2; j< dtp.typeSize(i)-7;j++){
                question += ", "  + dtp.column(i,j);
            }
            question+= ", updated_at, updated_by, id);\n }\n" +
                    "}";









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
