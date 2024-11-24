CREATE TABLE IF NOT EXISTS courses_tags(
    tag_id INT REFERENCES tags(id) NOT NULL,
    course_id UUID REFERENCES courses(id) NOT NULL,
    PRIMARY KEY(tag_id, course_id)
);