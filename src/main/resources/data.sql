INSERT INTO ACCOUNT_HOLDER (id, name, email) VALUES
(1, 'Jane Smith', 'jane.smith@example.com');

INSERT INTO ACCOUNT (id, account_holder_id, account_number, balance) VALUES (1, 1, 1, 123.45);
INSERT INTO ACCOUNT (id, account_holder_id, account_number, balance) VALUES (2, 1, 2, 12);

INSERT INTO TRANSACTION (id, from_account_id, to_account_id, amount, description)
    VALUES (1, 1, 2, 10, 'description');
