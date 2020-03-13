a=int(input("Entrez un entier positif : "))
while a<0:
    a=int(input("Entrez un entier positif : "))
    if a<0:
        print("Entier refusé !")
prec=int(input("Entrez une précision : "))
racine=a
racineprec=round(racine,prec)
while round(pow(racineprec,2),prec)!=a:
    racine=0.5*(racine+a/racine)
    racineprec=round(racine,prec)
print("Racine : ",racineprec)
