# Introduction to R
# Library / package
library(datasets)
library(help = "datasets")

# Calculator
x=10^2
y=2*x
z=y-5
d=z/5


# Vectors
vec2 = c(1,21,50,80,45,0)


# Matrices
mymatrix=matrix(data = vec1,nrow = 2)


# Data frames
t=data.frame(x=rnorm(100),y=rnorm(100,mean=8))
summary(t)
x=t$x
y=t$y
# R Datasets
mdata=cars

# Math/Stat Functions 
sum(vec1)
mean(vec1)
sd(vec1)
min(vec1)
max(vec1)
abs(vec1)
sqrt(vec1)
2**9
2^9
exp(1)
log(1)
log10(10)
log2(2)
log(45,base = 45)
factorial(3)
choose(10,5)
summary(vec1)


# Probability Distibutions

#Binomial
#************************
# P(Y=3)
dbinom(x = 3, size = 100, prob = 0.05)
#P(X=46)
dbinom(x = 46, prob = 0.5,size=100)

# Compute P(45 < X < 55) for X Binomial(100,0.5)

dbinom(46:54,100,0.5)
sum(dbinom(46:54, 100, 0.5))

# CDF 
pbinom(q = 50, 100, 0.5,lower.tail = TRUE)

# ICDF 
qbinom(p = c(0.25,0.5,0.75), size = 100, prob = 0.5, lower.tail = T)
#P(Y<=y_0)=0.5

#Generate random numbers
rb=rbinom(n =500,size = 10,prob = 0.05)

# Histogram
hist(rb,freq = T)


#Geometric
#************************
datageo=rgeom(n = 10,prob = 0.5)

# Poisson
#************************
pdata=rpois(n = 1000,lambda = 20)
mean(pdata)
var(pdata)
dpois(x = 10,lambda = 2)



# Normal Distribution
#************************
x=rnorm(100,mean=1,sd = 2)
pnorm(0,mean = 0,sd=1)

pnorm(5,mean=2,sd=1.2)

qnorm(0.5)



# Uniform
#************************
x=runif(10,min = 0,max = 1)
punif(8,min = 0,max = 100)

hist(runif(10000))

#Exponential
#************************
expdata=rexp(1000,rate = 1)
hist(expdata)
pexp(10,rate = 1/2)

dexp(10,rate = 1)

# Gamma
#************************
pgamma(q = 10,shape = 1,scale = 2)
hist(rgamma(1000,shape=3,scale=2))





