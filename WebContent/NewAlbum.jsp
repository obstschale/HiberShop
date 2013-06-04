<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="links.html" %>
	<title>New Album</title>
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
				<input type="text" name="name" placeholder="Album name" required />
				<input type="text" name="interpret" placeholder="Interpret" />
				<select>
					<option>No Selection</option>
				</select>
				<input type="file" name="cover" placeholder="Cover" />
				<br>
				<input type="submit" class="pure-button pure-button-success" name="confirmButton" value="Neues Album" />		
				<input type="reset" class="pure-button pure-button-error" name="cancle" value="Abbrechen" />
			</form>
	    </div>
	
	</div>	
</body>
</html>