package com.vut.fit.gja.servlet_jsp_example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * An example of HTTP Servlet using Jakarta EE 10 API. This is an easy random
 * number guesser game, using Cookies for simple session management. The purpose
 * of this example is to showcase a servlet above "Hello World" that utilizes
 * simple session management through cookies - and to showcase why it's not 
 * always a good idea to use "simple" servlets for HTML rendering.
 *
 * @author xbarta47
 */
public class RandomNumGuessSrv extends HttpServlet {

    // Specify explicit serialVersionUID for correct serialization. 
    private static final long serialVersionUID = 42L;

    // Number guessed by user, number of guesses, random number
    private int numGuessed, numGuesses, secretNumber, score, highestScore;
    private Random random; // Instance of the Random class    
    private String username; // User's score and name

    // HTML header and footer.
    private static final String HTML_HEADER = "<!DOCTYPE html>\n"
            + "<html lang=\"en\">\n"
            + "\n"
            + "<head>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    <title>Random Number Guesser</title>\n"
            + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
            + "        integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\n"
            + "</head>\n"
            + "\n"
            + "<body>";

    private static final String HTML_FOOTER = "</div><div class=\"card opacity-75 w-50 py-4 mx-auto my-5 text-center\">\n"
            + "        To test PUT and DELETE HTTP requests, use cURL or some API testing program such as Postman.\n"
            + "        <pre><code>$ curl -X PUT http://localhost:8080/servlet_jsp_example/ExampleServlet    # restart the game\n"
            + "      $ curl -X DELETE http://localhost:8080/servlet_jsp_example/ExampleServlet # ... and delete cookies</code></pre>\n"
            + "    </div>\n"
            + "</body>\n"
            + "</html>";

    /**
     *
     * Default no-arg constructor.
     */
    public RandomNumGuessSrv() {
        super();
    }

    /**
     * Override the parent's init() method. Use it for initializing of variables
     * needed for the game.
     *
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init() throws ServletException {
        // Generate a random number between 1 and 10
        random = new Random();
        secretNumber = random.nextInt(10) + 1;
        // Keep track of the number of guesses the user has made
        numGuesses = 1; // Number of guesses in a game
        numGuessed = 100; // Default guessed number.
        username = ""; // Username of the player
        score = 0; // Number of correctly guessed numbers without failing
        highestScore = 0;
    }

    /**
     * Override the parent's destroy() method. Shown here only for demonstration
     * purposes. Perform cleanup tasks - we don't have to here, since Java has
     * garbage collection.
     */
    @Override
    public void destroy() {
    }

