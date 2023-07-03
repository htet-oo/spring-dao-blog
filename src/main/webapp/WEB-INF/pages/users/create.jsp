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
		<c:url value="/users/create/save" var="createDb"></c:url>
		<form:form method="POST" action="${createDb}"
			modelAttribute="saveUser">
			<div class="form-group mb-3 col-5">
				<form:label path="name">Name</form:label>
				<form:input path="name" class="form-control"
					placeholder="Enter Name" />
				<form:errors path="name"></form:errors>
			</div>
			<div class="form-group mb-3 col-5">
				<form:label path="email">Email</form:label>
				<form:input path="email" class="form-control"
					placeholder="Enter Email" />
				<form:errors path="email"></form:errors>
			</div>
			<div class="form-group mb-3 col-5">
				<form:label path="password">Password</form:label>
				<form:input path="password" class="form-control"
					type="password" placeholder="Enter Password" />
				<form:errors path="password"></form:errors>
			</div>
			<div class="col-5 mb-3">
				<form:select path="roleId" cssClass="form-select">
					<c:forEach items="${roles}" var="role">
						<option value="${role.id}">${role.name}</option>
					</c:forEach>
				</form:select>
				<form:errors path="roleId"></form:errors>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>