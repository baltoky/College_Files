(defconstant DFA
	'((ALPHABET        (a b))
	(STATES (1 2 3 4 5))
	(INITIAL-STATE 1)
	(FINAL-STATES (4))
	(TRANSITIONS ((1 a 2) (1 b 5)
				(2 a 4) (2 b 3)
				(3 a 5) (3 b 2)
				(4 a 5) (4 b 5)
				(5 a 5) (5 b 5))))
	"A deterministic finite-state automaton that accepts the language
	{(a b^n a), n is an even number}, i.e. {(a a), (a b b a), (a b b
	b b a), ...}."
)

(defun accepts(state str)
	(cond(NULL str) NIL
		(
)

(defun transition (state token)
	(find DFA-TRANSITIONS
)