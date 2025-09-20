CREATE TABLE IF NOT EXISTS courses_students(
    course_id UUID REFERENCES courses(id) NOT NULL,
    student_id UUID REFERENCES users(id) NOT NULL,
    PRIMARY KEY (course_id, student_id)
);