<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <%@ page language="java" import="cz.vutbr.fit.knot.gja.calendar.*" %>
        <jsp:useBean id="table" scope="session" class="cz.vutbr.fit.knot.gja.calendar.TableBean" />

        <%
          table.processRequest(request);
          if (table.getProcessError() == false) {
        %>
        <div class="card w-50 mx-auto my-5 text-center">
            <div class="card-header text-info">
                GJA - CalendarJSP 2.0 Example (JakartaEE 10)
            </div>
            <div class="card-body">
                <table class="table">
                    <tr>
                        <td> <a href="Calendar1.jsp?date=prev"> Previous date </a>
                        <td> Plan for: <strong><%= table.getDate()%></strong></td>
                        <td> <a href="Calendar1.jsp?date=next"> Next date </a>
                    </tr>
                </table>
                <form method="POST" ACTION="Calendar1.jsp">
                    <table class="table" >
                        <tr>
                            <th> Time </th>
                            <th> Event </th>
                        </tr>
                        <%
                          for (int i = 0; i < table.getEntries().getRows(); i++) {
                            cz.vutbr.fit.knot.gja.calendar.Entry entr = table.getEntries().getEntry(i);
                        %>
                        <tr>
                            <td> 
                                <a href="Calendar2.jsp?time=<%= entr.getHour()%>">
                                    <%= entr.getHour()%> </a>
                            </td>
                            <td>
                                <%= entr.getDescription()%>
                            </td> 
                        </tr>
                        <%
                          }
                        %>
                    </table>
                </form>

                <%
                } else {
                %>
                <script>alert("You must enter your name and email address correctly.");</script>
                <%  }
                %>
            </div>
            <div class="card-footer text-muted ">
                <%= table.getName()%> : <%= table.getEmail()%> 
            </div>
        </div>
    </body>
</html>
