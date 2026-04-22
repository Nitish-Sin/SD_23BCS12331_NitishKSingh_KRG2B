# 🐱 MEOWSIC — Setup Guide (PostgreSQL)

## Project Structure
```
meowsic/
├── frontend/
│   └── index.html              ← Standalone demo (open in browser, works without a server)
│
├── backend/
│   ├── src/meowsic/
│   │   ├── util/
│   │   │   ├── DBConnection.java
│   │   │   └── PasswordUtil.java
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── dao/
│   │   │   └── UserDAO.java
│   │   └── servlet/
│   │       ├── LoginServlet.java
│   │       ├── RegisterServlet.java
│   │       └── LogoutServlet.java
│   └── WebContent/
│       ├── index.html          ← Copy from frontend/index.html
│       ├── dashboard.jsp
│       └── WEB-INF/
│           └── web.xml
│
└── sql/
    └── meowsic_db.sql          ← Run this first in PostgreSQL
```

---

## ✅ Step 1 — PostgreSQL Setup

Open **pgAdmin** or **psql** and run:

```sql
CREATE DATABASE meowsic_db;
\c meowsic_db
```

Then run the script:
```
sql/meowsic_db.sql
```

This creates the `users` table with a SERIAL primary key and a unique index on `user_id`.

---

## ✅ Step 2 — Configure DB Credentials

Open `backend/src/meowsic/util/DBConnection.java` and update:
```java
private static final String URL      = "jdbc:postgresql://localhost:5432/meowsic_db";
private static final String USER     = "postgres";   // your PostgreSQL username
private static final String PASSWORD = "postgres";   // your PostgreSQL password
```

---

## ✅ Step 3 — Eclipse Project Setup

1. **File → New → Dynamic Web Project**
   - Project name: `Meowsic`
   - Target runtime: Apache Tomcat 10+ (Jakarta EE)

2. **Copy source files** into `src/` keeping the package structure

3. **Copy WebContent files** into `WebContent/`
   - Copy `frontend/index.html` → `WebContent/index.html`

4. **Add the PostgreSQL JDBC driver** to `WebContent/WEB-INF/lib/`:
   - Download: https://jdbc.postgresql.org/download/
   - File: `postgresql-42.x.x.jar`
   - Right-click project → Build Path → Add to Build Path

5. **Run As → Run on Server** (Tomcat)

---

## ✅ Step 4 — Test

1. Open: `http://localhost:8080/Meowsic/`
2. Register a new account via the **Join Free** tab
3. Sign in with your credentials
4. Dashboard loads 🎉

---

## 🔐 Security Features

| Feature | Implementation |
|---|---|
| Password Hashing | SHA-256 via `PasswordUtil.java` |
| SQL Injection Prevention | PreparedStatements throughout |
| Duplicate User Handling | PostgreSQL SQLState `23505` |
| Session Management | HttpSession, 30-min timeout |
| Input Validation | Client-side + server-side |

---

## 📦 Dependencies

- Java 17+
- Apache Tomcat 10+
- PostgreSQL 13+
- postgresql-42.x.x.jar (add to WEB-INF/lib)

---

*Happy streaming! 🐾🎵*
