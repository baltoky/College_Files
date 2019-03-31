SELECT d.dname
FROM Department d
WHERE NOT d.dcode IN (SELECT y.dcode
FROM Secretary y
WHERE y.dcode IS NOT NULL)
/
