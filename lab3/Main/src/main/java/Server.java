import java.net.*;
import java.io.*;

public class Server {
   public static void main(String[] ar)    {
     int port = 8009;
       try {
         InetAddress addr = InetAddress.getByName("localhost");
         ServerSocket ss = new ServerSocket(port, 8010,  addr);
         System.out.println("Waiting for a client...");

         Socket socket = ss.accept();
         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
         System.out.println();

         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

         String line = null;
         while(true) {
           line = in.readUTF();
           System.out.println(line);
         }
      } catch(Exception x) { x.printStackTrace(); }
   }
}
