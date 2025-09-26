CREATE TABLE IF NOT EXISTS professors_data(
    id SERIAL PRIMARY KEY,
    user_id UUID REFERENCES users(id) UNIQUE NOT NULL,
    occupation_id SERIAL REFERENCES occupations(id) NOT NULL,
    other_occupation VARCHAR(50),
    biography TEXT NOT NULL,
    birthdate DATE NOT NULL,
    gender_id SERIAL REFERENCES genders(id) NOT NULL,
    other_gender VARCHAR(30),
    phone_number VARCHAR(13) UNIQUE NOT NULL,
    profile_image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by UUID REFERENCES users(id),
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by UUID REFERENCES users(id),
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);