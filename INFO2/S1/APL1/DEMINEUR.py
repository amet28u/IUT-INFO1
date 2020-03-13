# fichier DEMINEUR.py

import random
import time
import datetime

print("Bienvenue sur le jeu du Démineur !")
print("Réalisé par Johan Lahougue et Antony Lorenzelli")

def generateur(size, T, bomb):
#Tableau
    T = [[0]*size for _ in range(size)]
#Bombes
    PosAbs=random.sample(range(size*size), bomb)
    PosCoo=[(x%size, x//size) for x in PosAbs]
    for p in PosCoo:
        T[p[0]][p[1]]=-9
#Numéros
    for i in range(size):
        for j in range(size):
            if T[i][j]<0:
                if i==0:
                    if j==0:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j+1]=T[i+1][j+1]+1
                        T[i][j+1]=T[i][j+1]+1
                    elif j<size-1:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j+1]=T[i+1][j+1]+1
                        T[i+1][j-1]=T[i+1][j-1]+1
                        T[i][j+1]=T[i][j+1]+1
                        T[i][j-1]=T[i][j-1]+1
                    elif j==size-1:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j-1]=T[i+1][j-1]+1
                        T[i][j-1]=T[i][j-1]+1
                elif i<size-1:
                    if j==0:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j+1]=T[i+1][j+1]+1
                        T[i][j+1]=T[i][j+1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j+1]=T[i-1][j+1]+1
                    elif j<size-1:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j+1]=T[i+1][j+1]+1
                        T[i+1][j-1]=T[i+1][j-1]+1
                        T[i][j+1]=T[i][j+1]+1
                        T[i][j-1]=T[i][j-1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j+1]=T[i-1][j+1]+1
                        T[i-1][j-1]=T[i-1][j-1]+1
                    elif j==size-1:
                        T[i+1][j]=T[i+1][j]+1
                        T[i+1][j-1]=T[i+1][j-1]+1
                        T[i][j-1]=T[i][j-1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j-1]=T[i-1][j-1]+1
                elif i==size-1:
                    if j==0:
                        T[i][j+1]=T[i][j+1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j+1]=T[i-1][j+1]+1
                    elif j<size-1:
                        T[i][j+1]=T[i][j+1]+1
                        T[i][j-1]=T[i][j-1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j+1]=T[i-1][j+1]+1
                        T[i-1][j-1]=T[i-1][j-1]+1
                    elif j==size-1:
                        T[i][j-1]=T[i][j-1]+1
                        T[i-1][j]=T[i-1][j]+1
                        T[i-1][j-1]=T[i-1][j-1]+1
    return T

def gestionScore(score, date, S):
    S[0].append(score)
    S[1].append(date)
    k=0
    for i in range(len(S[0])):
        mini=S[0][i]
        for j in range(i,len(S[0])):
            if S[0][j]<mini:
                mini=S[0][j]
                k=j
            else:
                k=i
        S[0][k]=S[0][i]
        S[0][i]=mini
        tmp=S[1][k]
        S[1][k]=S[1][i]
        S[1][i]=tmp

    return S

def CaseVide(y, x, size, AffGrille, DonneeGrille):
    if 0<=x<size or 0<=y<size:
        if AffGrille[y][x]=='x':
            if DonneeGrille[y][x]==0:
                AffGrille[y][x]=' '
                if y==0 and x==0:
                    #Haut gauche
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x+1,size,AffGrille,DonneeGrille)
                elif y==0 and x<size-1:
                    #Haut
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x+1,size,AffGrille,DonneeGrille)
                elif y==0 and x==size-1:
                    #Haut droit
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                elif y<size-1 and x==0:
                    #Centre gauche
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x+1,size,AffGrille,DonneeGrille)
                elif y<size-1 and x<size-1:
                    #Centre
                    CaseVide(y-1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x+1,size,AffGrille,DonneeGrille)
                elif y<size-1 and x==size-1:
                    #Centre droit
                    CaseVide(y-1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y+1,x,size,AffGrille,DonneeGrille)
                elif y==size-1 and x==0:
                    #Bas gauche
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                elif y==size-1 and x<size-1:
                    #Bas
                    CaseVide(y-1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x+1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y,x+1,size,AffGrille,DonneeGrille)
                elif y==size-1 and x==size-1:
                    #Bas droit
                    CaseVide(y-1,x-1,size,AffGrille,DonneeGrille)
                    CaseVide(y-1,x,size,AffGrille,DonneeGrille)
                    CaseVide(y,x-1,size,AffGrille,DonneeGrille)
            else:
                AffGrille[y][x]=DonneeGrille[y][x]

