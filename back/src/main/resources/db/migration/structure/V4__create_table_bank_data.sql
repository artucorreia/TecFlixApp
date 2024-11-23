CREATE TABLE bank_data(
    id SERIAL PRIMARY KEY,
    user_id UUID REFERENCES users(id) NOT NULL,
    account_number VARCHAR(12) NOT NULL,
    dv CHAR NOT NULL,
    account VARCHAR(11) NOT NULL,
    agency VARCHAR(5) NOT NULL
);