<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="model.Medium" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>Insert title here</title>
</head>
<body>

<div class="pure-g">
	
    <div class="pure-u-1-2 sidebar">
    	<%@ include file="navigation.html" %>
    </div>
    
    <div class="pure-u-1-2 main">
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
    	<form action="ControllerMediumDetailed">
			<button class="pure-button pure-button-active" name="back" value="back">Zur&uuml;ck</button><br>
			<button class="pure-button pure-button-disabled" name="buy" value="${mediumdata.id}">Kaufen</button><br>
			<button class="pure-button  pure-button-disabled" name="play">Abspielen</button>
		</form>
    </div>

</div>
</body>
</html>