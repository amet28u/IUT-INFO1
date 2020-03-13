a=-5
mini=2*pow(a,3)-pow(a,2)-37*a+36
a=5
maxi=2*pow(a,3)-pow(a,2)-37*a+36
i=-5
while i<=5:
    k=2*pow(a,3)-pow(a,2)-37*a+36
    if mini>k:
        mini=k
    if maxi<k:
        maxi=k
    i=i+0.25
print("Maximum et minimum : ",maxi," et ",mini,"\n")
