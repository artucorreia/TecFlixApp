CREATE TABLE IF NOT EXISTS socials(
    id SERIAL PRIMARY KEY,
    social_name_id SERIAL REFERENCES social_names(id) NOT NULL,
    url VARCHAR(255) NOT NULL,
    user_id UUID REFERENCES users(id) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by UUID REFERENCES users(id),
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by UUID REFERENCES users(id),
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);