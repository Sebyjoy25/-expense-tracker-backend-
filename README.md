# 💰 Expense Tracker Backend

A Spring Boot backend for tracking personal expenses. Features include JWT authentication, RESTful endpoints, DTO-based input/output, validation, error handling, and a summary analytics endpoint.

---

## 🚀 Features

- ✅ User registration & login with JWT
- ✅ Expense CRUD operations
- ✅ Category CRUD operations
- ✅ Postman-tested endpoints

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- JPA + Hibernate
- PostgreSQL
- Postman (for testing)

---

 -  The screenshot below shows a successful authenticated request to GET /api/expenses, with the JWT token provided in the header and a JSON list of expenses returned.

<img width="1359" height="1044" alt="Screenshot 2025-08-22 174155" src="https://github.com/user-attachments/assets/6811192e-2f63-4ce4-a40a-c466db501d1b" />

 
 - The screenshot below shows a successful DELETE /api/expenses/{id} request, with the updated list of expenses returned after deletion.


<img width="1373" height="1032" alt="Screenshot 2025-08-22 174048" src="https://github.com/user-attachments/assets/2ce3a9da-61db-4598-b660-52576fe49767" />
✅ Full CRUD supported: Create, Read, Update, Delete — all secured with JWT.









## 📦 Setup Instructions

```bash
# Clone the repo
git clone https://github.com/Sebyjoy25/-expense-tracker-backend-.git
cd expense-tracker-backend

# Run the app
./mvnw spring-boot:run
