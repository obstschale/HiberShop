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
	<p>Folgender Type wurde an diese Seite geschickt: <b>${param.type}</b>.</p>
	<p>Folgender Album Name wurde an diese Seite geschickt: <b>${param.album}</b>.</p>
	<p>Folgender Titel wurde an diese Seite geschickt: <b>${param.titel}</b>.</p>
	<p>Folgender Interpret wurde an diese Seite geschickt: <b>${param.interpret}</b>.</p>
	<p>Folgende L&auml;nge wurde an diese Seite geschickt: <b>${param.laenge}</b>.</p>
	<p>Folgende Dateigr&ouml;&szlig;e wurde an diese Seite geschickt: <b>${param.dateigroesse}</b>.</p>
	<p>Folgendes Medium wurde an diese Seite geschickt: <b>${param.medium}</b>.</p>
	<form action="Controller">
		<input type="hidden" name = "type" value="${param.type}">
		<input type="hidden" name = "album" value="${param.album}">
		<input type="hidden" name = "titel" value="${param.titel}">
		<input type="hidden" name = "interpret" value="${param.interpret}">
		<input type="hidden" name = "laenge" value="${param.laenge}">
		<input type="hidden" name = "dateigroesse" value="${param.dateigroesse}">
		<input type="hidden" name = "medium" value="${param.medium}">
		<input type = "submit" name = "backButton" value="Zurück zu New Medium">
		<input type = "submit" name = "submitButton" value="Weiter: Medium speichern">
	</form>
</body>
</body>
</html>