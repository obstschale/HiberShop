<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*" %>

<% if (request.getParameter("errortext") != null) {
	out.print("<div class=\"error\">");
		out.println(request.getParameter("errortext"));
	out.print("</div>");
} %>
