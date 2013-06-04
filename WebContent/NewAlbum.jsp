<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Album</title>
</head>
<body>	
	<form action="AlbumConfirmation.jsp">
		<input type="text" name="name" placeholder="Album name" required />
		<input type="text" name="interpret" placeholder="Interpret" />
		<select>
			<option>No Selection</option>
		</select>
		<input type="file" name="cover" placeholder="Cover" />
		<br>
		<input type="submit" name="submit" value="OK" />		
		<input type="reset" name="cancle" value="Abbrechen" />
	</form>
</body>
</html>