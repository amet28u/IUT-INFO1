a=int(input("Entrez un age :\n"))
if a<6:
    print("Tu n'as pas de categorie\n")
else:
    if a>=6 and a<8:
        print("Tu es un poussin\n")
    else:
        if a>=8 and a<10:
            print("Tu es un pupille\n")
        else:
            if a>=10 and a<12:
                print("Tu es un minime\n")
            else:
                print("Tu es un cadet\n")
