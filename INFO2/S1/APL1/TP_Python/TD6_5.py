T=[]
C=[]
N = int(input("Entrez la taille du tableau : "))

for i in range(1, N+1):
    T.append(i)

print("Tableau de tous les entiers de 1 à ",N," :",T)

for i in range(0, N+1):
    for j in range(2, i):
        if T[i-1]%j==0:
            T[i-1]=0

for i in range(0, N):
    for j in range(0, i):
        if T[i]!=0:
            C.append(T[i])
        break
        
print("Le chiffre 1 n'est pas premier par convention")
print(C)
