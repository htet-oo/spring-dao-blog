<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post List</title>
</head>
<body>
	<div class="container py-5">
		<div class="d-flex justify-content-between">
			<h3>Post List</h3>
			<div>
				<c:url var="searchPost" value="/posts/search" />
				<form action="${searchPost}" method="GET" class="d-flex">
					<input type="text" name="search" class="form-control"
						placeholder="Search....." />
					<button type="submit" class="btn btn-success ml-3">Search</button>
				</form>
			</div>
			<c:url var="createJSP" value="/posts/create" />
			<a class="inline-block btn btn-primary" href="${createJSP}">Add
				New</a>
		</div>
		<div class="table-responsive py-5">
			<table class="table table-bordered table-striped">
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>Description</th>
					<th>Post Date</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${userPost}" var="userPosts">
					<tr>
						<td>${userPosts.id}</td>
						<td>${userPosts.title}</td>
						<td>${userPosts.description}</td>
						<td>${userPosts.created_at}</td>
						<td><c:url value="/posts/edit" var="updateDb">
								<c:param name="updateObjId" value="${userPosts.id}"></c:param>
							</c:url> <a class="btn btn-info" href="${updateDb}">Edit</a> <c:url
								value="/posts/delete" var="deleteDb">
								<c:param name="deleteId" value="${userPosts.id}" />
							</c:url> <a class="btn btn-danger" href="${deleteDb}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>