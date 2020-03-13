#!/usr/bin/env bash

VAR=`ls –l $i | tr –s " " | cut –d " " –f 3`
if [ $# -gt 2 ]
then
  echo "usage: $0 [-i file]"
  exit 1
fi
if [ $# -eq 2 ]
then
  if [ $1 = "-i" ]
  then
    if [ -e $2 ]
    then
      if [ -f $2 ]
      then
        if [ `logname` = $VAR ]
        then
          for fichier in `ls $2`
          do
            rm fichier
          done
        fi
      fi
    else
      echo "error: file do no exist"
      exit 2
    fi
  else
    echo "usage: $0 [-i file]"
    exit 1
  fi
else
  for fichier in `ls /tmp`
  do
    rm fichier
  done
fi
