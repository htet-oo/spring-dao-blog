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
		<h3>Post Create</h3>
		<c:url value="/posts/create/save" var="createDb"></c:url>
		<form:form method="POST" action="${createDb}"
			modelAttribute="saveForm">
			<div class="form-group mb-3 col-5">
				<form:label path="description">Description</form:label>
				<form:input path="description" class="form-control"
					placeholder="Enter Description" />
				<form:errors path="description"></form:errors>
			</div>
			<div class="form-group mb-3 col-5">
				<form:label path="title">Title</form:label>
				<form:input path="title" class="form-control"
					placeholder="Enter title" />
				<form:errors path="title"></form:errors>
			</div>

			<div class="col-5 mb-3">
				<form:select path="userId" cssClass="form-select">
					<form:option value="0">Select User</form:option>
					<c:forEach items="${users}" var="user">
						<option value="${user.id}">${user.name}</option>
					</c:forEach>
				</form:select>
				<form:errors path="userId"></form:errors>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>