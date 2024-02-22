/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_fileupload_widget.htm
 */

package cz.vutbr.fit.gja.example.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  private final String repository = "/images/";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String filename = request.getParameter("file");

    // Security: '..' in the filename will let sneaky users access files
    // not in your repository.
    filename = filename.replace("..", "");

    File file = new File(getServletContext().getRealPath(repository + filename));
    if (!file.exists())
      throw new FileNotFoundException(file.getAbsolutePath());

    response.setHeader("Content-Type", "image/jpeg");
    response.setHeader("Content-Length", String.valueOf(file.length()));
    response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
    byte[] buf = new byte[1024];
    while (true) {
      int length = bis.read(buf);
      if (length == -1) {
        break;
      }

      bos.write(buf, 0, length);
    }
    bos.flush();
    bos.close();
    bis.close();
  }
}
