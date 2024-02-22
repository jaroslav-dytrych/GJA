<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Adapted from http://www.roseindia.net/c-tutorials/example-of-statelfulbean.shtml --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.naming.*"%>
<%@ page import="net.roseindia.ejb3.statefull.example.AccountRemote" %>
<%@ page import="java.lang.Float" %>
<html>
<head>
  <title>Bank Account</title>
</head>

<body>
<%!   
  public AccountRemote account = null;
  float bal = 0;

  public void jspInit() {
    try {
      InitialContext ic = new InitialContext();
      account = (AccountRemote) ic.lookup("java:global/StatefullBean/AccountBean");
      System.out.println("Loaded Account Bean");

    } catch (Exception ex) {
      System.out.println("Error:"
              + ex.getMessage());
    }
  }

  public void jspDestroy() {
    account = null;
  }

%>
<%
  
  try {
    String s1 = request.getParameter("amt");
    String s2 = request.getParameter("group1");

    if (s1 != null && !s1.equals("")) {
      
      Float amt = Float.parseFloat(s1);

      if (s2.equals("dep")) {
        bal = account.deposit(amt.floatValue());
        %>
        <p>Deposited!</p>
        <%
      }
      else if (s2.equals("with")) {
        bal = account.withdraw(amt.floatValue());
        %>
        <p>Withdrawn!</p>
        <%
      }
      else {
        %>
        <p>Please select your choice</p>
        <%
      }
    } else {
      %>
      <br>Please enter the amount<br>
      <%
    }
    %>
    <p>
      The Transaction is complete<br>
      <b>Your Current Balance is:</b> <%= bal%>
    <p>
    <%
  } // end of try
  catch (Exception e) {
    e.printStackTrace();
  }
  %>
  </body>
</html>