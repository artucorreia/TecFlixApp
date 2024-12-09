CREATE TABLE IF NOT EXISTS modules(
    id SERIAL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    course_id UUID REFERENCES courses(id) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);