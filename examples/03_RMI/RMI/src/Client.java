/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Client.java
 * Description: Example usage of RMI - client
 */

/**
 * @file Client.java
 *
 * @brief Example Example usage of RMI - client
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Client
 */
public class Client extends UnicastRemoteObject implements ClientInterface {

  /** Constructor */
  public Client() throws RemoteException {
    super();
  }

  /** 
   * Receives message from server
   * 
   * @param message Message
   * @param nickname user nickname
   */
  @Override
  public void getMessage(String message, String nickname) throws RemoteException {
    GUI.showMessage(message, nickname);
  }
}
