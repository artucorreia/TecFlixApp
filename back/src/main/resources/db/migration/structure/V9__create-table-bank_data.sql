CREATE TABLE bank_data(
    id BIGINT PRIMARY KEY,
    user_id UUID REFERENCES users(id) NOT NULL,
    account_number VARCHAR(12) NOT NULL,
    dv CHAR NOT NULL,
    account ACCOUNT NOT NULL,
    agency VARCHAR(5) NOT NULL
);