CREATE TABLE addresses(
    id SERIAL PRIMARY KEY,
    CEP VARCHAR(8) NOT NULL,
    number VARCHAR(8) NOT NULL,
    state VARCHAR(20) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(20) NOT NULL,
    complement VARCHAR(50),
    user_id UUID REFERENCES users(id) NOT NULL
);