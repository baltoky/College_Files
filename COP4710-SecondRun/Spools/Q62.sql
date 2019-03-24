SELECT s.sname, s.class, t.course_num
FROM Student s, Section t, Grade g
WHERE s.stno = g.student_number
AND g.section_Id = t.section_Id
MINUS
SELECT s.sname, s.class, t.course_num
FROM Student s, Section t, Grade g
WHERE s.class = 2
/
