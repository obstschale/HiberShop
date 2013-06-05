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
		    <p>Medium Type: <strong>${mediumdata.type}</strong>.</p>
			<p>Medium Album: <strong>${mediumdata.album}</strong>.</p>
			<p>Titel: <strong>${mediumdata.titel}</strong>.</p>
			<p>Interpret: <strong>${mediumdata.interpret}</strong>.</p>
			<p>L&auml;nge: <strong>${mediumdata.laenge}</strong>.</p>
			<p>Dateigr&ouml;&szlig;e: <strong>${mediumdata.dateigroesse}</strong>.</p>
			<p>Medium: <strong>${mediumdata.pfad}</strong>.</p>
	    </div>
	
	</div>
</body>
</html>
