<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"  %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.Medium" %>
<%@ page import="model.Type" %>
<%@ page import="model.Album" %>
<%@ page import="backend.DatabaseQueries" %>
<%@ page import="hibernate.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction" %>

<%@ include file="include/header.jsp" %>

<div class="pure-g">
    <div class="pure-u-1-2 main">
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
		            <th>Cover</th>
		            <th></th>
		        </tr>
		    </thead>
			<%! @SuppressWarnings("unchecked") %>
			<%
			if(request.getAttribute("allMedia") != null) {
				List<Medium> media = (List<Medium>) request.getAttribute("allMedia");
				
				Iterator<Medium> iterM = media.iterator();
				
				while (iterM.hasNext()) {
					Medium medium = iterM.next();
					String albumName = "-kein Album-";
					if (medium.getAlbum() != null) {
						albumName = medium.getAlbum().getName();
					}
					%>
					<tr>
						<td><% out.print(medium.getId()); %></td>
						<td><% out.print(medium.getTitel()); %></td>
						<td><% out.print(medium.getInterpret()); %></td>
						<td><%= albumName %></td>
						<td><% out.print(Float.toString(medium.getLaenge())); %></td>
						<td><% out.print(medium.getDateigroesseMB()); %></td>
						<td><% out.print(medium.getType().getName()); %></td>
						<td>
						<%
						if (medium.getAlbum()!= null) {
							%><img src="<% out.print(medium.getAlbum().getCover()); %>" width="50" alt="Cover Bild"/><%
						}
						%>
						</td>
						<td>
							<form action="ControllerAllMedia" method="post">
									<button class="pure-button  pure-button-success pure-button-xsmall" name="buy" value="<% out.print(medium.getId()); %>">Kaufen</button><br>
									<button class="pure-button pure-button-active pure-button-xsmall" name="details" value="<% out.print(medium.getId()); %>">Details</button><br>
									<button class="pure-button  pure-button-secondary pure-button-xsmall" name="play" value="<% out.print(medium.getId()); %>">Abspielen</button>
							</form>
						</td>
					</tr>
					<%
				}
			}
			%>			    
    	</table>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
