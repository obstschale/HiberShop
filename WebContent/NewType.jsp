<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Type</title>
</head>
<body>
	<form action="TypeConfirmation.jsp">
		<input type="text" name="name" placeholder="Typen name" required />
		<input type="file" name="icon" value="Icon hochladen" disabled />
		<br>
		<input type="submit" name="submit" value="OK" />		
		<input type="reset" name="cancle" value="Abbrechen" />
	</form>
</body>
</html>