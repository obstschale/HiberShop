<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Map<String, String[]> parameters = request.getParameterMap();
	for(String parameter : parameters.keySet()) {
		if(!parameter.toLowerCase().startsWith("submitbutton")) {
	        String[] values = parameters.get(parameter);
	        //your code here
	        %> <p> <%= values[0] %> </p> <%
		}
	}
%>
</body>
</html>