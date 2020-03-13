#!/bin/bash

if [ ! -e Taux ]
then
  echo "Erreur: Fichier introuvable"
  exit 1
else
  if [ ! -r Taux ]
  then
    echo "Erreur: Fichier illisible"
    exit 1
  fi
fi
TAUX=$( cut -c 7-13 Taux )
if [ $# = 1 ]
then
  echo "Erreur: Nombre arguments"
  exit 2
fi
if [ $# = 2 ] ### Il peut -e et une somme ou erreur
then
  if [ $1 = '-e' ] && [ $2 != '[a-z][A-Z]' ]
  then
    RES=`echo $2*$TAUX | bc -l`
  else
    echo "Erreur: Arguments"
    exit 2
  fi
fi
if [ $# = 3 ] ### Il peut -f FILE -e ou erreur
  if [ $1 = '-e' ]
  then
    if [ $2 = '-f' ]
    then
      while ### Parcourir toutes les lignes
      FILE=$( cat $3 )





##  if [ $# -gt 2 ]
##  then
##    echo "Erreur: Nombre arguments/ options"
##    exit 2
##  else
##    RES=`echo $1/$TAUX | bc -l`
##  fi
##fi
echo $RES
