import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BRepository {
    public void createRepository() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "repository/" + dtp.table(i) + "Repository.java");

            String question = "package com.codegym.cms.repository; \n";
            if (dtp.pkSize(0) > 0) {
                for (int j = 0; j < dtp.pkSize(i); j++) {
                    question += "import com.codegym.cms.model." + dtp.pk(i, j) + ";\n";
                }
            }
            question += "import com.codegym.cms.model." + dtp.table(i) + ";\n" +
                    "import org.springframework.data.jpa.repository.Modifying;\n" +
                    "import org.springframework.data.jpa.repository.Query;\n" +
                    "import org.springframework.data.repository.query.Param;\n" +
                    "import org.springframework.data.repository.PagingAndSortingRepository;\n" +
                    "import javax.transaction.Transactional;\n" +
                    "import java.time.LocalDate;\n" +
                    "public interface " + dtp.table(i) + "Repository extends PagingAndSortingRepository<" + dtp.table(i) + ", " + dtp.type(i, 0) + "> {\n" +
                    "\n" +
                    "    Iterable<" + dtp.table(i) + "> findAllByIsDeletedEquals(int isDeleted);\n" +
                    "\n";
//            if (dtp.pkSize(0) > 0) {
//                for (int j = 0; j < dtp.pkSize(i); j++) {
//                    question += "    Iterable<" + dtp.table(i) + "> findAllBy" + dtp.pk(i, j) + "AndIsDeletedEquals(" + dtp.pk(i, j) + "  " + dtp.pk(i, j).toLowerCase() + ", int isDeleted);\n" +
//                            "\n";
//                }
//
//            }
            question += "    @Transactional\n" +
                    "    @Modifying\n" +
                    "    @Query(\"UPDATE " + dtp.table(i) + " b SET b.isDeleted = 1, b.deleted_at = :deleted_at, b.deleted_by = :deleted_by WHERE b." + dtp.column(i, 0) + "=:" + dtp.column(i, 0) + "\")\n" +
                    "    void softDelete(@Param(\"deleted_at\") LocalDate deleted_at, @Param(\"deleted_by\") String deleted_by, @Param(\"" + dtp.column(i, 0) + "\") " + dtp.type(i, 0) + " " + dtp.column(i, 0) + ");\n" +
                    "\n" +
                    "\n" +
                    "    @Transactional\n" +
                    "    @Modifying\n" +
                    "    @Query(value = \"INSERT INTO " + dtp.table(i) + " ( " + dtp.column(i, 0);
            for (int j = 1; j < dtp.typeSize(i) - 7; j++) {
                question += ", " + dtp.column(i, j);
            }
            question += ", created_at, created_by )\" + \n       " +
                    "        \"VALUES ((SELECT IFNULL((SELECT MAX(" + dtp.column(i, 0) + ") FROM " + dtp.table(i) + " C) + 1, 1 ))";
            for (int j = 1; j < dtp.typeSize(i) - 7; j++) {
                question += ", :" + dtp.column(i, j);
            }
            question += ", :created_at, :created_by);\", nativeQuery = true)\n" +
                    "    void create(@Param(\"" + dtp.column(i, 1) + "\") " + dtp.type(i, 1) + " " + dtp.column(i, 1) + "";
            for (int j = 2; j < dtp.typeSize(i) - 7; j++) {
                question += ", @Param(\"" + dtp.column(i, j) + "\") " + dtp.type(i, j) + " " + dtp.column(i, j);
            }
            question += ", @Param(\"created_at\") LocalDate created_at, @Param(\"created_by\") String created_by);\n" +
                    "\n" +
                    "\n" +
                    "    @Transactional\n" +
                    "    @Modifying\n" +
                    "    @Query(value = \"UPDATE " + dtp.table(i) + " SET " + dtp.column(i, 1) + " = :" + dtp.column(i, 1);
            for (int j = 2; j < dtp.typeSize(i) - 7; j++) {
                question += ", " + dtp.column(i, j) + " = :" + dtp.column(i, j);
            }
            question += ", updated_at = :updated_at, updated_by = :updated_by WHERE " + dtp.column(i, 0) + " = :" + dtp.column(i, 0) + "\", nativeQuery = true)\n " +
                    "    void edit(@Param(\"" + dtp.column(i, 1) + "\") " + dtp.type(i, 1) + " " + dtp.column(i, 1) + "";
            for (int j = 2; j < dtp.typeSize(i) - 7; j++) {
                question += ", @Param(\"" + dtp.column(i, j) + "\") " + dtp.type(i, j) + " " + dtp.column(i, j);
            }
            question += ", @Param(\"updated_at\") LocalDate updated_at, @Param(\"updated_by\") String updated_by, @Param(\"id\") " + dtp.type(i, 0) + " " + dtp.column(i, 0) + ");\n" +
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
