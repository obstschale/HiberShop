<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="backend.Cart" %>
<%@ page import="java.io.IOException;" %>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
    <img src="${bild}" />
    	<h2>Warenkorb</h2>
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
						<td>
							<form action="ControllerCart">
								<button class="pure-button  pure-button-error" name="rmid" value="<% out.print(medium.getId()); %>">Entfernen</button><br>
								<button class="pure-button pure-button-active" name="details" value="<% out.print(medium.getId()); %>">Details</button><br>
								<button class="pure-button  pure-button-secondary" name="play" value="<% out.print(medium.getId()); %>">Abspielen</button>
							</form>
						</td>
					</tr>
					<%
				}

				request.getSession().setAttribute("cart", cart);
			%>	    
    	</table>
    	<br>
    	<form action="ControllerCart">
			<button class="pure-button pure-button-warning" name="back" value="back">Zur&uuml;ck</button>
			<button class="pure-button pure-button-error" name="clean" value="empty">Leeren</button>
			<button class="pure-button  pure-button-success" name="print" value="print">Drucken</button>
		</form>
    </div>

</div>

<%@ include file="include/footer.jsp" %>
