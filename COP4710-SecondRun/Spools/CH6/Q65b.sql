SELECT s.stno, s.sname, t.course_num, g.grade 
FROM Student s, Section t, Grade g
WHERE s.stno = g.student_number
AND g.section_ID = t.section_ID
AND t.course_num LIKE 'MATH%'
/
