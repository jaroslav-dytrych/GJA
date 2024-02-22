/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: FooBar.java
 * Description: Example JAX-WS Service for Document Binding - Service Client
 */

/**
 * @file FooBar.java
 *
 * @brief Example Example JAX-WS Service for Document Binding - Service Client
 */

package cz.vutbr.fit.knot.gja.ws;

import java.net.MalformedURLException; 
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;

/**
 * Service Client
 */
public class FooBarClient {

  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    URL url = null;
    try {
      url = new URL("http://localhost:7777/JAX-WS-Document/foobar?wsdl");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    // 1st argument service URI, refer to wsdl document above
    // 2nd argument is service name, refer to wsdl document above 
    QName qname = new QName("http://ws.gja.knot.fit.vutbr.cz/", "FooBarImplService");
    Service service = Service.create(url, qname);
    FooBar foobar = service.getPort(FooBar.class);
    System.out.println(foobar.callFooBar("Localhost"));
    System.out.println(foobar.getServerDetail("Localhost").toString());
  }
}
