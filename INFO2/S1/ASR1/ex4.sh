#!/bin/bash

set `date`
case $1 in
  'Lun') jour='Monday';;
  'Mar') jour='Tuesday';;
  'Mer') jour='Wednesday';;
  'Jeu') jour='Thursday';;
  'Ven') jour='Friday';;
  'Sam') jour='Saturday';;
  'Dim') jour='Sunday';;
  *);;
esac
case $3 in
  'jan') mois='january';;
  'fev') mois='february';;
  'mar') mois='march';;
  'avr') mois='april';;
  'mai') mois='may';;
  'jui') mois='june';;
  'juil') mois='july';;
  'aou') mois='agust';;
  'sep') mois='september';;
  'oct') mois='october';;
  'nov') mois='november';;
  'dec') mois='december';;
  *);;
esac
if [ $2 = 1 ] || [ $2 = 21 ] || [ $2 = 31 ]
then
  suffixe='st'
elif [ $2 = 2 ] || [ $2 = 22 ]
then
  suffixe='nd'
elif [ $2 = 3 ] || [ $2 = 23 ]
then
  suffixe='rd'
else
  suffixe='th'
fi
echo $jour $2$suffixe $mois $4
