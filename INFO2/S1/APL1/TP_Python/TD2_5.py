a=float(input("Entrez a :\n"))
b=float(input("Entrez b :\n"))
c=input("Donnez un caractere parmis : +, -, * ou / :\n")
if c=="+":
    res=a+b
else:
    if c=="-":
        res=a-b
    else:
        if c=="*":
            res=a*b
        else:
            res=a/b
print("Le resultat de ",a," ",c," ",b," est egal a : ",res,"\n")
