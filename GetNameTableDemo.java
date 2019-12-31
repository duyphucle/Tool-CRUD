import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GetNameTableDemo {
    public static void main(String[] args) throws IOException {
        //Tao cung cac thu muc, controller,model,service,webpro(Nam trong WEB-INF -COPY tha vao do)


        Path path = Paths.get("/home/ubuntu/Desktop/duyphuc1/controller");
        Files.createDirectories(path);
        Path path1 = Paths.get("/home/ubuntu/Desktop/duyphuc1/model");
        Files.createDirectories(path1);
        Path path2 = Paths.get("/home/ubuntu/Desktop/duyphuc1/service");
        Files.createDirectories(path2);
        Path path3 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro");
        Files.createDirectories(path3);
        getname();

//        Xay dung toan bo thu muc va file bang cach goi ham getname();


        //      GetId dang phat trien chua truy xuat dc
        getId();

        getColum();
    }

    public static void getId() {
        Path wiki_path = Paths.get("/home/ubuntu/Desktop/aaa.txt");
        Charset charset = StandardCharsets.ISO_8859_1;
        try {
            List<String> lines = Files.readAllLines(wiki_path, charset);
            ArrayList<ArrayList<String>> myList = new ArrayList<>(20);
            int t = 0;
            for (int i = 0; i < lines.size(); i++) {

                boolean check1 = lines.get(i).contains("DROP TABLE IF EXISTS `mvc_demo`.");

                if (check1) {

                    //lam the nao de doi ten duoc arr1
                    myList.add(new ArrayList<>());

                    for (int j = i; j < lines.size(); j++) {
                        boolean check = lines.get(j).contains("NULL");
                        boolean check2 = lines.get(j).contains("DEFAULT CHARACTER");

                        if (check) {

                            //   myList.get(0).add("int");
                            String s1 = lines.get(j).substring(lines.get(j).lastIndexOf("`") + 2, lines.get(j).indexOf("NULL") - 1);
                            switch (s1) {
                                case "TINYINT(4)":
                                case "INT(11)":
                                case "INT(11) NOT":
                                    myList.get(t).add("int");
                                    break;
                                case "VARCHAR(255)":
                                    myList.get(t).add("String");
                                    break;
                                case "DATETIME":
                                    myList.get(t).add("Local Time");
//
                                    break;

                            }
                        }
                        if (check2)
                            break;

                    }
                    t++;
                    //Chua truy xuat den tung thang duoc

                }

            }
            System.out.println(myList);
            System.out.println(myList.get(1).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getColum() {
        Path wiki_path = Paths.get("/home/ubuntu/Desktop/aaa.txt");
        Charset charset = StandardCharsets.ISO_8859_1;
        try {
            List<String> lines = Files.readAllLines(wiki_path, charset);
            ArrayList<ArrayList<String>> myList = new ArrayList<>(20);
            int t = 0;
            for (int i = 0; i < lines.size(); i++) {
                boolean check1 = lines.get(i).contains("DROP TABLE IF EXISTS `mvc_demo`.");
                if (check1) {
                    //lam the nao de doi ten duoc arr1
                    myList.add(new ArrayList<>());
                    for (int j = i + 1; j < lines.size(); j++) {
                        boolean check = lines.get(j).contains("NULL");
                        boolean check2 = lines.get(j).contains("DEFAULT CHARACTER");

                        if (check) {

                            myList.get(t).add(lines.get(j).substring(lines.get(j).indexOf("`") + 1, lines.get(j).lastIndexOf("`")));
                        }
                        if (check2)
                            break;

                    }
                    t++;
                    //Chua truy xuat den tung thang duoc

                }

            }
            System.out.println(myList);
            System.out.println(myList.get(1).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Ham tao thu muc.
    public static void CreateDirec(String file) {
        //Tam thoi chua nghi ra cach lam Interface chung
        // cho tat ca service nen phai tao thu muc,file service rieng
        Path path = Paths.get("/home/ubuntu/Desktop/duyphuc1/service/" + file);
        // Trong thu muc webpro tao cac thu muc dai dien cho cac bang get duoc
        Path path1 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro/" + file);

        try {
            Files.createDirectories(path);
            Files.createDirectories(path1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Ham tao file .java,.jsp,....
    public static void CreateFile(String txt) {
        //
        Path path = Paths.get("/home/ubuntu/Desktop/duyphuc1/model/" + txt + ".java");
        Path path1 = Paths.get("/home/ubuntu/Desktop/duyphuc1/controller/" + txt + "Servlet.java");

        try {
            Files.createFile(path);
            Files.createFile(path1);

            // System.out.println("Created a file at : "+createdFilePath);
            //   System.out.println("Created a file at : "+createdFilePath1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getname() {

// Doc du lieu tu file aaa.txt
        Path wiki_path = Paths.get("/home/ubuntu/Desktop/aaa.txt");
        Charset charset = StandardCharsets.ISO_8859_1;
        try {
            //Doc Lan luot tat ca cac dong
            List<String> lines = Files.readAllLines(wiki_path, charset);

            //Duyet qua tung dung
            for (String line : lines) {
                //ham contains Kiem tra trong hang do co chua "DROP TABLE IF EXISTS `mvc_demo" hay khong
                // tra ve gia tri true/false
                boolean check = line.contains("DROP TABLE IF EXISTS `mvc_demo`.");
                if (check) {
                    //Neu tai hang do co chua "DROP TABLE IF EXISTS `mvc_demo"
                    // thi tao file do trong thu muc model duoc xay dung tu ham createfile
                    CreateFile(line.substring(line.indexOf(".") + 2, line.lastIndexOf("`"))); //bat dau .+2 , ket thuc "`"
                    // Tao thu muc( muc dich la tao cac thu muc con trong service)
                    CreateDirec(line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase());
                    // Nen nang cap toLowerCase theo chuan thong nhat the gioi :v(khong nen su dung dung toLoweCase)

                    //Khoi tao 1 bien check1 xem co bang - MySQL ,ten model- Java ten bang do hay khong?
                    boolean check1 = line.contains(line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")));
                    //Neu bang thi lan luot tao file trong thu muc do :v. Dan va Phuc hai ban co hieu doan nay khong? :v
                    if (check) {
                        Path path = Paths.get("/home/ubuntu/Desktop/duyphuc1/service/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")) +
                                "Service.java");

                        Path path1 = Paths.get("/home/ubuntu/Desktop/duyphuc1/service/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")) +
                                "JDBCServiceImpl.java");

                        Path path2 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/create.jsp");

                        Path path3 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/list.jsp");

                        Path path4 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/edit.jsp");

                        Path path5 = Paths.get("/home/ubuntu/Desktop/duyphuc1/webpro/"
                                + line.substring(line.indexOf(".") + 2, line.lastIndexOf("`")).toLowerCase()
                                + "/view.jsp");
                        //Vay la chung ta da xong phan tao cay thu muc suong suong roi cac ban nhe.
                        try {

                            Files.createFile(path);
                            Files.createFile(path1);
                            Files.createFile(path2);
                            Files.createFile(path3);
                            Files.createFile(path4);
                            Files.createFile(path5);

                            // System.out.println("Created a file at : "+createdFilePath);
                            //   System.out.println("Created a file at : "+createdFilePath1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}