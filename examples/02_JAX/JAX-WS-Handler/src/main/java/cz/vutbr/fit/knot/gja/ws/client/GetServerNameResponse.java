/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: GetServerNameResponse.java
 * Description: Example of  Web Service handlers
 */

/**
 * @file GetServerNameResponse.java
 *
 * @brief Example of Web Service handlers
 */
package cz.vutbr.fit.knot.gja.ws.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServerNameResponse", propOrder = {
  "_return"
})
public class GetServerNameResponse {

  @XmlElement(name = "return")
  protected String _return;

  /**
   * Gets the value of the return property.
   *
   * @return possible object is
   *         {@link String }
   *
   */
  public String getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   *
   * @param value allowed object is
   *              {@link String }
   *
   */
  public void setReturn(String value) {
    this._return = value;
  }
}
