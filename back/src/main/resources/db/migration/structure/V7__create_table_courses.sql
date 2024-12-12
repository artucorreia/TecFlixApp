CREATE TABLE IF NOT EXISTS courses(
    id UUID PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    cape_image VARCHAR(255),
    user_id UUID REFERENCES users(id) NOT NULL,
    total_score_reviews FLOAT,
    total_reviews BIGINT,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);