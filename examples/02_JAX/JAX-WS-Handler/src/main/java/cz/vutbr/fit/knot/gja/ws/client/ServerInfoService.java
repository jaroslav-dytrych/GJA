/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ServerService.java
 * Description: Example of Web Service handlers
 */

/**
 * @file ServerService.java
 *
 * @brief Example of Web Service handlers
 */

package cz.vutbr.fit.knot.gja.ws.client;

import jakarta.jws.HandlerChain;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 * Server service
 */
@WebServiceClient(name = "ServerInfoService",
                  targetNamespace = "http://ws.gja.knot.fit.vutbr.cz/",
                  wsdlLocation = "http://localhost:9999/ws/server?wsdl")
@HandlerChain(file = "handler-client.xml")
public class ServerInfoService extends Service {

  private final static URL SERVERINFOSERVICE_WSDL_LOCATION;

  static {
    URL url = null;
    try {
      url = new URL("http://localhost:9999/ws/server?wsdl");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    SERVERINFOSERVICE_WSDL_LOCATION = url;
  }

  public ServerInfoService(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public ServerInfoService() {
    super(SERVERINFOSERVICE_WSDL_LOCATION, new QName("http://server.ws.gja.knot.fit.vutbr.cz/", "ServerService"));
  }

  /**
   *
   * @return returns ServerInfo
   */
  @WebEndpoint(name = "ServerPort")
  public ServerInfo getServerPort() {
    return (ServerInfo) super.getPort(new QName("http://server.ws.gja.knot.fit.vutbr.cz/", "ServerPort"), ServerInfo.class);
  }

  /**
   *
   * @param features A list of {@link javax.xml.ws.WebServiceFeature} to
   *                 configure on the proxy. Supported features not in the
   * <code>features</code> parameter will have their default values.
   * @return Returns ServerInfo
   */
  @WebEndpoint(name = "ServerPort")
  public ServerInfo getServerPort(WebServiceFeature... features) {
    return (ServerInfo) super.getPort(new QName("http://server.ws.gja.knot.fit.vutbr.cz/", "ServerPort"), ServerInfo.class, features);
  }
}
