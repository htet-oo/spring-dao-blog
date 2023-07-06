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
				 <h2 class="text-center">RESET PASSWORD</h2>
				 
				 <!-- Form -->
				 <c:url value="/reset_password" var="resetPassword"></c:url>				  
				  <form:form method="POST" action="${resetPassword}" modelAttribute="resetPasswordForm">
				  	<form:hidden path="token" value="${token}"/>
				  	<div class="mb-3">
				  		<form:label path="email">Your Email</form:label>
				  		<form:input path="email" class="form-control" readonly="readonly"/>
				  	</div>
				  	<div class="mb-3">
                        <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.resetPasswordForm']}">
                            <c:set var="bindingResult" value="${requestScope['org.springframework.validation.BindingResult.resetPasswordForm']}"/>
                            <c:if test="${bindingResult.hasFieldErrors('confirmPassword')}">
                                <p style="color: red;">Password and Confirm Password must match</p>
                            </c:if>
                        </c:if>
				  		<form:label path="password" class="mb-2">New Password</form:label>
				  		<form:input path="password" id="password" class="form-control" placeholder="Enter your new Password" required="required" autofocus="autofocus"/>
				  	</div>
				  	<div class="mb-3">
				  		<span id="validPassword" style="color: red;"></span>
				  		<form:label path="confirmPassword" class="mb-2">Confirm Password</form:label>
				  		<form:input path="confirmPassword" id="confirmPassword" class="form-control" placeholder="Enter your Confirm Password" required="required"/>
				  	</div>
				  	<div class="text-center">
				  		<button type="submit" class="btn btn-primary">Change</button>
				  	</div>
				  </form:form>
			</div>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>