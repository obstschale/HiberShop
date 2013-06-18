<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Album" %>
<%@ page import="java.io.IOException;" %>

<%@ include file="include/header.jsp" %>

<div class="pure-g">
	    
    <div class="pure-u-1-2 main">
		<p>Album Name: <b>${albumdata.name}</b>.</p>
		<p>Interpret: <b>${albumdata.interpret}</b>.</p>
		<p>Cover: <br>
		<p>
		<%
			Album album;
			try {
				album = (Album) request.getSession().getAttribute("albumdata");
				if(album.getCover() != null) {
					%>
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
    </div>

</div>
</body>
</html>

<%@ include file="include/footer.jsp" %>
