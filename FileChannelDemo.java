//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.nio.charset.Charset;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//public class FileChannelDemo {
//
//    public static void main(String args[]) throws IOException {
//        ArrayList<ArrayList<String>> type = new ArrayList<>(20);
//        ArrayList<ArrayList<String>> coml = new ArrayList<>(20);
//        ArrayList<String> bang = new ArrayList<>();
//        bang.add("City");
//        bang.add("Customer");
//        type.add(new ArrayList<>());
//        type.get(0).add("int");
//        type.get(0).add("String");
//        type.get(0).add("Local Date");
//        type.get(0).add("int");
//        type.get(0).add("String");
//
//        type.add(new ArrayList<>());
//        type.get(1).add("Local Date");
//        type.get(1).add("int");
//        type.get(1).add("String");
//        type.get(1).add("String");
//        type.get(1).add("int");
//        type.get(1).add("int");
//        type.get(1).add("String");
//        type.get(1).add("String");
//
//        coml.add(new ArrayList<>());
//        coml.get(0).add("id");
//        coml.get(0).add("name");
//        coml.get(0).add("DOB");
//        coml.get(0).add("phoneNumber");
//        coml.get(0).add("Address");
//
//        coml.add(new ArrayList<>());
//        coml.get(1).add("DeleteAt");
//        coml.get(1).add("Tuoi");
//        coml.get(1).add("City");
//        coml.get(1).add("Contry");
//        coml.get(1).add("ListID");
//        coml.get(1).add("OrderId");
//        coml.get(1).add("NXB");
//        coml.get(1).add("NCB");
//        System.out.println(type);
//        System.out.println(coml);
//        System.out.println(bang.get(0));
//        for(int i=0;i<bang.size();i++){
//            writeFileChannel(ByteBuffer.wrap("import java.time.LocalDate;\n".getBytes()));
//            writeFileChannel(ByteBuffer.wrap("public class "  + bang.get(i)
//
//            for(int j=0;j<type[j].size()){
//                switch (type[j].get[0]){
//                    case "int":
//                        writeFileChannel(ByteBuffer.wrap("private int "+colm[i].get[j]+ Arrays.toString(";\n".getBytes())));
//                        break;
//                    case "varchar":
//                    case "Nvarchar":
//                        writeFileChannel(ByteBuffer.wrap("private String "+colm[i].get[j]+ ";\n".getBytes()));
//                        break;
//                    case "DATE":
//                        writeFileChannel(ByteBuffer.wrap("private LocalDate"+colm[i].get[j]+ ";\n".getBytes()));
//                        break;
//                }
//            }
//            writeFileChannel(ByteBuffer.wrap("public class " +bang[i]+  "(".getBytes()));
//            for ( int j=0;i<type[j].size-1;j++){
//                writeFileChannel(ByteBuffer.wrap( type[i].get[j] + " " + colm[i].get[j] + ",".getBytes()));
//            }
//            writeFileChannel(ByteBuffer.wrap( type[i].get[j] + " " + colm[i].get[j] + ");\n".getBytes()));
//
//            for ( int j=0;i<type[j].size;j++){
//                writeFileChannel(ByteBuffer.wrap(  colm[i].get[j] + "=" +(colm[i].get[j]).toLowerCase()  +";/n".getBytes()));
//            }
//
//            //read the file
//            readFileChannel();
//        }
//
//    }
//    public static void readFileChannel() throws IOException {
//        RandomAccessFile randomAccessFile = new RandomAccessFile("/home/ubuntu/Desktop/duyphuc1/model/"+bang.get+".java",
//                "rw");
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
//        Charset charset = Charset.forName("US-ASCII");
//        while (fileChannel.read(byteBuffer) > 0) {
//            byteBuffer.rewind();
//            System.out.print(charset.decode(byteBuffer));
//            byteBuffer.flip();
//        }
//        fileChannel.close();
//        randomAccessFile.close();
//    }
//    public static void writeFileChannel(ByteBuffer byteBuffer)throws IOException {
//        Set<StandardOpenOption> options = new HashSet<>();
//        options.add(StandardOpenOption.CREATE);
//        options.add(StandardOpenOption.APPEND);
//        Path path = Paths.get("/home/ubuntu/Desktop/duyphuc1/model/"+bang[i]+".java");
//        FileChannel fileChannel = FileChannel.open(path, options);
//        fileChannel.write(byteBuffer);
//        fileChannel.close();
//    }
//}