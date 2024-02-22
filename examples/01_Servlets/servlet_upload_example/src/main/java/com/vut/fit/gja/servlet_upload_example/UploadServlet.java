package com.vut.fit.gja.servlet_upload_example;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;

/**
 * An example of HTTP Servlet using Jakarta EE 10 API. This is current example
 * for a FileUpload using Servlet 3.0+.
 *
 * @author xbarta47
 *
 * <p>
 * The annotation (MultipartConfig) specifies that this servlet will handle
 * file uploads and limits of these files. 1 MB - the file will be stored onto
 * the disk if it is bigger than this. 2 MB - maximal one file size and 10 MB -
 * max for all files uploaded in one request. maxFileSize does not work properly
 * so it's handled manually (it throws an exception correctly, but does not
 * display it correctly in the web browser).
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024)
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    // Specify explicit serialVersionUID for correct serialization. 
    private static final long serialVersionUID = 42L;

    // Max uploaded file size
    private static final int MAX_FILE_SIZE = 2 * 1024 * 1024;

    //Temporary Windows and Linux solution for uploading directory.
    private static final String UPLOAD_DIRECTORY = System.getProperty("java.io.tmpdir");

    //HTML header for drawing the page correctly
    private static final String HTML_HEADER = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <title>File Upload Servlet Example</title>\n"
            + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
            + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \n"
            + "              integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\n"
            + "    </head>\n"
            + "    <body>"
            + "<div class=\"card w-50 mx-auto my-5 text-center\">\n"
            + "        <div class=\"card-header text-info\">\n"
            + "            GJA - Jakarta EE 10 Upload Servlet Example\n"
            + "        </div>\n"
            + "        <div class=\"card-body\">\n";
    private String HTML_FOOTER;

    // Root context path of the servlet (index.html)
    private String contextPath;

    /**
     * Default no-arg constructor.
     */
    public UploadServlet() {
        super();
    }

    /**
     * Override the parent's init() method.
     *
     * @param config Configuration object
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        contextPath = super.getServletContext().getContextPath();

        HTML_FOOTER = "</div>\n"
                + " <div class=\"card-footer text-muted\">\n"
                + " <a href=\"" + contextPath + "\">Go back to upload</a>\n"
                + " </div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";

        if (!new File(UPLOAD_DIRECTORY).isDirectory()) {
            throw new ServletException(UPLOAD_DIRECTORY + " is not a directory");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method. A simple way to display an
     * uploaded image in a servlet from local disk to the web browser.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (ServletOutputStream out = response.getOutputStream()) {
            String imageName = request.getParameter("imname");
            // Only allow images to be loaded from the disk
            if (validateFileExtension(imageName)) {
                response.setContentType("text/html;charset=UTF-8");
                out.println(HTML_HEADER);
                out.println("<h1>Invalid file type - .jpg, .jpeg, and .png "
                        + "are allowed to be shown.</h1>");
                out.println(HTML_FOOTER);
                return;
            }
            if (imageName.endsWith(".png")) {
              response.setContentType("image/png");
            } else {
              response.setContentType("image/jpeg");
            }
            // Input buffer of the loaded image from disk
            BufferedInputStream bufIn;
            // Output buffer (the web browser)
            BufferedOutputStream bufOut;
            // Try to load the file from the disk 
            try (FileInputStream fileInput
                    = new FileInputStream(UPLOAD_DIRECTORY + "/" + imageName)) {
                // Populate the buffers
                bufIn = new BufferedInputStream(fileInput);
                bufOut = new BufferedOutputStream(out);
                // Read and write the image through the buffers
                int b;
                while ((b = bufIn.read()) != -1) {
                    bufOut.write(b);
                }
                // Close all files / buffers
                fileInput.close();
                bufIn.close();
                bufOut.close();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.<p>
     * This method handles multiple file upload with Servlet 3.0+ capabilities
     * (using the Jakarta Part class). Validate the uploaded files and print an
     * HTML page that lists links so the uploaded files can be shown in the
     * browser. Only images are allowed to be uploaded (with basic extension
     * checking).
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Print the HTML header
            out.println(HTML_HEADER);

            // Print an HTML List "header"
            out.println("<ul class=\"list-group\">\n");
            // Process multifile upload
            for (Part filePart : request.getParts()) {
                // Check if a file was actually uploaded
                if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
                    out.println("<h1>No file uploaded.</h1>");
                    out.println(HTML_FOOTER);
                    return;
                }
                /* Get the uploaded filename, replace non-ASCII and whitespaces
                 * convert to lowercase so even things like .PNG gets recognized
                 */
                String fileName = Paths.get(filePart.getSubmittedFileName())
                        .getFileName()
                        .toString()
                        .toLowerCase()
                        .replaceAll("\\s", "")
                        .replaceAll("[^\\x00-\\x7F]", "");
                // Validate the uploaded file size manually
                long size = filePart.getSize();
                if (size > MAX_FILE_SIZE) { // 2 MB
                    request.setAttribute("error", "File is too large (max size is 1 MB)");
                    out.println("<h1>File is too large max allowed "
                            + "size is " + MAX_FILE_SIZE / 1000000 + " MB.</h1>");
                    out.println(HTML_FOOTER);
                    return;
                }

                // Validate the uploaded file extension
                if (validateFileExtension(fileName)) {
                    out.println("<h1>Invalid file type - .jpg, .jpeg, and .png are allowed.</h1>");
                    out.println(HTML_FOOTER);
                    return;
                }
                // Save the file to the specified location
                try (InputStream inputStream = filePart.getInputStream()) {
                    Files.copy(inputStream, Paths.get(UPLOAD_DIRECTORY + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    out.println("<h1>Error while uploading the file.</h1>");
                    out.println(HTML_FOOTER);
                }

                /* Print the file as a part of HTML list and make a href/button
                 * that will show it after being clicked
                 */
                out.println("<li class=\"list-group-item\">"
                        + "<form id=\"image_name\" method=\"GET\""
                        + "action=\"" + request.getRequestURI() + "\">"
                        + "<input type=\"hidden\" id=\"imname\" name=\"imname\""
                        + "value=\"" + fileName + "\">"
                        + "<button type=\"submit\" class=\"btn btn-primary m-2\">"
                        + "Show "
                        + fileName.substring(0, fileName.length() / 2)
                        + "...</button>"
                        + "</form>"
                        + "</li>");
            }
            // Print the closing tag of the list and HTML footer
            out.println("</ul>");
            out.println(HTML_FOOTER);
        }
    }

    /**
     * Validate the file extension of string to be image (.jpg .jpeg .png).
     *
     * @param fileName a string to be validated
     * @return a Boolean result containing the result of checking the extension.
     */
    private Boolean validateFileExtension(String fileName) {
        return !fileName.endsWith(".jpg")
                && !fileName.endsWith(".jpeg")
                && !fileName.endsWith(".png");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "An example of HTTP Servlet using Jakarta EE 10 API.";
    }

}
