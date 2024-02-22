<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "cz.vutbr.fit.knot.gja.number.NumberGuessBean" %>
<jsp:useBean id="numguess" class="cz.vutbr.fit.knot.gja.number.NumberGuessBean" scope="session"/>
<jsp:setProperty name="numguess" property="*"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Number Guesser JSP 2.0</title>
        <link rel="icon" type="image/x-icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Jakarta_ee_logo_schooner_color_stacked_default.svg/1280px-Jakarta_ee_logo_schooner_color_stacked_default.svg.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>

    <body>
        <div class="card w-50 mx-auto my-5 text-center">
            <div class="card-header text-info">
                GJA - Jakarta Number Guesser 2.0 Example (JakartaEE 10)
            </div>
            <div class="card-body">
                <h4 class="card-title"><strong>A Simple Number Guesser Application</strong></h4>
                <p class="card-text lead">Guess number between 1 and 100</p>
                <% if (numguess.getSuccess()) {%>

                Congratulations!  You got it after <strong><%= numguess.getNumGuesses()%></strong> tries.<p>

                    <% numguess.reset();%>

                    <a class="btn btn-primary my-2" href="NumberGuess.jsp">Try again</a>

                    <% } else if (numguess.getNumGuesses() == 0) {%>

                <form method=get>
                    <input class="mx-auto form-control w-50" placeholder="Your Guess" type=text name=guess>
                    <input class="mx-auto btn btn-primary my-2" type=submit value="Submit">
                </form>

                <% } else {%>

                Try <b><%= numguess.getHint()%></b>.
                You have made <%= numguess.getNumGuesses()%> guesses.<p>

                <form method=get>
                    <input class="mx-auto form-control w-50" placeholder="Your Guess" type=text name=guess>
                    <input class="mx-auto btn btn-primary my-2"  type=submit value="Submit">
                </form>

                <% }%>
            </div>
        </div>
    </body>
</html>