    /**
     * Processes requests for HTTP <code>GET</code>, <code>POST</code>,
     * <code>PUT</code> and <code>DELETE</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Check if user sent his name through form, if not check if he has
         * it saved in a cookie and use it. 
         */
        String uname = request.getParameter("uname");
        if (uname != null && !uname.isEmpty()) {
            response.addCookie(new Cookie("uname", uname));
            username = uname;
        } else {
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    switch (cookie.getName()) {
                        case "uname":
                            username = cookie.getValue();
                            break;
                        case "uscore":
                            score = Integer.parseInt(cookie.getValue());
                            break;
                        case "uhscore":
                            highestScore = Integer.parseInt(cookie.getValue());
                            break;
                    }
                }
            }
        }

        // Print the webpage dynamically based on the state of the game.
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(HTML_HEADER);
            out.println("<div class=\"card w-50 mx-auto my-5 text-center\">\n"
                    + "        <div class=\"card-header text-info\">\n"
                    + "            GJA - Jakarta EE 10 Servlet Example\n"
                    + "        </div>\n"
                    + "        <div class=\"card-body\">\n"
                    + "            <h4 class=\"card-title\"><strong>Random Number Guesser Game</strong></h4>\n"
                    + "            <p class=\"card-text\">Guess random number from 1 to 10, you win if you guess it correctly in 3 tries.</p>\n"
                    + "            <form action=\"/servlet_jsp_example/ExampleServlet\" method=\"POST\">");
            
            // Print input form. If user specified his name already, disable the input.
            if (username.equals("")) {
                out.println("<input placeholder=\"Username\" required=\"true\" type=\"text\" id=\"uname\" name=\"uname\">");
            } else {
                out.println("<input placeholder=\"" + username + "\" disabled required=\"true\" type=\"text\" id=\"uname\" name=\"uname\">");
            }
            out.println("<input placeholder=\"Guessed Number\" required=\"true\" type=\"text\" id=\"unum\" name=\"unum\"><br><br>");
            out.println("<button  type=\"submit\" class=\"btn btn-primary\">Guess</button>");
            out.println("</form>");
            
            // Print scoreboard
            out.println("            <div class=\"d-flex mx-auto w-50 my-3 flex-row justify-content-center\">\n"
                    + "                <div class=\"p-2 mx-1 flex-fill rounded border border-secondary\">User: " + username + " </div>\n"
                    + "                <div class=\"p-2 mx-1 flex-fill rounded border border-secondary\">Score: " + score + "</div>\n"
                    + "                <div class=\"p-2 mx-1 flex-fill rounded border border-secondary\">Record: " + highestScore + "</div>\n"
                    + "            </div> ");

            // Print different outputs for each HTTP request.
            switch (request.getMethod()) {
                case "DELETE":
                    out.println("<h1>Game has been forcefully restarted. "
                            + "All cookies have been deleted.</h1>");
                    out.println("<h1>Secret number was " + secretNumber + "</h1>");
                    break;
                case "PUT":
                    out.println("<h1>Game has been forcefully restarted.");
                    out.println("<h1>Secret number was " + secretNumber + "</h1>");
                    break;
                case "POST":
                    runGame(request, response);
                    break;
                default:
                    break;
            }
            // Print the HTML footer. 
            out.println("<div class=\"card-footer text-muted \">\n"
                    + "            Number of guesses <div class=\"text-danger\">" + numGuesses + " of 3</div>\n"
                    + "        </div>\n"
                    + "    </div>\n");
            out.println(HTML_FOOTER);
        }
    }

    /**
     * Handle the game logic.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void runGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Parse the HTTP request and get the user guessed number parameter from form.
        numGuessed = Integer.parseInt(request.getParameter("unum"));
        PrintWriter writer = response.getWriter();
        // Increment the number of guesses
        numGuesses++;
        
        // If user guessed more than 3 times, stop the game and restart it.
        if (numGuesses > 3) {
            writer.println("<div class=\"rounded m-3 p-3 border text-danger\"> You ran out of guesses, game restarted.</div>");
            writer.println("</div>");
            init();
            // Restart the score and save the 0 to the user's Cookie that holds score.
            response.addCookie(new Cookie("uscore", Integer.toString(score)));
            return;
        }

        // Check if the guess is correct - if not give a hint to the user.
        if (numGuessed == secretNumber) {
            writer.println("<div class=\"rounded m-3 p-3 border text-success\">Congratulations! You guessed "
                    + "the secret number " + secretNumber + " in " + numGuesses + " guesses.\nRestarting the game. </div>");
            score += 1; // Increase the score and save it to user's Cookie.
            response.addCookie(new Cookie("uscore", Integer.toString(score)));
            // If user's personal record has been passed, save it.
            if (score > highestScore) {
                response.addCookie(new Cookie("uhscore", Integer.toString(score)));
            }
            init(); // Restart the game.
        } else if (numGuessed < secretNumber) {
            writer.println("<div class=\"rounded m-3 p-3 border text-warning\">"
                    + "Your guess " + numGuessed + " is too low. Try again."
                    + "</div>");
        } else {
            writer.println("<div class=\"rounded m-3 p-3 border text-warning\">"
                    + "Your guess  " + numGuessed + " is too high. Try again."
                    + "</div>");

        }
        writer.println("</div>");
    }

    /**
     * Handles the HTTP <code>GET</code> method. Draw the "index" webpage of the
     * game.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Handle the input from user
     * based on the cookies and his inputs.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>PUT</code> method. Restart the game while leaving
     * cookies.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        init();
    }

    /**
     * Handles the HTTP <code>DELETE</code> method. Clear cookies and restart
     * the game to the original state. Remove all cookies.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Restart the game and delete all cookies - progress. 
        init();
        Cookie cookie = new Cookie("uname", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie("uscore", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        cookie = new Cookie("uhscore", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Random number guessing game using Cookies for saving session info.";
    }
}
