/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Server.java
 * Description: Example usage of RMI - server
 */

/**
 * @file Server.java
 *
 * @brief Example Example usage of RMI - server
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Server
 */
public class Server extends UnicastRemoteObject implements ServerInterface {

  /** List of clients */
  protected ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

  /** Constructor */
  public Server() throws RemoteException {
  }

  /**
   * Login function
   * 
   * @param client Client
   * @param nickname User nickname
   * @throws RemoteException 
   */
  public void login(ClientInterface client, String nickname) throws RemoteException {
    broadcastMessage("--> " + nickname + " is entering the chatroom", "");
    clients.add(client);
  }

  /**
   * Broadcast message between logged in users
   * 
   * @param message
   * @param nickname
   * @throws RemoteException 
   */
  @Override
  public void broadcastMessage(String message, String nickname) throws RemoteException {
    for (int i = 0; i < clients.size(); i++) {
      ClientInterface c = clients.get(i);
      try {
        c.getMessage(message, nickname);
      } catch (RemoteException e) {
        /*
         * If a client is not accessible, then it is removed from the ArrayList
         * and the index i is decremented because all other clients go down one
         * place
         */
        logout(c);
        i = i - 1;
      }
    }
  }

  /**
   * Logs out the client
   * 
   * @param client Client
   */
  public void logout(ClientInterface client) {
    clients.remove(client);
  }

  /**
   * Main function
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    try {
      Naming.rebind("Server", new Server());  // start server
      System.out.println("Server is ready");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}  // public class Server
