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
</body>
</body>
</html>