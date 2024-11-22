CREATE TABLE addresses(
    id BIGINT PRIMARY KEY,
    CEP VARCHAR(8) NOT NULL,
    number VARCHAR(8) NOT NULL,
    state VARCHAR(30) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(30) NOT NULL,
    complement VARCHAR(100),
    user_id UUID REFERENCES users(id) NOT NULL
);