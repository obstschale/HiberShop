<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
	<div class="pure-u-1-2 main">
		<h2>Statistiken</h2>
		
		<form class="pure-form-aligned pure-form"  action="ControllerStatistics" method="post">
			
			<div class="pure-control-group">
				<label  for="limit">Wie oft sollte ein Titel gekauft worden sein?</label>
				<input type="number" value="1" name="limit" min="0"/>
			</div>
			
			<div class="pure-control-group">
				<label>Wie viele Titel sollen in TopPlayed angezeigt werden?</label>
				<input type="number" value="5" name="max" min="0" />
			</div>
			
			<div class="pure-controls">
				<input type="submit" class="pure-button pure-button-secondary" name="stats" value="Statistiken Anzeigen" />
			</div>
		</form>
		<%! @SuppressWarnings("unchecked") %>
		<%
		List<Medium> topBought = null;
		List<Medium> topPlayed = null;

		try {
			if (request.getAttribute("topBought") != null) {
				%>
				<h3>Am h&auml;ufigsten gekauft</h3>
				<table class="pure-table pure-table-bordered">
		    	<thead>
			        <tr>
			            <th>Titel</th>
			            <th>Interpret</th>
			            <th>Album</th>
			            <th>Gekauft</th>
			        </tr>
			    </thead>
				<%
				topBought = (List<Medium>) request.getAttribute("topBought");
				Iterator<Medium> iter1 = topBought.iterator();
				int i = 1;
				while (iter1.hasNext()) {
					Medium medium = iter1.next();
					
					String albumName = "";
					if(medium.getAlbum() != null){
						albumName = medium.getAlbum().getName();
					}
					
					out.println("<tr>");
					out.println("<td>" + i + "</td>");
					out.println("<td>" + medium.getTitel() + "</td>");
					out.println("<td>" + albumName + "</td>"); 
					out.println("<td>" + medium.getGekauft() + "</td>");
					out.println("</tr>");
					i++;
				}
				%>
				</table>
				<%
			}
			
			if (request.getAttribute("topPlayed") != null) {
				%>
				<h3>Am h&auml;ufigsten angeh&ouml;rt</h3>
				<table class="pure-table pure-table-bordered">
		    	<thead>
			        <tr>
			            <th>Titel</th>
			            <th>Interpret</th>
			            <th>Album</th>
			            <th>Angeh&ouml;rt</th>
			        </tr>
			    </thead>
				<%
				topPlayed = (List<Medium>) request.getAttribute("topPlayed");
				Iterator<Medium> iter2 = topPlayed.iterator();
				int i = 1;
				while (iter2.hasNext()) {
					Medium medium = iter2.next();

					String albumName = "";
					if(medium.getAlbum() != null){
						albumName = medium.getAlbum().getName();
					}
					
					out.println("<tr>");
					out.println("<td>" + i + "</td>");
					out.println("<td>" + medium.getTitel() + "</td>");
					out.println("<td>" + albumName + "</td>"); 
					out.println("<td>" + medium.getAngehoert() + "</td>");
					out.println("</tr>");
					i++;
				}
				%>
				</table>
				<%
			}
		} catch (NullPointerException ex) {
			System.err.println("Failed to create statistics. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
		%>
	</div>
</div>

<%@ include file="include/footer.jsp" %>
