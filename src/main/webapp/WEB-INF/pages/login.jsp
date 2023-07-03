<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
  crossorigin="anonymous">
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
  crossorigin="anonymous"></script>
</head>
<body>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="card px-5 py-5 shadow" style="width: 50%;">
        <h2 class="text-center">LOGIN</h2>
        <!-- Error Message -->
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
          <span class="text-danger font-bold text-center mb-3"> Your login attempt was not
            successful due to <c:out
              value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
          </span>
        </c:if>
        <!-- Login Form -->
        <form action="<c:url value="/login" />" method="post">
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email
              address</label> <input type="email" name="username"
              class="form-control" id="exampleInputEmail1"
              aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">We'll never
              share your email with anyone else.</div>
          </div>
          <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="password" class="form-control"
              id="exampleInputPassword1">
          </div>
          <div class="mb-5 form-check">
            <input type="checkbox" name="remember-me" class="form-check-input"
              id="exampleCheck1"> <label
              class="form-check-label" for="exampleCheck1">Remember Me</label>
          </div>
          <div class="d-grid col-12 mx-auto">
          <button type="submit" class="btn btn-primary">Submit</button>
          </div>
        </form>
        <a href="<c:url value="/forgot_password" />" class="text-center">Forgot password?</a>
      </div>
    </div>
  </div>
</body>
</html>