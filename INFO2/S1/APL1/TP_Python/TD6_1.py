T=[5,5,3,8,4]
taille=len(T)
tri=1
i=0
croissant=2
print(T)
while i<taille-1:
    if T[i]<=T[i+1]:
        croissant=1
    if croissant==1 and T[i]>T[i+1]:
        tri=0
    if T[i]>=T[i+1]:
        croissant=0
    if croissant==0 and T[i]<T[i+1]:
        tri=0
    i=i+1
if tri==0:
    print("Tableau non trié")
else:
    if croissant==1:
        print("Tableau trié dans l'ordre croissant")
    else:
        print("Tableau trié dans l'ordre décroissant")
