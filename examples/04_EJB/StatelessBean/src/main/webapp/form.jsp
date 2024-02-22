<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Adapted from http://www.roseindia.net/ejb/examples-of-StatelessBean.shtml --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Calculator</title>
  </head>

  <h1>Calculator</h1>
  <hr>

  <form action="WebClient.jsp" method="POST">
    <p>Enter first value:
      <input type="text" name="num1" size="25">
    </p>
    <br>
    <p>Enter second value:
      <input type="text" name="num2" size="25">
    </p>
    <br>

    <b>Seclect your choice:</b><br>
    <input type="radio" name="group1" value ="add" id="add">
      <label for="add">Addition</label><br>
    <input type="radio" name="group1" value ="sub" id="sub">
      <label for="sub">Subtraction</label><br>
    <input type="radio" name="group1" value ="multi" id="multi">
      <label for="multi">Multiplication</label><br>
    <input type="radio" name="group1" value ="div" id="div">
      <label for="div">Division</label><br>
    <p>
      <input type="submit" value="Submit">
      <input type="reset" value="Reset">
    </p>

  </form>

</body>
</html>