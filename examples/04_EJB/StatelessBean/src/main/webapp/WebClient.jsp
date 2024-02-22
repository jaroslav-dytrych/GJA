
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Adapted from http://www.roseindia.net/ejb/examples-of-StatelessBean.shtml --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.naming.*"%>
<%@ page import="net.roseindia.ejb3.stateless.example.CalculatorRemote"%>
<html>
<head>
  <title>Calculator</title>
</head>

<body>
<%!   private CalculatorRemote calculator = null;
  float result = 0;

  public void jspInit() {
    try {

      InitialContext ic = new InitialContext();
      //calculator = (CalculatorRemote)ic.lookup(CalculatorRemote.class.getName());
      calculator = (CalculatorRemote) ic.lookup("java:global/StatelessBean/CalculatorBean");

      System.out.println("Loaded Calculator Bean");
      //CalculatorBean

    } catch (Exception ex) {
      System.out.println("Error:" + ex.getMessage());
    }
  }

  public void jspDestroy() {
    calculator = null;
  }
%>


<%

  try {
    String s1 = request.getParameter("num1");
    String s2 = request.getParameter("num2");
    String s3 = request.getParameter("group1");

    System.out.println(s3);

    if (s1 != null && !s1.equals("") && s2 != null && !s2.equals("") && s3 != null) {
      Float num1 = new Float(s1);
      Float num2 = new Float(s2);

      if (s3.equals("add")) {
        result = calculator.add(num1.floatValue(), num2.floatValue());
      } else if (s3.equals("sub")) {
        result = calculator.subtract(num1.floatValue(), num2.floatValue());
      } else if (s3.equals("multi")) {
        result = calculator.multiply(num1.floatValue(), num2.floatValue());
      } else if (s3.equals("div")) {
        result = calculator.division(num1.floatValue(), num2.floatValue());
      } else {
        %>
        <p>
          <b>Operation was not selected!</b>
        <p>
        <%
      }
      %>
      <p>
        <b>The result is:</b> <%= result%>
      <p>
      <%
    } else {
      %>
      <p>
        <b>Form was not filled in properly.</b>
      <p>
      <%  
    }
  }  // end of try
  catch (Exception e) {
    e.printStackTrace();
    //result = "Not valid";
  }
  %>
  </body>
</html>