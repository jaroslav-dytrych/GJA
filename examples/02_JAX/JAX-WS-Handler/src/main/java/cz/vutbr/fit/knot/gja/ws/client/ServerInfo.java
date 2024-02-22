/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ServerInfo.java
 * Description: Example of Web Service handlers
 */

/**
 * @file ServerInfo.java
 *
 * @brief Example of Web Service handlers
 */

package cz.vutbr.fit.knot.gja.ws.client;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(name = "ServerInfoService", targetNamespace = "http://server.ws.gja.knot.fit.vutbr.cz/")
public interface ServerInfo {

    /**
     * Gets server name
     * 
     * @return Returns server name
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getServerName", targetNamespace = "http://server.ws.gja.knot.fit.vutbr.cz/", className = "cz.vutbr.fit.knot.gja.ws.client.GetServerName")
    @ResponseWrapper(localName = "getServerNameResponse", targetNamespace = "http://server.ws.gja.knot.fit.vutbr.cz/", className = "cz.vutbr.fit.knot.gja.ws.client.GetServerNameResponse")
    public String getServerName();

}
