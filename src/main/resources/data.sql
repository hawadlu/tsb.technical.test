INSERT INTO account_holder (id, name, username, password) VALUES
(1, 'Jane Smith', 'one', '$2a$12$/ssvAPOpOIpv.MTfvO7SJO/kTA/pXu4GgPpHm73jueLlmfbF8Twva');
INSERT INTO account_holder (id, name, username, password) VALUES
(2, 'John Smith', 'two', '$2a$12$/ssvAPOpOIpv.MTfvO7SJO/kTA/pXu4GgPpHm73jueLlmfbF8Twva');
-- "test" BCrypt 12 rounds

INSERT INTO account (id, account_holder_id, account_number, balance) VALUES (1, 1, 1, 123.45);
INSERT INTO account (id, account_holder_id, account_number, balance) VALUES (2, 1, 2, 12);
INSERT INTO account (id, account_holder_id, account_number, balance) VALUES (3, 2, 2, 12);

INSERT INTO transaction (id, from_account_id, to_account_id, amount, account_owner_id)
    VALUES (1, 1, 2, 10, 1);
