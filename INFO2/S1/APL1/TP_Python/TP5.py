def echange(T,i,j):
    tmp=T[i]
    T[i]=T[j]
    T[j]=tmp
    return T

def iminT(T,deb,fin):
    mini=T[deb]
    iMin=0
    for i in range(deb,fin):
        if mini>=T[i]:
            mini=T[i]
            iMin=i
    return iMin

def triBulle(T):
    taille=len(T)
    for i in range(taille-1,0,-1):
        j=taille-1
        while T[j]<T[j-1] and j!=0:
            tmp=T[j]
            T[j]=T[j-1]
            T[j-1]=tmp
            j=j-1
    return T

def triInsert(T):
    taille=len(T)
    for i in range(0,taille-1):
        if T[i]>T[i+1]:
            j=i
            if j>=0:
                while j>=0 and T[j]>T[j+1]:
                    tmp=T[j]
                    T[j]=T[j+1]
                    T[j+1]=tmp
                    j=j-1
    return T

def triSelect(T):
    taille = len(T)
    for i in range(0,taille): 
        T = echange(T,i,iminT(T,i,taille))
    return T

def triCpt(T):
    cpt = 10 * [0]
    for i in range(0,len(T)):
        cpt[T[i]] = cpt[T[i]] +1
    rg = 0
    for j in range(0,9):
        while cpt[j] > 0:
            T[rg] = j
            rg = rg + 1 
            cpt[j] = cpt[j-1]
    return T

def triRdx(T):
    start = time.time()
    N = len(T)
    maxDigitt = maxDigit(max(T))
    
    for i in range(maxDigitt):
        tableTri = [[],[],[],[],[],[],[],[],[],[]]
        for j in range(N):
            dig = digit(T[j],i+1)
            tableTri[dig].append(T[j]) 
        rang=0
        for k in range(len(tableTri)):
            for l in range(len(tableTri[k])):
                T[rang] = tableTri[k][l]
                rang += 1
    end = time.time()
    print("La fonction triRadix a mis ", end - start,"seconde à s'éxécuter")
    return T

def digit(n,d):
  for i in range(d-1):
    n //= 10
  return n % 10

def maxDigit(n):
    count = 0
    while (n > 0):
        n = n//10
        count = count + 1
    return count

        
D=[4,3,2,1,0]
print("Tableau initial : ",D)
triBulle(D)
print(D)

D=[4,3,2,1,0]
print("Tableau initial : ",D)
triInsert(D)
print(D)

D=[4,3,2,1,0]
print("Tableau initial : ",D)
triSelect(D)
print(D)

D=[4,3,2,1,0]
print("Tableau initial : ",D)
triCpt(D)
print(D)

D=[4,3,2,1,0]
print("Tableau initial : ",D)
triRdx(D)
print(D)
