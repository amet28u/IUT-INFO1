T=[4,5,4,6,5,4]
C=[]
find=0
taille=len(T)

print(T)

for i in range(0, taille):
    tailleC=len(C)
    find=0
    for j in range(0, tailleC):
        if C[j]!=T[i]:
            find=find+0
        else:
            find=find+1
    if find==0:
        C.append(T[i])
        
print(C)
        


        

    
    
        
        
