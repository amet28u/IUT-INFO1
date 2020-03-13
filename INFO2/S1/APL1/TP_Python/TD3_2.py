n=int(input("Saisir n :\n"))
if n>=2:
    a=int(input("Saisir un nombre :\n"))
    b=int(input("Saisir un nombre :\n"))
    mini=min(a,b)
    maxi=max(a,b)
if n>=3:
    for i in range(1,n-2):
        a=int(input("Saisir un nombre :\n"))
        if a<mini:
            mini=a
        if a>maxi:
            maxi=a
print("Maximum atteint en :",maxi,"\n")
print("Minimum atteint en :",mini,"\n")
    
    
