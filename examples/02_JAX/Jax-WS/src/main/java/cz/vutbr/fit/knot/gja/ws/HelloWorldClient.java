/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: HelloWorldClient.java
 * Description: Example JAX-WS Service Client
 */

/**
 * @file HelloWorldClient.java
 *
 * @brief Example JAX-WS Service Client
 */

package cz.vutbr.fit.knot.gja.ws;

import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;

/**
 * Example JAX-WS Service Client
 */
public class HelloWorldClient {

  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) throws Exception {

    URL url = new URL("http://localhost:6666/ws/hello?wsdl");

    // 1st argument service URI, refer to wsdl document above
    // 2nd argument is service name, refer to wsdl document above
    QName qname = new QName("http://ws.gja.knot.fit.vutbr.cz/", "HelloWorldImplService");

    Service service = Service.create(url, qname);

    HelloWorld hello = service.getPort(HelloWorld.class);

    System.out.println(hello.getHelloWorldAsString("fit"));

  }
}
