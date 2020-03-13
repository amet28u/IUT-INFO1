#!/usr/bin/env bash

PATH_DIRECTORY=/Users/antony/.Trash
if [ $# -gt 0 ]
then
  if [ ! -d $PATH_DIRECTORY ]
  then
    mkdir $PATH_DIRECTORY
  fi
  for i in $*
  do
    if [ -e $i ]
    then
      if [ ! -e $PATH_DIRECTORY/$i ]
      then
        mv $i $PATH_DIRECTORY
      else
        echo "error: file $i already exist in the destination folder"
      fi
    else
      echo "error: file $i do not exist"
    fi
  done
else
  echo "usage: $0 <file> <file> ..."
  exit 1
fi
