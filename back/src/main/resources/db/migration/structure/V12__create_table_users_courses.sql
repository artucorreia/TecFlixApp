CREATE TABLE IF NOT EXISTS users_courses(
    user_id UUID REFERENCES users(id) NOT NULL,
    course_id UUID REFERENCES courses(id) NOT NULL,
    PRIMARY KEY(user_id, course_id)
);