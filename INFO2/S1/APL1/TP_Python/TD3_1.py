b=int(input("Entrez un nombre strictement compris entre 50 et 100 :\n"))
erreur=0
if b<=50 or b>=100:
    print("Erreur\n")
    erreur=1
else:
    a=b+62
    print(a)
if erreur!=1:
    a=a-100
    a=a+1
    print(a)
    a=b-a
    print(a)
    if a==37:
        print("37 est bien trouvé pour a\n")
    
