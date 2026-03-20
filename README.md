# 📚 Secure Library Management System

A secure web-based Library Management System built using **Spring Boot**, implementing key principles of the **Secure Software Development Lifecycle (SSDLC)**.

---

## 🚀 Features

### 👤 User Features
- Register and Login securely
- Browse available books
- Search books by title or author
- Request to borrow books

### 🛠️ Admin Features
- Add new books
- Edit existing books
- Delete books
- Approve / Reject borrow requests

### 🔐 Security Features
- BCrypt password hashing
- Role-Based Access Control (USER / ADMIN)
- CSRF protection using Spring Security
- Input validation (Jakarta Validation)
- XSS protection via Thymeleaf escaping
- Secure session management

---

## 🏗️ Architecture

The application follows a **3-tier architecture**:

- **Client Layer** → Browser (Thymeleaf UI)
- **Backend Layer** → Spring Boot (Business logic & security)
- **Database Layer** → H2 Database

A **trust boundary** ensures all incoming requests are validated and authenticated before processing.

---

## ⚠️ Threat Modeling (STRIDE)

The system was analyzed using the STRIDE model:

- Spoofing  
- Tampering  
- Repudiation  
- Information Disclosure  
- Denial of Service  
- Elevation of Privilege  

✔ Total: 8 threats identified and mitigated

---

## 🛡️ Security Implementation

- Passwords stored using BCrypt hashing
- CSRF protection enabled
- Input validation applied to user forms
- Role-based authorization enforced
- Sensitive data is not exposed in error responses

---

## 🔍 Security Testing

### SAST (Static Analysis)
- Tool: SonarQube  
- Before: 30 issues detected  
- After: Issues reduced and code secured  

### DAST (Dynamic Testing)
- Tool: OWASP ZAP  
- Result: No high-risk vulnerabilities  
- Only minor informational issues detected  

---

## 🔄 CI/CD Pipeline

The project follows a secure CI/CD pipeline:

1. Code Push (GitHub)
2. Build & Test (Maven)
3. SAST (SonarQube)
4. SCA (Dependency Check)
5. DAST (OWASP ZAP)
6. Security Gate (Pass / Fail)
7. Deployment

Only secure code is allowed to pass the pipeline.

---

## 🗄️ Database

- H2 in-memory database used for development
- Tables:
  - USERS
  - BOOK
  - BORROW_REQUEST

---

## ▶️ How to Run the Project

1. Clone the repository
```bash
git clone https://github.com/Udhayakumar-V
