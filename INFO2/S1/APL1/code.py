#Initialisation taille
file=open("source.txt", "r")
size=len(file.read())
file.close()
#Initialisation Tableaux
tab=[]
bat=[]
for i in range(size):
    tab.append(0)
    bat.append(0)

#Affectation Tableaux
file=open("source.txt", "r") 
for i in range(size):
    tab[i]=file.read(1)
    bat[i]=ord(tab[i])
file.close()

x=int(input("Entrez la valeur de deplacement : "))

#Traitement Tableaux
for i in range(size):
    bat[i]+=x
    tab[i]=chr(bat[i])
    
file = open("source.txt", "r")
print(file.read())
file.close()

#Creation fichier
file = open("source_cod.txt", "w")
for i in range(size):
    file.write(tab[i])
file.close()

file = open("source_cod.txt", "r")
print(file.read())
file.close()

def codage(size,x):
    tab=[]
    bat=[]
    file=open("source.txt", "r")
    for i in range(size):
        tab[i]=file.read(1)
        bat[i]=ord(tab[i])
    file.close()
    for i in range(size):
        bat[i]=+x
        tab[i]=chr(bat[i])
    file = open("source_cod.txt", "w")
    for i in range(size):
        file.write(tab[i])
    file.close()





