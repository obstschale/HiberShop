<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="include/header.jsp" %>


<div class="pure-g">	    
    <div class="pure-u-1-2 main">
    <h2>Neues Album</h2>
    
	    <form class="pure-form-aligned pure-form"  action="ControllerAlbum" enctype="multipart/form-data" method="post">

			<div class="pure-control-group">
	            <label for="name">Albumname</label>
				<input type="text" name="name" placeholder="Album name" required />
			</div>
			
			<div class="pure-control-group">
	            <label for="interpret">Interpret</label>
				<input type="text" name="interpret" placeholder="Interpret" />
			</div>
			
			<div class="pure-control-group">
	            <label for="cover">Cover</label>
				<input type="file" name="cover" placeholder="Cover" />
			</div>
			
			<div class="pure-controls">
				<input type="submit" class="pure-button pure-button-success" name="confirmButton" value="Neues Album" />		
				<input type="reset" class="pure-button pure-button-error" name="cancle" value="Abbrechen" />
			</div>
		</form>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
