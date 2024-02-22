/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: FooBarServer.java
 * Description: Example JAX-WS Service for Document Binding - Server detail
 */

/**
 * @file FooBarServer.java
 *
 * @brief Server detail
 */

package cz.vutbr.fit.knot.gja.ws;

/**
 * Server detail
 */
public class Server {

  /** Name */
  private String name;
  /** IP Address */
  private String ip;
  /** MAC Address */
  private String mac;
  /** Operating system */
  private String os;

  /**
   * Gets name
   * 
   * @return Returns name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name
   * 
   * @param name New name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets IP address
   * 
   * @return Returns IP address
   */
  public String getIp() {
    return ip;
  }

  /**
   * Sets IP address
   * 
   * @param ip New IP address
   */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /**
   * Gets MAC address
   * 
   * @return Returns MAC address
   */
  public String getMac() {
    return mac;
  }

  /**
   * Sets MAC address
   * 
   * @param mac New MAC address
   */
  public void setMac(String mac) {
    this.mac = mac;
  }

  /**
   * Gets operating system
   * 
   * @return Returns operating system
   */
  public String getOs() {
    return os;
  }

  /**
   * Sets operating system
   * 
   * @param os New operating system
   */
  public void setOs(String os) {
    this.os = os;
  }

  /**
   * Serializes this object to string
   * 
   * @return String representation of this object
   */
  @Override
  public String toString() {
    return "Server [name=" + name + ", ip=" + ip + ", mac=" + mac + ", os=" + os + ", toString()=" + super.toString() + "]";
  }
}  // public class Server
