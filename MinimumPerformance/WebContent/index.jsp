<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<h1>Performance application</h1>
<body>
   <form action="MainApplicationServlet" method="post">
      <input type="hidden" id="webTime" name="webTime" value="<%=(new Date()).getTime()%>">
      <input type="submit" value ="Execute app">
   </form>
   
</body>
</html>