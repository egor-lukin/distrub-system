package src.main.java.monitor;

import java.io.*;

public class ConsoleStorage implements IStorage
{
  public void open(){
    System.out.println("Open storage");
  }

  public void write(String text){
    System.out.println(text);
  }

  public String read(){
    return "Not implement read";
  }

  public void close(){
    System.out.println("Close storage");
  }
}
