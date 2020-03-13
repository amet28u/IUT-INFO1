Alpha=[]
chaine=input("Entrez une phrase en majuscules : ")
taille=len(chaine)
for i in range(0,taille):
    if chaine[i]=="A":
        Alpha.append("B")
    elif chaine[i]=="B":
        Alpha.append("C")
    elif chaine[i]=="C":
        Alpha.append("D")
    elif chaine[i]=="D":
        Alpha.append("E")
    elif chaine[i]=="E":
        Alpha.append("F")
    elif chaine[i]=="F":
        Alpha.append("G")
    elif chaine[i]=="G":
        Alpha.append("H")
    elif chaine[i]=="H":
        Alpha.append("I")
    elif chaine[i]=="I":
        Alpha.append("J")
    elif chaine[i]=="J":
        Alpha.append("K")
    elif chaine[i]=="K":
        Alpha.append("L")
    elif chaine[i]=="L":
        Alpha.append("M")
    elif chaine[i]=="M":
        Alpha.append("N")
    elif chaine[i]=="N":
        Alpha.append("O")
    elif chaine[i]=="O":
        Alpha.append("P")
    elif chaine[i]=="P":
        Alpha.append("Q")
    elif chaine[i]=="Q":
        Alpha.append("R")
    elif chaine[i]=="R":
        Alpha.append("S")
    elif chaine[i]=="S":
        Alpha.append("T")
    elif chaine[i]=="T":
        Alpha.append("U")
    elif chaine[i]=="U":
        Alpha.append("V")
    elif chaine[i]=="V":
        Alpha.append("W")
    elif chaine[i]=="W":
        Alpha.append("X")
    elif chaine[i]=="X":
        Alpha.append("Y")
    elif chaine[i]=="Y":
        Alpha.append("Z")
    elif chaine[i]=="Z":
        Alpha.append("A")
    elif chaine[i]==" ":
        Alpha.append(" ")
    elif chaine[i]=="?":
        Alpha.append("?")
    elif chaine[i]=="!":
        Alpha.append("!")
for i in range(0,taille):
    print(Alpha[i],end='')
