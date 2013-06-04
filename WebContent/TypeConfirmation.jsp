<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Type Confirmation</title>
</head>
<body>
<h1>Type Confirmation</h1>
	<p> Folgender Type wurde an diese Seite geschickt: <b>${param.name}</b>.<br>
	<p> Folgendes Icon wurde an diese Seite geschickt: <b>${param.icon}</b>.
	<form action="Controller">
		<input type="hidden" name = "name" value="${param.name}">
		<input type="hidden" name = "icon" value="${param.icon}">
		<input type = "submit" name = "backButton" value="Zurück zu New Type">
		<input type = "submit" name = "submitButton" value="Weiter: Type speichern">
	</form>
</body>
</html>