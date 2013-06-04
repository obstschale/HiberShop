<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="links.html"%>
<title>Album Confirmation</title>
</head>
<body>
	<div class="pure-g">

		<div class="pure-u-1-2 sidebar">
			<%@ include file="navigation.html"%>
		</div>

		<div class="pure-u-1-2 main">
			<h1>Album Confirmation</h1>
			<p>Folgender Album name wurde an diese Seite geschickt: <b>${albumdata.name}</b>.</p>
			<p>Folgender Interpret wurde an diese Seite geschickt: <b>${albumdata.interpret}</b>.</p>
			<p>Folgendes Cover wurde an diese Seite geschickt: <b>${albumdata.cover}</b>.</p>
			
			<form action="Controller">
				<input type="submit" class="pure-button pure-button-error"
					name="backButton" value="Zurück zu New Album">
				<input type="submit" class="pure-button pure-button-success"
					name="submitButton" value="Weiter: Album speichern">
			</form>
		</div>

	</div>
</body>
</html>