jour=int(input("Entrez le numéro du jour : "))
mois=int(input("Entrez le numéro du mois : "))
annee=int(input("Entrez le numéro de l'année : "))
bis=0
if annee%400==0 or annee%4==0:
    bis=1
if annee%100==0:
    bis=0
if mois==1 or mois==3 or mois==5 or mois==7 or mois==8 or mois==10 or mois==12:
    if jour>=1 and jour<=31:
        okdate=1
    else:
        okdate=0
if mois==4 or mois==6 or mois==9 or mois==11:
    if jour>=1 and jour<=30:
        okdate=1
    else:
        okdate=0
if mois==2:
    if bis==1:
        if jour>=1 and jour<=29:
            okdate=1
        else:
            okdate=0
    else:
        if jour>=1 and jour<=28:
            okdate=1
        else:
            okdate=0
if okdate==1:
    print("Date existante !")
else:
    print("Date impossible !")
