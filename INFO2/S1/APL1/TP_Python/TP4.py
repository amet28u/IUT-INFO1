def factorielI(n):
    if n==0:
        return 1
    for i in range(n-1,0,-1):
        n=n*i
    return n

def factorielR(n):
    if n==0:
        return 1
    if n==1:
        return 1
    return factorielR(n-1)*n

def puissanceI(x,n):
    res=1
    for i in range(0,n):
        res=res*x
    return res

def puissanceR(x,n):
    if n==0:
        return 1
    return puissanceR(x,n-1)*x

def reverseI(char):
    T=[]
    taille=len(char)
    for i in range(taille-1,-1,-1):
        T.append(char[i])
    for i in range(0,taille):
        print(T[i],end='')

def reverseR(char):
    if len(char)==1:
        return char
    if len(char)==2:
        return char[1]+char[0]
    return char[len(char)-1]+reverseR(char[1:-1])+char[0]

def pgcdI(a,b):
    r=1
    while r!=0:
        q=a//b
        r=a%b
        if r==0:
            return b
        else:
            a=b
            b=r
    
def pgcdR(a,b):
    if a%b==0:
        return b
    return pgcdR(b,a%b)

def fiboI(n):
    if n==1 or n==2:
        return 1
    u1=1
    u2=1
    for i in range(0,n-2):
        u=u1+u2
        u1=u2
        u2=u
    return u

def fiboR(n):
    if n==1 or n==2:
        return 1
    return fiboR(n-1)+fiboR(n-2)


r=6
t=7
z=1
x=0
c=2
print("ITERATIF")
print("Factoriel de 6 : ",factorielI(r))
print("Factoriel de 7 : ",factorielI(t))
print("Factoriel de 1 : ",factorielI(z))
print("Factoriel de 0 : ",factorielI(x))
print("Factoriel de 2 : ",factorielI(c))
print("Factoriel de 3 : ",factorielI(3))
print("RECURSIF")
print("Factoriel de 6 : ",factorielR(r))
print("Factoriel de 7 : ",factorielR(t))
print("Factoriel de 1 : ",factorielR(z))
print("Factoriel de 0 : ",factorielR(x))
print("Factoriel de 2 : ",factorielR(c))
print("Factoriel de 3 : ",factorielR(3))
print("ITERATIF")
print("7 à la puissance 2 ",puissanceI(t,c))
print("3 à la puissance 3 ",puissanceI(3,3))
print("RECURSIF")
print("7 à la puissance 2 ",puissanceR(t,c))
print("3 à la puissance 3 ",puissanceR(3,3))
print("ITERATIF")
ch=input("Entrez une chaine de caractere : ")
reverseI(ch)
print("")
print("RECURSIF")
ch=input("Entrez une chaine de caractere : ")
print(reverseR(ch))
print("ITERATIF")
print("3eme élément de la suite de Fibonacci : ",fiboI(3))
print("4eme élément de la suite de Fibonacci : ",fiboI(4))
print("RECURSIF")
print("3eme élément de la suite de Fibonacci : ",fiboR(3))
print("4eme élément de la suite de Fibonacci : ",fiboR(4))
print("ITERATIF")
print("PGCD(678,94) : ",pgcdI(678,94))
print("RECURSIF")
print("PGCD(678,94) : ",pgcdR(678,94))
