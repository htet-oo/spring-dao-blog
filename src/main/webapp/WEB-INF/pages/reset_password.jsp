<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="card px-5 py-5 shadow" style="width: 50%;">
				 <h2 class="text-center">RESET PASSWORD</h2>
				 
				 <!-- Form -->
				 <c:url value="/reset_password" var="resetPassword"></c:url>
				  <form action="${resetPassword}" method="post">
				  	<input type="hidden" name = "token" value="${token}"/>
				  	<div class="mb-3">
						 <div>
						 	<p>
						 		<input type="password" name="password" class="form-control" placeholder="Enter your new Password" required="required" autofocus="autofocus">
						 	</p>
						 	
						 	<p>
						 		<input type="password" name="confirmPassword" class="form-control" placeholder="Confirm your new  Password" required="required">
						 	</p>
						 	
						 	<p class="text-center">
						 		<input type="submit" value="Change Password" class="btn btn-primary ps-4 pe-4">
						 	</p>
						 	
						 </div>
						 
				  	</div>

				  </form>
			</div>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>