def affichage(size, T):
    #Numérotation haut affichage
    print("      ",end='')
    for i in range(size):
        if i<9:
            print("0",end='')
        print(i+1,'',end='')
    print()
    #Haut affichage
    print("     ",end='')
    for i in range(3*size):
        print("-",end='')
    print()
    #Grille et numérotation droite/gauche
    for i in range(size):
        if i<9:
            print("0",end='')
        print(i+1,'',end="")
        print("|  ",end='')
        for j in range(size):
            print(T[i][j]," ",end='')
        print("| ",end='')
        if i<9:
            print("0",end='')
        print(i+1)
    #Bas affichage
    print("     ",end='')
    for i in range(3*size):
        print("-",end='')
    print()
    #Numérotation bas affichage
    print("      ",end='')
    for i in range(size):
        if i<9:
            print("0",end='')
        print(i+1,'',end='')
    print()

def jouer(Score):
    perdu=0
    DonneeGrille=[]
    AffGrille=[]
    diff=int(input("Veuillez choisir la difficulté (1/2/3) : "))
    size=int(input("Veuillez entrer la taille de la grille (30/40) : "))
    if diff==1:
        #Choix diffculté 1
        nbBombes=size
        DonneeGrille=generateur(size, DonneeGrille, nbBombes)
        AffGrille=[["x"] * size for _ in range(size)]
    elif diff==2:
        #Choix diffculté 2
        nbBombes=2*size
        DonneeGrille=generateur(size, DonneeGrille, nbBombes)
        AffGrille=[["x"] * size for _ in range(size)]
    elif diff==3:
        #Choix difficulté 3
        nbBombes=3*size
        DonneeGrille=generateur(size, DonneeGrille, nbBombes)
        AffGrille=[["x"] * size for _ in range(size)]
    else:
        print("Difficulté invalide")
        perdu=1
    start_time=time.time() #Début du décompte
    cpt=0
    while perdu==0:
        affichage(size, AffGrille)
        op=input("Voulez-vous découvrir une case ou placer un drapeau ? (Entrée/!)")
        x=-10
        y=-10
        #Sélection coordonnées
        while x<0 or x>size:
            x=int(input("Entrez la coordonnée horizontale : "))-1
        while y<0 or y>size:
            y=int(input("Entrez la coordonnée verticale : "))-1
        if DonneeGrille[y][x]<0:
            #Bombe
            if op=='!':
                #Drapeau
                if AffGrille[y][x]=='x':
                    AffGrille[y][x]='!'
                elif AffGrille[y][x]=='!':
                    AffGrille[y][x]='x'
            else:
                AffGrille[y][x]='B'
                affichage(size, AffGrille)
                end_time=time.time() #Fin du décompte
                print("GAME OVER, nombre de coups :", cpt+1,", temps passé :", round(end_time-start_time)," secondes")
                perdu=1 #Fin du jeu
        elif DonneeGrille[y][x]>0:
            #Bombe(s) proche(s)
            if op=='!':
                #Drapeau
                if AffGrille[y][x]=='x':
                    AffGrille[y][x]='!'
                elif AffGrille[y][x]=='!':
                    AffGrille[y][x]='x'
                else:
                    print("Case déjà découverte, elle ne peut pas être une bombe !")
            else:
                AffGrille[y][x]=DonneeGrille[y][x]
        elif DonneeGrille[y][x]==0:
            #Case Vide
            if op=='!':
                #Drapeau
                if AffGrille[y][x]=='x':
                    AffGrille[y][x]='!'
                elif AffGrille[y][x]=='!':
                    AffGrille[y][x]='x'
                else:
                    print("Case déjà découverte, elle ne peut pas être une bombe !")
            else:
                CaseVide(y,x,size,AffGrille,DonneeGrille)
        cpt=cpt+1
        #On compte le nombre de cases découverte à chaque fois que l'on
        #découvre une case, et on arrête le jeu si la partie est gagnée
        casedec=0
        for i in range(size):
            for j in range(size):
                if AffGrille[i][j] != 'x':
                    casedec=casedec+1
        if casedec == (size*size)-size:
            affichage(size, AffGrille)
            end_time=time.time() #Fin du décompte
            date=datetime.datetime.now() #Récupération de la date
            Score=gestionScore((round(end_time-start_time)), date.strftime("%X"), Score) #MàJ tableau Score
            print("Félicitations, grille déminée !\nNombre de coups :", cpt,"\ntemps passé :", round(end_time-start_time)," secondes")
            perdu=1 #Fin du jeu
    tabS=input("Voulez-vous voir le tableau de score ? (o/n) : ")
    if tabS=='o' or tabS=='O':
        print(Score)
    #Rejouer
    rejouer=input("Voulez-vous rejouer ? (o/n) : ")
    while rejouer!='O' and rejouer!='o' and rejouer!='N' and rejouer!='n':
        print("Veuillez entrer une réponse correcte (o/n) : ")
        rejouer=input("Voulez-vous rejouer ? (o/n) : ")
    if rejouer=='O' or rejouer=='o':
        jouer(Score)

Score=[[0]*1 for _ in range(2)]
jouer(Score)
