#Binomial Distribution; n = 10, p = 0.05
sum(dbinom(x = 3:4, size = 10, prob = 0.05))
sum(dbinom(x = 2:5, size = 10, prob = 0.05))
pbinom(q = 8, size = 10, prob = 0.05, lower.tail = T)
dbinom(x = 5, size = 10, prob = 0.05)

#Poisson Distribution; lambda = 0.5
dpois(x = 5, lambda = 0.5)
sum(dpois(x = 2:5, lambda = 0.5))
ppois(q = 8, lambda = 0.5, lower.tail = T)

#Normal Distribution; Exp = 3, Var = 1
dnorm(x = 5, mean = 3, sd = 1)
pnorm(q = 5, mean = 3, sd = 1)
1 - pnorm(8, mean = 3, sd = 1)
sum(dnorm(x = 0:6, mean = 3, sd = 1))

#Gamma Distribution; alpha = 3, beta = 1.5
pgamma(q = 10, shape = 3, rate = 1.5)
1 - pgamma(q = 9, shape = 3, rate = 1.5)
dgamma(x = 9, shape = 3, rate = 1.5)
sum(dgamma(x = 0:6, shape = 3, rate = 1.5))

# The function dnorm gives back the Probability Density Function of a Normal Distribution