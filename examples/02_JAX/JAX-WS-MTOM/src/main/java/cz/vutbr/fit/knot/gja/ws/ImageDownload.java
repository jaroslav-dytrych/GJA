/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ImageDownload.java
 * Description: Example JAX-WS Service with using MTOM - Image download
 */

/**
 * @file ImageDownload.java
 *
 * @brief Example JAX-WS Service with using MTOM - Image download
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.xml.ws.Service;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
 
/**
 * Image download
 */
public class ImageDownload {

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
     * ********** test download  **************
     */
    Image image = imageServer.downloadImage("test.png");

    // display it in frame
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    JLabel label = new JLabel(new ImageIcon(image));
    frame.add(label);
    frame.setVisible(true);

    System.out.println("imageServer.downloadImage() : Download Successful!");

  }
}
