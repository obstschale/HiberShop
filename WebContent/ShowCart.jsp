<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="view.Cart" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.io.IOException;" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Warenkorb</title>
</head>
<body>
<div class="pure-g">
	
    <div class="pure-u-1-2 sidebar">
    	<%@ include file="navigation.html" %>
    </div>
    
    <div class="pure-u-1-2 main">
    	<h2>Warenkorb</h2>
    	
		<jsp:include page="ErrorText.jsp">
			<jsp:param value="${requestScope.errortext}" name="errortext"/>
		</jsp:include>
    	<table class="pure-table pure-table-bordered">
	    	<thead>
		        <tr>
		            <th>#</th>
		            <th>Titel</th>
		            <th>Interpret</th>
		            <th>Album</th>
		            <th>L&auml;nge</th>
		            <th>Dateigr&ouml;&szlig;e</th>
		            <th>Type</th>
		            <th></th>
		        </tr>
		    </thead>
			<%
				Cart cart;
				try {
					if (request.getSession().getAttribute("cart") != null) {
						cart = (Cart) request.getSession().getAttribute("cart");
					} else {
						cart = new Cart();
						request.getSession().setAttribute("cart", cart);
					}
				} catch (NullPointerException ex) {
					System.err.println("Failed to create cart object." + ex);
				    throw new ExceptionInInitializerError(ex);
				}
				
				for (Medium medium : cart.getMedia()) {
				    %>
				    <tr>
						<td><% out.print(medium.getId()); %></td>
						<td><% out.print(medium.getTitel()); %></td>
						<td><% out.print(medium.getInterpret()); %></td>
						<td><% out.print(medium.getAlbum().getName()); %></td>
						<td><% out.print(Float.toString(medium.getLaenge())); %></td>
						<td><% out.print(medium.getDateigroesseMB()); %></td>
						<td><% out.print(medium.getType().getName()); %></td>
						<td><a href="ControllerCart?rmid=<% out.print(medium.getId()); %>" class="pure-button pure-button-error">Remove Item</a></td>
					</tr>
					<%
				}

				request.getSession().setAttribute("cart", cart);
			%>	    
    	</table>
    	<form action="ControllerCart">
			<button class="pure-button pure-button-active" name="back" value="back">Zur&uuml;ck</button>
			<button class="pure-button pure-button-active" name="clean" value="empty">Leeren</button>
			<button class="pure-button  pure-button-active" name="print" value="print">Drucken</button>
		</form>
    </div>

</div>
</body>
</html>