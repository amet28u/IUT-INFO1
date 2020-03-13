
import math

a=float(input("Saisir a :\n"))
b=float(input("Saisir b :\n"))
c=float(input("Saisir c :\n"))
delta=pow(b,2)-4*a*c
if delta>0:
    sol1=-b-math.sqrt(delta)/(2*a)
    sol2=-b+math.sqrt(delta)/(2*a)
    print("Racines : ",sol1," et ",sol2,"\n")
if delta==0:
    sol=-b/(2*a)
    print("Racine : ",sol,"\n")
if delta<0:
    print("Aucune racines dans R\n")
