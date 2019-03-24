SELECT s.sname
FROM Section t, Student s, Grade g
WHERE t.instructor = 'HERMANO'
AND (g.grade = 'B' OR g.grade = 'A')
AND (s.stno = g.student_number AND g.section_ID = t.section_ID)
/
