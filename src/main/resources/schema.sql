-- schema.sql
-- Drop tables if they exist (H2 specific)
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS "user";

-- User Table Schema
CREATE TABLE "user" (
                        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL
);

-- Account Table Schema
CREATE TABLE account (
                         id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         account_number VARCHAR(20) UNIQUE NOT NULL,
                         account_type VARCHAR(50) NOT NULL,
                         balance DECIMAL(19,4) NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES "user"(id)
);

-- Transaction Table Schema
CREATE TABLE transaction (
                             id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             from_account_id BIGINT NOT NULL,
                             to_account_id BIGINT NOT NULL,
                             amount DECIMAL(19,4) NOT NULL,
                             timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             description VARCHAR(255),
                             FOREIGN KEY (from_account_id) REFERENCES account(id),
                             FOREIGN KEY (to_account_id) REFERENCES account(id)
);
