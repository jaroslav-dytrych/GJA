/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: MacAddressValidatorHandler.java
 * Description: Example of Web Service handlers - MAC Address validator
 */

/**
 * @file MacAddressValidatorHandler.java
 *
 * @brief Example of Web Service handlers - MAC Address validator
 */

package cz.vutbr.fit.knot.gja.ws.server;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;

/**
 * MAC Address validator
 */
public class MacAddressValidatorHandler implements SOAPHandler<SOAPMessageContext> {

  /**
   * Handles message 
   * 
   * @param context
   * @return Returns whether continue in handler chain
   */
  @Override
  public boolean handleMessage(SOAPMessageContext context) {

    System.out.println("Server : handleMessage()......");

    Boolean isRequest = !(Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    // For request message only, true for inbound messages, false for outbound
    if (isRequest) {

      try {
        SOAPMessage soapMsg = context.getMessage();
        SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader = soapEnv.getHeader();

        // If no header, add one
        if (soapHeader == null) {
          soapHeader = soapEnv.addHeader();
          // Throw exception
          generateSOAPErrMessage(soapMsg, "No SOAP header.");
        }

        // Get client MAC address from SOAP header
        Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

        // If no header block for next actor found? throw exception
        if (it == null || !it.hasNext()) {
          generateSOAPErrMessage(soapMsg, "No header block for next actor.");
        }

        // If no MAC address found? throw exception
        Node macNode = (Node) it.next();
        String macValue = (macNode == null) ? null : macNode.getValue();

        if (macValue == null) {
          generateSOAPErrMessage(soapMsg, "No mac address in header block.");
        }

        // If MAC address is not match, throw exception
	/*
         * eth0 if(!macValue.equals("E4-11-5B-F2-54-AA")){
         *   generateSOAPErrMessage(soapMsg, "Invalid mac address, access is denied."); 
         * }
         */

        if (!macValue.equals("AC-7B-A1-D4-90-E0")) {
          generateSOAPErrMessage(soapMsg, "Invalid mac address, access is denied.");
        }

        // wlan0
	/*
         * If(!macValue.equals("08-11-96-2F-D3-DC")){
         *   generateSOAPErrMessage(soapMsg, "Invalid mac address, access is denied."); 
         * }
         */

        // tracking
        soapMsg.writeTo(System.out);

      } catch (SOAPException | IOException e) {
        System.err.println(e);
      }
    }

    // continue other handler chain
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
    System.out.println("Server : handleFault()......");

    return true;
  }

  /**
   * Called at the conclusion of a message exchange pattern
   * 
   * @param context Message context
   */
  @Override
  public void close(MessageContext context) {
    System.out.println("Server : close()......");
  }

  /**
   * Gets the header blocks that can be processed by this Handler instance
   * 
   * @return Returns header block (not implemented - returns null)
   */
  @Override
  public Set<QName> getHeaders() {
    System.out.println("Server : getHeaders()......");
    return null;
  }

  /**
   * Generates SOAP error message
   * 
   * @param msg Error message
   * @param reason Error reason
   */
  private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
    try {
      SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
      SOAPFault soapFault = soapBody.addFault();
      soapFault.setFaultString(reason);
      throw new SOAPFaultException(soapFault);
    } catch (SOAPException e) {
    }
  }
}  // public class MacAddressValidatorHandler
