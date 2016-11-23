package src.main.java.monitor;

import java.io.*;
import java.util.*;

public class Controller extends Thread
{
  private int _id;

  private Thread _thread;

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
    while(true) {
      if (Thread.interrupted()) {
        _storage.close();
        return;
      }

      try {
        Thread.sleep(rand.nextInt(5) * 1000);
        _storage.write("Get data from " + _id + " device - " + Calendar.getInstance().getTime());
      } catch(InterruptedException ex) {
        _storage.close();
      }
    }
  }
}
