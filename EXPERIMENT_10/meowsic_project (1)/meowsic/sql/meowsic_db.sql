-- ==============================================
--  MEOWSIC DATABASE SETUP — PostgreSQL
--  Run in psql or pgAdmin
-- ==============================================

-- 1. Create the database (run as superuser):
--    CREATE DATABASE meowsic_db;
--    \c meowsic_db

-- 2. Create the users table:
CREATE TABLE IF NOT EXISTS users (
    id           SERIAL PRIMARY KEY,
    user_id      VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,        -- stored as SHA-256 hex hash
    display_name VARCHAR(100),
    email        VARCHAR(150),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index for fast login lookups
CREATE INDEX IF NOT EXISTS idx_users_user_id ON users(user_id);
