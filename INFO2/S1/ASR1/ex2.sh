#!/bin/bash

if test -d $1
then
  rep=1 #dossier
elif test -f $1
then
  rep=2 #fichier
else
  rep=9 #autreChose
fi

if test $2 = 'r'
then
  if test -r $1
  then
    exe=1 #accessLecture
  else
    exe=19 #PasAccessLecture
  fi
else
  if test $2 = 'w'
  then
    if test -w $1
    then
      exe=2 #accessEcriture
    else
      exe=29 #PasAccessEriture
    fi
  else
    if test $2 = 'x'
    then
      if test -x $1
      then
        exe=3 #accessExecut
      else
        exe=39 #PasAccessExecut
      fi
    fi
  fi
fi

case $rep in
  1) case $exe in
      1) echo "$1 est un dossier et est accessible en lecture";;
      19) echo "$1 est un dossier et n'est pas accessible en lecture";;
      2) echo "$1 est un dossier et est accessible en ecriture";;
      29) echo "$1 est un dossier et n'est pas accessible en ecriture";;
      3) echo "$1 est un dossier et est accessible en execution";;
      39) echo "$1 est un dossier et n'est pas accessible en execution";;
      *);;
    esac;;
  2) case $exe in
    1) echo "$1 est un fichier et est accessible en lecture";;
    19) echo "$1 est un fichier et n'est pas accessible en lecture";;
    2) echo "$1 est un fichier et est accessible en ecriture";;
    29) echo "$1 est un fichier et n'est pas accessible en ecriture";;
    3) echo "$1 est un fichier et est accessible en execution";;
    39) echo "$1 est un fichier et n'est pas accessible en execution";;
    *);;
  esac;;
  9) echo "Le fichier n'existe pas";;
  *);;
esac
