T=[9,8,7,6,5,4,3,2,1]
taille=len(T)
print(T)
for i in range(0, taille//2):
    tmp=T[i]
    T[i]=T[taille-1-i]
    T[taille-1-i]=tmp
print(T)
