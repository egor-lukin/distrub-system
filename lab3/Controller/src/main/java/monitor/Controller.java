package src.main.java.monitor;

import java.io.*;
import java.util.*;

public class Controller extends Thread
{
  private int _id;

  private IStorage _storage;

  public Controller(int id, IStorage storage)
  {
    _id = id;
    _storage = storage;
  }

  @Override
  public void run()
  {
    Random rand = new Random();
    String message = "";
    while(true) {
      if (Thread.interrupted()) {
        _storage.close();
        return;
      }

      try {
        Thread.sleep(rand.nextInt(5) * 1000);
        message = "Get data from " + _id + " device - " + Calendar.getInstance().getTime();
        System.out.println(message);
        _storage.write(message);
      } catch(InterruptedException ex) {
        _storage.close();
      }
    }
  }
}
