/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ImageServerImpl.java
 * Description: Example JAX-WS Service with using MTOM - Service Implementation
 */

/**
 * @file ImageServerImpl.java
 *
 * @brief Example JAX-WS Service with using MTOM - Service Implementation
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.soap.MTOM;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Service Implementation
 */
@MTOM
@WebService(endpointInterface = "cz.vutbr.fit.knot.gja.ws.ImageServer")
public class ImageServerImpl implements ImageServer {

  /**
   * Downloads image
   *
   * @param name File name of image
   * @return Returns downloaded image
   */
  @Override
  public Image downloadImage(String name) {

    try {
      File image = new File(System.getProperty("user.home") + "/downloads/" + name);
      return ImageIO.read(image);

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Upload image
   *
   * @param data Image data
   * @param filename File name of image
   * @return Returns message about success
   */
  @Override
  public String uploadImage(Image data, String filename) {

    if (data != null) {
      try {
        BufferedImage bi = (BufferedImage) data;
        File f = new File(System.getProperty("user.home") + "/downloads/" + filename);
        ImageIO.write(bi, "png", f);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return "Upload Successful";
    }

    throw new WebServiceException("Upload Failed!");

  }
}  // public class ImageServerImpl
