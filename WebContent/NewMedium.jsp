<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"  %>
<%@ page import="model.Album" %>
<%@ page import="model.Type" %>
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
	<title>New Medium</title>
</head>
<body>
	<div class="pure-g">
	
	    <div class="pure-u-1-2 sidebar">
	    		<%@ include file="navigation.html" %>
	    </div>
	    
	    <div class="pure-u-1-2 main"> 
			<form action="Controller">
				<jsp:include page="ErrorText.jsp">
					<jsp:param value="${requestScope.errortext}" name="errortext"/>
				</jsp:include>
				<br>
				<select name="album">
					<option value="NULL">W&auml;hle ein Album</option>
					<%
						Session ses;
						Transaction transaction;
						SessionFactory sf = HibernateUtil.getSessionFactory();
						DatabaseQueries query = new DatabaseQueries();
						ses = sf.getCurrentSession();
						transaction = ses.beginTransaction();
						
						List<Album> albums = query.getAllAlbums(ses, transaction);
						Iterator<Album> iter1 = albums.iterator();
						while (iter1.hasNext()) {
							Album album = iter1.next();
							String name = album.getName();
							int id = album.getId(); %>
							<option value="<% out.print(id); %>">
							<% out.print(name); %>
							</option>
							<%
						}

						transaction.commit();
						
					%>
				</select><br>
				<select name="type">
					<option value="NULL">W&auml;hle einen Typen</option>
					<%
						ses = sf.getCurrentSession();
						transaction = ses.beginTransaction();
						
						List<Type> types = query.getAllTypes(ses, transaction);
						Iterator<Type> iter2 = types.iterator();
						while (iter2.hasNext()) {
							Type type = iter2.next();
							String name = type.getName();
							int id = type.getId(); %>
							<option value="<% out.print(id); %>">
							<% out.print(name); %>
							</option>
							<%
						}
						
						transaction.commit();
					%>
				</select><br>
				<input type="text" name="titel" placeholder="Titel" /><br>
				<input type="text" name="interpret" placeholder="Interpret" /><br>
				<input type="number" name="laenge" placeholder="L&auml;nge" step="any" min="0" max="20" /><br>
				<input type="number" name="dateigroesse" placeholder="Dateigr&ouml;&szlig;e" step="any" min="0" max="100" /><br>
				<input type="file" name="pfad" value="Medium hochladen" /><br>
				<br>
				<input type="submit" class="pure-button pure-button-success" name="confirmButton" value="Neues Medium" />		
				<input type="reset" class="pure-button pure-button-error" name="cancle" value="Abbrechen" />
			</form>
	    </div>
	
	</div>
</body>
</html>