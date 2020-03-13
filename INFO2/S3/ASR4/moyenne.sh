#!/usr/bin/env bash

if [ -e notes ]
then
  while read line
  do
    set $line
    let sum=$3+$4+$5+$6+$7
    average=$(echo "scale=2;$sum/5" |bc -l)
    echo "Moyenne des notes de $1 $2 est : $average"
  done < notes
else
  echo "error: file do not exist"
fi
