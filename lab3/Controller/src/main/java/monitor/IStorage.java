package src.main.java.monitor;

public interface IStorage {
  void open();
  void write(String text);
  String read();
  void close();
}
