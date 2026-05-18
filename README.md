# Laundry System

A full-stack Laundry Management System built using Spring Boot, React, and MySQL. This project helps manage laundry services, bookings, customers, agents, payments, and order tracking through a modern web application.

---

## Features

### Customer Features

* User Registration & Login
* Book Laundry Services
* View Order Status
* Online Payment Integration
* Order History
* Profile Management

### Admin Features

* Admin Dashboard
* Manage Users
* Manage Laundry Services
* Manage Orders & Payments
* Assign Delivery Agents
* Update Order Status
* Analytics & Reports

### Agent Features

* Agent Dashboard
* View Assigned Orders
* Update Pickup & Delivery Status
* Manage Deliveries

---

# Tech Stack

## Frontend

* React.js
* Axios
* React Router
* CSS
* React Hot Toast

## Backend

* Spring Boot
* Spring Security
* Spring Data JPA
* REST APIs
* Maven

## Database

* MySQL

## Payment Gateway

* Cashfree Payment Gateway

---

# Project Structure

```bash
Laundry-System/
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
│
└── README.md
```

---

# Installation & Setup

## Clone Repository

```bash
git clone https://github.com/sameeramarjinta1580/laundry-System.git
cd laundry-System
```

---

# Backend Setup

## Configure Database

Create MySQL database:

```sql
CREATE DATABASE LaundrySystem;
```

## Configure `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/LaundrySystem
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD

cashfree.appId=YOUR_APP_ID
cashfree.secretKey=YOUR_SECRET_KEY
```

## Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs on:

```bash
http://localhost:8080
```

---

# Frontend Setup

Install dependencies:

```bash
cd frontend
npm install
```

Run frontend:

```bash
npm start
```

Frontend runs on:

```bash
http://localhost:3000
```

---

# API Features

* Authentication APIs
* User APIs
* Booking APIs
* Payment APIs
* Order Tracking APIs
* Agent Management APIs
* Admin Management APIs

---

# Security Features

* JWT Authentication
* Role-Based Authorization
* Secure Payment Integration
* Password Encryption

---

# Screenshots

Add project screenshots here.

Example:

* Homepage
* Admin Dashboard
* Booking Page
* Agent Dashboard
* Payment Page

---

# Future Improvements

* SMS Notifications
* Real-Time Order Tracking
* Mobile App Version
* AI-Based Price Prediction
* Multi-Vendor Support

---

# Author

## Sameer Amarjinta

GitHub:
[https://github.com/sameeramarjinta1580](https://github.com/sameeramarjinta1580)

---

# License

This project is developed for learning and educational purposes.
