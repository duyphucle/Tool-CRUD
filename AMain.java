import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;


public class AMain {
    static String hostName = "localhost";
    static String link = "/home/ubuntu/Desktop/JavaBackEnd/CaseStudy/src/main/java/com/codegym/cms/";
    static String link1 = "/home/ubuntu/Desktop/JavaBackEnd/CaseStudy/src/main/webapp/WEB-INF/views/";

    //    static String link = "/home/muoi/Desktop/Amazon2/src/main/java/com/codegym/cms/";
//    static String link1 = "/home/muoi/Desktop/Amazon2/src/main/webapp/WEB-INF/views/";
    static String dbName = "year";
    ;
    static String userName = "root";
    static String password = "Phuc@123z2";

    static String dbName2 = "amazon1";

        static List<String> upfiles = Arrays.asList();
      static String columnImage = "";
//
//static List<String> upfiles = Arrays.asList("KhachHang");
//    static String columnImage = "Anh";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        BServiceImpl bServiceImpl = new BServiceImpl();
        BRepository bRepository = new BRepository();
        BService bService = new BService();
        BConfig bConfig = new BConfig();
        BApplicationInitializer bApplicationInitializer = new BApplicationInitializer();
        BFormatter bFormatter = new BFormatter();
        BViewcreate bViewcreate = new BViewcreate();
        BViewedit bViewedit = new BViewedit();
//        BViewdelete bViewdelete = new BViewdelete();
        BLayout blayout = new BLayout();
        BController bcontroller = new BController();
        BModel bModel = new BModel();
        BList bList = new BList();
        BUpFile bUpFile = new BUpFile();
        BView bView = new BView();

        createDirec("formatter");
        createDirec("controller");
        createDirec("model");
        createDirec("repository");
        createDirec("service");
        createDirec("service/impl");
        for (int i = 0; i < dtp.tableSize(); i++) {
            createDirec1(dtp.table(i).toLowerCase());
        }


        System.out.println(dtp.getTables());
        System.out.print("Ten bang:(dtp.table(0))         : ");
        System.out.println(dtp.table(0));
        System.out.println("So bang khoa ngoai:(dtp.pkSize(0))       :" + dtp.pkSize(0));
        System.out.print("Danh sach cac bang khoa ngoai:(dtp.pk(0))   : ");
        System.out.println(dtp.pk(0));
        System.out.print("Danh sach cac ten khoa ngoai : dtp.fk(0) :");
        System.out.println(dtp.fk(0));
        System.out.print("Ten cua khoa ngoai do trong bang :(dtp.fkName(0,0))           :");
     //   System.out.println(dtp.fkName(0, 0));
        System.out.print("Ten cua khoai ngoai do trong bang cua no: dtp.pkName(0,0)        :");
    //    System.out.println(dtp.pkName(0, 0));
        System.out.println("So truong: dtp.typeSize(0):         " + dtp.typeSize(0));
        System.out.println("Danh sach cac truong:" + dtp.column(0));
        System.out.println("Danh sach cac bien: dtp.type(0)    :" + dtp.type(0));
//
        bFormatter.createFormatter();
        bRepository.createRepository();
        bService.createService();
        bServiceImpl.createServiceImpl();
        bcontroller.createController();
        bConfig.createConfig();
        bApplicationInitializer.createApplicationInitializer();
        bModel.createModel();
        bUpFile.createUpFile();
        blayout.createLayout();

        bViewcreate.showcreate();
        bViewedit.showedit();
        bView.showview();
        bList.listInterFace();
//        deleteCRUD();
//        inserCRUD();


//        for (int i = 0; i < dtp.getFull().size(); i++) {
//            createFile("src/service/" + dtp.table(i).toLowerCase() + "/" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "Service.java");
//            createFile("src/service/" + dtp.table(i).toLowerCase() + "/" + dtp.table(i).substring(0, 1).toUpperCase() + dtp.table(i).substring(1) + "JDBCServiceImpl.java");
//
//        }
//
//
//
//
////        deleteCRUD();
//        aCreateModel.createTable();
//        aCreateJDBC.createInterFace();
//        aCreateJDBC.createService();
//        aViewCreate.showcreate();
//        aViewCreate.showDelete();
//        aViewCreate.showEdit();
//        aViewCreate.showList();
//        aViewCreate.showViews();
//        aServlet.createSerlet();
//        aServlet.homeServlet();
//        inserCRUD();
//        aUtils.createUtils();
//        aViewCreate.sidebarmini();
//        aViewCreate.home();
//

    }

    public static void inserCRUD() throws ClassNotFoundException, SQLException {
        DTP dtp = new DTP();
        dtp.setFullDTP();


        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/case_study_version1",
//                "root", "Phuc@123z2");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + AMain.dbName + "",
                "" + AMain.userName + "", "" + AMain.password + "");


        Statement st = con.createStatement();
        for (int i = 0; i < dtp.tableSize(); i++) {
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN isDeleted TINYINT NULL DEFAULT 0");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN deleted_at DATE ");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN deleted_by VARCHAR(45)");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN updated_at DATE ");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN updated_by VARCHAR(45) ");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN created_at DATE ");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " ADD COLUMN created_by VARCHAR(45) ");
            System.out.println("Insert BackLog By Tran Huu Phuc");
        }

    }

    public static void deleteCRUD() throws ClassNotFoundException, SQLException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/case_study_version1",
//                "root", "Phuc@123z2");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + AMain.dbName + "",
                "" + AMain.userName + "", "" + AMain.password + "");

        Statement st = con.createStatement();
        for (int i = 0; i < dtp.tableSize(); i++) {


            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN isDeleted");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN deleted_at");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN deleted_by");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN updated_at");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN updated_by");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN created_at");
            st.executeUpdate("ALTER TABLE " + dtp.table(i) + " DROP COLUMN created_by");
            System.out.println("Deleted BackLog By Nguyen Ngoc Linh Dan");
        }

    }

    public static void createDirec(String direction) {

        Path path = Paths.get(link + direction);

        try {
            Path createDirectories = Files.createDirectories(path);

            System.out.println("Created a Directories at : " + createDirectories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirec1(String direction) {

        Path path = Paths.get(link1 + direction);

        try {
            Path createDirectories = Files.createDirectories(path);

            System.out.println("Created a Directories at : " + createDirectories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String file) {

        Path path = Paths.get(link + file);

        try {
            Path createdFilePath = Files.createFile(path);
            System.out.println("Created a file at : " + createdFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


