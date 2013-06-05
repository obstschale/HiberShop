<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Insert title here</title>
</head>
<body>
	<div class="pure-g">
	
	    <div class="pure-u-1-2 sidebar">
	    		<%@ include file="navigation.html" %>
	    </div>
	    
	    <div class="pure-u-1-2 main">
			<p>Album Name: <b>${albumdata.name}</b>.</p>
			<p>Interpret: <b>${albumdata.interpret}</b>.</p>
			<p>Cover: <b>${albumdata.cover}</b>.</p>
	    </div>
	
	</div>
</body>
</html>