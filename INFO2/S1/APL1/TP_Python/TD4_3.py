cpt=0
for dix in range(0,11):
    for cinq in range(0,21):
        for deux in range(0,51):
            if dix*10+cinq*5+deux*2==100:
                print("1E = ",deux,"*2c + ",cinq,"*5c + ",dix,"*10c\n")
                cpt=cpt+1
print("Possibilités trouvés : ",cpt,"\n")
