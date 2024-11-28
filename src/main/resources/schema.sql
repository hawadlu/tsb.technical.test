-- Drop existing tables in reverse order of foreign key dependencies
DROP TABLE IF EXISTS TRANSACTION;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS ACCOUNT_HOLDER;

-- Create tables with appropriate constraints
CREATE TABLE ACCOUNT_HOLDER (
                                ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                NAME VARCHAR(255) NOT NULL,
                                EMAIL VARCHAR(255)
--                                     UNIQUE NOT NULL
);

CREATE TABLE ACCOUNT (
                         ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         ACCOUNT_HOLDER_ID BIGINT NOT NULL, -- Matches Long type
                         ACCOUNT_NUMBER BIGINT,
--                              UNIQUE NOT NULL, -- Matches Long type
                         BALANCE BIGINT NOT NULL
);

CREATE TABLE TRANSACTION (
                             ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             FROM_ACCOUNT_ID BIGINT NOT NULL, -- Matches Long type
                             TO_ACCOUNT_ID BIGINT NOT NULL, -- Matches Long type
                             AMOUNT BIGINT NOT NULL, -- Matches Long type
                             DESCRIPTION VARCHAR(255)
);
