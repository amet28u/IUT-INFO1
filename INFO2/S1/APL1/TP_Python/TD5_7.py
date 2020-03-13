cpt=0
chaine=input("Entrez une phrase : ")
taille=len(chaine)
for i in range(0,taille):
    if chaine[i]=="a" or chaine[i]=="e" or chaine[i]=="i" or chaine[i]=="o" or chaine[i]=="u" or chaine[i]=="y":
        cpt=cpt+1
print("Nombre de voyelle dans cette phrase : ",cpt)
        
        
