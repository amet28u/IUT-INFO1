#!/bin/bash

echo "Voulez-vous continuer ?"
read $reponse
if [ $reponse ="N" ]||[ $reponse ="n" ]||[ $reponse ="no" ]||[ $reponse ="NO" ]
then
  exit 0
else
  exit 1
fi
