<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>
	<div class="container py-5">
		<div class="d-flex justify-content-between">
			<h3>User List</h3>
			<div>
				<c:url var="searchUser" value="/user/search" />
				<form action="${searchUser}" method="GET" class="d-flex">
					<input type="text" name="search" class="form-control"
						placeholder="Search....." />
					<button type="submit" class="btn btn-success ml-3">Search</button>
				</form>
			</div>
			<c:url var="createJSP" value="/users/create" />
			<a class="inline-block btn btn-primary" href="${createJSP}">Add
				New</a>
		</div>
		<div class="table-responsive py-5">
			<table class="table table-bordered table-striped">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Password</th>
					<th>Join Date</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.password}</td>
						<td>${user.created_at}</td>
						<td><c:url value="/users/edit" var="updateDb">
								<c:param name="updateObjId" value="${user.id}"></c:param>
							</c:url> <a class="btn btn-info" href="${updateDb}">Edit</a> <c:url
								value="/users/delete" var="deleteDb">
								<c:param name="deleteId" value="${user.id}" />
							</c:url> <a class="btn btn-danger" href="${deleteDb}">Delete</a> <c:url
								value="/posts/list" var="userPostList"></c:url> <a
							class="btn btn-warning" href="${userPostList}">Posts</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>