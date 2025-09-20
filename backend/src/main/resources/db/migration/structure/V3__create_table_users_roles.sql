CREATE TABLE IF NOT EXISTS users_roles(
    user_id UUID REFERENCES users(id) NOT NULL,
    role_id SERIAL REFERENCES roles(id) NOT NULL,
    PRIMARY KEY (user_id, role_id)
);