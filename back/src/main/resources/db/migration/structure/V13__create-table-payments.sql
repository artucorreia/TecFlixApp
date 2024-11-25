CREATE TABLE IF NOT EXISTS payments(
    id SERIAL PRIMARY KEY,
    user_id UUID REFERENCES users(id) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    made_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);