INSERT INTO course (course_id, title, description) VALUES
(1, 'Advanced Algebra', 'Advanced concepts of the algebraic type'),
(2, 'Philosophy', 'Delve into the mysteries of ...'),
(3, 'Network Protocols', 'What is a network? Learn about ...');


INSERT INTO student (student_id, firstname, lastname, sex, birthdate) VALUES
(1, 'Mary', 'Poppins', 'Female', '1999-01-01'),
(2, 'John', 'Doe', 'Male', '2003-03-05'),
(3, 'Mr', 'Glass', 'Male', '2001-02-02'),
(4, 'Carol', 'Danvers', 'Female', '2000-11-17');


INSERT INTO student_course (student_id, course_id, enrolment_date) VALUES
(1, 2, '2019-01-12'),
(2, 1, '2019-01-21'),
(2, 3, '2019-01-11'),
(3, 1, '2019-01-10'),
(3, 2, '2019-01-10'),
(2, 2, '2019-01-10'),
(4, 2, '2019-01-10'),
(4, 3, '2019-01-19');

