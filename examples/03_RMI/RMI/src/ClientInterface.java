/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ClientInterface.java
 * Description: Example usage of RMI - client interface
 */

/**
 * @file ClientInterface.java
 *
 * @brief Example Example usage of RMI - client interface
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client interface
 */
public interface ClientInterface extends Remote {

  /** 
   * Receives message from server
   * 
   * @param message Message
   * @param nickname user nickname
   */
  public void getMessage(String message, String nickname) throws RemoteException;
}
