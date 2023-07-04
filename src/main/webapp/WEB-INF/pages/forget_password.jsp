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
				 <h2 class="text-center">FORGET PASSWORD</h2>
				 <c:url value="/forget_password" var="forgetPassword"></c:url>
				  <form action="${forgetPassword}" method="post">
				  	<div class="mb-3">
						 <div class="text-center">
						  	<p>We will be sending a reset password link  to your email.</p>
						 </div>
						 <div>
						 	<p>
						 		<input type="email" name="email" class="form-control" placeholder="Enter your E-mail" required="required" autofocus="autofocus">
						 	</p>
						 	<p class="text-center">
						 		<input type="submit" value="Send" class="btn btn-primary ps-4 pe-4">
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