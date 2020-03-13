n=int(input("Entrez n : "))
u1=1
u2=1
for i in range(1,n+1):
    u=u1+u2
    u1=u2
    u2=u
print(u)
