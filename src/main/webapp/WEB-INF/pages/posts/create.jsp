<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="ISO-8859-1">

    <title>Post Create</title>

</head>

<body>

    <div class="container py-5">

        <h3>Post Create</h3>

        <c:url value="/posts/create/save" var="createDb" />

        <form:form method="POST"
            action="${createDb}"
            modelAttribute="saveForm">

            <!-- Description -->

            <div class="form-group mb-3 col-5">

                <form:label path="description">
                    Description
                </form:label>

                <form:input
                    path="description"
                    class="form-control"
                    placeholder="Enter Description" />

                <form:errors
                    path="description">
                </form:errors>

            </div>


            <!-- Title -->

            <div class="form-group mb-3 col-5">

                <form:label path="title">
                    Title
                </form:label>

                <form:input
                    path="title"
                    class="form-control"
                    placeholder="Enter title" />

                <form:errors
                    path="title">
                </form:errors>

            </div>


            <!-- Submit -->

            <button
                type="submit"
                class="btn btn-primary">

                Submit

            </button>

        </form:form>

    </div>

</body>

</html>