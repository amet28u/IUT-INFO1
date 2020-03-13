AFF=[]
CLE=["X","K","G","U","J","P","V","R","E","A","Y","B","N","D","O","F","S","Q","Z","C","W","M","L","I","T","H"]
chaine=input("Entrez une phrase qui sera codé : ")
taille=len(chaine)
for i in range(0,taille):
    if chaine[i]=="A":
        AFF.append(CLE[0])
    elif chaine[i]=="B":
        AFF.append(CLE[1])
    elif chaine[i]=="C":
        AFF.append(CLE[2])
    elif chaine[i]=="D":
        AFF.append(CLE[3])
    elif chaine[i]=="E":
        AFF.append(CLE[4])
    elif chaine[i]=="F":
        AFF.append(CLE[5])
    elif chaine[i]=="G":
        AFF.append(CLE[6])
    elif chaine[i]=="H":
        AFF.append(CLE[7])
    elif chaine[i]=="I":
        AFF.append(CLE[8])
    elif chaine[i]=="J":
        AFF.append(CLE[9])
    elif chaine[i]=="K":
        AFF.append(CLE[10])
    elif chaine[i]=="L":
        AFF.append(CLE[11])
    elif chaine[i]=="M":
        AFF.append(CLE[12])
    elif chaine[i]=="N":
        AFF.append(CLE[13])
    elif chaine[i]=="O":
        AFF.append(CLE[14])
    elif chaine[i]=="P":
        AFF.append(CLE[15])
    elif chaine[i]=="Q":
        AFF.append(CLE[16])
    elif chaine[i]=="R":
        AFF.append(CLE[17])
    elif chaine[i]=="S":
        AFF.append(CLE[18])
    elif chaine[i]=="T":
        AFF.append(CLE[19])
    elif chaine[i]=="U":
        AFF.append(CLE[20])
    elif chaine[i]=="V":
        AFF.append(CLE[21])
    elif chaine[i]=="W":
        AFF.append(CLE[22])
    elif chaine[i]=="X":
        AFF.append(CLE[23])
    elif chaine[i]=="Y":
        AFF.append(CLE[24])
    elif chaine[i]=="Z":
        AFF.append(CLE[25])
    elif chaine[i]==" ":
        AFF.append(" ")
    elif chaine[i]=="?":
        AFF.append("?")
    elif chaine[i]=="!":
        AFF.append("!")
for i in range(0,taille):
    print(AFF[i],end='')
    
    
