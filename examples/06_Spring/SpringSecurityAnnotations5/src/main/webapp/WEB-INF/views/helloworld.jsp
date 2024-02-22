<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello World</title>
  </head>
  <body>
    <h1>Title : ${title}</h1>	
    <h1>Message : ${message}</h1>	
    <a href="/SpringSecurityAnnotations/rprotected">Protected</a><br/>
    <a href="/SpringSecurityAnnotations/confidential">Confidential</a>
  </body>
</html>