<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Medium</title>
</head>
<body>
	<form action="Controller">
		<jsp:include page="ErrorText.jsp">
			<jsp:param value="${requestScope.errortext}" name="errortext"/>
		</jsp:include>
		<input type="text" name="type" placeholder="Typen name"  />
		<input type="text" name="album" placeholder="Album name" />
		<input type="text" name="titel" placeholder="Titel name" />
		<input type="text" name="interpret" placeholder="Interpret" />
		<input type="number" name="laenge" min="0" max="20" />
		<input type="number" name="dateigroesse" min="0" max="100" />
		<input type="file" name="medium" value="Medium hochladen" />
		<br>
		<input type="submit" name="confirmButton" value="Neues Medium" />		
		<input type="reset" name="cancle" value="Abbrechen" />
	</form>
</body>
</html>