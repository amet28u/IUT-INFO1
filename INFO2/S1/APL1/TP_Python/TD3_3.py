n=int(input("Saisir n : "))
s1=1
s2=1
for i in range(1,n):
    s1=s1+(1/(i+1))
for j in range(1,n):
    if j%2==0:
        s2=s2+(1/(j+1))
    else:
        s2=s2-(1/(j+1))
print("S1 : ",s1," S2 : ",s2)
