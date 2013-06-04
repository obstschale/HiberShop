<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Type Confirmation</title>
</head>
<body>
	<div class="pure-g">
	
	    <div class="pure-u-1-2 sidebar">
	    		<%@ include file="navigation.html" %>
	    </div>
	    
	    <div class="pure-u-1-2 main">
		    <h1>Type Confirmation</h1>
			<p> Folgender Type wurde an diese Seite geschickt: <b>${typedata.name}</b>.<br>
			<p> Folgendes Icon wurde an diese Seite geschickt: <b>${typedata.icon}</b>.
			<form action="Controller">
				<input type = "submit" class="pure-button pure-button-error" name = "backButton" value="Zurück zu New Type">
				<input type = "submit" class="pure-button pure-button-success" name = "submitButton" value="Weiter: Type speichern">
			</form>
	    </div>
	
	</div>
</body>
</html>