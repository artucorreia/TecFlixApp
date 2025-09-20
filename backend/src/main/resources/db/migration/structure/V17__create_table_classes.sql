CREATE TABLE IF NOT EXISTS classes(
    id UUID PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    video_url VARCHAR(255) NOT NULL,
    module_id SERIAL REFERENCES modules(id) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by UUID REFERENCES users(id),
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by UUID REFERENCES users(id),
    deleted BOOLEAN DEFAULT FALSE NOT NULL
);