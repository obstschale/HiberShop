<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="java.io.IOException;" %>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
		<%
		Medium medium;
		try {
			
			medium = (Medium) request.getSession().getAttribute("mediumdata");
			String pfad;	
			if (medium.getPfad() != null) {
				pfad = medium.getPfad();
				out.println("<h2>" + medium.getTitel() + " von " + medium.getInterpret() + "</h2>");
			} else {
				pfad = "";
			}
			if (pfad.matches("(.*)(.mp3|.ogg|.midi|.wav)$")) {
				%>
				<audio preload controls>
				       <source src="<%= pfad %>">
				</audio>
				<%
			} else if (pfad.matches("(.*)(.mp4)$")) {
				%>
				<video width="320" height="240" src= "<%= pfad %>" autobuffer controls>
				   <div class="video-fallback">
				     <br>Sie ben&ouml;tigen einen Browser, der HTML5 unterst&uuml;zt.
					</div>
				</video>
				<%
			} else {
				%><p>Das ist jetzt aber doof. Sieht so aus, also ob es f&uuml;r diesen Track keine Datei zum abspielen gibt.</p><%
			}
		} catch (NullPointerException ex) {
			System.err.println("Failed to create audio/video object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		%>
		<br>
		<form action="ControllerAllMedia" method="post">
			<input type="submit" class="pure-button pure-button-secondary" name="backPlay" value="Zur&uuml;ck" />
		</form>
	</div>
</div>

<%@ include file="include/footer.jsp" %>
