# Spring DAO Blog

A web-based blog application developed using Java and the Spring Framework.

This project implements a layered architecture with Spring MVC, Spring Security, Hibernate, MySQL, JSP, and JSTL. It provides user management, authentication, blog post management, role-based access control, password recovery, validation, image upload, search, and Excel export functionality.

## Features

### Authentication & Authorization

- User login and logout
- Spring Security authentication
- Role-based authorization
- Admin and User roles
- BCrypt password encryption
- Password recovery via email
- Password reset using a secure reset token

### User Management

- Create users
- Edit user information
- Delete users
- Search users
- Upload user profile images
- Display user profile images
- Role assignment
- Export user information to Excel

### Blog Post Management

- Create blog posts
- View blog posts
- Edit blog posts
- Delete blog posts
- Search blog posts
- View posts by specific users
- User-based post ownership
- Users can manage only their own posts

### Validation

- Form validation using Bean Validation
- Required field validation
- Input error messages
- Password validation during user creation

## Technologies

### Backend

- Java 17
- Spring Framework 5.3
- Spring MVC
- Spring Security 5.8
- Spring JDBC
- Hibernate ORM
- Hibernate Validator

### Database

- MySQL 8.0

### Frontend

- JSP
- JSTL
- HTML5
- CSS3
- Bootstrap

### Development Tools

- Eclipse
- Apache Maven
- Apache Tomcat
- Git
- GitHub

### Libraries

- Lombok
- Apache POI
- JavaMail
- MySQL Connector/J

## Architecture

The application follows a layered architecture to separate responsibilities between the presentation, business, and data access layers.

```text
Browser
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
DAO Layer
   │
   ▼
Hibernate / JDBC
   │
   ▼
MySQL Database
