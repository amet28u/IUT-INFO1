cpt=0
chaine=input("Entrez une phrase, chaque mots séparés par un espace : ")
taille=len(chaine)
for i in range(0,taille):
    if chaine[i]==" ":
        cpt=cpt+1
print("Nombre de mots dans cette phrase : ",cpt+1)
