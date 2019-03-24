SELECT s.stno, s.sname, t.course_num, g.grade
FROM Student s, Section t, Grade g
WHERE s.stno = g.student_number
AND g.section_Id = t.section_Id
AND t.course_num LIKE 'COSC%'
AND (g.grade = 'A' OR g.grade = 'B')
/
