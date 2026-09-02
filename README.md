
# Spring Blog Management System

A web-based blog management application developed using Java and the Spring Framework.

This project implements user management, authentication and authorization,
blog post management, password recovery with email delivery, form validation,
database persistence, profile image upload, and Excel export.

## Features

### User Management
- User registration
- User editing
- User deletion
- User search
- Role management
- Profile image upload
- Automatic image storage

### Authentication & Security
- User login
- Spring Security authentication
- Role-based authorization
- Admin and User roles
- Logout
- Password recovery
- Password reset
- Email-based password recovery
- Password reset link sent by email

### Blog Management
- Create blog posts
- Edit blog posts
- Delete blog posts
- Search blog posts
- View user posts

### Other Features
- Form validation
- MySQL database persistence
- Excel export
- Email service integration

## Technologies

### Backend
- Java 17
- Spring Framework 5.3
- Spring MVC
- Spring Security
- Hibernate
- Maven

### Frontend
- JSP
- JSTL
- HTML
- CSS
- Bootstrap
- JavaScript

### Database
- MySQL

### Email
- JavaMail / Spring Mail

### Server
- Apache Tomcat 9

## Architecture

The application follows a layered MVC architecture.

```text
Browser
   ↓
Controller
   ↓
Service
   ↓
DAO
   ↓
Hibernate
   ↓
MySQL
