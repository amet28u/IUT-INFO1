T0=[]
T1=[4,5,6]
T2=[1,2,3]
T0=T1+T2
print("Tableau entier",T0)
print("Premiere partie",T1)
print("Deuxieme partie",T2)
taille1=len(T1)
for i in range(0, taille1//2):
    tmp=T1[i]
    T1[i]=T1[taille1-1-i]
    T1[taille1-1-i]=tmp
taille2=len(T2)
for i in range(0, taille2//2):
    tmp=T2[i]
    T2[i]=T2[taille2-1-i]
    T2[taille2-1-i]=tmp
T0=T1+T2
taille0=len(T0)
for i in range(0, taille0//2):
    tmp=T0[i]
    T0[i]=T0[taille0-1-i]
    T0[taille0-1-i]=tmp
print("Tableau entier avec l'inversement",T0)
