a=int(input("Entrez a :\n"))
b=int(input("Entrez b :\n"))
c=int(input("Entrez c :\n"))
res=0
if a<b+c:
    res=res+1
    if b<a+c:
        res=res+1
        if c<b+a:
            res=res+1
if res==3:
    print("C'est un triangle\n")
else:
    print("Ce n'est pas un triangle\n")
