find=0;
for i in range(1,100):
    if i%2==1:
        if i%3==2:
            if i%4==3:
                if i%5==4:
                    print(i," est resout toutes ces equations\n")
                    find=1
if find==0:
    print("Aucun nombre ne resout ces equations dans cet interval\n")
