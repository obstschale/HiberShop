<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"  %>
<%@ page import="model.Medium" %>
<%@ page import="model.Type" %>
<%@ page import="model.Album" %>
<%@ page import="view.DatabaseQueries" %>
<%@ page import="hibernate.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Alle Mediendateien</title>
</head>
<body>
<div class="pure-g">
	
	    <div class="pure-u-1-2 sidebar">
	    		<%@ include file="navigation.html" %>
	    </div>
	    
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
			        </tr>
			    </thead>
				<%
					Session ses;
					Transaction transaction;
					SessionFactory sf = HibernateUtil.getSessionFactory();
					DatabaseQueries query = new DatabaseQueries();
					ses = sf.getCurrentSession();
					transaction = ses.beginTransaction();
					
					List<Medium> media = query.getAllMedia(ses, transaction);
					Iterator<Medium> iterM = media.iterator();
					
					while (iterM.hasNext()) {
						Medium medium = iterM.next(); %>
						<tr>
							<td><% out.print(medium.getId()); %></td>
							<td><% out.print(medium.getTitel()); %></td>
							<td><% out.print(medium.getInterpret()); %></td>
							<td><% out.print(medium.getAlbum().getName()); %></td>
							<td><% out.print(Float.toString(medium.getLaenge())); %></td>
							<td><% out.print(Float.toString(medium.getDateigroesse())); %></td>
							<td><% out.print(medium.getType().getName()); %></td>
						</tr>
						<%
					}
	
					transaction.commit();
				%>			    
	    	</table>
	    </div>
	
	</div>
</body>
</html>