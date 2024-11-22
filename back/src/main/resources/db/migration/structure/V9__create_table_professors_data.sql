CREATE TABLE professors_data(
    id SERIAL PRIMARY KEY,
    user_id UUID REFERENCES users(id) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    occupation OCCUPATION NOT NULL,
    biography TEXT NOT NULL,
    birthdate DATE NOT NULL,
    gender GENDER NOT NULL,
    contact VARCHAR(13) UNIQUE NOT NULL,
    profile_image VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL  
);