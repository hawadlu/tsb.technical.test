-- data.sql
-- Insert Initial Users
INSERT INTO "user" (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com');

-- Insert Initial Accounts
INSERT INTO account (user_id, account_number, account_type, balance) VALUES
(1, 'ACC001', 'CHECKING', 5000.00),
(1, 'ACC002', 'SAVINGS', 10000.00),
(2, 'ACC003', 'CHECKING', 7500.00),
(2, 'ACC004', 'SAVINGS', 15000.00);

-- Insert Initial Transactions (optional, for demonstration)
INSERT INTO transaction (from_account_id, to_account_id, amount, description) VALUES
(1, 2, 500.00, 'Internal transfer'),
(3, 4, 1000.00, 'Savings deposit');
