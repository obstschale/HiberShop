<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"  %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.Album" %>
<%@ page import="model.Type" %>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
    <h2>Neues Medium</h2>
    
		<form class="pure-form-aligned pure-form" action="ControllerMedium" enctype="multipart/form-data" method="post">
			
			<div class="pure-control-group">
	            <label for="titel">Titel</label>
				<input type="text" name="titel" placeholder="Titel" required/>
			</div>
			
			<div class="pure-control-group">
	            <label for="interpret">Interpret</label>
				<input type="text" name="interpret" placeholder="Interpret" required/>
			</div>
			
			<div class="pure-control-group">
	            <label for="album">Album</label>
	            <select name="album">
					<option value="NULL" disabled>W&auml;hle ein Album</option>
					<option value="NULL">Kein Album</option>
					
					<%! @SuppressWarnings("unchecked") %>
					<%
						if(request.getAttribute("albumList") != null) {
							List<Album> albums = (List<Album>) request.getAttribute("albumList");
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
						} else {
							%>
							<option disabled>Lieder kein Album vorhanden :(</option>
							<%
						}
					%>
				</select>
	        </div>
			
			<div class="pure-control-group">
	            <label for="type">Type</label>
				<select name="type">
					<option value="NULL" disabled>W&auml;hle einen Typen</option>
					<%
						if (request.getAttribute("typeList") != null) {
							List<Type> types = (List<Type>) request.getAttribute("typeList");
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
						} else {
							%>
							<option disabled>Bitte zuerst einen Type erstellen!</option>
							<%
						}
					%>
				</select>
			</div>
			
			<div class="pure-control-group">
	            <label for="laenge">Spieldauer</label>
				<input type="number" name="laenge" placeholder="L&auml;nge" step="any" min="0" max="20" required/>
			</div>
			
			<div class="pure-control-group">
	            <label for="pfad">Datei</label>
				<input type="file" name="pfad" value="Medium hochladen" required/>
			</div>
			
			<div class="pure-controls">
				<input type="submit" class="pure-button pure-button-success" name="confirmButton" value="Neues Medium" />		
				<input type="reset" class="pure-button pure-button-error" name="cancle" value="Abbrechen" />
			</div>
		</form>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
