----------------------
ACD1 - TP6
LORENZELLI ANTONY
02 04 2019
----------------------

2 façon de faire des Exceptions :

Ajouter après la défintion de la méthode avant l'accolade "throws ExceptionMere"
Dans une fonction mettre en instructions : "throw new ExceptionFille()"

ou :

Dans la méthode mettre l'instruction suscpetible de créer l'Exception dans
un try{} et mettre à la suite un catch(Exception e){} qui s'éxécute si le
try fait l'Exception.

Les exceptions permettent d'effectuer une instruction (qui est suceptible de
 générer une exception) et soit d'effectuer normalement l'instructions s'il
 n'y a pas de problème, soit d'aller chercher l'exception.

 Exercice 3 :

 Création de GeometrieException héritant de Exception
 Création de PointsConfondusException héritant de GeometrieException
 Rajout dans le constructeur d'un triangle des test entre les différents
 sommets, s'ils sont pareil, alors cela jète l'exception PointsConfondusException
