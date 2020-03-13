h=int(input("Hauteur du triangle : "))
for i in range(1,h+1):
    for j in range(2,i+1):
        if j==2 or j==i:
            print("x",end='')
        if j!=2 and j!=i:
            print("o",end='')
    print("")
for i in range(1,h+1):
    print("x",end='')




