package src.main.java.monitor;

import java.io.*;
import java.net.*;

public class SocketStorage implements IStorage
{
  private DataInputStream in;
  private DataOutputStream out;

  public void open(){
    String address = "localhost";
    int serverPort = 8009;

    try{
      InetAddress ipAddress = InetAddress.getByName(address);

      Socket socket = new Socket(ipAddress, serverPort);

      InputStream sin = socket.getInputStream();
      OutputStream sout = socket.getOutputStream();

      in = new DataInputStream(sin);
      out = new DataOutputStream(sout);
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void write(String text){
    try{
      out.writeUTF(text);
      out.flush();
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }

  public String read(){
    return "Not implement read";
  }

  public void close(){
    System.out.println("Close storage");
  }
}
