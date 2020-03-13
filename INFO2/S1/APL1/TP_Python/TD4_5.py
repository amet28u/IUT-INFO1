h=int(input("Entrez la hauteur du triangle : "))
i=1
while i<h:
    for j in range(0,i):
        print("*",end='')
    i=i+1
    print("")
i=h
while i<=h and i>0:
    for j in range(0,i):
        print("*",end='')
    i=i-1
    print("")
    
