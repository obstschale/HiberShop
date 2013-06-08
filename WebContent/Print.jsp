<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
    	<h2>Warenkorb Drucken</h2>
    	<p>Es wurden ${checkout} Titel gedruckt.</p>
    	<form action="ControllerCart">
			<button class="pure-button pure-button-secondary" name="backPrint" value="back">Zur&uuml;ck</button>
    	</form>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
