<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="include/header.jsp" %>


<div class="pure-g">
    <div class="pure-u-1-2 main">
    	<h2>Neuer Type</h2>
    	
		<form class="pure-form-aligned pure-form"  action="ControllerType" enctype="multipart/form-data" method="post">
			
			<div class="pure-control-group">
	            <label for="name">Typename</label>
				<input type="text" name="name" placeholder="Typen name" required />
			</div>
			
			<div class="pure-control-group">
	            <label for="icon">Icon</label>
				<input type="file" name="icon" value="Icon hochladen" />
			</div>
			
			<div class="pure-controls">
				<input type="submit" class="pure-button pure-button-success"
				name="confirmButton" value="Neuer Typ" />
				<input type="reset"	class="pure-button pure-button-error" name="cancle"
				value="Abbrechen" />
			</div>
		</form>
	</div>
</div>

<%@ include file="include/footer.jsp" %>
