//import javax.servlet.http.HttpServletRequest;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * Simple Java program to find IP Address of localhost. This program uses
// * InetAddress from java.net package to find IP address.
// *
// */
//public class IPTest {
//
//    private static HttpServletRequest request;
//
//    public static void main(String args[]) throws UnknownHostException {
//
//        InetAddress addr = InetAddress.getLocalHost();
//
//        //Getting IPAddress of localhost - getHostAddress return IP Address
//        // in textual format
//        String ipAddress =  request.getHeader("X-FORWARDED-FOR");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }
//
//        System.out.println("IP address of localhost from Java Program: " + ipAddress);
//
//        //Hostname
//        String hostname = addr.getHostName();
//        System.out.println("Name of hostname : " + hostname);
//    }
//}