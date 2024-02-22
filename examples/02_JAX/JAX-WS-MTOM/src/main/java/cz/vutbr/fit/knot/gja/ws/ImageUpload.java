/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ImageUpload.java
 * Description: Example JAX-WS Service with using MTOM 
 */

/**
 * @file ImageUpload.java
 *
 * @brief Example JAX-WS Service with using MTOM - Image upload
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.soap.SOAPBinding;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;

/**
 * Image upload
 */
public class ImageUpload {

  /**
   * Main method
   *
   * @param args Command line arguments
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {

    URL url = new URL("http://localhost:8888/ws/image?wsdl");
    QName qname = new QName("http://ws.gja.knot.fit.vutbr.cz/", "ImageServerImplService");

    Service service = Service.create(url, qname);
    ImageServer imageServer = service.getPort(ImageServer.class);

    /**
     * ********** test upload ***************
     */
    Image imgUpload = ImageIO.read(new File(System.getProperty("user.home") + "/test.png"));

    // enable MTOM in client
    BindingProvider bp = (BindingProvider) imageServer;
    SOAPBinding binding = (SOAPBinding) bp.getBinding();
    binding.setMTOMEnabled(true);

    String status = imageServer.uploadImage(imgUpload, "test.png");
    System.out.println("imageServer.uploadImage() : " + status);

  }
}
