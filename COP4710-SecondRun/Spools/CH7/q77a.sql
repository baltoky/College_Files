SELECT d.dname
FROM Department d
WHERE d.dcode IN (SELECT y.dcode
FROM Secretary y)
/