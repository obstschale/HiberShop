<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Album" %>
<%@ page import="java.io.IOException;" %>

<%@ include file="include/header.jsp" %>

<div class="pure-g">

	<div class="pure-u-1-2 main">
		<h1>Album Confirmation</h1>
		<p>Folgender Album name wurde an diese Seite geschickt: <b>${albumdata.name}</b>.</p>
		<p>Folgender Interpret wurde an diese Seite geschickt: <b>${albumdata.interpret}</b>.</p>
		<p>
		<%
			Album album;
			try {
				album = (Album) request.getSession().getAttribute("albumdata");
				if(album.getCover() != null) {
					%>
					Folgendes Cover wurde an diese Seite geschickt: <br>
					<img src="${albumdata.cover}" alt="cover of album" width="200" />
					<%
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
		
		<form action="ControllerAlbum" method="post">
			<input type="submit" class="pure-button pure-button-error"
				name="backButton" value="Zur&uuml;ck zu New Album">
			<input type="submit" class="pure-button pure-button-success"
				name="submitButton" value="Weiter: Album speichern">
		</form>
	</div>

</div>

<%@ include file="include/footer.jsp" %>