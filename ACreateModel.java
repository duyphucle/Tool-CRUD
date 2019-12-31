import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ACreateModel {
  public void createTable() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
      dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link+"src/model/"+dtp.table(i).substring(0,1).toUpperCase() + dtp.table(i).substring(1) +".java");
            String question = "package model;\n" ;
            question += "import java.time.LocalDate;\n" +
                    "public class " + dtp.table(i).substring(0,1).toUpperCase() + dtp.table(i).substring(1) + "{\n";
            for (int j=0;j<dtp.columnSize(i);j++){
                question +="       private  "+dtp.type(i,j)+ " "+dtp.column(i,j).toLowerCase()+";\n";
            }
            question+= "       private int isDeleted;\n"
            + "       private LocalDate deleted_at = LocalDate.now();\n"
            + "       private String deleted_by;\n"
            + "       private LocalDate updated_at = LocalDate.now();\n"
            + "       private String updated_by;\n"
            + "       private LocalDate created_at  = LocalDate.now();\n"
            + "       private String created_by;\n"
            +"\n"
            +"      public "+ dtp.table(i).substring(0,1).toUpperCase() + dtp.table(i).substring(1)+"(";
            for (int j=0;j<dtp.columnSize(i)-1;j++){
                question +=dtp.type(i,j)+ " "+dtp.column(i,j).toLowerCase() +" , ";
            }

            question += dtp.type(i,dtp.typeSize(i)-1) + " " + dtp.column(i,dtp.columnSize(i)-1).toLowerCase() + " ){\n";
            for (int j=0;j<dtp.columnSize(i);j++){
                question +=  "          this."+dtp.column(i,j).toLowerCase()  + " = " + dtp.column(i,j).toLowerCase() + ";\n";
            }
            question+= "        }\n";

            question += "       public "+ dtp.table(i).substring(0,1).toUpperCase() + dtp.table(i).substring(1)+"(";
            for (int j=1;j<dtp.columnSize(i)-1;j++){
                question +=dtp.type(i,j)+ " "+dtp.column(i,j).toLowerCase() +" , ";
            }
            question += dtp.type(i,dtp.typeSize(i)-1) + " " + dtp.column(i,dtp.columnSize(i)-1).toLowerCase() + " ){\n";
            for (int j=1;j<dtp.columnSize(i);j++){
                question +=  "          this."+dtp.column(i,j).toLowerCase()  + " = " + dtp.column(i,j).toLowerCase() + ";\n";
            }
            question+= "        }\n";
            for (int j=0;j<dtp.columnSize(i);j++){
                question+="     public void set"+dtp.column(i,j).substring(0,1).toUpperCase()+
                        dtp.column(i,j).substring(1) +"("+dtp.type(i,j)+" "+dtp.column(i,j).toLowerCase()+") {\n           this."
                        +dtp.column(i,j).toLowerCase()+ " = "+ dtp.column(i,j).toLowerCase()+";\n     }\n"
                + "     public "+ dtp.type(i,j)  +" get"+ dtp.column(i,j).substring(0,1).toUpperCase()+
                        dtp.column(i,j).substring(1) + "(){" +
                        "       \n          return "+  dtp.column(i,j).toLowerCase()+";\n     }\n";

            }
            question+="     public int getIsDeleted() {\n" +
                    "        return isDeleted;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setIsDeleted(int isDeleted) {\n" +
                    "        this.isDeleted = isDeleted;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getDeleted_by() {\n" +
                    "        return deleted_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setDeleted_by(String deleted_by) {\n" +
                    "        this.deleted_by = deleted_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public LocalDate getDeleted_at() {\n" +
                    "        return deleted_at;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setDeleted_at(LocalDate deleted_at) {\n" +
                    "        this.deleted_at = deleted_at;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getCreated_by() {\n" +
                    "        return created_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setCreated_by(String created_by) {\n" +
                    "        this.created_by = created_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public LocalDate getCreated_at() {\n" +
                    "        return created_at;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setCreated_at(LocalDate created_at) {\n" +
                    "        this.created_at = created_at;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getUpdated_by() {\n" +
                    "        return updated_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setUpdated_by(String updated_by) {\n" +
                    "        this.updated_by = updated_by;\n" +
                    "    }\n" +
                    "\n" +
                    "    public LocalDate getUpdated_at() {\n" +
                    "        return updated_at;\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setUpdated_at(LocalDate updated_at) {\n" +
                    "        this.updated_at = updated_at;\n" +
                    "    }\n" +
                    "}";

            System.out.println("Model created by Nguyen Ngoc Linh Dan");
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
//              List<String> lines = Files.readAllLines(path, charset);
////                for (String line : lines) {
////                    if (dtp.table(i)=="City")
////                        System.out.println(line);
////                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}