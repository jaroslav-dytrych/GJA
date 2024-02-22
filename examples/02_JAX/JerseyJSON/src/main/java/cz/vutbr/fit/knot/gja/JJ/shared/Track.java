/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Track.java
 * Description: Example Jersey application with using JSON - shared object
 */

/**
 * @file Track.java
 *
 * @brief Example Jersey application with using JSON - shared object
 */

/**
 * @package cz.vutbr.fit.knot.gja.JJ.shared
 * 
 * @brief Example Jersey application with using JSON - shared object
 */
package cz.vutbr.fit.knot.gja.JJ.shared;

/**
 * Example Jersey application with using JSON - shared object
 * 
 * Sound track description
 */
public class Track {

  String title;
  String singer;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSinger() {
    return singer;
  }

  public void setSinger(String singer) {
    this.singer = singer;
  }

  @Override
  public String toString() {
    return "Track [title=" + title + ", singer=" + singer + "]";
  }
}
