-- Initialize schema
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255),
    type VARCHAR(10) NOT NULL,
    user_id VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat_messages (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    message_type VARCHAR(10) NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    metadata TEXT
);

-- Sample transactions for user 'user1'
INSERT INTO transactions (amount, category, date, description, type, user_id)
VALUES 
    (1500.00, 'Salary', '2025-04-01', 'Monthly Salary', 'INCOME', 'user1'),
    (700.00, 'Rent', '2025-04-05', 'Monthly Rent', 'EXPENSE', 'user1'),
    (120.00, 'Groceries', '2025-04-08', 'Weekly Shopping', 'EXPENSE', 'user1'),
    (50.00, 'Dining', '2025-04-12', 'Restaurant', 'EXPENSE', 'user1'),
    (80.00, 'Transportation', '2025-04-14', 'Fuel', 'EXPENSE', 'user1'),
    (200.00, 'Entertainment', '2025-04-18', 'Concert Tickets', 'EXPENSE', 'user1'),
    (150.00, 'Groceries', '2025-04-22', 'Supermarket', 'EXPENSE', 'user1'),
    (75.00, 'Utilities', '2025-04-25', 'Electricity Bill', 'EXPENSE', 'user1'),
    (60.00, 'Dining', '2025-04-28', 'Dinner', 'EXPENSE', 'user1'),
    (1500.00, 'Salary', '2025-05-01', 'Monthly Salary', 'INCOME', 'user1'),
    (700.00, 'Rent', '2025-05-05', 'Monthly Rent', 'EXPENSE', 'user1'),
    (130.00, 'Groceries', '2025-05-08', 'Weekly Shopping', 'EXPENSE', 'user1'),
    (45.00, 'Dining', '2025-05-10', 'Lunch', 'EXPENSE', 'user1'),
    (90.00, 'Transportation', '2025-05-13', 'Fuel', 'EXPENSE', 'user1');

-- Sample chat messages for user 'user1'
INSERT INTO chat_messages (user_id, message_type, content, timestamp)
VALUES 
    ('user1', 'USER', 'How much did I spend last month?', '2025-05-10 14:23:45'),
    ('user1', 'BOT', 'Based on your transaction history, you spent a total of $1,435.00 in April 2025. This includes $700.00 for rent, $270.00 for groceries, $110.00 for dining, $80.00 for transportation, $200.00 for entertainment, and $75.00 for utilities.', '2025-05-10 14:23:50'),
    ('user1', 'USER', 'Which category did I spend the most on?', '2025-05-10 14:24:15'),
    ('user1', 'BOT', 'In April 2025, you spent the most on Rent with $700.00, followed by Groceries ($270.00) and Entertainment ($200.00).', '2025-05-10 14:24:20');
