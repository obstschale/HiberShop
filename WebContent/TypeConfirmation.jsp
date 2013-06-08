<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Type" %>
<%@ page import="java.io.IOException;" %>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
	    <h1>Type Confirmation</h1>
		<p> Folgender Type wurde an diese Seite geschickt: <b>${typedata.name}</b>.</p>
		<p>
		<%
			Type type;
			try {
				type = (Type) request.getSession().getAttribute("typedata");
				if(type.getIcon() != null) {
					%>
					Folgendes Icon wurde an diese Seite geschickt: <br>
					<img src="${typedata.icon}" alt="icon of type" width="25" />
					<%
				} else {
					%>
					Leider wurde kein Icon hochgeladen.
					<%
				}
			} catch (NullPointerException ex) {
				System.err.println("Failed to create audio/video object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		
		%>
		</p>
		<form action="ControllerType" method="post">
			<input type = "submit" class="pure-button pure-button-error" name = "backButton" value="Zurück zu New Type">
			<input type = "submit" class="pure-button pure-button-success" name = "submitButton" value="Weiter: Type speichern">
		</form>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
