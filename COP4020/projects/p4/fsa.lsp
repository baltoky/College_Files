(defun demo()
	(setq fp (open "theString.txt" :direction :input)) 
	(setq l (read fp "done")) 
	(princ "processing ") 
	(princ l) 
	(fsa l)
)
(defun fsa(l)
	(cond ((null l) "illegal empty string")
		(t (state0 l))
	)
)
(defun State0(l)
	(cond
		((null l) "illegal state"
		((equal (car l) 'x) (State0(cdr l)))
		((equal (car l) 'y) (State1(cdr l)))
		(t "Illegal character in the state")
	)
)
(defun State1(l)
	(cond
		((null l) "legal state"
		((equal (car l) 'x) (State2(cdr l)))
		(t "Illegal character in the state")
	)
)
(defun State2(l)
	(cond
		((null l) "illegal state"
		((equal (car l) 'x) (State2(cdr l)))
		((equal (car l) 'y) (State3(cdr l)))
		(t "Illegal character in the state")
	)
)
(defun State3(l)
	(cond
		((null l) "legal state"
		((equal (car l) 'x) (State3(cdr l)))
		((equal (car l) 'z) (State4(cdr l)))
		(t "Illegal character in the state")
	)
)
(defun State4(l)
	(cond
		((null l) "illegal state"
		((equal (car l) 'x) (State4(cdr l)))
		((equal (car l) 'a) (State1(cdr l)))
		(t "Illegal character in the state")
	)
)
