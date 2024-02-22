package cz.vutbr.fit.gja.kufr.client;

public interface FileListener {
  void fileAdded(String a);

  void fileRemoved(String a);
}