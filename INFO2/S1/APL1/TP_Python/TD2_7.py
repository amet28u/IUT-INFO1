res=0
while res==0:
    a=int(input("Saisir un entier compris entre 10 et 20 :\n"))
    if a>20 or a<10:
        res=0
    else:
        res=1
    if a<10:
        print("Plus grand !\n")
    if a>20:
        print("Plus petit !\n")
