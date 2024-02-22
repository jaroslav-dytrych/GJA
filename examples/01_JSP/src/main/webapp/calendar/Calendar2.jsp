<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCtype html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CalendarJSP 2.0</title>
        <link rel="icon" type="image/x-icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Jakarta_ee_logo_schooner_color_stacked_default.svg/1280px-Jakarta_ee_logo_schooner_color_stacked_default.svg.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>

    <body>
        <jsp:useBean id="table" scope="session" class="cz.vutbr.fit.knot.gja.calendar.TableBean" />

        <div class="card w-50 mx-auto my-5 text-center">
            <div class="card-header text-info">
                GJA - CalendarJSP 2.0 Example (JakartaEE 10)
            </div>
            <div class="card-body">
                <p class="lead">
                    <%
                      String time = request.getParameter("time");
                    %>
                    Please add event for the following:
                <h3> Date <%= table.getDate()%>
                    Time <%= time%> </h3>
                </p>
                <form method="post" action="Calendar1.jsp">
                    <input name="date" type="hidden" value="current">
                    <input name="time" type="hidden" value="<%= time%>">
                    <input placeholder="Description of the event" name="description" type="text" size="20">
                    <input type="submit" value="Submit">
                </form>
            </div>
        </div>
    </body>
</html>
