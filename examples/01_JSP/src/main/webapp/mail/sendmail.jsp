<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jakarta.mail.*"%>
<%@ page import="jakarta.mail.internet.*,jakarta.activation.*"%>
<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%@ page import = "cz.vutbr.fit.knot.gja.mail.MailBean" %>
<jsp:useBean id="mailBean" class="cz.vutbr.fit.knot.gja.mail.MailBean" scope="session"/>
<jsp:setProperty name="mailBean" property="*"/> 
<%
  String result;
  // Recipient's email ID needs to be mentioned.
  String to = mailBean.getMailto();
  // Sender's email ID needs to be mentioned
  String from = mailBean.getMailfrom();
  // Assuming you are sending email from localhost
  String host = "localhost";
  // Get system properties object
  Properties properties = System.getProperties();
  // Setup mail server
  properties.setProperty("mail.smtp.host", host);
  // Get the default Session object.
  Session mailSession = Session.getDefaultInstance(properties);
  try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(mailSession);
    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));
    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO,
            new InternetAddress(to));
    // Set Subject: header field
    message.setSubject(mailBean.getMailsubject());
    // Send the actual HTML message, as big as you like
    message.setContent(mailBean.getMailcontent(), "text/plain");
    // Send message
    Transport.send(message);
    result = "Sent message successfully....";
  } catch (MessagingException mex) {
    mex.printStackTrace();
    result = "Error: unable to send message....";
  }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mail JSP 2.0</title>
        <link rel="icon" type="image/x-icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Jakarta_ee_logo_schooner_color_stacked_default.svg/1280px-Jakarta_ee_logo_schooner_color_stacked_default.svg.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
    <body>
        <div class="card w-50 mx-auto my-5 text-center">
            <div class="card-header text-info">
                GJA - Jakarta Mail 2.0 Example (JakartaEE 10)
            </div>
            <div class="card-body">
                <h4 class="card-title"><strong>Sent Email using JSP</strong></h4>
                <p class="lead">
                    <%
                      out.println("Result: " + result + "\n");
                    %>
                </p>
            </div>
        </div>
    </body>
</html>