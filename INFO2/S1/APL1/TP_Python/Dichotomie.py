#CREATION DE TABLEAU
N=int(input("Entrez une taille pour le tableau : "))
T=[]
for i in range(0,N):
    T.append(i)
    
#SOLUTION NAIVE
def solNaif(T,R):
    taille=len(T)
    i=0
    while 1:
        if R<T[i]:
            if i==0:
                return -1
            else:
                if R!=T[i-1]:
                    return -1
        if R>T[i]:
            if i==taille-1:
                return -1
            else:
                i=i+1
        if R==T[i]:
            return i

#DICHOTOMIE
taille=len(T)
arret=0
cpt2=0
deb=0
fin=taille-1
C=int(input("Entrer un entier C : "))
while arret==0:
    pivot=(fin+deb)//2
    if C>T[pivot]:
        deb=pivot+1
    if C<T[pivot]:
        fin=pivot-1
    if C==T[pivot]:
        print("Entier trouvé à la position : ",pivot)
        arret=1
    if C<T[0]:
        print("Entier non trouvé, erreur 1")
        arret=2
    if C>T[taille-1]:
        print("Entier non trouvé, erreur 2")
        arret=2
    cpt2=cpt2+1
print("Compteur dichotomique : ",cpt2)

