import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class index {

    public static void main(String args[]) throws IOException {
    for(int i=0;i<5;i++) {
        createDirectories(i);
        CreateFile(i);
    }
    }

    public static void createDirectories(int i) throws IOException {
        Path path = Paths.get("/home/ubuntu/Desktop/file"+i);
        // //Tao thu muc
        try {
            Path createDirectories = Files.createDirectories(path);

            System.out.println("Created a Directories at : " + createDirectories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void CreateFile(int i) throws IOException {
        Path path = Paths.get("/home/ubuntu/Desktop/file"+i+"/file"+i+".txt");
        //Tao mot tep
        try {
            Path createdFilePath = Files.createFile(path);
            System.out.println("Created a file at : "+createdFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
