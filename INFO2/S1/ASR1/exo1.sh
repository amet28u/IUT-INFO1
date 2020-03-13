#!/bin/bash

echo '########################################## Information sur la ligne de commande'

echo La ligne de commande comporte $# paramètres

echo Exécution de la commmande: $0

echo Avec les paramètres: $1 $2 $3

echo '########################################## L utilisateur' $1 's est connecté'

last >last

wc -l < last

echo 'fois sur'

hostname

echo 'Ces 3 dernières connexions ont été aux dates suivantes:'

last $1 | head -n3 | cut -c 37-52
