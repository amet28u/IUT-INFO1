#!/usr/bin/env bash

PATH_DIRECTORY=/Users/antony/.Trash
if [ $# -gt 2 ]
then
  echo "usage: $0 [-C ]"
  exit 1
else
  if [ $# -eq 2 ]
  then
    if [ $1 = "-C" ]
    then
      for fichier in `ls $PATH_DIRECTORY`
      do
        rm $PATH_DIRECTORY/$fichier
      done
    else
      echo "usage: $0 [-C ]"
      exit 1
    fi
  else
    if [ -e $PATH_DIRECTORY ]
    then
      echo "- Afficher la poubelle: 1"
      echo "- Supprimer un fichier: 2"
      echo "- Restaurer un fichier: 3"
      echo "- fin: 4"
      echo "Votre choix:"
      read choix
    else
      echo "error: $PATH_DIRECTORY do not exist"
      exit 2
    fi
  fi
fi
