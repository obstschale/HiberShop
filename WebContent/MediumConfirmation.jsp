<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Album Confirmation</title>
</head>
<body>
	<div class="pure-g">
	
	    <div class="pure-u-1-2 sidebar">
	    		<%@ include file="navigation.html" %>
	    </div>
	    
	    <div class="pure-u-1-2 main">
		    <h1>Medium Confirmation</h1>
			<p>Folgender Type wurde an diese Seite geschickt: <b>${mediumdata.type}</b>.</p>
			<p>Folgender Album Name wurde an diese Seite geschickt: <b>${mediumdata.album}</b>.</p>
			<p>Folgender Titel wurde an diese Seite geschickt: <b>${mediumdata.titel}</b>.</p>
			<p>Folgender Interpret wurde an diese Seite geschickt: <b>${mediumdata.interpret}</b>.</p>
			<p>Folgende L&auml;nge wurde an diese Seite geschickt: <b>${mediumdata.laenge}</b>.</p>
			<p>Folgende Dateigr&ouml;&szlig;e wurde an diese Seite geschickt: <b>${mediumdata.dateigroesse}</b>.</p>
			<p>Folgendes Medium wurde an diese Seite geschickt: <b>${mediumdata.pfad}</b>.</p>
			<form action="Controller">
				<input type = "submit" class="pure-button pure-button-error" name = "backButton" value="Zurück zu New Medium">
				<input type = "submit" class="pure-button pure-button-success" name = "submitButton" value="Weiter: Medium speichern">
			</form>
	    </div>
	
	</div>
</body>
</html>
