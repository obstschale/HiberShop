<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Type" %>
<%@ page import="java.io.IOException;" %>
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

			<p> Type Name: <strong>${typedata.name}</strong> </p>
			<p> Type Icon:</p>
			<p>
			<%
				Type type;
				try {
					type = (Type) request.getSession().getAttribute("typedata");
					if(type.getIcon() != null) {
						%>
						<img src="${typedata.icon}" alt="icon of type" width="25" />
						<%
					} else {
						%>
						Leider wurde kein Icon hochgeladen.
						<%
					}
				} catch (NullPointerException ex) {
					System.err.println("Failed to create audio/video object." + ex);
					throw new ExceptionInInitializerError(ex);
				}
			
			%>
			</p>
	    </div>
	    
	</div>
</body>
</html>