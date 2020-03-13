#!/usr/bin/env bash

Affichage(){
  echo "Bienvenue novice !"
  echo "Voici les commandes de bases :"
  echo "ls : Liste le contenu d'un répertoire, taper 1"
  echo "cat : Liste le contenu d'un fichier, taper 2"
  echo "cd : Change de répertoire, taper 3"
  echo "touch : Créer un fichier, taper 4"
  echo "rm : Supprime un fichier, taper 5"
  echo "mkdir : Créer un dossier, taper 6"
  echo "rmdir : Supprime un dossier, taper 7"
  echo "quitter : taper 8"
}

clear
while true
do
  Affichage
  read choix
  case $choix in
    1)  echo "Entrez le nom du dossier :"
        read tmp
        ls $tmp
        ;;
    2)  echo "Entrez le nom du fichier :"
        read tmp
        cat $tmp
        ;;
    3)  echo "Entrez le nom du dossier :"
        read tmp
        cd $tmp
        ;;
    4)  echo "Entrez un nom de fichier :"
        read tmp
        touch $tmp
        ;;
    5)  echo "Entrez un nom de fichier :"
        read tmp
        rm $tmp
        ;;
    6)  echo "Entrez le nom du dossier :"
        read tmp
        mkdir $tmp
        ;;
    7)  echo "Entrez le nom du dossier :"
        read tmp
        rmdir $tmp
        ;;
    8)  exit 0
        ;;
    *)
        ;;
  esac
done
