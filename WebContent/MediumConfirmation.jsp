<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="java.io.IOException" %>
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
		    <%
		    Medium medium;
			try {
				medium = (Medium) request.getSession().getAttribute("mediumdata");
		    %>
			<p>Folgender Type wurde an diese Seite geschickt: <b><%= medium.getType().getName() %></b>.</p>
			<p>Folgender Album Name wurde an diese Seite geschickt: <b><%= medium.getAlbum().getName() %></b>.</p>
			<p>Folgender Titel wurde an diese Seite geschickt: <b>${mediumdata.titel}</b>.</p>
			<p>Folgender Interpret wurde an diese Seite geschickt: <b>${mediumdata.interpret}</b>.</p>
			<p>Folgende L&auml;nge wurde an diese Seite geschickt: <b>${mediumdata.laenge}</b>.</p>
			<p>Folgende Dateigr&ouml;&szlig;e wurde an diese Seite geschickt: <b>${mediumdata.dateigroesse}</b>.</p>
			
			<p>
			<%
				
					if(medium.getPfad() != null) {
						if(medium.getPfad().matches("(.*)(.mp3|.ogg|.midi|.wav)$")) {
							%>
							Folgender Song  wurde an diese Seite geschickt: <br>
							<audio preload controls>
				       			<source src="${mediumdata.pfad}">
							</audio>
							<%
						} else if(medium.getPfad().matches("(.*)(.mp4)$")) {
							%>
							Folgendes Video  wurde an diese Seite geschickt: <br>
							<video width="320" height="240" src= "${mediumdata.pfad}" autobuffer controls>
							   <div class="video-fallback">
							     <br>Sie benötigen einen Browser, der HTML5 unterstützt.
								</div>
							</video>
							<%
						}
						
					} else {
						%>
						Leider wurde kein Coverbild hochgeladen.
						<%
					}
				} catch (NullPointerException ex) {
					System.err.println("Failed to create audio/video object." + ex);
					throw new ExceptionInInitializerError(ex);
				}
			
			%>
			</p>
			
			<form action="Controller">
				<input type = "submit" class="pure-button pure-button-error" name = "backButton" value="Zur&uuml;ck zu New Medium">
				<input type = "submit" class="pure-button pure-button-success" name = "submitButton" value="Weiter: Medium speichern">
			</form>
	    </div>
	
	</div>
</body>
</html>
