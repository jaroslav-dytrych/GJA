<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "cz.vutbr.fit.knot.gja.mail.MailBean" %>
<jsp:useBean id="mailBean" class="cz.vutbr.fit.knot.gja.mail.MailBean" scope="session"/>
<jsp:setProperty name="mailBean" property="*"/> 

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
        <div class="card w-50 mx-auto my-5 text-center">
            <div class="card-header text-info">
                GJA - Jakarta Mail 2.0 Example (JakartaEE 10)
            </div>
            <div class="card-body">
                <h4 class="card-title"><strong>A Simple Email Application</strong></h4>
                <p class="card-text lead">This page will send an electronic mail message via the
                    <code>jakarta.mail.Session</code> resource factory that is configured into
                    the JNDI context for this web application.  Before it can be used
                    successfully, you must take note of the following:</p>
                <ul class="border mx-auto p-2 w-75 list-unstyled">
                    <li class="my-2">The default configuration assumes that there is an SMTP server running
                        on <strong>localhost</strong>.  If this is not the case, edit your
                        <code>conf/server.xml</code> file and change the value for the
                        <code>mail.smtp.host</code> parameter to the name of a host that provides
                        SMTP service for your network.</li>
                    <li class="my-2">The application logic assumes that no user authentication is required
                        by your SMTP server before accepting mail messages to be sent.</li>
                </ul>
                <form class="border border-primary rounded p-5" method="post" action="sendmail.jsp">
                    <div class="form-group my-1">
                        <input required type="text" name="mailfrom" size="60" class="form-control"  placeholder="From: Enter email">
                    </div>

                    <div class="form-group my-1">
                        <input required type="text" name="mailto" size="60" class="form-control" placeholder="To: Enter email">
                    </div>

                    <div class="form-group my-1">
                        <input required type="text" name="mailsubject" size="60" class="form-control" placeholder="Subject: Enter subject">
                    </div>

                    <div class="form-group  my-1">
                        <textarea class="form-control" name="mailcontent" rows="10" cols="80"></textarea>
                    </div>

                    <input class="btn my-1 mx-3 btn-primary" type="submit" value="Send">
                    <input class="btn my-1 btn-danger" type="reset" value="Reset">
                </form>
            </div>
        </div>
    </body>
</html>