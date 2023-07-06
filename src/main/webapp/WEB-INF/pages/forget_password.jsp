<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				  <form:form method="POST" action="${forgetPassword}" modelAttribute="forgetPasswordForm">
				  	<div class="mb-3">
				  		<p class="text-center">
				  			We will sent reset password link to your email.
				  		</p>
				  		<div class="mb-2">
				  			<form:errors path="email"></form:errors>
				  			<form:input path="email" class="form-control" placeholder="Enter Your login Email"/>
				  		</div>
				  		<div class="text-center">
				  			<button type="submit" class="btn btn-primary">Send</button>
				  		</div>
				  	</div>
				  </form:form>
			</div>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>