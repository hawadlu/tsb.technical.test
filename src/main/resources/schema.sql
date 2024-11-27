-- Drop existing tables in reverse order of foreign key dependencies
DROP TABLE IF EXISTS TRANSACTION CASCADE;
DROP TABLE IF EXISTS ACCOUNT CASCADE;
DROP TABLE IF EXISTS ACCOUNT_HOLDER CASCADE;

-- Create tables with appropriate constraints
CREATE TABLE ACCOUNT_HOLDER (
                                ID SERIAL PRIMARY KEY,
                                NAME VARCHAR(255) NOT NULL,
                                EMAIL VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE ACCOUNT (
                         ID SERIAL PRIMARY KEY,
                         ACCOUNT_HOLDER_ID BIGINT NOT NULL,
                         ACCOUNT_NUMBER NUMERIC(20) UNIQUE NOT NULL,
                         BALANCE NUMERIC(19,4) NOT NULL,
                         FOREIGN KEY (ACCOUNT_HOLDER_ID) REFERENCES ACCOUNT_HOLDER(ID)
);

CREATE TABLE TRANSACTION (
                             ID SERIAL PRIMARY KEY,
                             FROM_ACCOUNT_ID BIGINT NOT NULL,
                             TO_ACCOUNT_ID BIGINT NOT NULL,
                             AMOUNT NUMERIC(19,4) NOT NULL,
                             DESCRIPTION VARCHAR(255),
                             FOREIGN KEY (FROM_ACCOUNT_ID) REFERENCES ACCOUNT(ID),
                             FOREIGN KEY (TO_ACCOUNT_ID) REFERENCES ACCOUNT(ID)
);
