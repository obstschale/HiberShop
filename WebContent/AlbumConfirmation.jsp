<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album Confirmation</title>
</head>
<body>
<h1>Album Confirmation</h1>
	<p>Folgender Album name wurde an diese Seite geschickt: <b>${param.name}</b>.</p>
	<p>Folgender Interpret wurde an diese Seite geschickt: <b>${param.interpret}</b>.</p>
	<p>Folgendes Cover wurde an diese Seite geschickt: <b>${param.cover}</b>.</p>
		<form action="Controller">
		<input type="hidden" name = "name" value="${param.name}">
		<input type="hidden" name = "interpret" value="${param.interpret}">
		<input type="hidden" name = "conver" value="${param.cover}">
		<input type = "submit" name = "backButton" value="Zurück zu New Album">
		<input type = "submit" name = "submitButton" value="Weiter: Album speichern">
	</form>
</body>
</body>
</html>