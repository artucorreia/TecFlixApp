CREATE TABLE IF NOT EXISTS courses(
    id UUID PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    cape_image VARCHAR(255),
    professor_id UUID REFERENCES users(id) NOT NULL,
    total_score BIGINT NOT NULL DEFAULT 0,
    total_reviews BIGINT NOT NULL DEFAULT 0,
    average_score FLOAT NOT NULL DEFAULT 0,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);