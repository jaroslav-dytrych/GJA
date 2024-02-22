<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Bank Account</title>
</head>

<body>

  <h1>Bank Transaction Request Form</h1>

  <form action="WebClient.jsp" method="POST">
    <p>Enter amount of money:
      <input type="text" name="amt" size="25">
    </p>
    <br>

    <b>Seclect your choice:</b><br>
    <input type="radio" name="group1" value ="dep" id="dep">
      <label for="dep">Deposit</label><br>
    <input type="radio" name="group1" value ="with" id="with">
      <label for="dep">Withdraw</label><br>
    <p>
      <input type="submit" value="Submit">
      <input type="reset" value="Reset">
    </p>

  </form>

</body>
</html>