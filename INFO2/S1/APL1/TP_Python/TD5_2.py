a=int(input("Saisir un entier : "))
n=1
p=1
while a>n:
    n=2*n
    p=p+1
b=a
while b>0:
    if b-n>=0:
        b=b-n
        n=n/2
        print("1",end='')
    elif b-n<0:
        n=n/2
        print("0",end='')
while n>=1:
    print("0",end='')
    n=n/2
