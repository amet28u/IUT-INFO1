#!/usr/bin/env bash

Usage() {
  echo "Usage : "
  echo –n "$0 login [-g groupe] [-r répertoire]"
  echo "[-s shell] [-i info] [-u uid] "
  exit
}

if [ $# -gt 10 ] || [ $# -eq 0 ]
then
  Usage
fi
case $2 in
  "-g")
    group=$2
    ;;
  "-r")
    rep=$2
    ;;
  "-s")
    shell=$2
    ;;
  "-i")
    info=$2
    ;;
  "-u")
    uid=$2
    ;;
  *)
    ;;
esac
case $3 in
  "-g")
    group=$3
    ;;
  "-r")
    rep=$3
    ;;
  "-s")
    shell=$3
    ;;
  "-i")
    info=$3
    ;;
  "-u")
    uid=$3
    ;;
  *)
    ;;
esac
case $4 in
  "-g")
    group=$4
    ;;
  "-r")
    rep=$4
    ;;
  "-s")
    shell=$4
    ;;
  "-i")
    info=$4
    ;;
  "-u")
    uid=$4
    ;;
  *)
    ;;
esac
case $5 in
  "-g")
    group=$5
    ;;
  "-r")
    rep=$5
    ;;
  "-s")
    shell=$5
    ;;
  "-i")
    info=$5
    ;;
  "-u")
    uid=$5
    ;;
  *)
    ;;
esac
case $6 in
  "-g")
    group=$6
    ;;
  "-r")
    rep=$6
    ;;
  "-s")
    shell=$6
    ;;
  "-i")
    info=$6
    ;;
  "-u")
    uid=$6
    ;;
  *)
    ;;
esac
case $1 in
  [!a-z]*)
    echo "Erreur: login incorrect"
    ;;
  *)
    ;;
esac
case $1 in
  *[!a-zA-Z0-9]*)
    echo "Erreur: caractères spéciaux"
    ;;
  *)
    ;;
esac

if ! grep $1 passwd
then
  echo "Erreur: fichier déjà utlisé"
fi
