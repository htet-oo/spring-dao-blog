<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
  String contextPath = request.getContextPath();
%>
<nav class="navbar-dark bg-dark">
	<div class="navbar navbar-expand navbar-dark">
		<a class="navbar-brand" href="/Demo/post"> BulletinBoard </a>
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="/springInit/posts/list">Home</a></li>
			<security:authorize access="hasAuthority('Admin')">
				<li class="nav-item"><a class="nav-link" href="<%= contextPath %>/users/create">Add User</a></li>
			</security:authorize>
			<security:authorize access="hasAuthority('User')">
				<li class="nav-item"><a class="nav-link" href="/springblog/posts/create">Add Post</a></li>
			</security:authorize>
		</ul>
		  <div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    ${sessionScope.account}
			</button>
		    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		      <a class="dropdown-item" href="/springblog/logout">Log Out</a>
		    </div>
		  </div>
	</div>
</nav>
