CREATE TABLE IF NOT EXISTS courses_students(
    course_id UUID REFERENCES courses(id),
    student_id UUID REFERENCES users(id),
    PRIMARY KEY (course_id, student_id)
);