T=[]
U=[]
nbr=0
taille=int(input("Indiquez le nombre d'éléments que vous voulez insérer dans le tableau : "))
for i in range(1,taille+1):
    T.append(int(input("Entrez un élément : ")))
print("Taille : ",taille)
occ=int(input("Entrez le chiffre dont vous voulez connaitre le nombre d'occurence : "))
for i in range(0,taille):
    if occ==T[i]:
        nbr=nbr+1
print("Nombre d'occurences : ",nbr)
add=int(input("Entrez le chiffre que vous voulez ajouter : "))
pos=int(input("Entrez la position où vous voulez le placer : "))
T.append(0)
tmp=T[pos-1]
T[pos-1]=add
taille=taille+1
if pos%2==0:
    for i in range(pos,taille):
        if i%2==0:
            tmp2=T[i]
            T[i]=tmp
        else:
            tmp=T[i]
            T[i]=tmp2
else:
    for i in range(pos,taille):
        if i%2==1:
            tmp2=T[i]
            T[i]=tmp
        else:
            tmp=T[i]
            T[i]=tmp2
print(T)
delocc=int(input("Entrez le chiffre dont vous voulez supprimer les occurences : "))
for i in range(0,taille):
    if T[i]!=delocc:
        U.append(T[i])
print("Occurences supprimées !")
print(U)
        
