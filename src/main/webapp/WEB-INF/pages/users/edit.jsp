<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container py-5">
		<h3>User Create</h3>
		<c:url value="/users/edit/save" var="editDb"></c:url>
		<form:form method="POST" action="${editDb}" modelAttribute="editUser">
			<form:hidden path="id" />
			<div class="row">
				<div class="col">
					<div class="form-group mb-3">
						<form:label path="name">Name</form:label>
						<form:input path="name" class="form-control"
							placeholder="Enter Name" />
							<form:errors path="name"></form:errors>
					</div>
				</div>
				<div class="col">
					<div class="form-group mb-3">
						<form:label path="email">Email</form:label>
						<form:input path="email" class="form-control"
							placeholder="Enter Email" />
						<form:errors path="email"></form:errors>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group mb-3">
						<form:label path="password">Password</form:label>
						<form:input path="password" class="form-control"
							placeholder="Enter Password" />
						<form:errors path="password"></form:errors>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>