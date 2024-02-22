/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_fileupload_widget.htm
 */

package cz.vutbr.fit.gja.example.server;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.io.FilenameUtils;

public class FileUploadServlet extends HttpServlet {

  private static final long serialVersionUID = 207411964288155592L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // process only multipart requests
    if (ServletFileUpload.isMultipartContent(req)) {

      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      // Parse the request
      try {
        String pattern = "MESSAGE";
        String responseText = "";
        String responseMSG = "" + "<html><body>" + "<script type=\"text/javascript\">"
            + "parent.window.uploadComplete('" + pattern + "');" + "</script>" + "</body></html>";
        List<FileItem> items = upload.parseRequest(req);
        for (FileItem item : items) {
          // process only file upload - discard other form item types
          if (item.isFormField())
            continue;

          String fileName = item.getName();
          // get only the file name not whole path
          if (fileName != null) {
            fileName = FilenameUtils.getName(fileName);
          }
          String imgFolderPath = getServletContext().getRealPath("/") + "/images/";
          File f = new File(imgFolderPath);
          if (!f.exists())
            f.mkdir();

          File uploadedFile = new File(imgFolderPath, fileName);
          if (uploadedFile.exists())
            uploadedFile.delete();
          if (uploadedFile.createNewFile()) {
            item.write(uploadedFile);
            // resp.setStatus(HttpServletResponse.SC_CREATED);
            // resp.getWriter().print("img/"+fileName);
            // resp.flushBuffer();
            if (responseText.length() != 0)
              responseText += ",";
            responseText += "images/" + fileName;
          }

        }
        resp.setContentType("text/html");
        resp.setHeader("Pragma", "No-cache");
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        responseMSG = responseMSG.replace(pattern, responseText);
        out.println(responseMSG);
        out.flush();
      } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            "An error occurred while creating the file : " + e.getMessage());
      }

    } else {
      resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
          "Request contents type is not supported by the servlet.");
    }
  }
}