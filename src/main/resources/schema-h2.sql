CREATE TABLE student (
  student_id INT(11) NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  sex VARCHAR(50) NULL,
  birthdate DATETIME NULL,
  PRIMARY KEY (student_id));

CREATE TABLE course (
  course_id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(200) NULL,
  PRIMARY KEY (course_id));

CREATE TABLE student_course (
  student_id INT(11) NOT NULL,
  course_id INT(11) NOT NULL,
  enrolment_date DATETIME NOT NULL,
  PRIMARY KEY (student_id, course_id),
  CONSTRAINT FK_STUDENT
    FOREIGN KEY (student_id)
    REFERENCES student (student_id),
  CONSTRAINT FK_COURSE
    FOREIGN KEY (course_id)
    REFERENCES course (course_id));
