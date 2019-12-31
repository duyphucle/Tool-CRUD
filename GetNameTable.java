//import com.sun.org.apache.bcel.internal.generic.SWITCH;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.SQLOutput;
//import java.util.List;
//import java.util.ArrayList;
//public class GetNameTable {
//    public static void main(String[] args) {
// //        getname();
//        getId();
//    }
//    public static void getId() {
//        Path wiki_path = Paths.get("/home/ubuntu/Desktop/aaa.txt");
//        Charset charset = Charset.forName("ISO-8859-1");
//        try {
//            List<String> lines = Files.readAllLines(wiki_path, charset);
//            for (String line : lines) {
//                boolean check = line.contains("NULL");
//                if (check) {
//                    System.out.print(line.substring(line.indexOf("`") + 1, line.lastIndexOf("`")) + "|");
//                }
//            }
//            System.out.println("\n");
//            for (String line : lines) {
//                boolean check = line.contains("NULL");
//                if (check) {
//                    String s1 = line.substring(line.lastIndexOf("`") + 2, line.indexOf("NULL") - 1);
//                    switch (s1){
//                        case "TINYINT(4)":
//                        case "INT(11)":
//                        case "INT(11) NOT":
//                            System.out.print("int|");
//                            break;
//                        case "VARCHAR(255)":
//                            System.out.print("String|");
//                            break;
//                        case "DATETIME":
//                            System.out.print("Local Time|");
//                            break;
//
//
//                    }
//                }
//            }
//        }
//
//        catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//
//
//
//    public static void getname() {
//
//
//        Path wiki_path = Paths.get("/home/ubuntu/Desktop/aaa.txt");
//        Charset charset = Charset.forName("ISO-8859-1");
//        try {
//            List<String> lines =  Files.readAllLines(wiki_path, charset);
//            //  System.out.println(lines);
//
//            for (String line : lines) {
//
//                boolean check = line.contains("DROP TABLE IF EXISTS `mvc_demo`.");
//                if (check)
//                    System.out.println(line.substring(line.indexOf(".")+2,line.lastIndexOf("`")));
//
//
//            }
//        }
//
//        catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//}