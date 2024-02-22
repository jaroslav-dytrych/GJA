/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_injecting_collection.htm
 */

package cz.vutbr.fit.gja.springCollections;

import java.util.*;

public class JavaCollection {

  List addressList;
  Set addressSet;
  Map addressMap;
  Properties addressProp;

  /**
   * A setter method to set List
   * 
   * @param addressList Address list
   */
  public void setAddressList(List addressList) {
    this.addressList = addressList;
  }

  /**
   * Prints and returns all the elements of the list
   * 
   * @return Returns all the elements of the list
   */
  public List getAddressList() {
    System.out.println("List Elements :" + addressList);
    return addressList;
  }

  /**
   * A setter method to set Set
   * 
   * @param addressSet Address set
   */
  public void setAddressSet(Set addressSet) {
    this.addressSet = addressSet;
  }

  /**
   * Prints and returns all the elements of the Set
   * 
   * @return Returns all the elements of the Set
   */
  public Set getAddressSet() {
    System.out.println("Set Elements :" + addressSet);
    return addressSet;
  }

  /**
   * A setter method to set Map
   * 
   * @param addressMap Address map
   */
  public void setAddressMap(Map addressMap) {
    this.addressMap = addressMap;
  }

  /**
   * Prints and returns all the elements of the Map
   * 
   * @return Returns all the elements of the Map
   */
  public Map getAddressMap() {
    System.out.println("Map Elements :" + addressMap);
    return addressMap;
  }

  /**
   * A setter method to set Property
   * 
   * @param addressProp Address property
   */
  public void setAddressProp(Properties addressProp) {
    this.addressProp = addressProp;
  }

  /**
   * Prints and returns all the elements of the Property
   * 
   * @return Returns all the elements of the Property
   */
  public Properties getAddressProp() {
    System.out.println("Property Elements :" + addressProp);
    return addressProp;
  }
}
