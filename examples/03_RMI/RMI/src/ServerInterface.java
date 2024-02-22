/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ServerInterface.java
 * Description: Example usage of RMI - server interface
 */

/**
 * @file ServerInterface.java
 *
 * @brief Example Example usage of RMI - server interface
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server interface
 */
public interface ServerInterface extends Remote {

  public void login(ClientInterface client, String nickname) throws RemoteException;

  public void broadcastMessage(String message, String nickname) throws RemoteException;
}
