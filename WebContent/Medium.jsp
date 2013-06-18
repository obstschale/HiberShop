<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Medium" %>

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
		        </tr>
		    </thead>
			<tr>
				<td>${mediumdata.id}</td>
				<td>${mediumdata.titel}</td>
				<td>${mediumdata.interpret}</td>
				<td>${album}</td>
				<td>${mediumdata.laenge}</td>
				<td>${dateigroesse}</td>
				<td>${type}</td>
			</tr>
    	</table>
    	<form action="ControllerMediumDetailed" method="post">
			<button class="pure-button pure-button-warning" name="back" value="back">Zur&uuml;ck</button>
			<button class="pure-button pure-button-success" name="buy" value="${mediumdata.id}">Kaufen</button>
			<button class="pure-button  pure-button-secondary" name="play" value="${mediumdata.id}">Abspielen</button>
		</form>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
