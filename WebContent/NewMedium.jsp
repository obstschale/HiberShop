<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				<input type="text" name="type" placeholder="Typen name"  />
				<input type="text" name="album" placeholder="Album name" />
				<input type="text" name="titel" placeholder="Titel name" />
				<input type="text" name="interpret" placeholder="Interpret" />
				<input type="number" name="laenge" step="any" min="0" max="20" />
				<input type="number" name="dateigroesse" step="any" min="0" max="100" />
				<input type="file" name="pfad" value="Medium hochladen" />
				<br>
				<input type="submit" class="pure-button pure-button-success" name="confirmButton" value="Neues Medium" />		
				<input type="reset" class="pure-button pure-button-error" name="cancle" value="Abbrechen" />
			</form>
	    </div>
	
	</div>
</body>
</html>