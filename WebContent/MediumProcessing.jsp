<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="java.io.IOException" %>
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
	    	<%
		    Medium medium;
			try {
				medium = (Medium) request.getSession().getAttribute("mediumdata");
		    %>
		    <p>Medium Type: <strong><%= medium.getType().getName() %></strong>.</p>
			<p>Medium Album: <strong><%= medium.getAlbum().getName() %></strong>.</p>
			<p>Titel: <strong>${mediumdata.titel}</strong>.</p>
			<p>Interpret: <strong>${mediumdata.interpret}</strong>.</p>
			<p>L&auml;nge: <strong>${mediumdata.laenge}</strong>.</p>
			<p>Dateigr&ouml;&szlig;e: <strong>${mediumdata.dateigroesse}</strong>.</p>
			<p>
			<%
					medium = (Medium) request.getSession().getAttribute("mediumdata");
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
						Leider wurde kein Medium hochgeladen.
						<%
					}
				} catch (NullPointerException ex) {
					System.err.println("Failed to create audio/video object." + ex);
					throw new ExceptionInInitializerError(ex);
				}
			
			%>
			</p>
	    </div>
	
	</div>
</body>
</html>
