import java.io.*;
import java.util.*;
import src.main.java.monitor.*;

class ShutdownHook
{
  public void listen(List<Controller> controllers){
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run()
      {
        controllers.stream().forEach(cont -> cont.interrupt());
        System.out.println("\n Stop");
      }
    });
  }
}

class EntryPoint
{
  public static void main(String args[])
  {
    System.out.println("Run monitoring system...");
    ShutdownHook hook = new ShutdownHook();

    List<Controller> controllers = new ArrayList<Controller>();
    hook.listen(controllers);

    int count;
    try {
      System.out.println("Input controllers count");
      count = Integer.parseInt(System.console().readLine());
    } catch(Exception ex) {
      count = 5;
    }
    System.out.println("Created " + count + " controllers.");

    IStorage storage = new ConsoleStorage();

    for(int i = 0; i < count; i++){
      controllers.add(new Controller(i, storage));
    }
    controllers.stream().forEach(cont -> cont.start());
    System.out.println("Controllers are running.");
  }
}
