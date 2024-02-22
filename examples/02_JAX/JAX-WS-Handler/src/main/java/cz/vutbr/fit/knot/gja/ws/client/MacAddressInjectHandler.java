/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: MacAddressInjectHandler.java
 * Description: Example of Web Service handlers - MAC Address inject handler
 */

/**
 * @file MacAddressInjectHandler.java
 *
 * @brief Example of Web Service handlers - MAC Address inject handler
 */

package cz.vutbr.fit.knot.gja.ws.client;

import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Set;
import javax.xml.namespace.QName;

/**
 * MAC Address inject handler
 */
public class MacAddressInjectHandler implements SOAPHandler<SOAPMessageContext> {

  /**
   * Handles message
   * 
   * @param context
   * @return Returns whether continue in handler chain
   */
  @Override
  public boolean handleMessage(SOAPMessageContext context) {

    System.out.println("Client : handleMessage()......");

    Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    // If this is a request, true for outbound messages, false for inbound
    if (isRequest) {

      try {
        SOAPMessage soapMsg = context.getMessage();
        SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader = soapEnv.getHeader();

        // If no header, add one
        if (soapHeader == null) {
          soapHeader = soapEnv.addHeader();
        }

        // Get mac address
        String mac = getMACAddress();

        // Add a soap header, name as "mac address"
        QName qname = new QName("http://server.ws.gja.knot.fit.vutbr.cz/", "macAddress");
        SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(qname);

        soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
        soapHeaderElement.addTextNode(mac);
        soapMsg.saveChanges();

        //tracking
        soapMsg.writeTo(System.out);


      } catch (SOAPException e) {
        System.err.println(e);
      } catch (IOException e) {
        System.err.println(e);
      }

    }

    //continue other handler chain
    return true;
  }

  /**
   * Fault handler
   * 
   * @param context Message context
   * @return Returns whether continue in processing
   */
  @Override
  public boolean handleFault(SOAPMessageContext context) {
    System.out.println("Client : handleFault()......");
    return true;
  }

  /**
   * Called at the conclusion of a message exchange pattern
   * 
   * @param context Message context
   */
  @Override
  public void close(MessageContext context) {
    System.out.println("Client : close()......");
  }

  /**
   * Gets the header blocks that can be processed by this Handler instance
   * 
   * @return Returns header block (not implemented - returns null)
   */
  @Override
  public Set<QName> getHeaders() {
    System.out.println("Client : getHeaders()......");
    return null;
  }

  /**
   * Gets current client mac address
   * 
   * @return Returns current client mac address
   */
  private String getMACAddress() {

    StringBuilder sb = new StringBuilder();
    InetAddress ip;
    try {

      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface network = networkInterfaces.nextElement();

        ip = InetAddress.getLocalHost();
        System.out.println("Current IP address : " + ip.getHostAddress());

        byte[] mac = network.getHardwareAddress();
        if (mac == null) {
          System.out.println("null mac");
        } else {
          System.out.print("MAC address : ");

          for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
          }
          System.out.println(sb.toString());
          break;
        }
      }
    } catch (SocketException | UnknownHostException e) {
      e.printStackTrace();
    }
    return sb.toString();

  }  // getMACAddress()
}  // public class MacAddressInjectHandler
