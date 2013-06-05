<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="java.io.IOException;" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Play Medium</title>
</head>
<body>
<div class="pure-g">
	<div class="pure-u-1-2 sidebar">
   		<%@ include file="navigation.html" %>
    </div>
    
    <div class="pure-u-1-2 main">
		<h2>Play Medium</h2>
		<jsp:include page="ErrorText.jsp">
			<jsp:param value="${requestScope.errortext}" name="errortext"/>
		</jsp:include>
		<%
		try {
			Medium medium = (Medium) request.getSession().getAttribute("medium");
			String pfad;	
			if (medium.getPfad() != null)
				pfad = medium.getPfad();
			else
				pfad = "";
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
				     <br>Sie benötigen einen Browser, der HTML5 unterstützt.
				</div> </video>
				<%
			} else {
				%><p>Das ist jetzt aber doof. Sieht so aus, also ob es für diesen Track keine Datei zum abspielen gibt.</p><%
			}
		} catch (NullPointerException ex) {
			System.err.println("Failed to create audio/video object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		%>
		<form action="ControllerMediumDetailed">
			<input type="submit" class="pure-button pure-button-secondary" name="back" value="Zur&uuml;ck" />
		</form>
	</div>
</div>
</body>
</html>